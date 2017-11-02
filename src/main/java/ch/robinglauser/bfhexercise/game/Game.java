package ch.robinglauser.bfhexercise.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener, Drawable {

    public boolean paused = false;
    public GameThread gameThread;

    public static void main(String[] args) {
        Game game = new Game();
    }

    Game() {
        this.setSize(640, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Screen screen = new Screen();
        InputHandler inputHandler = new InputHandler();
        Player player = new Player(KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT, KeyEvent.VK_UP, Color.RED);
        Player player2 = new Player(KeyEvent.VK_D,KeyEvent.VK_A, KeyEvent.VK_W, Color.BLUE);
        Stage stage = new Stage();
        gameThread = new GameThread(screen);
        inputHandler.addKeyListener(player);
        inputHandler.addKeyListener(player2);
        addKeyListener(inputHandler);
        screen.addDrawable(player);
        screen.addDrawable(player2);
        screen.addDrawable(stage);
        screen.addDrawable(this);
        gameThread.addElement(player);
        gameThread.addElement(player2);
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
            paused = !paused;
            gameThread.setPaused(paused);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void draw(Graphics paramGraphics) {
        if (paused) {
            paramGraphics.drawString("Game paused", paramGraphics.getClipBounds().width / 2, paramGraphics.getClipBounds().height / 2);
        }

    }
}
