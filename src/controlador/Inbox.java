package controlador;

import java.io.*;
import java.util.*;
import javax.mail.*;

import modelo.EMail;

public class Inbox implements AutoCloseable {

  public static String HOST_GMAIL = "imap.gmail.com";

  private final String host;
  private final String user;
  private final String password;

  private Store store = null;

  public Inbox(String host, String user, String password) {
    this.host = host;
    this.user = user;
    this.password = password;
  }

  public void connect() throws MessagingException {
    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");

    Session session = Session.getDefaultInstance(props, null);
    store = session.getStore("imaps");
    store.connect(host, user, password);
  }

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

  public Message[] getMessagesInFolder(Folder folder) throws MessagingException {
    folder.open(Folder.READ_WRITE);
    return folder.getMessages();
  }

  public void deleteMessage(Message message) throws MessagingException {
    message.setFlag(Flags.Flag.DELETED, true);
  }

  public void setMessageAsRead(Message message) throws MessagingException {
    message.setFlag(Flags.Flag.SEEN,true);
  }

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

  @Override
  public void close() throws Exception {
    if (store != null && store.isConnected()) { store.close(); }
  }
}