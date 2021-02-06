package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public Client() {
        start();
    }

    private void start() {
        try {

            Socket socket = new Socket("localhost", 8900);
            System.out.println("ClientInfo: " + socket);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        String responseMessage = in.readUTF();
                        System.out.println("From server: " + responseMessage);
                    }
                } catch (EOFException e) {
                    System.out.println("Server is unavailable");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    close();
                }
            }).start();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                if (socket.isClosed()) {
                    System.out.println("Connection was broken!");
                    System.out.println("Bye!");
                    break;
                }
                out.writeUTF(message);
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
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
    }
}

