package Task1;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientThread extends Thread {

    Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String message;
            //output the string to the server
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter writer;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                writer = new BufferedWriter(outputStreamWriter);
                //ask for user input
                System.out.print("Message: ");
                if (scanner.hasNextLine()) {
                    message = scanner.nextLine();
                } else
                    continue;
                //Exit Command
                if (message.equals("/exit"))
                    break;

                writer.write(message);
                writer.flush();
                outputStream.flush();
            }
            scanner.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
