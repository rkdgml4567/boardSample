package com.land.service.info;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.land.dao.info.NewsDao;
import com.land.vo.info.NewsVo;

@Service
public class NewsServiceImpl implements NewsService {
	@Inject
	NewsDao newsDao;
	
	public List getNewsList() { 
		return newsDao.getNewsList();
	}

	@Override
	public void insertList(List<NewsVo> newsList) {
		newsDao.insertList(newsList);
	}

	@Override
	public List getAdditionalNewsList(int page) {
		return newsDao.getAdditionalNewsList(page);
	}
}
