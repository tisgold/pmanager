package co.ssha.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	//필드
	Connection conn = null;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	//생성자
	
	//메소드
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@192.168.0.36:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "jsp";
		String pass = "jsp";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("오라클 드라이버 없음");
			e.printStackTrace();
		}
		return conn;
	}
}
