package gamer.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gamer.model.dto.GamerInfoDto;
import gamer.model.dto.GamerJoinDto;
import gamer.model.dto.GamerLoginDto;
import jdbc.common.JdbcTemplate;

public class GamerDao {
	//로그인 성공정보
	public GamerInfoDto loginGetInfo(Connection conn, GamerLoginDto dto) {
		GamerInfoDto result =null;
		String sql = "SELECT GAMER_ID,GAMER_EMAIL FROM GAMER WHERE GAMER_ID=? AND GAMER_PWD=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getGamerId());
			pstmt.setString(2, dto.getGamerPwd());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new GamerInfoDto(rs.getString("GAMER_ID"), rs.getString("GAMER_EMAIL"));
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
		JdbcTemplate.close(rs);
		JdbcTemplate.close(pstmt);		
		return result;
	}
	//로그인
	public int login(Connection conn, GamerLoginDto dto) {
		int result = 0;
		String sql ="SELECT COUNT(*) c FROM GAMER WHERE GAMER_ID=? AND MEM_PWD=?";
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getGamerId());
			pstmt.setString(2, dto.getGamerPwd());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
		JdbcTemplate.close(rs);
		JdbcTemplate.close(pstmt);
		return result;
	}
	//이메일 중복 확인
	public int selectCheckEmail(Connection conn, String gamerEmail) {
		int result =0;
		String sql = "SELECT COUNT(*) c FROM GAMER WHERE GAMER_EMAIL=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gamerEmail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcTemplate.close(rs);
		JdbcTemplate.close(pstmt);
		return result;
	}
	//회원가입
	public int insert(Connection conn, GamerJoinDto dto) {
		int result = 0 ;
		String sql = "INSERT INTO GAMER (GAMER_ID,GAMER_PWD,GAMER_EMAIL) VALUES (?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getGamerId());
			pstmt.setString(2, dto.getGamerPwd());
			pstmt.setString(3, dto.getGamerEmail());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			 e.printStackTrace();
		}
		JdbcTemplate.close(pstmt);
		return result;
	}
}
