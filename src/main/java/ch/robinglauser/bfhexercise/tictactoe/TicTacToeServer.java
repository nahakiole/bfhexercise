package ch.robinglauser.bfhexercise.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TicTacToeServer {

    public static void main(String[] args) {
        int port = 8888;
        TicTacToe ticTacToe = new TicTacToe();
        try (ServerSocket ss = new ServerSocket(port)) {
            System.out.println("Started");
            while (true) {
                Socket socket = ss.accept();
                Player player = new Player(socket);
                ticTacToe.addPlayer(player);
                new ClientHandler(socket, player).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}