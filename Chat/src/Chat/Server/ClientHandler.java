package Chat.Server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler implements Runnable {

    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean running;
    protected String name;
    private static int clientIndex = 0;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        running = true;
        clientIndex ++;
        name = "USER" + clientIndex;
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            System.out.println("CLIENT STARTS PROCESSING...");
            while (running) {
                String message = in.readUTF();
                if (message.equals("exit")) {
                    out.writeUTF(message);
                    server.broadCastMessage(String.format("[%s] LOGGED OUT", name));}

                else if (message.startsWith("TO:")) {
                    String[] words = message.split(":");
                    String nick = words[1];
                    String msg = words[2];
                    server.sendTo(this, nick, msg);
                }
                else {
                    server.broadCastMessage((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                            + " " + name + ": " + message);
                }
                System.out.println("FROM CLIENT  "+ name + ": " + message);

            }
        } catch (Exception e) {
            System.err.println("HANDLED CONNECTION WAS BROKEN...");
            server.removeClient(this);
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }
}
