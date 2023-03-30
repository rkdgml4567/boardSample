package com.land.dao.prices;

import com.land.vo.prices.AptVo;
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
