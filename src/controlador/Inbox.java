package controlador;
import java.io.*;
import java.util.*;
import javax.mail.*;
import modelo.EMail;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * That class contains methods to have an inbox
 */
public class Inbox implements AutoCloseable {

  //Host_Gmail
  public static String HOST_GMAIL = "imap.gmail.com";
  //Host
  private final String host;
  //User
  private final String user;
  //Password
  private final String password;
  //Store
  private Store store = null;

  /**
   * Constructor
   *
   * @param host -String
   * @param user -String
   * @param password -String
   */
  public Inbox(String host, String user, String password) {
    this.host = host;
    this.user = user;
    this.password = password;
  }

  /**
   * Method that connect
   * @throws MessagingException
   */
  public void connect() throws MessagingException {
    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");
    Session session = Session.getDefaultInstance(props, null);
    store = session.getStore("imaps");
    store.connect(host, user, password);
  }

  /**
   * Method that contains
   *
   * @return -ArrayList<Folder>
   * @throws MessagingException
   */
  public ArrayList<Folder> listAllFolders() throws MessagingException {
    ArrayList<Folder> validFolders = new ArrayList<>();
    Folder[] folders = store.getDefaultFolder().list("*");
    for (Folder folder : folders){
      if ((folder.getType() & Folder.HOLDS_MESSAGES) != 0) {
        validFolders.add(folder);
      }
    }
    return validFolders;
  }

  /**
   * get the messages from the folder
   * @param folder -Folder
   * @return -Message[]
   * @throws MessagingException
   */
  public Message[] getMessagesInFolder(Folder folder) throws MessagingException {
    folder.open(Folder.READ_WRITE);
    return folder.getMessages();
  }

  /**
   *
   * @param message -Message
   * @throws MessagingException
   */
  public void deleteMessage(Message message) throws MessagingException {
    message.setFlag(Flags.Flag.DELETED, true);
  }

  /**
   *
   * @param message -Message
   * @throws MessagingException
   */
  public void setMessageAsRead(Message message) throws MessagingException {
    message.setFlag(Flags.Flag.SEEN,true);
  }

  /**
   *
   * Method that read messages
   *
   * @param message -Message
   * @return -Email an email
   * @throws IOException
   * @throws MessagingException
   */
  public EMail readMessage(Message message) throws IOException, MessagingException {
    EMail mail = new EMail(message);
    Object content = message.getContent();
    if (!(content instanceof Multipart)) {
      mail.addPlainText(content.toString());
      return mail;
    }
    Multipart multipart = (Multipart) content;
    saveContentsToMail(multipart, mail);
    return mail;
  }

  /**
   *
   * @param multipart
   * @param mail
   * @throws MessagingException
   * @throws IOException
   */
  private void saveContentsToMail(Multipart multipart, EMail mail) throws MessagingException, IOException {
    for (int i = 0; i < multipart.getCount(); i++) {

      BodyPart bodyPart = multipart.getBodyPart(i);
      String disposition = bodyPart.getDisposition();

      if ((disposition != null) && (disposition.equalsIgnoreCase(BodyPart.ATTACHMENT))) { 
        if (bodyPart.getContent() instanceof Multipart) {
          saveContentsToMail((Multipart)bodyPart.getContent(), mail);
          continue;
        }
        mail.addAttachment(bodyPart);
        continue;
      }

      if(bodyPart.isMimeType("text/plain")){
        mail.addPlainText(bodyPart.getContent().toString());
        continue;
      }

      if(bodyPart.isMimeType("text/html")){
        mail.addHTMLText(bodyPart.getContent().toString());
        continue;
      }
    }
  }
  /**
   *
   * Method that download Attachment
   *
   * @param attachment  -BodyPart
   * @param dir -String
   * @param filename  -String
   * @throws MessagingException
   * @throws IOException
   */
  public void downloadAttachment(BodyPart attachment, String dir, String filename) throws MessagingException, IOException {
    String disposition = attachment.getDisposition();
    if (disposition == null || (!disposition.equalsIgnoreCase(BodyPart.ATTACHMENT))) { 
      throw new MessagingException("Provided object is not an attachment.");
    }

    File saveDir = new File(dir);
    if (!saveDir.isDirectory()) throw new IllegalArgumentException("Not a valid directory.");
    if (!saveDir.exists()) saveDir.mkdir();

    File saveFile = new File(saveDir, filename);
    if (!saveFile.exists()) saveFile.createNewFile();

    try (
      OutputStream out = new FileOutputStream(saveFile);
      InputStream in = attachment.getInputStream();
    ) {
      int k;
      while ((k = in.read()) != -1) {
        out.write(k);
      }
    }
  }

  /**
   *Method that closes the inbox
   *
   * @throws Exception
   */
  @Override
  public void close() throws Exception {
    if (store != null && store.isConnected()) { store.close(); }
  }
}