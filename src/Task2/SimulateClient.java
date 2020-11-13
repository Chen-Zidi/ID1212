package Task2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Random;

//this class is used to simulate the browser using httpurlconnection
public class SimulateClient {


    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8081");

        //array[0] is the number
        //array[1] is the count
        //array[2] is the id
        int[] array = new int[3];

        //count how many times that I have already guessed
        int guessCounter = 0;
        int winCounter = 0;
        //the guess number in the round
        int guessNum = new Random().nextInt(99) + 1;

        //for calculating average guess;
        int sum = 0;

        //for guess number logic
        int higherBound = 100;
        int lowerBound = 0;

        //to receive the incoming html
        String feedback = "";

        getRequest(url, array);

        while (winCounter < 100) {
            sum += guessNum;
            feedback = postRequest(url, guessNum, array);
            if (feedback.contains("lower")) {
                lowerBound = guessNum;
                guessNum = (higherBound + lowerBound) / 2;
                System.out.println("lower, next guess:" + guessNum);
            } else if (feedback.contains("higher")) {
                higherBound = guessNum;
                guessNum = (higherBound + lowerBound) / 2;
                System.out.println("higher, next guess:" + guessNum);
            } else {
                System.out.println("I am right");
//                break;
                //start the next round
                getRequest(url, array);
                guessNum = new Random().nextInt(99) + 1;
                higherBound = 100;
                lowerBound = 0;
                winCounter++;
                System.out.println("I have won the game: " + winCounter + " times");
            }
            guessCounter++;
        }
        System.out.println("the average guess is: " + sum / guessCounter);


    }

    //send get request
    public static void getRequest(URL url, int[] array) throws Exception {
        //System.out.println("Get Request");
        String result = "";
        Map<String, List<String>> header;
        List<String> cookie;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);//this connection can be output

        connection.connect();

        //get header fields
        header = connection.getHeaderFields();
        cookie = header.get("Set-Cookie");
        for (String s : cookie) {
            if (s.contains("number")) {
                int number = Integer.parseInt(s.replaceAll("number=", "").trim());
                array[0] = number;
            } else if (s.contains("count")) {
                int count = Integer.parseInt(s.replaceAll("count=", "").trim());
                array[1] = count;
            } else if (s.contains("id")) {
                int id = Integer.parseInt(s.replaceAll("id=", "").trim());
                array[2] = id;
            }
        }
//        System.out.println("cookie:number"+array[0]+",count"+array[1]+",id"+array[2]);
        String responseMsg = connection.getResponseMessage();

        if (responseMsg.trim().equals("OK")) {
            InputStream inputStream = connection.getInputStream();
            //to read the response html
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {

                result += line;
            }
            reader.close();

        }
        connection.disconnect();

        System.out.println(result);
    }

    //send post request
    public static String postRequest(URL url, int guessNum, int[] array) throws Exception {
        //System.out.println("Post Request");
        String result = "";

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        byte[] data = ("number=" + guessNum).getBytes();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", String.valueOf(data.length));
        connection.setRequestProperty("Set-Cookie", "number=" + array[0] + ";count=" + array[1] + ";id=" + array[2]);
        //write guess number in to stream
        OutputStream outStream = connection.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();


        //read response html
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null) {
            result += line;
        }
        br.close();

        System.out.println(result);
        return result;
    }

}
