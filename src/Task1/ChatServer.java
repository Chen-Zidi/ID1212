package Task1;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


@SuppressWarnings("InfiniteLoopStatement")
public class ChatServer {
    public static void main(String[] args) throws Exception {
        final int PORT = 8089;
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket;

//        //all the clients address
//        ArrayList<InetAddress> clientsAddress = new ArrayList<>();
        //store all the server threads
        ArrayList<ServerThread> serverThreads = new ArrayList<>();

        System.out.println("Server starts.");

        //listen to the clients who want connection
        while (true) {
            try {
                socket = serverSocket.accept();

                //generate a new server thread for the client
                ServerThread thread = new ServerThread(socket);
                serverThreads.add(thread);

                //update all the server threads with a new client list
//                for (ServerThread t : serverThreads) {
//                    t.updateClients(clientsAddress);
//                }
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
