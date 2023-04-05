package com.land.dao.info;

import java.util.List;

import com.land.vo.info.NewsVo;

public interface NewsDao {
	public List<NewsVo> getNewsList();
	public List<NewsVo> getNewsList(String keyword);
	public void insertList(List<NewsVo> newsList);
	public List<NewsVo> getAdditionalNewsList(int page, String keyword);
}
