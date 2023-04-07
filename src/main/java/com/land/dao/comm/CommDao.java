package com.land.dao.comm;

import com.land.vo.comm.CommVo;
import com.land.vo.prices.PrBatAptMmVo;
import com.land.vo.prices.PrBatVo;
import com.land.vo.prices.AptVo;
import com.land.vo.prices.PrBatAptJsVo;

import java.util.List;

public interface CommDao {
  /*
   * 법정동 코드 리스트 조회
   */
  List<CommVo> getBjdCdList(CommVo paramCommVo);
  /*
   *  법정동 시군구 리스트 조회 
   */
  List<CommVo> getBjdSggList();
  /*
   *  배치작업조회
   */
  PrBatVo getBatWk(PrBatVo paramPrBatVo);
  /*
   *  배치작업등록
   */
  int regBatWkHis(PrBatVo paramPrBatVo);
  /*
   *  아파트매매이력 삭제
   */
  int delPrAptMmHis(PrBatAptMmVo paramPrBatAptMmVo);
  /*
   *  아파트매매등록 삭제
   */
  int regPrAptMmHis(PrBatAptMmVo paramPrBatAptMmVo);
  /*
   *  아파트전세이력 삭제
   */
  int delPrAptJsHis(PrBatAptJsVo paramPrBatAptJsVo);
  /*
   *  아파트전세등록 삭제
   */
  int regPrAptJsHis(PrBatAptJsVo paramPrBatAptJsVo);
  /*
   *  아파트등록
   */
  int regPrApt(AptVo paramAptVo);
}
