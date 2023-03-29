package com.land.service.comm;

import com.land.vo.comm.CommVo;
import java.util.Map;

public interface CommService {
  Map<String, Object> getBjdCdList(CommVo paramCommVo);
}
