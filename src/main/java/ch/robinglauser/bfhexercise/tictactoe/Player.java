package ch.robinglauser.bfhexercise.tictactoe;

import java.net.Socket;

public class Player {

    private String name;
    private Field game;
    private Socket socket;

    public Player(Socket socket) {
        this.socket = socket;
    }

    public boolean isPlaying(){
        return !(game == null);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Field getGame() {
        return game;
    }

    public void setGame(Field game) {
        this.game = game;
    }

    public Socket getSocket() {
        return socket;
    }
}