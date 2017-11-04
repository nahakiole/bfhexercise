package ch.robinglauser.bfhexercise.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener, Drawable, Updateable {

    public boolean paused = false;
    public GameThread gameThread;
    InputHandler inputHandler;

    public static void main(String[] args) {
        Game game = new Game();
    }

    Game() {
        this.setFocusable(true);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Screen screen = new Screen();
         inputHandler = new InputHandler();
        Player player = new Player(KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT, KeyEvent.VK_UP, Color.RED, 10, KeyEvent.VK_CONTROL);
        Player player2 = new Player(KeyEvent.VK_D,KeyEvent.VK_A, KeyEvent.VK_W, Color.BLUE, 400, KeyEvent.VK_Q);

        Player[] players = {player,player2};
       // Enemy enemy = new Enemy(players);
        Stage stage = new Stage();

        gameThread = new GameThread(screen);
        inputHandler.addKeyListener(player);
        inputHandler.addKeyListener(player2);

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventPostProcessor(inputHandler);

        CollisionHandler collisionHandler = new CollisionHandler();
        collisionHandler.addCollidable(player);
        collisionHandler.addCollidable(player2);

        screen.addDrawable(player);
        screen.addDrawable(player2);
        screen.addDrawable(stage);
        //screen.addDrawable(enemy);
        screen.addDrawable(this);
        gameThread.addElement(player);
        gameThread.addElement(collisionHandler);
        gameThread.addElement(player2);
        //gameThread.addElement(enemy);
        gameThread.addElement(stage);
        gameThread.addElement(this);
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

    @Override
    public void update(double time) {

    }
}
