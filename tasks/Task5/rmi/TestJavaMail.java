import com.sun.mail.imap.IMAPFolder;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

public class TestJavaMail {
    public static void main(String[] args) throws MessagingException, IOException {

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", "webmail.kth.se");
        props.setProperty("mail.imap.port", "993");
//        props.setProperty("mail.debug", "true");
        props.setProperty("mail.imap.ssl.enable","true");

        Session session = Session.getDefaultInstance(props, null);
//        session.setDebug(true);

        Store store = session.getStore("imap");
        store.connect("webmail.kth.se", 993, "zidi", "Page1256");

        IMAPFolder inbox = (IMAPFolder)store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        Message[] messages = inbox.getMessages();
        System.out.println("all mail number: " + messages.length );

        Message firstMail = messages[messages.length-1];

        System.out.println("From: " + firstMail.getFrom()[0].toString());
        System.out.println("Received time: " + firstMail.getReceivedDate());
        System.out.println("Subject: " + firstMail.getSubject());
        Address[] recList = firstMail.getAllRecipients();
        for(Address r: recList){
            System.out.println("To: " + r);
        }

        System.out.println("Status: " + firstMail.getFlags());
        System.out.println("ContentType: " + firstMail.getContentType());
        System.out.println("Content: " + firstMail.getContent().toString());

    }

}
