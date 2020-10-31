package Task1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws Exception {

        int port = 8089;
        Socket socket = new Socket("localhost", port);
        //Start the output thread
        ClientThread clientThread = new ClientThread(socket);
        clientThread.start();
        //Get the buffer reader for the socket
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        String text;
        //Print the input messages
        while ((text = bufferedReader.readLine()) != null) {
            System.out.println(text);
        }
    }
}
