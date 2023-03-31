package com.land.service.comm;

import com.land.dao.comm.CommDao;
import com.land.vo.comm.CommVo;
import com.sample.service.AnnouncementServiceImpl;

import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class CommServiceImpl implements CommService {
  @Inject
  CommDao commDao;
  
  public Map<String, Object> getBjdCdList(CommVo vo) {
    List<CommVo> list = this.commDao.getBjdCdList(vo);
    Map<String, Object> map = new HashMap<>();
    map.put("list", list);
    map.put("firstCd", ((CommVo)list.get(0)).getBjdCode());
    return map;
  }
}
