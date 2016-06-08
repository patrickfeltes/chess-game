package chess.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import chess.gamestates.GameStateManager;
import chess.images.Images;

public class Chess extends JPanel implements Runnable, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	
	// dimensions
    public static int WIDTH = 600;
    public static int HEIGHT = 600;

    // game loop fields
    private Thread thread;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;
    private boolean isRunning = false;

    // mouse position
    public static int mouseX, mouseY;
    
    // graphics
    private Graphics2D g2d;
    
    // GameStateManager
    private GameStateManager gsm;

    /**
     * Chess constructor, starts the game when created and creates JPanel
     */
    public Chess() {
        // load images
        System.out.println("Images loading");
        Images images = new Images();
        System.out.println("Images loaded");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        start();
    }

    /**
     * Method to start the game thread
     */
    private synchronized void start() {
    	gsm = new GameStateManager();
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
        gsm.update();
    }

    /**
     * Method to paint graphics to the JPanel
     * @param g Graphics object used for drawing
     */
    public void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gsm.draw(g2d);

        g2d.dispose();
    }
	
	public void mouseDragged(MouseEvent e) {	
		setMousePosition(e);
	}
	
	public void mouseMoved(MouseEvent e) {	
		setMousePosition(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		gsm.mouseClicked(e);
	}
	
	private void setMousePosition(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	// unused mouse methods
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
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
