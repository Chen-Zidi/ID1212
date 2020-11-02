package Task1;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;

public class ServerThread extends Thread {
    Socket socket;

    //constructor
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream;
        InputStreamReader inputStreamReader;//translate the message in stream into string
        BufferedReader bufferedReader;//for input stream
        try {
            inputStream = socket.getInputStream();
            //get the message from the client
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                if (text.equals("/exit")) {
                    bufferedReader.close();
                    inputStream.close();
                    inputStreamReader.close();
                    socket.close();
                    break;
                }
                //Format the message with the address and the timestamp
                text = "Client" + socket.getInetAddress() +
                        "(" + DateFormat.getTimeInstance().format(System.currentTimeMillis()) + "): " +
                        text + "\n";
                System.out.print(text);
                broadcast(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String text) throws IOException {
        BufferedWriter writer;
        OutputStream stream;
        for (ServerThread t : ChatServer.getServerThreads()) {
            //Do not send the message to self.
            if (t.equals(this))
                continue;
            stream = t.socket.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(
                    stream, StandardCharsets.UTF_8));
            writer.write(text);
            writer.flush();
            stream.flush();
        }
    }
}