package connection;

public class result {
	
	private String player1, player2;
	private int score1, score2;
	
	public result(String player1, String player2, int score1, int score2){
		this.player1 = player1;
		this.player2 = player2;
		this.score1 = score1;
		this.score2 = score2;
	}

	public String getPlayer1() {
		return player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public int getScore1() {
		return score1;
	}

	public int getScore2() {
		return score2;
	}
	
	
}
