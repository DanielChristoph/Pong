package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Klasse dbconnection
 * 
 * @author		C.Laurenz
 * @version		13.05.2015
 */
public class dbconnection {
	
	private Connection conn = null;
	private ResultSet rs = null;
	/**
	 * �ffnet die Datenbankverbindung
	 */
	private void openConn(){
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	//TODO DB Pfad ist nicht immer gleich L�sung?!??
	    	conn = DriverManager.getConnection("jdbc:sqlite:res/pong.sqlite");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Schlie�t die Datenbankverbindung
	 */
	public void closeConn(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Gibt ein ResultSet mit den 120 Besten Spielern zur�ck
	 * 
	 * @return rs
	 */
	public ResultSet getTop(){
		this.openConn();
	    Statement stat;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from gamedif order by Punktedifferenz desc");
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		try {
//			while(rs.next()){
//				System.out.println(rs.getString("Spieler").toString() + rs.getInt("Punktedifferenz"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		this.closeConn();
		return rs;
	}
	
	/**
	 * Bekommet ein Ergebnis und schreibt dieses in die Datenbank
	 * 
	 * @param r
	 */
	public void insertGameResult(Result r){
		this.openConn();
		Statement stat;
		try{
			stat = conn.createStatement();
			stat.executeUpdate("Insert into gameresults (player1, player2, score1, score2) Values('"+r.getPlayer1()+"','"+r.getPlayer2()+
					"',"+r.getScore1()+","+r.getScore2()+")");
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.closeConn();
	}
}

