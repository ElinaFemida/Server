package server;


import java.io.*;
import java.net.Socket;

import db.UsersDB;


public class ClientHandler {
    private String name;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private Chat chat;

    public ClientHandler(Socket socket, Chat chat) {
        this.socket = socket;
        this.chat = chat;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }

        listen();
    }

    public String getName() {
        return name;
    }

    private void listen() {
        new Thread(() -> {
            doAuth();
            receiveMessage();
        }).start();
    }

    private void doAuth() {
        sendMessage("Please enter credentials: [-auth login password]");
        try {

            while (true) {
                String mayBeCredentials = in.readUTF();
                if (mayBeCredentials.startsWith("-auth")) {
                    String[] credentials = mayBeCredentials.split("\\s");
                    String mayBeNickname = chat.getAuthenticationService().
                            findNicknameByLoginAndPassword(credentials[1], credentials[2]);
                    if (mayBeNickname != null) {
                        if (!chat.isNicknameOccupied(mayBeNickname)) {
                            sendMessage("[INFO] Auth OK");
                            name = mayBeNickname;
                            chat.broadcastMessage(String.format("[%s] logged in", name));
                            chat.subscribe(this);
                            ChatHistory.getInstance(new File(setFileName(name)));
                            System.out.println("!");
                            return;
                        } else {
                            sendMessage("[INFO] Current user is already logged in.");
                        }
                    } else {
                        sendMessage("[INFO] Wrong login or password.");
                        sendMessage("Repeat credentials, if you want to take this nickname");
                        UsersDB.createUser(credentials[1], credentials[2]);
                        name = credentials[1];
                        ChatHistory.getInstance(new File(setFileName(name)));
                        System.out.println("!");
                    }
                }
                }
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }
    }

    private static String setFileName(String nick) {
        return "D://Java//GB//HW//ServerChat//history_" + nick + ".txt";
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    public void receiveMessage() {
        while (true) {
            try {
                String message = in.readUTF();
                if (message.startsWith("-exit")) {
                    chat.broadcastMessage(String.format("[%s] logged out", name));
                    chat.unsubscribe(this);
                    ChatHistory.writeToFile(name +": " +  message);
                    break;
                }
                chat.broadcastMessage(String.format("[%s]: %s", name, message));
                ChatHistory.writeToFile(name +": " + message);
            } catch (IOException e) {
                throw new RuntimeException("SWW", e);
            }
        }
    }
}
