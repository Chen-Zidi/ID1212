import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


@SuppressWarnings("InfiniteLoopStatement")
public class ChatServer {

    //store all the server threads
    private static final ArrayList<ServerThread> serverThreads = new ArrayList<>();

    public static ArrayList<ServerThread> getServerThreads() {
        return serverThreads;
    }

    public static void main(String[] args) throws Exception {
        final int PORT = 8089;
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = null;
        ServerThread thread = null;
        System.out.println("Server starts.");

        //listen to the clients who want connection
        while (true) {
            try {
                socket = serverSocket.accept();

                //generate a new server thread for the client
                thread = new ServerThread(socket);
                serverThreads.add(thread);

                thread.start();
            } catch (Exception e) {
                serverThreads.remove(thread);
                System.out.println((socket != null ? socket.getInetAddress() : null) + " closed");
            }
        }

    }
}