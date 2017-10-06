package ch.robinglauser.bfhexercise.medhelp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class MedicineCalculatorGUI extends JFrame {

    private JTextField jLabel = new JTextField();
    private JComboBox<String> genderComboBox;
    private DocumentListener documentListener;

    public MedicineCalculatorGUI() {
        initializeFrame();
        createForm();
        calculateChange();
        setVisible(true);
    }

    private void initializeFrame() {
        documentListener = new DocumentListener();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridLayout experimentLayout = new GridLayout(5, 2);
        setLayout(experimentLayout);
        setTitle("Renal Function Calculator");
        setSize(400, 160);
        setResizable(false);
    }

    private void createForm(){
        String[] labels = {"Age in Years: ", "Weight in KG: ", "Serumkreatinin in mg/dl: "};
        for (String label : labels) {
            createField(label);
        }
        createGenderComboBox();
        createOutputField();
    }

    private void createOutputField() {
        JLabel jPanel = new JLabel("Renal function");
        jPanel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(jPanel);
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel.setEditable(false);
        add(jLabel);
    }

    private void createField(String label) {
        JLabel jLabel = new JLabel(label, JLabel.TRAILING);
        add(jLabel);
        final JTextField textField = new JTextField(10);
        textField.setText("0");
        jLabel.setLabelFor(textField);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(textField);
        textField.addFocusListener(new FocusListener() {
            @Override public void focusLost(final FocusEvent pE) {}
            @Override public void focusGained(final FocusEvent pE) {
                textField.selectAll();
            }
        });
        textField.getDocument().addDocumentListener(documentListener);
    }

    private void createGenderComboBox() {
        JLabel l = new JLabel("Gender: ", JLabel.TRAILING);
        add(l);
        String[] gender = {"Male", "Female"};
        genderComboBox = new JComboBox<>(gender);
        l.setLabelFor(genderComboBox);
        add(genderComboBox);
        genderComboBox.addItemListener(documentListener);
    }

    private void calculateChange() {
        JTextField age = (JTextField) this.getContentPane().getComponent(1);
        JTextField weight = (JTextField) this.getContentPane().getComponent(3);
        JTextField serumkreatinin = (JTextField) this.getContentPane().getComponent(5);
        MedicineCalculator.Gender gender = getGender(genderComboBox);
        int ageasint = (int) getNumberFromJTextField(age);
        double weightasdouble = getNumberFromJTextField(weight);
        double serumkreatininasdouble = getNumberFromJTextField(serumkreatinin);
        double gfr = MedicineCalculator.getRenalFunction(ageasint, weightasdouble, serumkreatininasdouble, gender);
        jLabel.setText("" + String.valueOf((double) Math.round(gfr * 1000d) / 1000d));

    }

    private double getNumberFromJTextField(JTextField textField) {
        String value = textField.getText().replaceAll(",", ".");
        if (value.equals("")){
            return 0;
        }
        if ((value.length() - value.replace(".", "").length()) > 1){
            return 0;
        }
        return Double.parseDouble(value);
    }

    private MedicineCalculator.Gender getGender(JComboBox<String> jComboBox) {
        return (Objects.equals(jComboBox.getSelectedItem(), "Male")) ? MedicineCalculator.Gender.MAN : MedicineCalculator.Gender.WOMAN;
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
