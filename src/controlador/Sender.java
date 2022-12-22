package controlador;

import javax.mail.*;
import javax.mail.internet.*;

import java.io.*;
import java.util.Properties;

public class Sender {

    public static String HOST_GMAIL = "smtp.gmail.com";
    public static String AUTH_PORT_GMAIL = "587";

    private String host;
    private String authPort;
    private Session session = null;

    public Sender() {
        this.host = HOST_GMAIL;
        this.authPort = AUTH_PORT_GMAIL;
    }

    public void connect(String username, String password) {
        Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", authPort);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public Message createNewMail() throws MessagingException {
        Message message = new MimeMessage(session);
        message.setContent(new MimeMultipart());
        return message;
    }

    public void setFrom(Message message, String address) throws AddressException, MessagingException {
        message.setFrom(new InternetAddress(address));
    }

    public void setRecipients(Message message, String addresses) throws AddressException, MessagingException {
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
    }
    
    public void setSubject(Message message, String subject) throws MessagingException {
        message.setSubject(subject);
    }

    public void addBody (Message message, String body) throws MessagingException, IOException {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/html; charset=utf-8");
        ((MimeMultipart) message.getContent()).addBodyPart(mimeBodyPart);
    }

    public void addAttachment (Message message, File file) throws IOException, MessagingException{
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(file);
        ((MimeMultipart) message.getContent()).addBodyPart(attachmentBodyPart);
    }

    public void sendMail(Message message) throws MessagingException {
        Transport.send(message);
    }
}
