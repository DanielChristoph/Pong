package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Hauptfenster der GUI
 * 
 * @author		C.Teipen
 * @version		22.04.2015
 */
public class MainFrame extends JFrame implements KeyListener{

	private static final long serialVersionUID = 8065773938646050109L;
	
	// View
	private View view = null;
	
	// Titel
	private String titel = "Pong - XBOX";
	
	// Höhe und Breite
	private int HEIGHT 	= 600;
	private int WIDTH 	= 800;
	private int POSITION = 100;
	private int WIDTH_OF_NAME_LABEL = 100;
	private int HEIGHT_OF_NAME_LABEL = 20;
	
	// Hintergrundfarbe
	private Color bgcolor = Color.BLACK;
	
	// Es folgen die programmbezogenen Komponenten
	private BalkenPanel[] 	spieler 	= new BalkenPanel[3];
	private ErgebnisPanel	ergebnis 	= null;
	private BallPanel 		ball 	 	= null;
	
	private JLabel 	labelNameSpieler1 = null;
	private JLabel	labelNameSpieler2 	= null;
	
	// Threads
	public  Thread 			ballThread  	= null;
	public  Thread 			spieler1Thread  = null;
	public  Thread 			spieler2Thread  = null;
	
	// Abstand der Balken vom Rand
	public int BalkenPanelAbstand = 20;
	
	private boolean pausiert = false;
	private boolean pausiertDurchPunkt = false;
	
	private MenueFrame menue = null;
	private PauseFrame pauseFrame = null;
	private NameFrame nameFrame = null;
	
	/**
	 * Konstruktor des FensterFrames mit Übergabe der View
	 * 
	 * @param view
	 */
	public MainFrame(View view) {
		
		super();
		this.view = view;
		
		init();
		
		//initThreads();
		
	}
	
	/**
	 * Initialisierung des Fensters
	 */
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
	
	/**
	 * Initialisierung der Threads
	 * - Ball
	 * - Spieler 1 Balken
	 * - Spieler 2 Balken
	 */
	public void initThreads(){
		
		this.ballThread = new Thread(this.getBall());
		this.ballThread.start();
		
		this.spieler1Thread = new Thread(this.getSpieler(1));
		this.spieler1Thread.start();
		
		this.spieler2Thread = new Thread(this.getSpieler(2));
		this.spieler2Thread.start();
		
	}
	
	/**
	 * Hinzufügen der Komponenten zum Fenster
	 */
	private void hinzufuegenElemente(){
		
		this.spieler[1] = new BalkenPanel(this, KeyEvent.VK_W, KeyEvent.VK_S, 1);
		this.add(this.getSpieler(1));
		
		this.spieler[2] = new BalkenPanel(this, KeyEvent.VK_UP, KeyEvent.VK_DOWN, 2);
		this.add(this.getSpieler(2));
		
		this.ball = new BallPanel(this);
		this.add(this.ball);
		this.ball.setLocation((this.WIDTH / 2) - (this.ball.getWidth() / 2), (this.HEIGHT / 2) - (this.ball.getHeight() / 2));
		
		this.ergebnis = new ErgebnisPanel();
		this.add(this.ergebnis);
		this.ergebnis.setLocation((this.WIDTH / 2) - (this.ergebnis.getWidth() / 2), 0);
	
		this.getMenue().setVisible(true);
		
		this.add(this.getLabelNameSpieler1());
		this.add(this.getLabelNameSpieler2());
		
	}

	/**
	 * Starten eines neuen Spiels
	 */
	public void neuesSpiel(){
		
		this.ball.init();
		
		this.getErgebnis().reset();

		this.ballThread = new Thread(this.getBall());
		this.ballThread.start();
		this.pausiertDurchPunkt = false;
	}
	
	/**
	 * Startet eine neue Spielrunde
	 */
	public void neueSpielrunde(){
		
		this.pausiertDurchPunkt = false;
		this.ball.init();
		
		this.ballThread = new Thread(this.getBall());
		this.ballThread.start();

	}
	
	/**
	 * Gibt die View zurück
	 * 
	 * @return
	 */
	public View getView() {
		return this.view;
	}
	
	/**
	 * Gibt den Spieler zurück
	 * 
	 * @param spieler
	 * @return Spieler
	 */
	public BalkenPanel getSpieler(int spieler) {
		
		if(spieler == 1){
			
			return this.spieler[1];		
			
		}else if(spieler == 2){
			
			return this.spieler[2];
			
		}else{
			
			return null;
			
		}
	}

