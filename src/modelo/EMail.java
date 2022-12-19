package modelo;

import java.util.ArrayList;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;

public class EMail {
    private Message message;
    private String plainText = "";
    private String htmlText = "";
    private ArrayList<BodyPart> attachments = new ArrayList<>(); 

    public EMail(Message message) {
        this.message = message;
    }

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

    public String[] listAttachments() throws MessagingException {
        String[] attachmentList = new String[attachments.size()];
        for (int i = 0; i < attachments.size(); i++) {
            attachmentList[i] = attachments.get(i).getDataHandler().getName();
        }
        return attachmentList;
    }
}