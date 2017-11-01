package ch.robinglauser.bfhexercise.game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener {

    public static void main(String[] args) {
        Game game = new Game();
    }

    Game(){
        this.setSize(640,480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Screen screen = new Screen();
        InputHandler inputHandler = new InputHandler();
        Player player = new Player();
        Stage stage = new Stage();
        GameThread gameThread = new GameThread(screen);
        inputHandler.addKeyListener(player);
        addKeyListener(inputHandler);
        screen.addDrawable(player);
        screen.addDrawable(stage);
        gameThread.addElement(player);
        gameThread.addElement(stage);
        this.add(screen);
        gameThread.start();
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
