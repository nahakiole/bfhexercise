package ch.robinglauser.bfhexercise.medhelp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class MedHelp extends JFrame {

    JTextField jLabel = new JTextField();
    JComboBox<String> textField;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        MedHelp medHelp = new MedHelp();
    }

    public MedHelp() {
        DocumentListener documentListener = new DocumentListener();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridLayout experimentLayout = new GridLayout(5, 2);
        setLayout(experimentLayout);
        setTitle("Renal Function Calculator");
        String[] labels = {"Age in Years: ", "Weight in KG: ", "Serumkreatinin in mg/dl: "};
        for (String label : labels) {
            JLabel l = new JLabel(label, JLabel.TRAILING);
            add(l);
            JTextField textField = new JTextField(10);
            textField.setText("0");
            l.setLabelFor(textField);
            textField.setHorizontalAlignment(SwingConstants.RIGHT);
            add(textField);
            textField.getDocument().addDocumentListener(documentListener);
        }
        JLabel l = new JLabel("Gender: ", JLabel.TRAILING);
        add(l);
        String[] gender = {"Male", "Female"};
        textField = new JComboBox<>(gender);
        l.setLabelFor(textField);
        add(textField);
        textField.addItemListener(documentListener);

        JLabel jPanel = new JLabel("Renal function");
        jPanel.setHorizontalAlignment(SwingConstants.RIGHT);

        add(jPanel);
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel.setEditable(false);
        add(jLabel);
        setSize(400, 160);
        setResizable(false);

        calculateChange();
        setVisible(true);
    }

    private void calculateChange() {
        JTextField age = (JTextField) this.getContentPane().getComponent(1);
        JTextField weight = (JTextField) this.getContentPane().getComponent(3);
        JTextField serumkreatinin = (JTextField) this.getContentPane().getComponent(5);


        MedicineCalculator.Gender gender = (Objects.equals(textField.getSelectedItem(), "Male")) ? MedicineCalculator.Gender.MAN : MedicineCalculator.Gender.WOMAN;


        int ageasint = Integer.parseInt(Objects.equals(age.getText(), "") ? "0" : age.getText());
        double weightasdouble = Double.parseDouble(Objects.equals(weight.getText(), "") ? "0" : weight.getText());
        double serumkreatininasdouble = Double.parseDouble(Objects.equals(serumkreatinin.getText(), "") ? "0" : serumkreatinin.getText());
        double gfr = MedicineCalculator.getRenalFunction(ageasint, weightasdouble, serumkreatininasdouble, gender);
        jLabel.setText("" + String.valueOf((double) Math.round(gfr * 1000d) / 1000d));

    }


    private class DocumentListener implements javax.swing.event.DocumentListener, ItemListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            calculateChange();

        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            calculateChange();

        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            calculateChange();
        }


        @Override
        public void itemStateChanged(ItemEvent e) {
            calculateChange();
        }
    }
}
