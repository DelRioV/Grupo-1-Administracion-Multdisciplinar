package vista;

import modelo.Modelo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 *         -José Ramón Gallego
 * @version 1.0
 * @date 23/12/2022
 * That class contains information about inbox Window
 */


public class InboxWindow extends JFrame implements Windows {

    //Model
    private Modelo model = new Modelo();
    //Arraylist of buttons
    private ArrayList<JButton> buttons = new ArrayList<>();
    //ArrayList of panels
    private ArrayList<JPanel> panels = new ArrayList<>();
    //ArrayList of labels
    private ArrayList<JLabel> labels = new ArrayList<>();
    //JTable
    private JTable table = new JTable();
    //DefaultTableModel
    private DefaultTableModel tableModel = new DefaultTableModel();

    /**
     * Constructor
     * @param windowsName -String the windows titlle
     */
    public InboxWindow(String windowsName) {
        super(windowsName);
    }

    /**
     * Method that creates JButtons
     * @param numButtons -int the number of buttons
     */
    @Override
    public void createButtons(int numButtons) {
        JButton btn = null;
        for (int i = 0; i < numButtons; i++)
            btn = new JButton(model.getINBOXWINDOWJBUTTONTXT()[i]);
            btn.setBackground(model.bgColorInboxButton);
            /*btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(model.bgColorInboxButtonBorder, 3),
                    BorderFactory.createLineBorder(model.bgColorInboxButton, 5)));*/
            buttons.add(btn);
    }

    /**
     * Method that creates panels
     * @param numPanels -int the number of panels
     */
    @Override
    public void createPanels(int numPanels) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout(100,100));
            panel.setBackground(model.bgColorInboxPanel);
            panels.add(panel);
    }
    //Not implemented
    @Override
    public void createTextFields(int numTextFields) {
    }

    /**
     * Method that create labels
     * @param numLabels -int number of labels
     */
    @Override
    public void createLabels(int numLabels) {
        for (int i = 0; i < numLabels; i++){
            JLabel label = new JLabel(model.getINBOXLBLTEXT()[i]);
            label.setFont(new Font("Consolas", Font.PLAIN, 16));
            label.setForeground(Color.white);
            label.setHorizontalAlignment(JLabel.CENTER);
            labels.add(label);
        }

    }
    //Not implemented
    @Override
    public void createJComboBox(int numComboBoxes) {
    }
    //Not implemented
    @Override
    public void createTextArea(int numTextArea) {
    }

    /**
     * Method that set the different properties of the window
     */
    @Override
    public void setDifferentProperties() {
        this.setIconImage(new ImageIcon("src/modelo/resources/ftp.png").getImage());
        this.setSize(model.windowsInboxWidth, model.windowsInboxHeight);
        this.setLayout(new BorderLayout());
        this.add(panels.get(0), BorderLayout.CENTER);
        this.setLocationRelativeTo(new JFrame());
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Method that creates a JTable
     */
    public void createTable() {
        table.setFont(model.fontInboxJTable);
        tableModel.setColumnIdentifiers(model.getINBOXWINDOWTABLEHEADERS());
        table.setModel(tableModel);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(model.windowsInboxWidth /3);
        columnModel.getColumn(0).setMinWidth(model.windowsInboxWidth /3);
        columnModel.getColumn(1).setMaxWidth(model.windowsInboxWidth - (model.windowsInboxWidth /3));
        columnModel.getColumn(1).setMinWidth(model.windowsInboxWidth - (model.windowsInboxWidth /3));

        table.setBackground(model.bgColorInboxJTable);

        table.setColumnModel(columnModel);
        table.setShowVerticalLines(false);
        table.setDefaultEditor(Object.class, null);


    }
    //Getters and Setters
    public ArrayList<JButton> getButtons(){
        return buttons;
    }
    public ArrayList<JPanel> getPanels() {
        return panels;
    }
    public JTable getTable() {
        return table;
    }
    public ArrayList<JLabel> getLabels() {
        return labels;
    }
    public void setTableContent(Object[][] tableContent) {
        tableModel = new DefaultTableModel(tableContent, model.getINBOXWINDOWTABLEHEADERS());
        table.setModel(tableModel);
    }
    public void addRow(Object[] row) {
        tableModel.addRow(row);
        table.setModel(tableModel);
    }
    public void removeRow(int i) {
        tableModel.removeRow(i);
        table.setModel(tableModel);
    }
    public Object searchValue(int row, int column) {
        return tableModel.getValueAt(row, column);
    }



}


