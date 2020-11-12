package Task2;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//this class is used to simulate the browser using httpurlconnection
public class SimulateClient {
    public static void main(String[] args) throws Exception{

        URL url = new URL("http://localhost:8081");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        System.out.println("url connection is open.\n");


        connection.setRequestMethod("GET");
        connection.setDoOutput(true);//this connection can be output

        connection.connect();
        String responseMsg = connection.getResponseMessage();
        if(responseMsg.trim().equals("OK")){
            InputStream inputStream = connection.getInputStream();
            //to read the response html
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
            reader.close();

        }
        HttpURLConnection connection2 = (HttpURLConnection)url.openConnection();
        byte [] entity=("number=2").getBytes();
        connection2.setRequestMethod("POST");
        connection2.setDoOutput(true);
        connection2.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection2.setRequestProperty("Content-Length",String.valueOf(entity.length));
        connection2.connect();
        OutputStream outStream=connection2.getOutputStream();
        outStream.write(entity);
        System.out.println("connection2");
//       connection.connect();
//        String responseMsg1 = connection.getResponseMessage();
//        if(responseMsg1.trim().equals("OK")){
//            InputStream inputStream = connection.getInputStream();
//            //to read the response html
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            while ((line = reader.readLine()) != null){
//                System.out.println(line);
//            }
//            reader.close();
//
//        }

//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//        connection.setRequestProperty("Connection", "Keep-Alive");
//        connection.setUseCaches(false);
//        connection.setDoOutput(true);
//        connection.setDoInput(true);



//        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//        out.write(params.getBytes("UTF-8"));
//        out.flush();
//        out.close();
//
//
//        OutputStream outputStream = connection.getOutputStream();
//        outputStream.write();
//        outputStream.close();


    }

}
