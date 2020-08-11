package lesson06;


import javax.swing.*;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8080;
    private static final String END_MESSAGE = "/q";

    private Socket socket;
    static DataInputStream inputStream;
    static DataOutputStream outputStream;
    Scanner scanner = new Scanner(System.in);

    public MyClient(){
        try {
            System.out.println("Open connection. Thread: " + Thread.currentThread().getName());
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка соединения с сервером. " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void openConnection() throws IOException{
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        Thread inputMessageThread = new RecMessage();
        inputMessageThread.start();

        try{
            while (true){
                String inputTXT = scanner.nextLine();
                if(inputTXT.equals(END_MESSAGE)) {
                    outputStream.writeUTF(inputTXT);
                    close(inputStream,outputStream,socket,scanner);
                    break;
                }
                outputStream.writeUTF(inputTXT);
            }
            System.out.println("Соединение разорвано");
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка соединения с сервером. " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }
    static class RecMessage extends Thread{

        @Override
        public void run() {
            try {
                while (true) {
                    String string = inputStream.readUTF();
                    if (string.equals(END_MESSAGE))
                        break;
                    System.out.println("> " + string);
                }
                System.out.println("Соединение разорвано");
                System.exit(0);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void close(Closeable... objects) {
        for (Closeable o : objects) {
            try {
                if (o != null) {
                    o.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new MyClient();

    }
}
