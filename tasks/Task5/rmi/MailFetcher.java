

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

public class MailFetcher extends UnicastRemoteObject implements IRemoteMailFetcher {
    protected MailFetcher() throws RemoteException {
    }


    public String fetchFirstMail(String username, String password) throws IOException, MessagingException {
        String emailContent = "";

        //set properties
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", "webmail.kth.se");
        props.setProperty("mail.imap.port", "993");
//        props.setProperty("mail.debug", "true");
        props.setProperty("mail.imap.ssl.enable","true");


        //set session
        Session session = Session.getDefaultInstance(props, null);
//        session.setDebug(true);

        //set store
        Store store = session.getStore("imap");
        store.connect("webmail.kth.se", 993, username, password);


        IMAPFolder inbox = (IMAPFolder)store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        //handle messages(emails)
        Message[] messages = inbox.getMessages();
        System.out.println("[Remote object] Total mail number: " + messages.length );

        //get the most recent mail
        Message firstMail = messages[messages.length-1];

        //print the mail message
        System.out.println("[Remote object] From: " + firstMail.getFrom()[0].toString());
        emailContent = emailContent + "From: " + firstMail.getFrom()[0].toString() + "\n";

        System.out.println("[Remote object] Received time: " + firstMail.getReceivedDate());
        emailContent = emailContent + "Received time: " + firstMail.getFrom()[0].toString() + "\n";

        System.out.println("[Remote object] Subject: " + firstMail.getSubject());
        emailContent = emailContent + "Subject: " + firstMail.getSubject() + "\n";

        Address[] recList = firstMail.getAllRecipients();
        for(Address r: recList){
            System.out.println("[Remote object] To: " + r);
            emailContent = emailContent + "To: " + r + "\n";
        }

        System.out.println("[Remote object] Status: " + firstMail.getFlags());
        emailContent = emailContent + "Status: " + firstMail.getFlags() + "\n";

        System.out.println("[Remote object] ContentType: " + firstMail.getContentType());
        emailContent = emailContent + "ContentType: " + firstMail.getContentType() + "\n";

        System.out.println("[Remote object] Content: " + firstMail.getContent().toString());
        emailContent = emailContent + "Content: " + firstMail.getContent().toString() + "\n";

        //return the mail content string
        return emailContent;

    }



}