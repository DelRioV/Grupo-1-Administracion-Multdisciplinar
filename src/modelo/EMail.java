package modelo;
import java.util.ArrayList;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * That class contains information about emails
 */
public class EMail {
    //Message message
    private Message message;
    //Plain Text
    private String plainText = "";
    //Html Text
    private String htmlText = "";
    //Attachments
    private ArrayList<BodyPart> attachments = new ArrayList<>();

    /**
     * Constructor
     * @param message
     */
    public EMail(Message message) {
        this.message = message;
    }
    //Getter and Setter
    public Message getMessage() {
        return message;
    }

    public void addPlainText(String text) {
        plainText += text + "\n";
    }

    public String getPlainText() {
        return plainText;
    }

    public void addHTMLText(String text) {
        htmlText += text + "\n";
    }

    public String getHTMLText() {
        return htmlText;
    }

    public void addAttachment(BodyPart bodyPart) {
        attachments.add(bodyPart);
    }

    public ArrayList<BodyPart> getAttachments() {
        return attachments;
    }

    public BodyPart getAttachment(int index) {
        return attachments.get(index);
    }

    public boolean hasAttachments() {
        return (attachments.size()>0);
    }

    public int countAttachments() {
        return attachments.size();
    }

    /**
     * Method that do a list of Attachments
     * @return
     * @throws MessagingException
     */
    public String[] listAttachments() throws MessagingException {
        String[] attachmentList = new String[attachments.size()];
        for (int i = 0; i < attachments.size(); i++) {
           // attachmentList[i] = attachments.get(i).getDataHandler().getName();
        }
        return attachmentList;
    }
}