package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtils {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement psmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch (Exception e) {
				System.out.println("오류 발생 : "+ e);
			}
		}
		close(conn, psmt);
	}
	
	public static void close(Connection conn, PreparedStatement psmt) {
		if(psmt != null) {
			try {
				psmt.close();
			}catch (Exception e) {
				System.out.println("오류 발생" + e);
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			}catch (Exception e) {
				System.out.println("오류 발생" + e);
			}
		}
	}
	

}
