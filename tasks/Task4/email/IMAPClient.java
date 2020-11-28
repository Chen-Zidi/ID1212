import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
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
        int number = 0;
        while (true) {
            if (count == 1) {
                System.out.println("Please input your username");
                String username = scanner.nextLine();
                System.out.println("Please input your password");
                String password = scanner.nextLine();
                out.print("a" + count++ + " login " + username + " " + password + "\r\n");
                out.flush();
                out.print("a" + count++ + " select inbox\r\n");
                out.flush();
                number = getNumber(socket);
            }
            //ask for user input
            if (scanner.hasNextLine()) {
                scanner.nextLine();
                System.out.println("You have " + number + " emails!\nWhich one is you want to read?");
                int n = Integer.parseInt(scanner.nextLine());
                out.print("a" + count++ + " fetch " + n + " body[header]\r\n");
                out.flush();
                out.print("a" + count++ + " fetch " + n + " body[text]\r\n");
                out.flush();
            }
        }
    }

    private static int getNumber(SSLSocket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String message = bufferedReader.readLine();
        while(!message.contains("EXISTS")){
            message = bufferedReader.readLine();
        }
        return Integer.parseInt((message.split(" "))[1]);
    }

}