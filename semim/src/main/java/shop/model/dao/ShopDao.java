package shop.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import shop.model.dto.ShopListDto;

public class ShopDao {
	public Integer selectTotalCount(SqlSession session) {
		return session.selectOne("shopMapper.selectTotalCount");
	}
	
	public List<ShopListDto> selectPageListRowBounds(SqlSession session, int pageSize, int currentPageNum){
		int offset = (currentPageNum-1) * pageSize;
		RowBounds rbounds = new RowBounds(offset,pageSize);
		return session.selectList("shopMapper.selectPageList",null,rbounds);
	}
	
	
	// 상점 목록 불러오기
	public List<ShopListDto> selectPageList(SqlSession session, int start, int end) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("startRownum", start);
		param.put("endRownum", end);
		return session.selectList("shopMapper.selectPageList", param);
	}
}