package Task1;

import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws Exception{

        int port = 8089;
        Socket socket = new Socket("localhost", port);

        ClientThread clientThread = new ClientThread(socket);
        clientThread.start();
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
//        DatagramPacket receivedMsg = new DatagramPacket(new byte[1024], 1024);
//        DatagramSocket ds = new DatagramSocket(8888);
//        while (true) {
//            try {
//                ds.receive(receivedMsg);
//
//                byte[] receivedBytes = Arrays.copyOfRange(receivedMsg.getData(), 0,
//                        receivedMsg.getLength());
//
//                System.out.println("Server:" + new String(receivedBytes));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
