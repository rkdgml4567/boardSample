package com.sample.service;

import java.util.List;

import com.sample.vo.BoardVO;
import com.sample.vo.Criteria;

public interface AnnouncementService {
	
	public List<BoardVO> getList();
	
	public BoardVO get(Long bno);
	
	public int register(BoardVO vo);
	
	public int getTotal();
	
	public List<BoardVO> adminList(Criteria cri);
	
	
	public List<BoardVO> notice(Criteria cri);
	
	public List<BoardVO> landscam(Criteria cri);
	
	public List<BoardVO> businessAccident(Criteria cri);
	
	
}
