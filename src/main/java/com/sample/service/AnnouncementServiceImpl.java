package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.mapper.BoardMapper;
import com.sample.vo.BoardVO;
import com.sample.vo.Criteria;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService{
	
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList() {
		log.info("getList in BoardService");
		return mapper.getList();
	}
	
	@Override
	public BoardVO get(Long bno) {
		return mapper.get(bno);
	}
	
	@Override
	public int register(BoardVO vo) {
		log.info("registerr in AnnouncementServiceImpl"+vo);
		
		return mapper.register(vo);
	}
	@Override
	public int getTotal() {
		log.info("getTotal in announcementController");
		
		return mapper.getTotal();
	}
	
	@Override
	public List<BoardVO> adminList(Criteria cri){
		log.info("getListWithPaging in announcementController");
		
		return mapper.adminList(cri);
	}
	
	
	@Override
	public List<BoardVO> businessAccident(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.businessAccident(cri);
	}
	@Override
	public List<BoardVO> landscam(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.landScam(cri);
	}
	@Override
	public List<BoardVO> notice(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.notice(cri);
	}
	

}
