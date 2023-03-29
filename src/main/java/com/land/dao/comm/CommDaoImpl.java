package com.land.dao.comm;

import com.land.vo.comm.CommVo;
import com.land.vo.prices.PrBatAptMmVo;
import com.land.vo.prices.PrBatVo;
import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CommDaoImpl implements CommDao {
  @Inject
  SqlSession sqlSession;
  
  public List<CommVo> getBjdCdList(CommVo vo) {
    return this.sqlSession.selectList("comm.getBjdCdList", vo);
  }
  
  public List<CommVo> getBjdSggList() {
    return this.sqlSession.selectList("comm.getBjdSggList");
  }
  
  public PrBatVo getBatWk(PrBatVo vo) {
    return (PrBatVo)this.sqlSession.selectOne("comm.getBatWk", vo);
  }
  
  public int regBatWkHis(PrBatVo vo) {
    return this.sqlSession.update("comm.regBatWkHis", vo);
  }
  
  public int delPrAptMmHis(PrBatAptMmVo vo) {
    return this.sqlSession.delete("comm.delPrAptMmHis", vo);
  }
  
  public int regPrAptMmHis(PrBatAptMmVo itemList) {
    return this.sqlSession.insert("comm.regPrAptMmHis", itemList);
  }
}
