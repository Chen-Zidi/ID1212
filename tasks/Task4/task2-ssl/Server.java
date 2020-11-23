import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("InfiniteLoopStatement")
public class Server {

    static final int PORT = 8081;

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket;
        int counter = 0;
        //need to be in threads in later
        while (true) {
            //get connected
            System.out.println("Waiting for client...");
            socket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(socket, counter);
            serverThread.start();
            counter++;
        }
    }


}
