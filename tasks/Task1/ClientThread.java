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
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Just type the message and enter to send.");
            while (true) {
                //ask for user input
                if (scanner.hasNextLine()) {
                    message = scanner.nextLine() + "\n";
                    writer.write(message);
                    writer.flush();
                    outputStream.flush();
                    //Exit Command
                    if (message.equals("/exit\n")) {
                        System.out.println("Bye bye!");
                        scanner.close();
                        writer.close();
                        socket.close();
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}