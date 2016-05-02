package chess.game;

import chess.images.Images;

import javax.swing.*;
import java.awt.*;

public class Chess extends JPanel implements Runnable {

    // dimensions
    public static int WIDTH = 600;
    public static int HEIGHT = 600;

    // game loop fields
    private Thread thread;
    private int FPS = 30;
    private long targetTime = 1000 / FPS;
    private boolean isRunning = false;

    // graphics
    private Graphics2D g2d;

    // Board
    private Board board;

    /**
     * Chess constructor, starts the game when created and creates JPanel
     */
    public Chess() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        board = new Board(0, 0, 560);

        start();
    }

    /**
     * Method to start the game thread
     */
    private synchronized void start() {
        isRunning = true;
        thread = new Thread(this, "Game Thread");
        thread.start();
    }

    /**
     * Method to stop the game thread
     */
    private synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run thread called when game thread is started, the main game loop
     */
    public void run() {
        long start, elapsed, wait;
        while(isRunning) {
            start = System.currentTimeMillis();

            update();
            repaint();

            elapsed = System.currentTimeMillis() - start;
            wait = targetTime - elapsed;
            if(wait < 5) wait = 5;
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();
    }

    /**
     * Method to update all game logic
     */
    public void update() {
        board.update();
    }

    /**
     * Method to paint graphics to the JPanel
     * @param g Graphics object used for drawing
     */
    public void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        // antialiasing to make stuff look good
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        board.draw(g2d);

        g2d.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(new Chess(), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
