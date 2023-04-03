package com.land.service.prices;

import com.land.batch.AptMmService;
import com.land.dao.prices.AptDao;
import com.land.mapper.BoardMapper;
import com.land.service.board.AnnouncementServiceImpl;
import com.land.vo.prices.AptVo;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AptServiceImpl implements AptService {
	
  @Inject
  AptDao aptDao;
  
  public Map<String, Object> getAptPrList(AptVo vo) {
    List<?> list = this.aptDao.getAptPrList(vo);
    int cnt = this.aptDao.getAptTotalcnt(vo);
    Map<String, Object> map = new HashMap<>();
    map.put("list", list);
    map.put("cnt", Integer.toString(cnt));
    return map;
  }
}
