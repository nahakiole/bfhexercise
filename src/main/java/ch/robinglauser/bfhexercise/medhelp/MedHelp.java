package ch.robinglauser.bfhexercise.medhelp;

import javax.swing.*;

public class MedHelp {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MedicineCalculatorGUI medicineCalculatorGUI = new MedicineCalculatorGUI();
            }
        });
    }
}
