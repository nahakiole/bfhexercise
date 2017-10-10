package ch.robinglauser.bfhexercise.tictactoe;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient extends Thread {


    PrintStream ps;
    BufferedReader buff;
    private boolean ready = false;

    public void run() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8888);

            OutputStream raus = socket.getOutputStream();
            PrintStream ps = new PrintStream(raus, true);
            InputStream rein = socket.getInputStream();
            buff = new BufferedReader(new InputStreamReader(rein));
            ready = true;

            while (buff.ready()) {
                System.out.println(buff.readLine());
            }

        } catch (UnknownHostException e) {
            System.out.println("Unknown Host...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOProbleme...");
            e.printStackTrace();
        } finally {
            if (socket != null)
                try {
                    socket.close();
                    System.out.println("Socket geschlossen...");
                } catch (IOException e) {
                    System.out.println("Socket nicht zu schliessen...");
                    e.printStackTrace();
                }
        }
    }

    public void sendName(String name){
        ps.println("NAME "+name);
    }

    public void sendMove(int x, int y){
        ps.println("SET "+x+" "+y);
    }


    public boolean isReady() {
        return ready;
    }
}


