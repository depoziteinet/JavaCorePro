package lesson06;

import javax.swing.*;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {
    private static final int SERVER_PORT = 8080;
    private static final String END_MESSAGE = "/q";
    private static Socket socket;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)){
            System.out.println("Сервер запущен...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключен");

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

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка соединения с сервером. " + e.getMessage(),
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }





        } catch (IOException e) {
            System.out.println("Соединение разорвано");
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
                System.out.println("Соединение разорвано");
            }
        }
    }

    static void close(Closeable... objects) {
        for (Closeable o : objects) {
            try {
                if (o != null) {
                    o.close();
                }
            } catch (IOException e) {
                System.out.println("Соединение разорвано");
            }
        }
    }


}
