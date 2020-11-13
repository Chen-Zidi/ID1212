package Task2;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class ServerThread extends Thread {
    Socket socket;
    Guess guess;
    String cookieInfo = "";
    BufferedReader request;
    int count = -1;
    int number = -1;
    int contentLength = -1;
    int guessNumber = -1;
    String method = "";
    String htmlContent = "";
    String requestedDocument = "";
    int id;


    public ServerThread(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    @Override
    public void run() {
        try {

            //receive http request
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            request = new BufferedReader(new InputStreamReader(inputStream));
            String str = request.readLine();

            if (str.contains("favicon.ico")) {
                //Read favicon.ico to byte
                byte[] favicon = Files.readAllBytes(Paths.get("src", "Task2", "favicon.ico"));
                String header = "HTTP/1.1 200 OK\nAccess-Control-Allow-Origin: *\nContent-Type: image/png\nContent-Length: " + favicon.length + "\r\n\r\n";
                outputStream.write(header.getBytes(StandardCharsets.UTF_8));
                outputStream.write(favicon);
            }

            System.out.println("str: " + str);

            //print the received http request
            StringTokenizer tokens = new StringTokenizer(str, " ?");
            method = tokens.nextToken();
            System.out.println("token: " + method); // The word GET/POST

            //identify whether a icon is needed or html is needed
            requestedDocument = tokens.nextToken();
            System.out.println(requestedDocument);

            //read http request
            while (true) {
                str = request.readLine();
                System.out.println("str: " + str);

                //find out the content length when is it a post request
                if (str.startsWith("Content-Length: ")) {
                    contentLength = Integer.parseInt(str.substring("Content-Length: ".length()));
                }
                //to get cookie values
                if (str.contains("Cookie")) {
                    String[] cInfo = str.split(":|;");
                    for (int i = 0; i < cInfo.length; i++) {
                        //get count value
                        if (cInfo[i].contains("count")) {
                            count = Integer.parseInt(cInfo[i].replaceAll("count=", "").trim());
                            //System.out.println("count"+count);
                        }
                        //get number value
                        if (cInfo[i].contains("number")) {
                            number = Integer.parseInt(cInfo[i].replaceAll("number=", "").trim());
                            //System.out.println("number"+number);
                        }

                    }

                }

                if (str.length() == 0) {
                    break;
                }
            }

            //if method is post, then find the submitted value in the http body
            //the while loop always results in print an empty line
            //this empty line is the line between http header and http body
            if (method.equals("POST") && contentLength > 0) {
                System.out.println("POST received");
                char[] content = new char[contentLength];
                request.read(content);
                String temp = new String(content);
                guessNumber = Integer.parseInt(temp.substring("number=".length()));
            }


            //check if the browser already visited the url
            if ((count >= 0) && number >= 0) {
                guess = new Guess(number, count);
            } else {
                guess = new Guess();
            }


            //if it is a get request, give the start page
            if (method.equals("GET")) {
                //read the html file
                FileReader fileReader = new FileReader("./src/Task2/GuessGamePage.html");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.ready()) {
                    htmlContent += bufferedReader.readLine();
                }
                System.out.println(guess.getNumber());
                //inject the number to the html
                htmlContent = String.format(htmlContent, guess.getNumber());

                //System.out.println(htmlContent);
                fileReader.close();
            } else if (method.equals("POST")) {//if it is a post request


                FileReader fileReader;
                String result = guess.compare(guessNumber);
                if (result.equals("equal")) {//if the guess number is right
                    fileReader = new FileReader("./src/Task2/GuessRight.html");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    while (bufferedReader.ready()) {
                        htmlContent += bufferedReader.readLine();
                    }

                    //inject the number to the html
                    htmlContent = String.format(htmlContent, guess.getCounter(), guess.getNumber());
                    fileReader.close();

                    //reset the guess number and counter
                    guess = new Guess();

                } else if (result.equals("higher")) {//if the gues number is higher
                    fileReader = new FileReader("./src/Task2/GuessHigher.html");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    while (bufferedReader.ready()) {
                        htmlContent += bufferedReader.readLine();
                    }

                    //inject the number to the html
                    htmlContent = String.format(htmlContent, guess.getCounter(), guess.getNumber());
                    fileReader.close();

                } else {//if the guess number is lower
                    fileReader = new FileReader("./src/Task2/GuessLower.html");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    while (bufferedReader.ready()) {
                        htmlContent += bufferedReader.readLine();
                    }

                    //inject the number to the html
                    htmlContent = String.format(htmlContent, guess.getCounter(), guess.getNumber());
                    fileReader.close();
                }


            }


            //write the response to the client with cookie number and count
//            String  page="HTTP/1.1 200 OK\r\n"+
//                        "Content-Length: "+htmlContent.getBytes().length+"\r\n"+
//                        "Content-Type: text/html; charset-utf-8\r\n"+
//                        "Set-Cookie: number="+guess.getNumber()+"\r\n; count="+guess.getCounter()+"\r\n; id="+id+"\r\n"+
//                        //"<link rel=\"shortcut icon\" href=\"./favicon.ico\">"+"\r\n"+
//                        //"Link: <http://foo.com/favicon.ico>; rel=\"shortcut icon\""+"\r\n"+
//                        "\r\n"+htmlContent+"\r\n";

            String page = "HTTP/1.1 200 OK\r\n" +
                    "Content-Length: " + htmlContent.getBytes().length + "\r\n" +
                    "Content-Type: text/html; charset-utf-8\r\n" +
                    "Set-Cookie: number=" + guess.getNumber() + "\r\n" +
                    "Set-Cookie: count=" + guess.getCounter() + "\r\n" +
                    "Set-Cookie: id=" + id + "\r\n" +
                    //"<link rel=\"shortcut icon\" href=\"./favicon.ico\">"+"\r\n"+
                    //"Link: <http://foo.com/favicon.ico>; rel=\"shortcut icon\""+"\r\n"+
                    "\r\n" + htmlContent + "\r\n";


            //tried to give favicon
            //need to fix
//            if(requestedDocument.equals("/favicon.ico")){
//                page += "\r\n";
//                File f = new File("./src/Task2"+requestedDocument);
//                FileInputStream infil = new FileInputStream(f);
//                byte[] b = new byte[1024];
//                while( infil.available() > 0){
//                    //response.write(b,0,infil.read(b));
//                    page += infil.read(b);
//                }
//            }

            //send the response
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