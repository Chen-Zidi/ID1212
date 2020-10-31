package Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("ALL")
public class ServerThread extends Thread {
    Socket socket;
//    ArrayList<InetAddress> clients = null;

    //constructor
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream;
        InputStreamReader inputStreamReader;//translate the message in stream into string
        BufferedReader bufferedReader;//for input stream
        //OutputStream outputStream = null;
        //OutputStreamWriter writer = null;
        try {
            inputStream = socket.getInputStream();
            //get the message from the client
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                if (text.toString().equals("/exit")) {
                    bufferedReader.close();
                    inputStream.close();
                    inputStreamReader.close();
                    socket.close();
                }
                System.out.println("Client" + socket.getInetAddress() + ": " + text);
            }
            //If client command exit, then stop the thread


            //outputStream = socket.getOutputStream();
            //writer = new OutputStreamWriter(outputStream, "UTF-8");
            //writer.write("from server:"+message);
            //writer.flush();//clear the buffer data

            //broadcast the received message to all the clients using datagram(UDP)
            //maybe I can use TCP by informing other server threads, but I am not sure how to do it
//            for (InetAddress addr : clients) {
//                byte[] msg = message.toString().getBytes();
//                DatagramSocket dgs = new DatagramSocket();
//                DatagramPacket sendPack = new DatagramPacket(msg, msg.length, addr, 8888);
//                dgs.send(sendPack);
//                //System.out.println("server broad cast message");
//                dgs.close();
//            }

            //writer.close();
            //outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //update all the client list
//    public void updateClients(ArrayList<InetAddress> clients) {
//        this.clients = clients;
//    }

}
