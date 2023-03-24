package com.sample.mapper;

import java.util.List;

import com.sample.vo.BoardVO;
import com.sample.vo.Criteria;


public interface BoardMapper {
	
	public List<BoardVO> getList();
	
	//public Integer insertAnnouncement();
	
	public BoardVO get(Long bno);
	
	public int register(BoardVO vo);
	
	public int getTotal();
	
	public List<BoardVO> adminList(Criteria cri);
	
	
	
	public List<BoardVO> landScam(Criteria cri);
	
	public List<BoardVO> notice(Criteria cri);
	
	public List<BoardVO> businessAccident(Criteria cri);
	
}
