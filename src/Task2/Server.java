package Task2;


import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {

    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(8089);

        //need to be in threads in later
        while(true){
            //get connected
            System.out.println("Waiting for client...");
            Socket socket = ss.accept();
            System.out.println("Client connected");
            BufferedReader request = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str = request.readLine();
            System.out.println(str);

            OutputStream outputStream = socket.getOutputStream();
//          InputStream inputStream = socket.getInputStream();

            //print the received http request
            StringTokenizer tokens = new StringTokenizer(str," ?");
            tokens.nextToken(); // The word GET
            String requestedDocument = tokens.nextToken();
            while( (str = request.readLine()) != null && str.length() > 0){
                System.out.println(str);
            }

            //read the html file
            FileReader fileReader = new FileReader("./src/Task2/GuessGamePage.html");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String htmlContent = "";
            while(bufferedReader.ready()){
                htmlContent += bufferedReader.readLine();
            }
            System.out.println(htmlContent);
            fileReader.close();

            //write the response to the client
            //Set-Cookie is not implemented yet
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Content-Length: " + htmlContent.getBytes().length + "\r\n" +
                    "Content-Type: text/html; charset-utf-8\r\n" +
                    "\r\n" + htmlContent + "\r\n";

            //handle html page by game logic
            Guess game = new Guess();

            //how to get user enter action in the input field using java???
//            Document doc = (Document) Jsoup.parse(htmlContent);



            outputStream.write(response.getBytes());
            outputStream.flush();
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();


        }


    }


}
