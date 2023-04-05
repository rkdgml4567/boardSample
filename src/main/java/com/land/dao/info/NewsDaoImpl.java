package com.land.dao.info;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.land.vo.info.KeywordPagingVo;
import com.land.vo.info.NewsVo;

@Repository
public class NewsDaoImpl implements NewsDao {
	@Inject
	SqlSession sqlSession;

	@Override
	public List<NewsVo> getNewsList() {
		return sqlSession.selectList("news.getList");
	}

	@Override
	public List<NewsVo> getNewsList(String keyword) {
		return sqlSession.selectList("news.getList", keyword);
	}
	
	@Override
	public void insertList(List<NewsVo> newsList) {
		sqlSession.insert("news.insertList", newsList);
	}

	@Override
	public List<NewsVo> getAdditionalNewsList(int page, String keyword) {
		return sqlSession.selectList("news.getAdditionalList", new KeywordPagingVo(page, keyword));
	}
}
