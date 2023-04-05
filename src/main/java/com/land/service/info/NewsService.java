package com.land.service.info;

import java.util.List;

import com.land.vo.info.NewsVo;

public interface NewsService {
	public List getNewsList();
	public List getNewsList(String keyword);
	public void insertList(List<NewsVo> newsList);
	public List getAdditionalNewsList(int page, String keyword);
}
