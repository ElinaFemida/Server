package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Server {
    private DataInputStream in;
    private DataOutputStream out;
    private boolean running;


    public Server() {
        start();
    }

    private void start() {

        running = true;
        try {
            ServerSocket server = new ServerSocket(8900);
            System.out.println("ServerInfo: " + server);

            while (running) {
                Socket client = server.accept();
                System.out.println("New client connection: " + new Date());
                System.out.println("ClientInfo: " + client);
                in = new DataInputStream(client.getInputStream());
                out = new DataOutputStream(client.getOutputStream());
                new Thread(() -> {
                    try {
                        handle(client);
                    } catch (IOException ioException) {
                        System.out.println("Client connection was broken");
                    } finally {
                        close();
                    }
                }).start();
                Scanner sc = new Scanner (System.in);
                while (true) {
                    String message = sc.nextLine();
                    if (client.isClosed()) {
                        System.out.println("Connection was broken!");
                        break;
                    }
                    out.writeUTF(message);
                    out.flush();
                }
            }
        } catch (IOException e) {
            close();
        }
    }

    private void handle(Socket client) throws IOException {
        while (true) {
            String message = in.readUTF();
            System.out.println("From client: " + message);
            if (message.equalsIgnoreCase("exit")) {
                out.writeUTF("Bye!");
                out.flush();
                break;
            } else if (message.equalsIgnoreCase("time")) {
                out.writeUTF(String.valueOf(new Date()));
                out.flush();
            } else if (message.equalsIgnoreCase("address")) {
                out.writeUTF(String.valueOf(client.getInetAddress()));
                out.flush();
            } else {
                out.writeUTF(message);
                out.flush();
            }
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
