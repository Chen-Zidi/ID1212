import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.Remote;
import java.rmi.RemoteException;
//remote interface
public interface IRemoteMailFetcher extends Remote {

    public String fetchFirstMail(String name, String password) throws IOException, MessagingException;
}