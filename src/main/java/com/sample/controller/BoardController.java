package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.service.AnnouncementService;
import com.sample.vo.BoardVO;
import com.sample.vo.Criteria;
import com.sample.vo.PageDTO;
import com.sample.vo.SampleVO;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2 
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	@Setter(onMethod_=@Autowired)
	private AnnouncementService service;
	
	@GetMapping("/adminList")
	public void adminList(Model model,Criteria cri) {
		log.info("list: in BoardController");
		
		model.addAttribute("list",service.adminList(cri));
		
		int total=service.getTotal();
		
		
		log.info("total:"+total);
		
		model.addAttribute("pageMaker",new PageDTO(cri,total));
		
	}
	@GetMapping("/adminGet")
	public void getAnnouncement(@RequestParam("bno")Long bno,Model model) {
		log.info("get In announcementController"+bno);
		model.addAttribute("get",service.get(bno));
	}
	
	@GetMapping("/adminRegister")
	public void register() {
		
	}
	@PostMapping("/adminRegister")
	public String register(BoardVO vo,RedirectAttributes rttr) {
		log.info("post register in boardController"+vo);
		System.out.println(vo);
		
		service.register(vo);
		
		rttr.addFlashAttribute("result",vo.getBno());
		
		return "redirect:/board/adminList";		
	}
	
	
	
	@GetMapping("/notice")
	public void notice(Model model,Criteria cri) {
		log.info("notice: in BoardController");
		
		model.addAttribute("list",service.notice(cri));
		
		int total=service.getTotal();
		
		
		log.info("total:"+total);
		
		model.addAttribute("pageMaker",new PageDTO(cri,total));
	}
	
	@GetMapping("/landscam")
	public void landscam(Model model,Criteria cri) {
		log.info("landscam: in BoardController");
		
		model.addAttribute("list",service.landscam(cri));
		
		int total=service.getTotal();
		
		
		log.info("total:"+total);
		
		model.addAttribute("pageMaker",new PageDTO(cri,total));
	}
	
	@GetMapping("/businessAccident")
	public void businessAccident(Model model,Criteria cri) {
		log.info("businessAccident: in BoardController");
		
		model.addAttribute("list",service.businessAccident(cri));
		
		int total=service.getTotal();
		
		
		log.info("total:"+total);
		
		model.addAttribute("pageMaker",new PageDTO(cri,total));
	}
	
	
	
}
