package ch.robinglauser.bfhexercise.tictactoe;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient extends Thread {


    private final ClientGUI cg;
    PrintStream ps;
    BufferedReader buff;
    private boolean ready = false;

    public TCPClient(ClientGUI clientGUI) {
        this.cg = clientGUI;
    }

    public void run() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8888);
            OutputStream raus = socket.getOutputStream();
            ps = new PrintStream(raus, true);
            InputStream rein = socket.getInputStream();
            buff = new BufferedReader(new InputStreamReader(rein));
            ready = true;
            cg.setStatus("Waiting");
            boolean running = true;
            while (running) {
                String line = buff.readLine();
                String command = line.split(" ")[0];
                switch (command){
                    case "START":
                        cg.isX = true;
                        cg.setStatus("Start");
                        break;
                    case "WRONG":
                        cg.setStatus("Wrong Move");
                        break;
                    case "MOV":
                        cg.setStatus("Your Move");
                        try {
                            int[] coordinates = Field.parseSet(line);
                            cg.setField(coordinates[0], coordinates[1], !cg.isX ? 'X' : 'O');
                        } catch (WrongMoveException e) {
                            System.out.println("Bad Server");
                            e.printStackTrace();
                        }
                        break;
                    case "WIN":
                    case "LOSE":
                    case "DRAW":
                        cg.setStatus(command);
                        running = false;
                        break;
                }

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.exit(1);
            e.printStackTrace();
        } finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void sendName(String name) {
        ps.println("NAME " + name);
    }

    public void sendMove(int x, int y) {
        ps.println("SET " + x + " " + y);
        cg.setStatus("Waiting");
    }

    public boolean isReady() {
        return ready;
    }
}


