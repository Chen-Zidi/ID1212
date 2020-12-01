import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client{
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please input your username");
            String username = scanner.nextLine();
            System.out.println("Please input your password");
            String password = scanner.nextLine();

            //set rmi registry
            Registry registry = LocateRegistry.getRegistry("localhost");
            //get the remote object in the rmi registry
            IRemoteMailFetcher remoteMailFetcher = (IRemoteMailFetcher) registry.lookup("MailFetcher");


            //call the method of remote object
            String firstMail = remoteMailFetcher.fetchFirstMail(username, password);
            System.out.println("Client receive: " + firstMail);


        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}