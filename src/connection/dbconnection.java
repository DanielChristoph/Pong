package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbconnection {
	
	private Connection conn = null;
	private ResultSet rs = null;
	
	public void openConn(){
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	//TODO DB Pfad ist nicht immer gleich Lösung?!??
	    	conn = DriverManager.getConnection("jdbc:sqlite: PATH");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConn(){
		try {
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getTop(){
	    Statement stat;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery("Select top 10 gamedif.* from gamedif");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void insertGameResult(Result r){
		Statement stat;
		try{
			stat = conn.createStatement();
			stat.executeUpdate("Insert into gameresults (player1, player2, score1, score2) Values('"+r.getPlayer1()+"','"+r.getPlayer2()+
					"',"+r.getScore1()+","+r.getScore2()+")");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

