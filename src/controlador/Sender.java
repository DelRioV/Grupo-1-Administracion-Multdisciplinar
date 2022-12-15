package controlador;



import modelo.CredentialsConstants;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

/**
 * <p>Sender class.</p>
 *
 * @author : Ismael Orellano Bello / Pablo Salvador Del Rio Vergara
 * @version : 1.0
 * Class that sends out the email
 */
public class Sender {


    private Properties mailProp = new Properties();

    private Properties credentialProp = new Properties();



    /**
     * Constructor that loads the credential within CredentialsConstants.java
     */
    public Sender() {
        try {
            // Loads all the properties of file "mail.properties".
            mailProp.load(getClass().getClassLoader().getResourceAsStream("modelo/mail.properties"));
            credentialProp.load(getClass().getClassLoader().getResourceAsStream("modelo/credentials.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that create the email and send it
     *
     * @param from    - String (Emisor)
     * @param to      - String (Remitent)
     * @param subject - String (the subject of the email)
     * @param content - String (the text inside it)
     * @return <ol>
     * <li>boolean true - when is correctly send </li>
     * <li>boolean false - when canÂ´t be send due to an error</li>
     * </ol>
     */
    public void send(String from, String to, String subject, String content) {
        // Get the Session object.// and pass username and password
        Properties prop = createSession();

        Session session = Session.getDefaultInstance(prop);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));   //Se podrían añadir varios de la misma manera
            message.setSubject(subject);
            message.setText(content);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", to, "whvazoisbscigaek");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }

    }

    /**
     * Method that creates the session for send the email
     *
     * @return session - Session
     */
    private Properties createSession() {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", "Xxismaelor03xX@gmail.com");
        props.put("mail.smtp.clave", "whvazoisbscigaek");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587");
        //Session session = Session.getInstance(props);
        return props;
    }


}
