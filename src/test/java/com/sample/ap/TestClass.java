package com.sample.ap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.land.mapper.BoardMapper;
import com.land.mapper.TestMapper;
import com.land.service.board.AnnouncementService;
import com.land.vo.board.TestVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Log4j
public class TestClass {
	
	
	

	  
	  @Setter(onMethod_=@Autowired)
	  private TestMapper testMapper;
	 
	
	
		/*
		 * @Setter(onMethod_= @Autowired) private BoardMapper mapper;
		 */
	 
	
	

	@Test
	public void test(){
		
		TestVO vo = new TestVO();
		
		vo.setTest_abc("test1");
		vo.setTest_abc2("test2");
		
		testMapper.test1(vo);

		
	
		
		
	}
}
