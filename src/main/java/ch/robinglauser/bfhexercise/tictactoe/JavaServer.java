package ch.robinglauser.bfhexercise.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class JavaServer {

    private static class ClientHandler extends Thread {

        private Socket socket;
        private Player player;
        private Field field = new Field();


        ClientHandler(Socket socket, Player player) {
            this.player = player;
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                writer.println("HELLO");
                String name = reader.readLine().split(" ")[1];
                this.player.setName(name);
                System.out.println(player.getName()+" has connected");


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                writer.println("START");
                Random rand = new Random();
                Boolean finished = false;
                String line;
                while (!finished) {
                    if (field.fieldFull()) {
                        writer.println("DRAW");
                        finished = true;
                        continue;
                    }
                    boolean hasPlayed = false;
                    while (!hasPlayed) {
                        try {
                            line = reader.readLine();
                            int[] points = field.parseSet(line);
                            field.setField(Field.X, points[0], points[1]);
                            hasPlayed = true;
                        } catch (Field.WrongMoveException e) {
                            writer.println("WRONG");
                        }
                    }
                    if (field.hasWon(Field.X)) {
                        writer.println("WIN");
                        finished = true;
                        continue;
                    }
                    boolean isSet = false;
                    while (!isSet) {
                        int x = rand.nextInt(3);
                        int y = rand.nextInt(3);
                        try {
                            field.setField(Field.O, x, y);
                            writer.println("TURN " + x + " " + y);
                            isSet = true;
                        } catch (Field.WrongMoveException e) {
                        }
                    }
                    if (field.hasWon(Field.O)) {
                        writer.println("LOSE");
                        finished = true;
                        continue;
                    }

                    field.printField();
                    System.out.println("");
                }
                socket.close();
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