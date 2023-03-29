package com.land.dao.prices;

import com.land.vo.prices.AptVo;
import java.util.List;

public interface AptDao {
  int getAptTotalcnt(AptVo paramAptVo);
  
  List<AptVo> getAptPrList(AptVo paramAptVo);
}
