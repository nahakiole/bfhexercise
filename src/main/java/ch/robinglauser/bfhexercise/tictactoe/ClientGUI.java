package ch.robinglauser.bfhexercise.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private boolean isX = false;


    public ClientGUI() throws HeadlessException {
        GridLayout experimentLayout = new GridLayout(3, 3);
        this.setLayout(experimentLayout);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setVisible(true);
        initGame();
        ClientHandler clientHandler = new ClientHandler();
    }

    public void initGame() {
        for (JButton[] buttonList : buttons) {
            for (JButton aButton : buttonList) {
                aButton = new JButton();
                aButton.setText(" ");
                aButton.addActionListener(this);
                add(aButton);
            }
        }
        pack();
        setSize(200, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isX = !isX;
        JButton source = (JButton) e.getSource();
        source.setText(isX ? "X" : "O");
    }
}
