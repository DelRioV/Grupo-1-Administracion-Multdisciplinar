package vista;

import modelo.Modelo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;



public class InboxWindow extends JFrame implements Windows {

    private Modelo model = new Modelo();
    private ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<JPanel> panels = new ArrayList<>();

    private ArrayList<JLabel> labels = new ArrayList<>();
    private JTable table = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();

    public InboxWindow(String windowsName) {
        super(windowsName);
    }

    @Override
    public void createButtons(int numButtons) {
        JButton btn = null;
        for (int i = 0; i < numButtons; i++)
            btn = new JButton(model.getINBOXWINDOWJBUTTONTXT()[i]);
            btn.setBackground(model.bgColorInboxButton);
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(model.bgColorInboxButtonBorder, 3),
                    BorderFactory.createLineBorder(model.bgColorInboxButton, 5)));
            buttons.add(btn);
    }
    @Override
    public void createPanels(int numPanels) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout(100,100));
            panel.setBackground(model.bgColorInboxPanel);
            panels.add(panel);
    }
    @Override
    public void createTextFields(int numTextFields) {
    }
    @Override
    public void createLabels(int numLabels) {
        for (int i = 0; i < numLabels; i++){
            JLabel label = new JLabel(model.getINBOXLBLTEXT()[i]);
            label.setFont(new Font("Consolas", Font.PLAIN, 16));
            label.setHorizontalAlignment(JLabel.CENTER);
            labels.add(label);
        }

    }
    @Override
    public void createJComboBox(int numComboBoxes) {
    }
    @Override
    public void createTextArea(int numTextArea) {
    }
    @Override
    public void setDifferentProperties() {
        this.setSize(model.windowsInboxWidth, model.windowsInboxHeight);
        this.setLayout(new BorderLayout());
        this.add(panels.get(0), BorderLayout.CENTER);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void createTable() {
        table.setFont(model.fontInboxJTable);
        tableModel.setColumnIdentifiers(model.getINBOXWINDOWTABLEHEADERS());
        tableModel.addRow(new Object[]{"", ""});
        tableModel.addRow(new Object[]{"", ""});
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


