package ch.robinglauser.bfhexercise.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread {

    BufferedReader reader;
    PrintWriter writer;

    public void run(){
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost().getHostName(), 8888);
            reader = new BufferedReader
                    (new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            String message = "";
            while (!message.equals("quit")) {
                System.out.println(reader.readLine());
                Scanner scanner = new Scanner(System.in);
                message = scanner.nextLine();
                writer.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
