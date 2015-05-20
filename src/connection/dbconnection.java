package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbconnection {
	
	Connection conn = null;
	
	public void openconn(){
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection("jdbc:sqlite:pong.sqlite");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void closeconn(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getTop(){
	    Statement stat;
	    ResultSet rs = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery("Select top 10 gamedif.* from gamedif");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void insertGameResult(){
		Statement stat;
		try{
			stat = conn.createStatement();
			stat.executeUpdate("Insert into gameresults Values()");
			//TODO
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

