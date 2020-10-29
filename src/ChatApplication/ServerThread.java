package ChatApplication;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerThread extends Thread{
    Socket socket = null;
    InetAddress inetAddress=null;
    ArrayList<InetAddress> clients = null;

//constructor
    public ServerThread(Socket socket, InetAddress inetAddress){
        this.socket = socket;
        this.inetAddress = inetAddress;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;//translate the message in stream into string
        BufferedReader bufferedReader = null;//for input stream
//        OutputStream outputStream = null;
//        OutputStreamWriter writer = null;
        String message = "";

        try {
            //get the message from the client
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String text = "";
            while ((text = bufferedReader.readLine()) != null) {
                message = message + text ;

            }
            System.out.println("Client: " + message);
            socket.shutdownInput();


//            outputStream = socket.getOutputStream();
//            writer = new OutputStreamWriter(outputStream, "UTF-8");
//            writer.write("from server:"+message);
//            writer.flush();//clear the buffer data

            //broadcast the received message to all the clients using datagram(UDP)
            //maybe I can use TCP by informing other server threads, but I am not sure how to do it
           for(InetAddress addr:clients){
               byte[] msg = message.getBytes();
               DatagramSocket dgs = new DatagramSocket();
               DatagramPacket sendPack = new DatagramPacket(msg, msg.length, addr, 8888);
               dgs.send(sendPack);
               //System.out.println("server broad cast message");
               dgs.close();
           }




//            writer.close();
//            outputStream.close();
            bufferedReader.close();
            inputStream.close();
            inputStreamReader.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //update all the client list
    public void updateClients(ArrayList<InetAddress> clients){
        this.clients = clients;
    }


}
