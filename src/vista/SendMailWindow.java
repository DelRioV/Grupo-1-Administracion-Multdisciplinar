package vista;


import modelo.Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panels.add(panel);
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
        }
    }

    @Override
    public void createJComboBox(int numComboBoxes) {
        //Not implemented in this window
    }

    @Override
    public void createTextArea(int numTextArea) {
        for (int i = 0; i < numTextArea; i++) {
            textAreas.add(new JTextArea(10,20));
        }
    }

    @Override
    public void setDifferentProperties() {
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.add(panels.get(0));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
