package gamer.model.service;

import java.sql.Connection;
import static jdbc.common.JdbcTemplate.close;
import static jdbc.common.JdbcTemplate.getConnetion;
import gamer.model.dao.GamerDao;
import gamer.model.dto.GamerInfoDto;
import gamer.model.dto.GamerJoinDto;
import gamer.model.dto.GamerLoginDto;

public class GamerService {
	private GamerDao dao = new GamerDao();
	
	//로그인 성공정보
	public GamerInfoDto loginGetInfo(GamerLoginDto dto) {
		GamerInfoDto result = null;
		Connection conn = getConnetion(true);
		result = dao.loginGetInfo(conn, dto);
		close(conn);
		return result;
	}
	
	//로그인
	public int login(GamerLoginDto dto) {
		int result = 0;
		Connection conn = getConnetion(true);
		result = dao.login(conn, dto);
		close(conn);
		return result;
	}
	//이메일 중복 확인
	public int selectCheckId(String gamerEmail) {
		int result = 0;
		Connection conn = getConnetion(true);
		result = dao.selectCheckEmail(conn, gamerEmail);
		close(conn);
		return result;
	}
	
	
	//회원가입
	public int insert(GamerJoinDto dto) {
		int result = 0;
		Connection conn = getConnetion(true);
		result = dao.insert(conn, dto);
		close(conn);
		return result;
	}
}
