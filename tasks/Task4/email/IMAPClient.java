import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.util.Scanner;

public class IMAPClient {
    public static void main(String[] args) throws Exception {

        SSLSocketFactory factory =
                (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocket socket =
                (SSLSocket)factory.createSocket("webmail.kth.se", 993);
        //Start the output thread
        Receiver receiver = new Receiver(socket);
        receiver.start();
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        PrintWriter out = new PrintWriter(writer);
//        out.println("GET / HTTP/1.0");
//        out.println();
//        out.flush();
        Scanner scanner = new Scanner(System.in);
        String message;
        int count = 1;
        while (true) {
            //ask for user input
            if (scanner.hasNextLine()) {
                message = scanner.nextLine();
                out.print("a" + count++ + " " + message + "\r\n");
                out.flush();
                outputStream.flush();
                System.out.println(message);
            }
        }
    }
}