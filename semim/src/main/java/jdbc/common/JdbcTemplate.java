package jdbc.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcTemplate {
	private JdbcTemplate() {}
	
	public static Connection getConnetion(boolean isIp) {
		Connection conn =null;
		Properties prop = new Properties();
		
		try {
			String currentPath = JdbcTemplate.class.getResource("./").getPath();
			prop.load(new FileReader(currentPath+"driver.properties"));
			Class.forName(prop.getProperty("jdbc.driver"));
			if (isIp) {
				conn = DriverManager.getConnection(prop.getProperty("jdbc.semi.localhost.url")
						,prop.getProperty("jdbc.semi.username")
						,prop.getProperty("jdbc.semi.password"));
			}else {
//				conn = DriverManager.getConnection(prop.getProperty("jdbc.semi.dbserver.url")	
//						, prop.getProperty("jdbc.semi.username")
//						, prop.getProperty("jdbc.semi.password"));
			}
			if(conn != null) System.out.println("연결 성공");else System.out.println("연결실패");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	public static void autoCommit(Connection con, boolean autocommit) {
		try {
			if(con!=null) {
				con.setAutoCommit(autocommit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void commit(Connection con) {
		try {
			if(con!=null) {
				con.commit();
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
	}
	public static void rollback(Connection con) {
		try {
			if(con!=null) {
				con.rollback();
			}
		}catch (SQLException e) {
			 e.printStackTrace();
		}
	}
	public static void close(Connection con) {
		try {
			if(con != null) {
				con.close();
			}
		} catch (Exception e) {
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
		}
	}
	public static void close(ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (Exception e) {
		}
	}
}
