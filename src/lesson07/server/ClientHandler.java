package lesson07.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private MyServer myServer;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String name;
    private int timeout;

    public String getName() {
        return name;
    }

    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            this.timeout = 120;
            new Thread(() -> {
                try {
                    threadAuthentication authentication = new threadAuthentication();
                    authentication.start();
                    for (int i = 0; i < timeout; i++) {
                        Thread.sleep(1000);
                        if(authentication.getState().toString() == "TERMINATED")
                            break;
                    }
                    if(authentication.getState().toString() != "TERMINATED")
                        closeConnection();
                    else
                        readMessages();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    public class threadAuthentication extends Thread{
        @Override
        public void run() {
            try {
                authentication();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void authentication() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s");
                String nick = myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMsg("/authok " + nick);
                        name = nick;
                        myServer.subscribe(this);
                        return;
                    } else {
                        sendMsg("Учетная запись уже используется");
                    }
                } else {
                    sendMsg("Неверные логин/пароль");
                }
            }
        }
    }

    public void readMessages() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();
            if (strFromClient.equals("/end")) {
                return;
            }
            String[] parts = strFromClient.split("\\s");
            if (parts[0].equals("/w")){
                System.out.println("от " + name + " " + "для " + parts[1] +
                        (!myServer.isNickBusy(parts[1])?" (нет в чате): ":": ") + strFromClient.substring(parts[1].length() + 4));
                myServer.sendMsg(name, strFromClient.substring(parts[1].length() + 4), parts[1]);

            }
            else {
                System.out.println("от " + name + " для всех: " + strFromClient);
                myServer.broadcastMsg(name, strFromClient);
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        myServer.unsubscribe(this);

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}