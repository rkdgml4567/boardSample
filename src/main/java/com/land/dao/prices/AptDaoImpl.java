package com.land.dao.prices;


import com.land.controller.prices.AptController;
import com.land.vo.prices.AptVo;

import lombok.extern.log4j.Log4j;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;

@Repository
public class AptDaoImpl implements AptDao {
  @Inject
  SqlSession sqlSession;
  
  public List<AptVo> getAptPrList(AptVo vo) {
    return this.sqlSession.selectList("prices.getAptList", vo);
  }
  
  public int getAptTotalcnt(AptVo vo) {
    return ((Integer)this.sqlSession.selectOne("prices.getAptListTotCnt", vo)).intValue();
  }
}
