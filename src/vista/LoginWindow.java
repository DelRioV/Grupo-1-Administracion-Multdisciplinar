package vista;

import modelo.Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginWindow extends JFrame implements Windows{

    private Modelo model = new Modelo();
    private ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<JPanel> panels = new ArrayList<>();
    private ArrayList<JTextField> textFields = new ArrayList<>();
    private ArrayList<JLabel> labels = new ArrayList<>();


    public LoginWindow(String name){
        super(name);
    }

    @Override
    public void createButtons(int numButtons) {
        for(int i = 0;i<numButtons;i++){
            buttons.add(new JButton(model.getLoginwindowbuttonsame()));
        }
    }

    @Override
    public void createPanels(int numPanels) {
        for(int i = 0;i<numPanels;i++){
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panels.add(panel);
        }
    }

    @Override
    public void createTextFields(int numTextFields) {
        for(int i = 0;i<numTextFields;i++){
            textFields.add(new JTextField(model.getLengthtextfields()));
        }
    }

    @Override
    public void createLabels(int numLabels) {
        for(int i = 0;i<numLabels;i++){
            labels.add(new JLabel(model.getLoginwindowlabelsnames()[i]));
        }
    }

    @Override
    public void createJComboBox(int numComboBoxes) {
        //Not implemented in this window
    }

    @Override
    public void setDifferentProperties() {
        this.setSize(500,500);
        this.setLayout(new FlowLayout());
        this.add(panels.get(0));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public ArrayList<JLabel> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<JLabel> labels) {
        this.labels = labels;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<JButton> buttons) {
        this.buttons = buttons;
    }

    public ArrayList<JPanel> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<JPanel> panels) {
        this.panels = panels;
    }

    public ArrayList<JTextField> getTextFields() {
        return textFields;
    }

    public void setTextFields(ArrayList<JTextField> textFields) {
        this.textFields = textFields;
    }
}
