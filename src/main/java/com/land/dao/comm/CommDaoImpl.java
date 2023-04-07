package com.land.dao.comm;

import com.land.vo.comm.CommVo;
import com.land.vo.prices.PrBatAptMmVo;
import com.land.vo.prices.PrBatVo;
import com.land.vo.prices.AptVo;
import com.land.vo.prices.PrBatAptJsVo;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * @author NH
 *
 */
@Repository
public class CommDaoImpl implements CommDao {
	@Inject
	SqlSession sqlSession;

	/*
	 * 법정동 코드 리스트 조회
	 */
	public List<CommVo> getBjdCdList(CommVo vo) {
		return this.sqlSession.selectList("comm.getBjdCdList", vo);
	}
	/*
	 * 법정동 시군구 리스트 조회
	 */

	public List<CommVo> getBjdSggList() {
		return this.sqlSession.selectList("comm.getBjdSggList");
	}

	/*
	 * 배치작업조회
	 */
	public PrBatVo getBatWk(PrBatVo vo) {
		return (PrBatVo) this.sqlSession.selectOne("comm.getBatWk", vo);
	}

	/*
	 * 배치작업등록
	 */
	public int regBatWkHis(PrBatVo vo) {
		return this.sqlSession.update("comm.regBatWkHis", vo);
	}

	/*
	 * 아파트매매이력 삭제
	 */
	public int delPrAptMmHis(PrBatAptMmVo vo) {
		return this.sqlSession.delete("comm.delPrAptMmHis", vo);
	}

	/*
	 * 아파트매매등록 삭제
	 */
	public int regPrAptMmHis(PrBatAptMmVo itemList) {
		return this.sqlSession.insert("comm.regPrAptMmHis", itemList);
	}

	/*
	 * 아파트전세이력 삭제
	 */
	public int delPrAptJsHis(PrBatAptJsVo vo) {
		return this.sqlSession.delete("comm.delPrAptJsHis", vo);
	}

	/*
	 * 아파트전세이력 등록
	 */
	public int regPrAptJsHis(PrBatAptJsVo itemList) {
		return this.sqlSession.insert("comm.regPrAptJsHis", itemList);
	}

	/*
	 * 아파트등록
	 */
	public int regPrApt(AptVo vo) {
		return this.sqlSession.update("comm.regPrAptMm", vo);
	}

}
