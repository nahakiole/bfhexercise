package ch.robinglauser.bfhexercise.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {

    private JButton[] buttons = new JButton[9];
    private TCPClient tcpClient = new TCPClient(this);
    private JLabel status = new JLabel();
    private JLabel otherplayer = new JLabel();
    public boolean isX = false;
    public boolean waiting = true;

    public ClientGUI() throws HeadlessException {
        GridLayout experimentLayout = new GridLayout(4, 3);
        this.setLayout(experimentLayout);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initGame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setVisible(true);
        status.setText("Connecting");
        tcpClient.setIp(getValue("Enter Server IP", "Enter Server IP", "127.0.0.1"));
        tcpClient.start();
        while (!tcpClient.isReady()) {
        }
        tcpClient.sendName(getValue("Enter name", "Enter Name", System.getProperty("user.name")));
    }

    public String getValue(String message, String title, String defaultValue) {
        String s = (String) JOptionPane.showInputDialog(
                this,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE);

        if ((s != null) && (s.length() > 0)) {
            return s;
        }

        return defaultValue;
    }

    public void setField(int x, int y, char cell) {
        int button = (x * 3 + y);
        buttons[button].setText(String.valueOf(cell));
    }


    public void setStatus(String statustxt) {
        status.setText(statustxt);
    }

    public void setPlayer(String playername) {
        otherplayer.setText("You vs " + playername);
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
                    JButton source = (JButton) e.getSource();
                    if (source.getText().equals(" ") && !waiting) {
                        source.setText(isX ? "X" : "O");
                        tcpClient.sendMove((finalI) / 3, (finalI) % 3);
                    }
                }
            });
            add(aButton);
            i++;
        }
        add(status);
        add(otherplayer);
        pack();
        setSize(300, 300);
    }

}
