package connection;

/**
 * Klasse result
 * 
 * @author		C.Laurenz
 * @version		13.05.2015
 */
public class Result {
	
	private String player1, player2;
	private int score1, score2;
	
	/**
	 * Konstruktor des Presenters mit �bergabe der View und des Models
	 * 
	 * @param player1
	 * @param player2
	 * @param score1
	 * @param score2
	 */
	public Result(String player1, String player2, int score1, int score2){
		this.player1 = player1;
		this.player2 = player2;
		this.score1 = score1;
		this.score2 = score2;
	}

	/**
	 * Gibt den Nicknamen des Spielers zur�ck
	 * 
	 * @return player1
	 */
	public String getPlayer1() {
		return player1;
	}

	/**
	 * Gibt den Nicknamen des Spielers zur�ck
	 * 
	 * @return player2
	 */
	public String getPlayer2() {
		return player2;
	}

	/**
	 * Gibt den Punktestand des 1. Spielers zur�ck
	 * 
	 * @return score1
	 */
	public int getScore1() {
		return score1;
	}

	/**
	 * Gibt den Punktestand des 2. Spielers zur�ck
	 * 
	 * @return score2
	 */
	public int getScore2() {
		return score2;
	}
	
	
}
