import javax.net.ssl.*;
import java.io.*;

import java.security.KeyStore;

public class ServerTest {

    static final int PORT = 443;

    public static void main(String[] args) throws Exception {
        SSLServerSocketFactory factory;
        KeyStore ks;
//        String password = "changeit";
        String password = "111111";
        KeyManagerFactory kmf;
        SSLContext ctx;

        ks = KeyStore.getInstance("JKS");
//        ks.load(new FileInputStream("D:\\jdk-12\\jre\\lib\\security\\cacerts"), password.toCharArray());
        ks.load(new FileInputStream("D:\\jdk-12\\bin\\myserver.jks"), password.toCharArray());

//        SSLContext ctx = SSLContext.getInstance("SSL");
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());


        /**
         * KeyManager[] 第一个参数是授权的密钥管理器，用来授权验证。TrustManager[]第二个是被授权的证书管理器，
         * 用来验证服务器端的证书。第三个参数是一个随机数值，可以填写null。如果只是服务器传输数据给客户端来验证，就传入第一个参数就可以，
         * 客户端构建环境就传入第二个参数。双向认证的话，就同时使用两个管理器。
         */

        kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, password.toCharArray());

        ctx = SSLContext.getInstance("TLSv1.2");
//        ctx = SSLContext.getInstance("TLS");
        ctx.init(kmf.getKeyManagers(), null, null);
        factory = ctx.getServerSocketFactory();


        SSLServerSocket serverSocket = (SSLServerSocket)factory.createServerSocket(PORT);
        SSLSocket socket;

        for(int i = 0; i < factory.getSupportedCipherSuites().length; i++)
            System.out.println(factory.getSupportedCipherSuites()[i]);
        System.out.println("______________________________");
        for(int i = 0; i < serverSocket.getEnabledCipherSuites().length; i++)
            System.out.println(serverSocket.getEnabledCipherSuites()[i]);
        int counter = 0;
        //need to be in threads in later
//        while (true) {
//            //get connected
//            System.out.println("Waiting for client...");
//            socket = (SSLSocket) serverSocket.accept();
//            ServerThread serverThread = new ServerThread(socket, counter);
//            serverThread.start();
//            counter++;
//        }


        System.out.println("Waiting for client...");
        socket = (SSLSocket) serverSocket.accept();

        System.out.println(socket.getRemoteSocketAddress());


        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream socketOut = new DataOutputStream(socket.getOutputStream());

//        String s = socketIn.readLine();
//
//        System.out.println("Client Message: " + s);


            socketOut.write("server received call".getBytes());
            socketOut.flush();






        socket.close();
        serverSocket.close();

    }


}
