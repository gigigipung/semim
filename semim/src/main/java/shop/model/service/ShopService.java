package shop.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import community.model.dto.CommunityReadDto;
import community.model.dto.FileReadDto;
import shop.model.dao.ShopDao;
import shop.model.dto.ShopListDto;
import shop.model.dto.ShopReadDto;

import static jdbc.common.JdbcTemplate.close;
import static jdbc.common.JdbcTemplate.getConnetion;
import static jdbc.common.MybatisTemplate.*;

public class ShopService {
	ShopDao dao = new ShopDao();

	public Map<String, Object> selectPageListMybatis(int pageSize, int pageBlockSize, int currentPageNum) {
		Map<String, Object> result = null;
		SqlSession session = getSqlSession();
		List<ShopListDto> boardList = dao.selectPageListRowBounds(session, pageSize, currentPageNum);
		session.close();
		return result;
	}
	// select list - all
	public Map<String, Object> selectJapanPageList(int pageSize, int pageBlockSize, int currentPageNum) {
		Map<String, Object> result = null;
		
		SqlSession session = getSqlSession();
		
		System.out.println("currentPageNum: " +currentPageNum);
		int start = pageSize*(currentPageNum-1)+1;
		int end = pageSize*currentPageNum;
		
		int totalCount = dao.selectTotalCount(session);
		System.out.println("totalCount:"+totalCount);
		int totalPageCount = (totalCount % pageSize == 0)? totalCount/pageSize :  totalCount/pageSize+1;
		
		int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1  :((currentPageNum/pageBlockSize))*pageBlockSize + 1;
		int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum+pageBlockSize-1;
		List<ShopListDto> dtolist = dao.selectJapanPageList(session, start, end);
		session.close();
		
		result = new HashMap<String, Object>();
		result.put("dtolist", dtolist);
		System.out.println("1");
		System.out.println(dtolist);
		result.put("totalPageCount", totalPageCount);
		result.put("startPageNum", startPageNum);
		result.put("endPageNum", endPageNum);
		result.put("currentPageNum", currentPageNum);
		System.out.println("selectPageList() : "+result);
		return result;
	}
	public Map<String, Object> selectKoreaPageList(int pageSize, int pageBlockSize, int currentPageNum) {
		Map<String, Object> result = null;
		
		SqlSession session = getSqlSession();
		
		System.out.println("currentPageNum: " +currentPageNum);
		int start = pageSize*(currentPageNum-1)+1;
		int end = pageSize*currentPageNum;
		
		int totalCount = dao.selectTotalCount(session);
		System.out.println("totalCount:"+totalCount);
		int totalPageCount = (totalCount % pageSize == 0)? totalCount/pageSize :  totalCount/pageSize+1;
		
		int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1  :((currentPageNum/pageBlockSize))*pageBlockSize + 1;
		int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum+pageBlockSize-1;
		List<ShopListDto> dtolist = dao.selectKoreaPageList(session, start, end);
		session.close();
		
		result = new HashMap<String, Object>();
		result.put("dtolist", dtolist);
		System.out.println("1");
		System.out.println(dtolist);
		result.put("totalPageCount", totalPageCount);
		result.put("startPageNum", startPageNum);
		result.put("endPageNum", endPageNum);
		result.put("currentPageNum", currentPageNum);
		System.out.println("selectPageList() : "+result);
		return result;
	}
	
	// select one
			public ShopReadDto selectOne(Integer gameId, Integer nationalCode) {
				ShopReadDto result = null;
				SqlSession session = getSqlSession();
				result = dao.selectOne(session, gameId,nationalCode);
				session.close();
				return result;
			}
	
}