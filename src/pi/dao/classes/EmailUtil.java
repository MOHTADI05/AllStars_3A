/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.classes;

/**
 *
 * @author swide
 */
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;




public class EmailUtil {

    public static void sendEmail(String recipient, String subject, String body) throws MessagingException {

    Properties props = new Properties();
    props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
 props.put("mail.smtp.ssl.trust", "sandbox.smtp.mailtrap.io");
       props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "2525");
    
    String smtpHost = "sandbox.smtp.mailtrap.io";
        int smtpPort = 587;
        String username = "2bfccca5fbcc9c";
        String password = "f3977487baef51";


    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
              

            return new PasswordAuthentication(username, password);
            
        }
    });

    Transport transport = session.getTransport("smtp");
    transport.connect(smtpHost, smtpPort, username, password);

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(username));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
    message.setSubject(subject);
    message.setText(body);

    Transport.send(message);
}

}
