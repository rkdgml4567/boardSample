package com.sample.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class TestTask {
	
	@Scheduled(cron ="0 * * * * *")
	public void check() throws Exception{
		log.warn("task is working");
		
		log.warn("====================");
	}
	
	@Scheduled(cron ="30 * * * * *")
	public void check2() throws Exception{
		log.warn("¾¾¹ß¾¾¹Ù¾¾¹ß¾¾¹Ù¾¾¹ß¾¾¹Ù");
		
		log.warn("====================");
	}
}
