import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class SMTPClient {
    public static void main(String[] args) throws Exception {
        //Get user info
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your username");
        String username = Base64.getEncoder().encodeToString(scanner.nextLine().getBytes(StandardCharsets.UTF_8));
        System.out.println("Please input your password");
        String password = Base64.getEncoder().encodeToString(scanner.nextLine().getBytes(StandardCharsets.UTF_8));
        Socket socket = new Socket("smtp.kth.se", 587);
        //Start the output thread
        Receiver receiver = new Receiver(socket);
        receiver.start();

        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        out.println("EHLO\r");
        out.flush();
        out.println("STARTTLS\r");
        out.flush();
//        Thread.sleep(5000);
//
        //SSL socket
        SSLSocketFactory factory =
                (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket)factory.createSocket(
                        socket,
                        socket.getInetAddress().getHostAddress(),
                        socket.getPort(),
                        true);
        sslSocket.setUseClientMode(true);
        sslSocket.setEnableSessionCreation(true);
        sslSocket.startHandshake();
//
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())));
        receiver.stop();
        StartsslReceiver sreceiver = new StartsslReceiver(sslSocket);
        sreceiver.start();
        out.println("EHLO\r");
        out.println("AUTH LOGIN\r");
        out.flush();
        out.println(username + "\r");
        out.flush();
        out.println(password + "\r");
        out.flush();


//        out.println("GET / HTTP/1.0");
//        out.println();
//        out.flush();
        String message;
        while (true) {
            //ask for user input
            if (scanner.hasNextLine()) {
                message = scanner.nextLine();
                out.print(message + "\r\n");
                out.flush();
                System.out.println(message);
            }
        }
    }
}
