package com.sample.ap;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.mapper.BoardMapper;
import com.sample.service.AnnouncementService;
import com.sample.vo.BoardVO;
import com.sample.vo.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Log4j
public class TestClass {
	
	
	@Setter(onMethod_= @Autowired)
	private AnnouncementService service;
	
	@Setter(onMethod_= @Autowired)
	private BoardMapper mapper;
	
	
	

	@Test
	public void test(){
		Criteria cri = new Criteria();
	
		
		System.out.println(mapper.landScam(cri));
		System.out.println(mapper.businessAccident(cri));
		System.out.println(mapper.notice(cri));
		
	
		
		
	}
}
