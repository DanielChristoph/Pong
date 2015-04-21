package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class FensterFrame extends JFrame implements KeyListener{

	private static final long serialVersionUID = 8065773938646050109L;
	
	private View view = null;
	
	// Titel
	private String titel = "Pong - XBOX";
	
	// Höhe und Breite
	private int HEIGHT 	= 600;
	private int WIDTH 	= 800;
	private int POSITION = 100;

	// Hintergrundfarbe
	private Color bgcolor = Color.BLACK;
	
	/**
	 * Es folgen die programmbezogenen Komponenten
	 */
	private BalkenPanel 	spieler1 = null;
	private BalkenPanel 	spieler2 = null;
	private ErgebnisPanel 	ergebnis = null;
	private BallPanel 		ball 	 = null;
	
	public Thread 			ballThread = null;
	
	public int BalkenPanelAbstand = 20;
	
	public FensterFrame(View view) {
		
		super();
		this.view = view;
		
		init();
		
		ballThread = new Thread(this.getBall());
		ballThread.start();
		
		Thread sp1 = new Thread(this.spieler1);
		sp1.start();
		
		Thread sp2 = new Thread(this.spieler2);
		sp2.start();
		
	}
	
	private void init(){
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setTitle(this.titel);
		this.setSize(this.WIDTH, this.HEIGHT);
		this.setLocation(this.POSITION, this.POSITION);
		this.getContentPane().setBackground(this.bgcolor);
		this.setLayout(null);
		this.setResizable(false);
		
		hinzufuegenElemente();
		
		addKeyListener(this);		
	}
	
	private void hinzufuegenElemente(){
		
		this.spieler1 = new BalkenPanel(this, KeyEvent.VK_W, KeyEvent.VK_S);
		this.add(this.spieler1);
		this.spieler1.setLocation(BalkenPanelAbstand, (this.HEIGHT / 2) - (this.spieler1.getHeight() / 2));
		
		this.spieler2 = new BalkenPanel(this, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		this.add(this.spieler2);
		this.spieler2.setLocation(this.WIDTH - this.spieler2.getWidth() - BalkenPanelAbstand, (this.HEIGHT / 2) - (this.spieler1.getHeight() / 2));
		
		this.ball = new BallPanel(this);
		this.add(this.ball);
		this.ball.setLocation((this.WIDTH / 2) - (this.ball.getWidth() / 2), (this.HEIGHT / 2) - (this.ball.getHeight() / 2));
		
		this.ergebnis = new ErgebnisPanel();
		this.add(this.ergebnis);
		this.ergebnis.setLocation((this.WIDTH / 2) - (this.ergebnis.getWidth() / 2), 0);
		
	}

	private void neuesSpiel(){
		
		this.ball.init();
		this.ballThread = new Thread(this.getBall());
		this.ballThread.start();
		
	}
	
	public View getView() {
		return this.view;
	}
	
	public BalkenPanel getSpieler1() {
		return spieler1;
	}

	public BalkenPanel getSpieler2() {
		return spieler2;
	}

	public ErgebnisPanel getErgebnis() {
		return ergebnis;
	}

	public BallPanel getBall() {
		return ball;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.spieler1.pressed(e.getKeyCode());
		this.spieler2.pressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.spieler1.released();
		this.spieler2.released();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		if(!ballThread.isAlive() && e.getKeyChar() == ' ')
			this.neuesSpiel();
		
	}
		
	
}