	/**
	 * Gibt das Ergebnispanel zurück
	 * 
	 * @return
	 */
	public ErgebnisPanel getErgebnis() {
		return ergebnis;
	}

	/**
	 * Gibt das BallPanel zurück
	 * 
	 * @return
	 */
	public BallPanel getBall() {
		return ball;
	}

	/**
	 * Wird aufgerufen wenn eine Tastegedrückt wird
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == 27){
			
			if(!this.pausiert){
				
				if(!this.pausiertDurchPunkt){
					
					this.pauseGame();
					this.getPauseFrame().setVisible(true);;
				}
				
			}else{
				
				this.resumeGame();
				
			}
				
		}
		
		this.getSpieler(1).pressed(e.getKeyCode());
		this.getSpieler(2).pressed(e.getKeyCode());
	}

	/**
	 * Wird aufgerufen wenn eine gedrückte Taste losgelassen wird
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		this.getSpieler(1).released();
		this.getSpieler(2).released();
	}

	/**
	 * Wird aufgerufen wenn eine Taste getippt wurde
	 */
	@Override
	public void keyTyped(KeyEvent e) {	
		if(!ballThread.isAlive() && e.getKeyChar() == ' ' && this.pausiertDurchPunkt)
			this.neueSpielrunde();
		
	}	
	
	public void pauseGame() {
		this.pausiert = true;
	}
	
	public void resumeGame() {
		
		this.pausiert = false;
		
		this.ballThread = new Thread(this.getBall());
		this.ballThread.start();
		
		this.spieler1Thread = new Thread(this.getSpieler(1));
		this.spieler1Thread.start();
		
		this.spieler2Thread = new Thread(this.getSpieler(2));
		this.spieler2Thread.start();
	}

	public boolean isPausiert() {
		return pausiert;
	}

	public void setPausiert(boolean pausiert) {
		this.pausiert = pausiert;
	}

	public boolean isPausiertDurchPunkt() {
		return pausiertDurchPunkt;
	}

	public void setPausiertDurchPunkt(boolean pausiertDurchPunkt) {
		this.pausiertDurchPunkt = pausiertDurchPunkt;
	}

	public MenueFrame getMenue() {
		
		if(menue == null){
			
			menue = new MenueFrame(this);
			menue.setLocation(this.getX() + (this.getWidth() / 2) - (menue.getWidth() / 2), this.getY() + (this.getHeight() / 2) - (menue.getHeight() / 2));
			menue.setAlwaysOnTop(true);
			
		}
		
		return menue;
	}
	
	public PauseFrame getPauseFrame(){
		
		if(pauseFrame == null) {
			pauseFrame = new PauseFrame(this);
			pauseFrame.setLocation(this.getX() + (this.getWidth() / 2) - (pauseFrame.getWidth() / 2), this.getY() + (this.getHeight() /2) - (pauseFrame.getHeight() / 2));
			pauseFrame.setAlwaysOnTop(true);
		}
		
		return pauseFrame;
	}
	
	public NameFrame getNameFrame() {
		if(this.nameFrame == null) {
			this.nameFrame = new NameFrame(this);
			this.nameFrame.setLocation(this.getX() + (this.getWidth() / 2) - (nameFrame.getWidth() / 2), this.getY() + (this.getHeight() /2) - (nameFrame.getHeight() / 2));
			this.nameFrame.setAlwaysOnTop(true);
		}
		
		return this.nameFrame;
	}
	
	public JLabel getLabelNameSpieler1() {
		if(this.labelNameSpieler1 == null) {
			this.labelNameSpieler1 = new JLabel();
			this.labelNameSpieler1.setSize(WIDTH_OF_NAME_LABEL, HEIGHT_OF_NAME_LABEL);
			this.labelNameSpieler1.setLocation(1, 0);
			this.labelNameSpieler1.setForeground(Color.WHITE);
		}
		
		return this.labelNameSpieler1;
	}
	
	public JLabel getLabelNameSpieler2() {
		if(this.labelNameSpieler2 == null) {
			this.labelNameSpieler2 = new JLabel();
			this.labelNameSpieler2.setSize(WIDTH_OF_NAME_LABEL, HEIGHT_OF_NAME_LABEL);
			this.labelNameSpieler2.setLocation(this.getWidth() - WIDTH_OF_NAME_LABEL - 8, 0);
			this.labelNameSpieler2.setForeground(Color.WHITE);
			this.labelNameSpieler2.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		
		return this.labelNameSpieler2;
	}

	public void setMenue(MenueFrame menue) {
		this.menue = menue;
	}
}
