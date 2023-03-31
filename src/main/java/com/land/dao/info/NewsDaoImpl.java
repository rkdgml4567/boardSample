package com.land.dao.info;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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
	public void insertList(List<NewsVo> newsList) {
		sqlSession.insert("news.insertList", newsList);
	}

	@Override
	public List<NewsVo> getAdditionalNewsList(int page) {
		return sqlSession.selectList("news.getAdditionalList", page);
	}
}
