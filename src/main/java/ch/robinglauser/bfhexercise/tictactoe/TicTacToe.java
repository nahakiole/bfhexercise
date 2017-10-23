package ch.robinglauser.bfhexercise.tictactoe;

import java.util.Iterator;
import java.util.Vector;

public class TicTacToe {

    private Vector<Game> games = new Vector<>();

    public synchronized void addPlayer(Player player) {
        //Remove old games
        for (Iterator<Game> iterator = games.iterator(); iterator.hasNext(); ) {
            Game game = iterator.next();
            if (!game.isRunning()) {
                games.remove(game);
            }
        }
        if (games.size() == 0 || games.lastElement().isFull()) {
            Game game = new Game();
            game.setPlayerOne(player);
            player.setGame(game);
            games.add(game);
        } else {
            Game game = games.lastElement();
            game.setPlayerTwo(player);
            player.setGame(game);
        }
    }
}