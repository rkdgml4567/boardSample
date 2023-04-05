package com.land.controller.info;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.land.service.info.NewsService;
import com.land.util.Crawling;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/info/news/*")
public class NewsController {
	
	@Inject
	private NewsService newsService;
	Crawling cw = new Crawling();
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getNewsList(Model model, String keyword) {
		if (keyword == "") {
			model.addAttribute("newsList", newsService.getNewsList());
		} else {
			model.addAttribute("newsList", newsService.getNewsList(keyword));
		}
		return "info/list";
	}
	
	@RequestMapping(value="/init", method=RequestMethod.GET)
	public void initNews() {
		newsService.insertList(cw.getNewsList());
	}
	
	@RequestMapping(value="/more", method=RequestMethod.GET)
	public ModelAndView getAdditionalNewsList(@RequestParam(value="paging") int page, String keyword) {
		System.out.println("page: " + page);
		System.out.println("keyword222: " + keyword);
		ModelAndView model = new ModelAndView("jsonView");
		model.addObject("list", newsService.getAdditionalNewsList(page, keyword));
		return model;
	}
}