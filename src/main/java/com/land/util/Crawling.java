package com.land.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.land.vo.info.NewsVo;

public class Crawling {
	
	String naverNewsURL = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid2=260&sid1=101";
	
	public ArrayList<NewsVo> getNewsList() {
		ArrayList<NewsVo> newsList = new ArrayList<NewsVo>();
//		newsList.addAll(getMKNewsList());
		newsList.addAll(getNaverNewsList());
		return newsList;
	}
	
	public ArrayList<NewsVo> getNaverNewsList() {
		ArrayList<NewsVo> list = new ArrayList<NewsVo>();
		HashMap<String, String> hm = new HashMap<String, String>();
		
		// "20230331","20230330",
		String[] newsDateArr = {"20230404","20230403","20230402","20230401"};
		int newsPage = 1;
		try {
			for(int day=0; day<newsDateArr.length; day++) {
				for(int i=newsPage; i<10; i++) {
					Document doc = Jsoup.connect(naverNewsURL + "&date=" + newsDateArr[day] + "&page=" + i).get();
					Elements ulElement1 = doc.select("ul.type06_headline").select("li");
					Elements ulElement2 = doc.select("ul.type06").select("li");
					
					// ulElement1 + ulElement2
					for(Element element : ulElement2) {
						ulElement1.add(element);
					}
					
					for(Element element : ulElement1) {
						String title = element.select("dt a").text();
						String content = element.select("dd span.lede").text();
						String href = element.select("dt a").attr("href");
						String img = element.select("img").attr("src");
						String writingSource = element.select("dd span.writing").text();
						String date = newsDateArr[day];
						NewsVo nv = new NewsVo(title, content, date, href, img, writingSource);
						System.out.println(nv.toString());
						// 똑같은 제목의 기사가 있는지 중복 검사
						if(content.length() > 0 && !hm.containsKey(content)) {
							hm.put(content, content);
							list.add(nv);
						}
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("끝!");
		return list;
	}
}