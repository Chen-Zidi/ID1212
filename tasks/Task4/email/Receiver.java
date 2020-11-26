import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Receiver extends Thread {

    Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String message;
            //output the string to the server
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("Just type the message and enter to send.");
            while (true) {
                String text = bufferedReader.readLine();
                if (text != null) {
                    System.out.println(text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}