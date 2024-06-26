package community.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import community.model.dto.CommunityInsertDto;
import community.model.dto.CommunityListDto;
import community.model.dto.CommunityReadDto;
import community.model.dto.CommunityReplyListDto;
import community.model.dto.FileReadDto;
import community.model.dto.FileWriterDto;
import community.model.dto.CommunityReplyWriteDto;

import static jdbc.common.JdbcTemplate.close;
public class CommunityDao {
	// select selectFileList
		public List<FileReadDto> selectFileList(Connection conn, Integer fileId) {
			List<FileReadDto> result = null;
			String sql = "SELECT FILE_ID, COMMUNITY_ID, FILE_PATH, FILE_ORIGINAL FROM FILES WHERE COMMUNITY_ID=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				// ? 처리
				pstmt.setInt(1, fileId);
				rs = pstmt.executeQuery();
				// ResetSet처리
				if(rs.next()) {
					result = new ArrayList<FileReadDto>();
					do {
						FileReadDto dto = new FileReadDto(	
								rs.getInt("FILE_ID"),rs.getInt("COMMUNITY_ID"),
								rs.getString("FILE_PATH"),rs.getString("FILE_ORIGINAL")
								);
						result.add(dto);
					}while (rs.next());
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(rs);
			close(pstmt);
			return result;
		}
		
		// select list - board reply : board_id 댓글 읽기
		public List<CommunityReplyListDto> selectCommunityReplyList(SqlSession session, Integer communityId) {
			return session.selectList("communityMapper.selectCommunityReplyList", communityId);
		}
		
	// select total Count
		public int selectTotalCount(Connection conn) {
			int result = 0;
			String sql = "select count(*) cnt from COMMUNITY";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				// ? 처리
				rs = pstmt.executeQuery();
				// ResetSet처리
				if(rs.next()) {
					result = rs.getInt("cnt");
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(rs);
			close(pstmt);
			return result;
		}
		
		// select list - all
		public List<CommunityListDto> selectPageList(Connection conn, int start, int end) {
			List<CommunityListDto> result = null;
			String sql = "select t2.*"
					+"   from (select t1.*, rownum rn" 
				    +"       from (SELECT COMMUNITY_ID , COMMUNITY_NAME,COMMUNITY_CONTENT,COMMUNITY_WRITE_TIME,GAMER_ID,READ_COUNT FROM COMMUNITY order by COMMUNITY_ID desc) t1 ) t2"
				    +"   where rn between ?   and ?"
				    ;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				// ? 처리
				pstmt.setInt(1, start);//한페이지당글수*(현재페이지-1)+1
				pstmt.setInt(2, end);//한페이지당글수*(현재페이지)
				rs = pstmt.executeQuery();
				// ResetSet처리
				if(rs.next()) {
					result = new ArrayList<CommunityListDto>();
					do {
						CommunityListDto dto = new CommunityListDto(	
								rs.getInt("COMMUNITY_ID"),rs.getString("COMMUNITY_NAME"),
								rs.getString("COMMUNITY_WRITE_TIME"),rs.getString("GAMER_ID"),
								rs.getInt("READ_COUNT")
								);
						result.add(dto);
					}while (rs.next());
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(rs);
			close(pstmt);
			return result;
		}
		// select one
		public CommunityReadDto selectOne(Connection conn, Integer communityId) {
			CommunityReadDto result = null;
			String sql = "SELECT COMMUNITY_ID,COMMUNITY_NAME,COMMUNITY_CONTENT,COMMUNITY_WRITE_TIME,GAMER_ID,READ_COUNT FROM COMMUNITY WHERE COMMUNITY_ID=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				// ? 처리
				pstmt.setInt(1, communityId);
				rs = pstmt.executeQuery();
				// ResetSet처리
				if(rs.next()) {
					result = new CommunityReadDto(	
							rs.getInt("COMMUNITY_ID"),rs.getString("COMMUNITY_NAME"),rs.getString("COMMUNITY_CONTENT"),
							rs.getString("COMMUNITY_WRITE_TIME"),rs.getString("GAMER_ID"),rs.getInt("READ_COUNT")
							);
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(rs);
			close(pstmt);
			return result;
		}
		
		// insert
		public int insert(Connection conn, CommunityInsertDto dto) {
			System.out.println("CommunityDao insert() param : "+dto);
			int result = 0;
//			INSERT INTO BOARD VALUES (SEQ_BOARD_ID.nextval, '제목1', '내용1', default, '127.0.0.1', 'kh1', default);
//			String sql = "INSERT INTO BOARD (BOARD_ID,SUBJECT,CONTENT,WRITE_TIME,LOG_IP,BOARD_WRITER,READ_COUNT)"
//					+ " VALUES (SEQ_BOARD_ID.nextval, ?, ?, default, ?, ?, default)";
			String sql = "INSERT ALL ";
			sql+="	INTO COMMUNITY (COMMUNITY_ID,COMMUNITY_NAME,COMMUNITY_CONTENT,COMMUNITY_WRITE_TIME,GAMER_ID,READ_COUNT) ";
			sql+="		VALUES (SEQ_COMMUNITY_ID.nextval, ?, ?, default, ?, default) ";
			if(dto.getFileList()!= null && dto.getFileList().size()>0) {
				for(FileWriterDto filedto :dto.getFileList()) {
			sql+="	INTO FILES (COMMUNITY_ID, FILE_ID, FILE_PATH, FILE_ORIGINAL) ";
			sql+="		VALUES (SEQ_COMMUNITY_ID.nextval, ?, ?, ?) ";
				}
			}
			sql+="	SELECT * FROM DUAL ";
			System.out.println("sql: "+ sql);
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				// ? 처리
				int i = 1;
				pstmt.setString(i++, dto.getCommunityName());
				System.out.println(dto.getCommunityName());
				pstmt.setString(i++, dto.getCommunityContent());
				System.out.println(dto.getCommunityContent());
				pstmt.setString(i++, dto.getGamerId());
				System.out.println(dto.getGamerId());
				if(dto.getFileList()!= null && dto.getFileList().size()>0) {
					int fileId = 1;
					for(FileWriterDto filedto :dto.getFileList()) {
						pstmt.setInt(i++, fileId++);
						pstmt.setString(i++, filedto.getFilePath());
						pstmt.setString(i++, filedto.getFileOriginal());
					}
				}
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(pstmt);
			System.out.println("commnumityDao insert() return : "+result);
			return result;
		}
		
		// insert - Reply 댓글 대댓글
		public int insertRReply(Connection conn, CommunityReplyWriteDto dto) {
			int result = 0;  // 1 정상, 0비정상
			String sql = " INSERT INTO REPLY VALUES ( (SELECT NVL(MAX(REPLY_ID),0)+1 FROM REPLY),"
					+ "             ?, ?, ? , default , "
					+ "            (SELECT REPLY_LEVEL+1 FROM REPLY WHERE REPLY_ID = ? )  , "
					+ "            (SELECT REPLY_REF     FROM REPLY WHERE REPLY_ID = ? )  , "
					+ "            (SELECT REPLY_STEP+1  FROM REPLY WHERE REPLY_ID = ? )  )";
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				// ? 처리
				pstmt.setInt(1, dto.getCommunityId());
				pstmt.setString(2, dto.getGamerId());
				pstmt.setString(3, dto.getReplyContent());
				pstmt.setInt(4, dto.getReplyId());
				pstmt.setInt(5, dto.getReplyId());
				pstmt.setInt(6, dto.getReplyId());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(pstmt);
			return result;
		}
		
		// insert - Reply 댓글 원본글
		public int insertReply(Connection conn, CommunityReplyWriteDto dto) {
			int result = 0;
			String sql = " INSERT INTO REPLY VALUES"
					+ "        ( (SELECT NVL(MAX(REPLY_ID),0)+1 FROM REPLY) , ?, "
					+ "            ?, ? , default , "
					+ "            DEFAULT , (SELECT NVL(MAX(REPLY_ID),0)+1 FROM REPLY), DEFAULT )";
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				// ? 처리
				pstmt.setInt(1, dto.getCommunityId());
				pstmt.setString(2, dto.getGamerId());
				pstmt.setString(3, dto.getReplyContent());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(pstmt);
			return result;
		}
		
		// update - reply Step
				public int updateReplyStep(Connection conn, Integer replyId) {
					System.out.println("boardDao updateReplyStep() param : "+replyId);
					int result = -1;  // 0~n 정상이므로 비정상인경우-1
					String sql = "UPDATE REPLY SET REPLY_STEP = REPLY_STEP+1  WHERE "
							+ "            REPLY_REF = ( SELECT REPLY_REF FROM REPLY WHERE REPLY_ID = ?)"
							+ "            AND "
							+ "            REPLY_STEP > ( SELECT REPLY_STEP FROM REPLY WHERE REPLY_ID = ? )";
					PreparedStatement pstmt = null;
					try {
						pstmt = conn.prepareStatement(sql);
						// ? 처리
						pstmt.setInt(1, replyId);
						pstmt.setInt(2, replyId);
						result = pstmt.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					close(pstmt);
					System.out.println("boardDao updateReplyStep() return : "+result);
					return result;
				}
		
		// update - readCount
		public int updateReadCount(Connection conn, Integer communityId) {
			int result = 0;
			String sql = "update community set read_count=read_count+1 where community_id=?"; 
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				// ? 처리
				pstmt.setInt(1, communityId);
				result = pstmt.executeUpdate();
				System.out.println("일까");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(pstmt);
			return result;
		}
		
}
