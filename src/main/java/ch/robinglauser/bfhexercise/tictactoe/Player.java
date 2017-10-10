package ch.robinglauser.bfhexercise.tictactoe;

import java.net.Socket;

public class Player {

    private String name = "";
    private Game game;
    private Socket socket;

    private boolean ready = false;

    public Player(Socket socket) {
        this.socket = socket;
    }

    public void setName(String name) {
        this.name = name;
        ready = true;
    }

    public String getName() {
        return name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean isReady() {
        return ready;
    }
}