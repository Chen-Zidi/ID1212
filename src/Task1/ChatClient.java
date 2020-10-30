package Task1;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("InfiniteLoopStatement")
public class ChatClient {
    public static void main(String[] args) throws Exception{

        int port = 8089;
        Socket socket = new Socket("localhost", port);
        String message = "";

        //ask for user input
        System.out.println("Please input your message:");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            message = scanner.nextLine();
        }
        scanner.close();


        //output the string to the server
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        writer.write(message);
        writer.flush();
        socket.shutdownOutput();


        writer.close();
        outputStream.close();

        socket.close();

//        InputStream inputStream = socket.getInputStream();//get data from server
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");//translate stream into string
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String receivedMessage = null;
//        while ((receivedMessage = bufferedReader.readLine()) != null) {
//            System.out.println("client receives:" + receivedMessage);
//        }
//        bufferedReader.close();
//        inputStreamReader.close();
//        inputStream.close();

        //receive the message from the server
        DatagramPacket receivedMsg = new DatagramPacket(new byte[1024], 1024);
        DatagramSocket ds = new DatagramSocket(8888);
        while (true) {
            try {
                ds.receive(receivedMsg);

                byte[] receivedBytes = Arrays.copyOfRange(receivedMsg.getData(), 0,
                        receivedMsg.getLength());

                System.out.println("Server:" + new String(receivedBytes));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }



    }
}
