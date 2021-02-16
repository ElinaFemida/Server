package Chat.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Server {

    private static final int DEFAULT_PORT = 8111;

    private ConcurrentLinkedDeque<ClientHandler> clients;

    public Server(int port) {
        clients = new ConcurrentLinkedDeque<>();
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("SERVER STARTED ON PORT: " + port);
            while (true) {
                Socket socket = server.accept();
                System.out.println("CLIENT ACCEPTED");
                ClientHandler handler = new ClientHandler(socket, this);
                addClient(handler);
                new Thread(handler).start();
            }
        } catch (Exception e) {
            System.err.println("SERVER WAS BROKEN...");
        }
    }
    public void addClient(ClientHandler clientHandler) {
        clients.add(clientHandler);
        System.out.println("CLIENT ADDED TO BROADCAST QUEUE");
    }

    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("CLIENT REMOVED FROM BROADCAST QUEUE");
    }

    public void broadCastMessage(String message) throws IOException {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
    public void sendTo(ClientHandler from, String nickTo, String msg) throws IOException {
        for (ClientHandler client : clients) {
            if (client.name.equals(nickTo)) {
                client.sendMessage("PRIVATE MESSAGE FROM " + from.name + ": " + msg);
                from.sendMessage("PRIVATE MESSAGE TO " + nickTo + ": " + msg);
            }
        }
    }



    public static void main(String[] args) {
//        int port = -1;
//        if (args !=  null && args.length == 1) {
//            port = Integer.parseInt(args[0]);
//        }
//        if (port == -1) {
        int port = DEFAULT_PORT;
//        }
        new Server(port);

    }
}

