package com.land.service.prices;


import com.land.dao.prices.AptDao;
import com.land.vo.prices.AptVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

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
