package ch.robinglauser.bfhexercise.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ClientHandler extends Thread {

    private Socket socket;
    private Player player;

    ClientHandler(Socket socket, Player player) {
        this.player = player;
        this.socket = socket;
    }

    @Override
    public void run() {
        Game game = null;
        try {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("HELLO");
            String name = reader.readLine().split(" ")[1];
            this.player.setName(name);
            System.out.println(player.getName() + " has connected");
            game = player.getGame();
            while (!player.getGame().isReady()) {
                Thread.sleep(10);
            }
            if (player.getGame().getPlayerOne() == player) {
                player.getGame().start();
                writer.println("PLAYER "+player.getGame().getPlayerTwo().getName());
                writer.println("START");
            }
            else {
                writer.println("PLAYER "+player.getGame().getPlayerOne().getName());
            }
            String line;
            while (player.getGame().isRunning()) {
                Thread.sleep(10);
                if (player.getGame().getCurrentPlayer() == player) {
                    Thread.sleep(10);
                    if (player.getGame().getTurns() != 0) {
                        int[] lastturn = player.getGame().getLastTurn();
                        writer.println("MOV " + lastturn[0] + " " + lastturn[1]);
                    }
                    boolean hasPlayed = false;
                    while (!hasPlayed && player.getGame().isRunning()) {
                        try {
                            line = reader.readLine();
                            player.getGame().send(player, line);
                            hasPlayed = true;
                        } catch (WrongMoveException e) {
                            writer.println("WRONG");
                        }
                    }
                    player.getGame().nextPlayer();
                }
            }
            writer.println(player.getGame().getStatus(player));

            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (game != null) {
                    game.stopGame();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}