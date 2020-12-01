import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteServer{
    public static void main(String[] args) {
        try{



            IRemoteMailFetcher mailFetcher = new MailFetcher();
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("MailFetcher", mailFetcher);
            System.out.println("Server ready!");

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}