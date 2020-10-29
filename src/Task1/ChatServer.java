package Task1;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ChatServer {
    public static void main(String[] args) throws Exception{
        int port = 8089;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = null;

        //all the clients address
        ArrayList<InetAddress> clientsList= new ArrayList<InetAddress>();
        //store all the server threads
        ArrayList<ServerThread> serversList = new ArrayList<ServerThread>();

        System.out.println("Server starts.");

        //listen to the clients who want connection
        while (true) {
            socket = serverSocket.accept();
            InetAddress inetAddress=socket.getInetAddress();
            clientsList.add(inetAddress);

            //generate a new server thread for the client
            ServerThread thread=new ServerThread(socket,inetAddress);
            serversList.add(thread);

            //update all the server threads with a new client list
            for(ServerThread t:serversList){
                t.updateClients(clientsList);
            }
            thread.start();


        }

    }
}
