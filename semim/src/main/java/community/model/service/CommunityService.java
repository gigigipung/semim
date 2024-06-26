package community.model.service;

import static jdbc.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import community.model.dao.CommunityDao;
import community.model.dto.CommunityInsertDto;
import community.model.dto.CommunityListDto;
import community.model.dto.CommunityReadDto;
import community.model.dto.CommunityReplyListDto;
import community.model.dto.FileReadDto;
import community.model.dto.CommunityReplyWriteDto;
import static jdbc.common.MybatisTemplate.*;

public class CommunityService {
	private CommunityDao dao =new CommunityDao();
	
	public Map<String, Object> selectPageList(int pageSize, int pageBlockSize, int currentPageNum){
		Map<String, Object> result =null;
		
		Connection conn =getConnetion(true);
		System.out.println("currentPageNum: " +currentPageNum);
		int start = pageSize*(currentPageNum-1)+1;
		int end = pageSize*currentPageNum;
//		select t2.*
//		from (select t1.*, rownum rn from (SELECT BOARD_ID, SUBJECT,CONTENT,WRITE_TIME,LOG_IP,BOARD_WRITER,READ_COUNT    FROM BOARD order by board_id desc) t1 ) t2
//		--where rn between 한페이지당글수*(현재페이지-1)+1   and 한페이지당글수*(현재페이지)
//		;
		
//		--- 총글개수 103
//		select count(*) cnt from board;
		int totalCount = dao.selectTotalCount(conn);
		System.out.println("totalCount:"+totalCount);
//		-- 전체페이지수  = ceil(총글개수/한페이지당글수) = (총글개수%한페이지당글수== 0)?(총글개수/한페이지당글수):(총글개수/한페이지당글수+1) 
//		int totalPageCount = (int)Math.ceil(totalCount/(double)pageSize);
		int totalPageCount = (totalCount % pageSize == 0)? totalCount/pageSize :  totalCount/pageSize+1;
		
//		-- 시작페이지 startPageNum = (현재페이지%페이지수 == 0) ? ((현재페이지/페이지수)-1)*페이지수 + 1  :((현재페이지/페이지수))*페이지수 + 1
//		-- 끝페이지 endPageNum =  (endPageNum > 전체페이지수) ? 전체페이지수 : startPageNum+페이지수 - 1;
		int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1  :((currentPageNum/pageBlockSize))*pageBlockSize + 1;
		int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum+pageBlockSize-1;
				
		List<CommunityListDto> dtolist = dao.selectPageList(conn, start, end);
		close(conn);
		
		result = new HashMap<String, Object>();
		result.put("dtolist", dtolist);
		result.put("totalPageCount", totalPageCount);
		result.put("startPageNum", startPageNum);
		result.put("endPageNum", endPageNum);
		result.put("currentPageNum", currentPageNum);
		System.out.println("selectPageList() : "+result);
		
		return result;
	}
	
	// select list - community reply 댓글 불러오기
	public List<CommunityReplyListDto> selectCommunityReplyList(Integer communityId) {
		List<CommunityReplyListDto> result = null;
		SqlSession session = getSqlSession(true);
		result = dao.selectCommunityReplyList(session, communityId);
		session.close();
		return result;
	}
	
	
	// select one
		public CommunityReadDto selectOne(Integer CommunityId) {
			CommunityReadDto result = null;
			Connection conn = getConnetion(true);
			result = dao.selectOne(conn, CommunityId);
			if(result != null) {
				dao.updateReadCount(conn, CommunityId);
			}
			List<FileReadDto> filelist = dao.selectFileList(conn, CommunityId);	
			result.setFiledtolist(filelist);
			
			// ajax 대체
//			List<BoardReplyListDto> replylist = dao.selectBoardReplyList(conn, boardId);	
//			result.setReplydtolist(replylist);
			close(conn);
			return result;
		}
		// insert - boardreply 댓글 작성
		public int insertReply(CommunityReplyWriteDto dto) {
			int result = 0;
			int resultupdate = 0;
			Connection conn = getConnetion(true);
			autoCommit(conn, false);
			if(dto.getReplyId() != 0) {
				resultupdate = dao.updateReplyStep(conn, dto.getReplyId());
				if(resultupdate > -1) {
					result = dao.insertRReply(conn, dto);
				}
			} else {
				result = dao.insertReply(conn, dto);
			}
			if(resultupdate > -1 && result > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			close(conn);
			return result;
		}
		
		// insert
		public int insert(CommunityInsertDto dto) {
			int result = 0;
			Connection conn = getConnetion(true);
//			int sequencNum = dao.getSequenceNum(conn);
			result = dao.insert(conn, dto);
			close(conn);
			return result;
		}
		
		
		
		
}
