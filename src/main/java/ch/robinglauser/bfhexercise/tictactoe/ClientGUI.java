package ch.robinglauser.bfhexercise.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {

    private JButton[] buttons = new JButton[9];
    private TCPClient tcpClient = new TCPClient(this);
    private JLabel status = new JLabel();
    public boolean isX = false;

    public ClientGUI() throws HeadlessException {
        GridLayout experimentLayout = new GridLayout(4, 3);
        this.setLayout(experimentLayout);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        initGame();
        setVisible(true);
        status.setText("Connecting");
        tcpClient.start();
        while (!tcpClient.isReady()) {

        }
        tcpClient.sendName("Robin");
    }

    public void setField(int x, int y, char cell) {
        int button = (x * 3 + y);
        System.out.println(button);
        System.out.println(buttons.length);
        System.out.println(buttons[button]);
        buttons[button].setText(String.valueOf(cell));
    }


    public void setStatus(String statustxt) {
        status.setText(statustxt);
    }

    public void initGame() {
        int i = 0;
        for (int i1 = 0; i1 < buttons.length; i1++) {
            buttons[i1] = new JButton();
            JButton aButton = buttons[i1];
            aButton.setText(" ");
            final int finalI = i;
            aButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Clicked");
                    JButton source = (JButton) e.getSource();
                    if (source.getText().equals(" ")) {
                        source.setText("X");
                        System.out.println((finalI) / 3);
                        System.out.println((finalI) % 3);
                        tcpClient.sendMove((finalI) / 3, (finalI) % 3);
                    }
                }
            });
            add(aButton);
            i++;
        }
        add(status);
        pack();
        setSize(200, 200);
    }

}
