package vista;
import modelo.Modelo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import static javax.swing.BoxLayout.Y_AXIS;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 *         -José Ramón Gallego
 * @version 1.0
 * @date 23/12/2022
 * That class contains information about SendMailWindow
 */
public class SendMailWindow extends JFrame implements Windows {

    private Modelo model = new Modelo();
    private ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<JPanel> panels = new ArrayList<>();
    private ArrayList<JLabel> labels = new ArrayList<>();
    private ArrayList<JTextField> textFields = new ArrayList<>();
    private ArrayList<JTextArea> textAreas = new ArrayList<>();

    public SendMailWindow(String name) {
        super(name);
    }

    @Override
    public void createButtons(int numButtons) {
        for (int i = 0; i < numButtons; i++) {
            buttons.add(new JButton(model.getSENDMAILWINDOWBTNSTXT()[i]));
        }
    }

    @Override
    public void createPanels(int numPanels) {
        for (int i = 0; i < numPanels; i++) {
            JPanel panel = new JPanel();
            switch (i){
                case 0: //Main Panel
                    panel.setLayout(new BoxLayout(panel, Y_AXIS));
                    panels.add(panel);
                    break;
                case 1: //Buttons panel
                    panel.setLayout(new BorderLayout(20,20));
                    panel.setBorder(new EmptyBorder(10,0,0,0));
                    panels.add(panel);
            }
        }
    }

    @Override
    public void createTextFields(int numTextFields) {
        for (int i = 0; i < numTextFields; i++) {
            textFields.add(new JTextField(model.getLengthtextfields()));
        }
    }

    @Override
    public void createLabels(int numLabels) {
        for (int i = 0; i < numLabels; i++) {
            labels.add(new JLabel(model.getSENDMAILWINDOWLABELTXT()[i]));
            labels.get(i).setForeground(Color.white);
        }
    }

    @Override
    public void createJComboBox(int numComboBoxes) {
        //Not implemented in this window
    }

    @Override
    public void createTextArea(int numTextArea) {
        for (int i = 0; i < numTextArea; i++) {
            JTextArea txtA = new JTextArea(10,50);
            txtA.setLineWrap(true);
            textAreas.add(txtA);
        }
    }

    @Override
    public void setDifferentProperties() {
        this.getContentPane().setBackground(new Color(42,42,42));
        this.panels.get(0).setBackground(new Color(12,15,65));
        this.panels.get(1).setBackground(new Color(12,15,65));
        this.setIconImage(new ImageIcon("src/modelo/resources/ftp.png").getImage());
        this.setSize(model.getSENDMAILWINDOWWIDTH(), model.getSENDMAILWINDOWHEIGTH());
        this.setLayout(new FlowLayout());
        this.add(panels.get(0));
        this.setLocationRelativeTo(new JFrame());
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public ArrayList<JPanel> getPanels() {
        return panels;
    }

    public ArrayList<JLabel> getLabels() {
        return labels;
    }

    public ArrayList<JTextField> getTextFields() {
        return textFields;
    }

    public ArrayList<JTextArea> getTextAreas() {
        return textAreas;
    }
}
