package com.land.dao.comm;

import com.land.vo.comm.CommVo;
import com.land.vo.prices.PrBatAptMmVo;
import com.land.vo.prices.PrBatVo;
import java.util.List;

public interface CommDao {
  List<CommVo> getBjdCdList(CommVo paramCommVo);
  
  List<CommVo> getBjdSggList();
  
  PrBatVo getBatWk(PrBatVo paramPrBatVo);
  
  int regBatWkHis(PrBatVo paramPrBatVo);
  
  int delPrAptMmHis(PrBatAptMmVo paramPrBatAptMmVo);
  
  int regPrAptMmHis(PrBatAptMmVo paramPrBatAptMmVo);
}
