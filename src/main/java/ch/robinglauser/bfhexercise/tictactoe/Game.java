package ch.robinglauser.bfhexercise.tictactoe;

import java.util.Vector;

public class Game extends Thread {

    private Player playerOne;

    private Player playerTwo;

    private Field field;

    private boolean running = true;

    private Player currentPlayer;

    private Vector<int[]> turns = new Vector<>();

    public enum Status {
        WIN,
        LOSE,
        DRAW
    }

    public Game() {
        field = new Field();
    }

    public boolean isFull() {
        return playerOne != null && playerTwo != null;
    }

    public boolean isReady() {
        if (playerOne != null && playerTwo != null) {
            if (playerOne.isReady() && playerTwo.isReady()) {
                return true;
            }
        }
        return false;
    }

    public void setPlayerOne(Player playerOne) {
        currentPlayer = playerOne;
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void run() {
        System.out.println("-------------------------");
        System.out.println(playerOne.getName()+" - vs - "+playerTwo.getName());
        field.printField();
        System.out.println("-------------------------");
        while (running) {

        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isRunning() {
        if (field.fieldFull() || field.hasWon(Field.X) || field.hasWon(Field.O)) {
            currentPlayer = null;
            return false;
        }
        return running;
    }

    public void send(Player player, String msg) throws Field.WrongMoveException {
        if (player == currentPlayer) {
            int[] coordinates = this.field.parseSet(msg);
            this.field.setField((currentPlayer == playerOne ? Field.X : Field.O), coordinates[0], coordinates[1]);
            turns.add(coordinates);
        }
    }

    public int[] getLastTurn() {
        return turns.lastElement();
    }

    public int getTurns() {
        return turns.size();
    }


    public void nextPlayer() {
        System.out.println("-------------------------");
        System.out.println(playerOne.getName()+" - vs - "+playerTwo.getName());
        field.printField();
        System.out.println("-------------------------");
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    public Status getStatus(Player player) {
        if (player == playerOne) {
            if (this.field.hasWon(Field.X)) {
                return Status.WIN;
            }
        }
        if (player == playerTwo) {
            if (this.field.hasWon(Field.O)) {
                return Status.WIN;
            }
        }
        if (field.fieldFull()) {
            return Status.DRAW;
        }
        return Status.LOSE;
    }
}
