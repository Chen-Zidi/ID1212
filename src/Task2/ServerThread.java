package Task2;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerThread extends Thread {
    Socket socket;
    Guess guess;

    BufferedReader request;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            request = new BufferedReader(new InputStreamReader(inputStream));
            String str = request.readLine();
            System.out.println("str: "+str);

            //print the received http request
            StringTokenizer tokens = new StringTokenizer(str," ?");
            System.out.println("token: "+tokens.nextToken()); // The word GET
            String requestedDocument = tokens.nextToken();
            System.out.println(requestedDocument);
            while( (str = request.readLine()) != null && str.length() > 0){
                System.out.println("str: "+str);
            }

            //read the html file
            FileReader fileReader = new FileReader("./src/Task2/GuessGamePage.html");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String htmlContent = "";
            while(bufferedReader.ready()){
                    htmlContent += bufferedReader.readLine();
            }
            guess = new Guess();
            htmlContent = String.format(htmlContent,guess.getNumber());
            System.out.println(guess.getNumber());
            System.out.println(htmlContent);
            fileReader.close();

            //write the response to the client
            String page="HTTP/1.1 200 OK\r\n"+
                    "Content-Length: "+htmlContent.getBytes().length+"\r\n"+
                    "Content-Type: text/html; charset-utf-8\r\n"+
                    "Set-Cookie: number="+guess.getNumber()+"\r\n"+
                    "Set-Cookie: count="+guess.getCounter()+"\r\n"+
                    "\r\n"+htmlContent+"\r\n";

            System.out.println(guess.getNumber());

            outputStream.write(page.getBytes());
            outputStream.flush();
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}