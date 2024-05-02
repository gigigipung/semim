package shop.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import shop.model.dto.ShopListDto;
import shop.model.dto.ShopReadDto;

public class ShopDao {
	public Integer selectTotalCount(SqlSession session) {
		return session.selectOne("shopMapper.selectTotalCount");
	}
	
	public List<ShopListDto> selectPageListRowBounds(SqlSession session, int pageSize, int currentPageNum){
		int offset = (currentPageNum-1) * pageSize;
		RowBounds rbounds = new RowBounds(offset,pageSize);
		return session.selectList("shopMapper.selectPageList",null,rbounds);
	}
	
	
	// 일본상점 목록 불러오기
	public List<ShopListDto> selectJapanPageList(SqlSession session, int start, int end) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("startRownum", start);
		param.put("endRownum", end);
		return session.selectList("shopMapper.selectPageJapanList", param);
	}
	public List<ShopListDto> selectKoreaPageList(SqlSession session, int start, int end) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("startRownum", start);
		param.put("endRownum", end);
		return session.selectList("shopMapper.selectPageKoreaList", param);
	}
	
	// select one
		public ShopReadDto selectOne(SqlSession session, Integer gameId,Integer nationalCode) {
			Map<String, Integer> param = new HashMap<String, Integer>();
			param.put("nationalCode", nationalCode);
			param.put("gameId", gameId);
			System.out.println(param);
			ShopReadDto result = session.selectOne("shopMapper.selectOne", param);
			System.out.println(result);
			return result;
		}
}
