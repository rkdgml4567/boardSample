package com.land.service.prices;

import com.land.vo.prices.AptVo;
import java.util.Map;

public interface AptService {
  Map<String, Object> getAptPrList(AptVo paramAptVo);
}
