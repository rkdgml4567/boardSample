package com.land.batch;

import com.land.dao.comm.CommDao;
import com.land.util.GetResult;
import com.land.vo.comm.CommVo;
import com.land.vo.prices.AptVo;
import com.land.vo.prices.PrBatAptJsVo;
import com.land.vo.prices.PrBatAptMmVo;
import com.land.vo.prices.PrBatVo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scheduleJobApt")
public class AptBatServiceImpl implements AptBatService {
	private static final Logger log = LoggerFactory.getLogger(AptBatService.class);

	@Autowired
	private CommDao commDao;
	/*
	 * 11. 아파트매매 이력 등록
	 */
	public void saveAptMm() throws Exception {
		log.info("==========TB_CM_H_PR_BAT_APTMM START - MM ==================");

		List<CommVo> bjdSggList = this.commDao.getBjdSggList();

		int result_row = 0;
		// 조회년월
		LocalDate ld = LocalDate.now(); // 날짜 구하기
		String batYm = ld.format(DateTimeFormatter.BASIC_ISO_DATE).substring(0,6);
		// 계약년월
		String contractYm = "";

		PrBatVo batWkIn = new PrBatVo();

		batWkIn.setWkGbn("11"); // 작업구분 11 : 아파트-매매
		// 최근 배치 작업결과 조회 = batAllWkVo
		PrBatVo batAllWkVo = this.commDao.getBatWk(batWkIn);
		/*
		 * 계약년월 초기
		 */
		// 지역코드 99999 전체완료
		if (batAllWkVo != null && batAllWkVo.getAreaCode().equals("99999")) {
			
			//계약년월이 현재년월과 같으면 배치 종료
			if(batYm.equals(batAllWkVo.getContractYm()))
			{
				return;
			}
			
			int yyyy = Integer.parseInt(batAllWkVo.getContractYm().substring(0, 4));
			int mm = Integer.parseInt(batAllWkVo.getContractYm().substring(4, 6));

			LocalDate ym = LocalDate.of(yyyy, mm, 1);
			
			// 최근 작업완료된 계약년월 + 1달
			contractYm = ym.plusMonths(1).format(DateTimeFormatter.ofPattern("yyyymm"));
			
			log.info("========================");
			log.info("====== NEW contractYm : " + contractYm);
			log.info("========================");
		} else {
			// 초기작업 계약시작년월
			if (batAllWkVo == null) {
				contractYm = "202212";
			} else { // 중단된 계약년월
				contractYm = batAllWkVo.getContractYm();
			}

		} // if end - 계약년월 초기

		// 법정동시군구
		for (int i = 0; i < bjdSggList.size(); i++) {
			GetResult getXmlConObj = new GetResult();
			String areaCd = ((CommVo) bjdSggList.get(i)).getBjdSggCode();
		
			batWkIn.setBatYm("");			// 배치조회년월-여기서는 미사용
			batWkIn.setContractYm(contractYm); 	// 배치작업등록 계약년월
			batWkIn.setAreaCode(areaCd);
			PrBatVo batWkVo = this.commDao.getBatWk(batWkIn);
			
			// 정상처리건 skip
			if (batWkVo != null && (batWkVo.getTotCnt() > 0 && batWkVo.getTotCnt() == batWkVo.getWkCnt())) {
				log.info("============= skip : " + batWkIn.getAreaCode());
				continue;
			}
			// 기존 적재 중단 데이터 삭제
			else if (batWkVo != null && (batWkVo.getTotCnt() == 0 || batWkVo.getTotCnt() != batWkVo.getWkCnt())) {
				PrBatAptMmVo delVo = new PrBatAptMmVo();
				delVo.setBatYm(batWkVo.getBatYm());   		//기존 적재된 배치년월
				delVo.setBjdSggCode(areaCd);
				delVo.setYyyy(contractYm.substring(0, 4));
				delVo.setMm(contractYm.substring(4, 6));
				
				result_row = this.commDao.delPrAptMmHis(delVo);
				
				log.info("============= del his : " +  batWkIn.getAreaCode());
			}
			
			int pageNo = 1;
			int wkCnt = 0;
			batWkIn.setResultCd("");
			batWkIn.setResultMsg("");
			batWkIn.setNumOfRows("");
			batWkIn.setTotCnt(0);
			batWkIn.setWkCnt(0);
			batWkIn.setPageNo(0);
			batWkIn.setBatYm(batYm);
			batWkIn.setModUser("START");
			result_row = this.commDao.regBatWkHis(batWkIn);
			log.info(": result_row=" + result_row);

			if (result_row == 0) {
				throw new Exception(String.valueOf(batWkIn.toString()) + "\n====== Exception End ========");
			}

			HashMap<String, Object> map = getXmlConObj.AptMmParseXml(contractYm, pageNo, areaCd);
			log.info("map : Integer.parseInt(map.get(\"totCnt\").toString())="
					+ Integer.parseInt(map.get("totCnt").toString()));
			if (Integer.parseInt(map.get("totCnt").toString()) > 0) {
				List<PrBatAptMmVo> itemList = new ArrayList<>();

				itemList = (List<PrBatAptMmVo>) map.get("itemList");
				
				if (map.get("resultCd").toString().contentEquals("99")) {
					throw new Exception("\n====== resultCd : " + map.get("resultCd").toString() + "resultMsg : "
							+ map.get("resultMsg").toString());
				}
				
				PrBatAptMmVo regPrVo = new PrBatAptMmVo();
				regPrVo.setBatYm(batYm);
				regPrVo.setItemList(itemList);
				result_row = this.commDao.regPrAptMmHis(regPrVo);
				wkCnt = result_row;
				batWkIn.setResultCd(map.get("resultCd").toString());
				batWkIn.setResultMsg(map.get("resultMsg").toString());
				batWkIn.setNumOfRows(map.get("numOfRows").toString());
				batWkIn.setTotCnt(Integer.parseInt(map.get("totCnt").toString()));
				batWkIn.setWkCnt(wkCnt);
				batWkIn.setPageNo(Integer.parseInt(map.get("pageNo").toString()));
				batWkIn.setModUser("UDT");
				result_row = this.commDao.regBatWkHis(batWkIn);

				if (result_row == 0) {
					throw new Exception(
							String.valueOf(batWkIn.toString()) + "\n====== Exception End pageNo : 1 ========");
				}

				log.info(">>>>>>> SUB Commit (sgg) page : 1");
			} // for end - 1 page reg

			int totPage = Integer.parseInt(map.get("totPage").toString());

			for (int j = 2; j <= totPage; j++) {
				HashMap<String, Object> mapNext = getXmlConObj.AptMmParseXml(contractYm, j, areaCd);

				List<PrBatAptMmVo> itemList2 = new ArrayList<>();
				itemList2 = (List<PrBatAptMmVo>) mapNext.get("itemList");

				if (mapNext.get("resultCd").toString().contentEquals("99")) {
					throw new Exception("\n====== resultCd : " + mapNext.get("resultCd").toString() + "resultMsg : "
							+ mapNext.get("resultMsg").toString());
				}

				PrBatAptMmVo regPrVo2 = new PrBatAptMmVo();
				regPrVo2.setBatYm(batYm);
				regPrVo2.setItemList(itemList2);
				result_row = this.commDao.regPrAptMmHis(regPrVo2);
				wkCnt += result_row;
				batWkIn.setResultCd(mapNext.get("resultCd").toString());
				batWkIn.setResultMsg(mapNext.get("resultMsg").toString());
				batWkIn.setNumOfRows(mapNext.get("numOfRows").toString());
				batWkIn.setTotCnt(Integer.parseInt(mapNext.get("totCnt").toString()));
				batWkIn.setWkCnt(wkCnt);
				batWkIn.setPageNo(Integer.parseInt(mapNext.get("totPage").toString()));
				batWkIn.setModUser("UDT");
				result_row = this.commDao.regBatWkHis(batWkIn);

				if (result_row == 0) {
					throw new Exception(
							String.valueOf(batWkIn.toString()) + "\n====== Exception End pageNo : " + j + " ========");
				}
				log.info(">>>>>>> SUB Commit (sgg) pageNo : " + j);
			} // for end - 2 page reg

		} // for end -(bjdSgg code)

		// 최근 아파트 시세 저장
		AptVo aptMmIn = new AptVo();
		aptMmIn.setTrGbn(batAllWkVo.getWkGbn()); // 거래구분=작업구분
		aptMmIn.setContractYmd(contractYm);
		result_row = this.commDao.regPrApt(aptMmIn);

		// 배치작업결과 등록 - 99999 마스터 등록 끝
		batWkIn.setAreaCode("99999");
		batWkIn.setResultCd("00");
		batWkIn.setResultMsg("END");
		batWkIn.setNumOfRows("");
		batWkIn.setTotCnt(result_row);
		batWkIn.setWkCnt(result_row);
		batWkIn.setPageNo(0);
		batWkIn.setModUser("END");
		
		result_row = this.commDao.regBatWkHis(batWkIn);
		
		if (result_row == 0) {
			throw new Exception(String.valueOf(batWkIn.toString()) + "\n====== Exception End ========");
		}

		log.info("==========TB_CM_H_PR_BAT_APTMM END ==================");
	}
	
	/*
	 * 12. 아파트전세 이력 등록
	 */
	public void saveAptJs() throws Exception {
		log.info("==========TB_CM_H_PR_BAT_APTJS START - JS ==================");

		List<CommVo> bjdSggList = this.commDao.getBjdSggList();

		int result_row = 0;
		// 조회년월
		LocalDate ld = LocalDate.now(); // 날짜 구하기
		String batYm = ld.format(DateTimeFormatter.BASIC_ISO_DATE).substring(0,6);
		// 계약년월
		String contractYm = "";

		PrBatVo batWkIn = new PrBatVo();

		batWkIn.setWkGbn("12"); // 작업구분 11 : 아파트-전세
		// 최근 배치 작업결과 조회 = batAllWkVo
		PrBatVo batAllWkVo = this.commDao.getBatWk(batWkIn);
		/*
		 * 계약년월 초기
		 */
		// 지역코드 99999 전체완료
		if (batAllWkVo != null && batAllWkVo.getAreaCode().equals("99999")) {

			int yyyy = Integer.parseInt(batAllWkVo.getContractYm().substring(0, 4));
			int mm = Integer.parseInt(batAllWkVo.getContractYm().substring(4, 6));

			LocalDate ym = LocalDate.of(yyyy, mm, 1);
			// 최근 작업완료된 계약년월 + 1달
			contractYm = ym.plusMonths(1).format(DateTimeFormatter.ofPattern("yyyymm"));

			log.info("========================");
			log.info("======[APTJS] NEW contractYm : " + contractYm);
			log.info("========================");
		} else {
			// 초기작업 계약시작년월
			if (batAllWkVo == null) {
				contractYm = "202212";
			} else { // 중단된 계약년월
				contractYm = batAllWkVo.getContractYm();
			}

		} // if end - 계약년월 초기

		// 법정동시군구
		for (int i = 0; i < bjdSggList.size(); i++) {
			GetResult getXmlConObj = new GetResult();
			String areaCd = ((CommVo) bjdSggList.get(i)).getBjdSggCode();
		
			batWkIn.setBatYm("");			// 배치조회년월-여기서는 미사용
			batWkIn.setContractYm(contractYm); 	// 배치작업등록 계약년월
			batWkIn.setAreaCode(areaCd);
			PrBatVo batWkVo = this.commDao.getBatWk(batWkIn);
			
			// 정상처리건 skip
			if (batWkVo != null && (batWkVo.getTotCnt() > 0 && batWkVo.getTotCnt() == batWkVo.getWkCnt())) {
				log.info("==========[APTJS] skip : " + batWkIn.getAreaCode());
				continue;
			}
			// 기존 적재 중단 데이터 삭제
			else if (batWkVo != null && (batWkVo.getTotCnt() == 0 || batWkVo.getTotCnt() != batWkVo.getWkCnt())) {
				PrBatAptJsVo delVo = new PrBatAptJsVo();
				delVo.setBatYm(batWkVo.getBatYm());   		//기존 적재된 배치년월
				delVo.setAreaCode(areaCd);
				delVo.setYyyy(contractYm.substring(0, 4));
				delVo.setMm(contractYm.substring(4, 6));
				
				result_row = this.commDao.delPrAptJsHis(delVo);
				
				log.info("===========[APTJS]  del his : " +  batWkIn.getAreaCode());
			}
			
			int pageNo = 1;
			int wkCnt = 0;
			batWkIn.setResultCd("");
			batWkIn.setResultMsg("");
			batWkIn.setNumOfRows("");
			batWkIn.setTotCnt(0);
			batWkIn.setWkCnt(0);
			batWkIn.setPageNo(0);
			batWkIn.setBatYm(batYm);
			batWkIn.setModUser("START");
			result_row = this.commDao.regBatWkHis(batWkIn);
			log.info(": result_row=" + result_row);

			if (result_row == 0) {
				throw new Exception(String.valueOf(batWkIn.toString()) + "\n====== Exception End ========");
			}

			HashMap<String, Object> map = getXmlConObj.AptJsParseXml(contractYm, pageNo, areaCd);
			log.info("map (AptJs): Integer.parseInt(map.get(\"totCnt\").toString())="
					+ Integer.parseInt(map.get("totCnt").toString()));
			
			int totCnt = Integer.parseInt(map.get("totCnt").toString()); // 총 건수
			
			if (totCnt > 0) {
				List<PrBatAptJsVo> itemList = new ArrayList<>();
				log.info("map (AptJs):itemList set before");
				itemList = (List<PrBatAptJsVo>) map.get("itemList");
				log.info("map (AptJs):itemList set after resultCd="+map.get("resultCd").toString());
				
				if (map.get("resultCd").toString().equals("99")) {
					throw new Exception("\n====== resultCd : " + map.get("resultCd").toString() + "resultMsg : "
							+ map.get("resultMsg").toString());
				}
				
				int commit_row = 10;
				
				List<PrBatAptJsVo> itemList100 = new ArrayList<>();
				int p = 0; // temp index 
				
				for(int k = 0; k < totCnt; k++) 
				{
					itemList100.add(p, itemList.get(k));
					p++;
					log.info("(k+1) % commit_row ="+k +"::"+(k+1) % commit_row);
					if((k+1) % commit_row == 0 || k == totCnt - 1)
					{
						PrBatAptJsVo regPrVo = new PrBatAptJsVo();
						regPrVo.setBatYm(batYm);
						regPrVo.setItemList(itemList100);
						
						log.info("map (AptJs):regPrAptJsHis before"+itemList100.size());
						result_row = this.commDao.regPrAptJsHis(regPrVo);
						wkCnt += result_row;
						batWkIn.setResultCd(map.get("resultCd").toString());
						batWkIn.setResultMsg(map.get("resultMsg").toString());
						batWkIn.setNumOfRows("10");
						batWkIn.setTotCnt(totCnt);
						batWkIn.setWkCnt(wkCnt);
						batWkIn.setPageNo(1);
						batWkIn.setModUser("UDT");
						result_row = this.commDao.regBatWkHis(batWkIn);
		
						if (result_row == 0) {
							throw new Exception(
									String.valueOf(batWkIn.toString()) + "\n====== Exception End pageNo : 1 ========");
						}
						/* 초기화 */
						itemList100.clear();
						p = 0; 
					}
				}
				log.info(">>>>>>> SUB Commit (sgg) page : 1");
				
			} // for end - 1 page reg

			/*
			 * 전월세 페이징 처리 미사용
			 * int totPage = Integer.parseInt(map.get("totPage").toString());

			for (int j = 2; j <= totPage; j++) {
				HashMap<String, Object> mapNext = getXmlConObj.AptJsParseXml(contractYm, j, areaCd);

				List<PrBatAptJsVo> itemList2 = new ArrayList<>();
				itemList2 = (List<PrBatAptJsVo>) mapNext.get("itemList");

				if (mapNext.get("resultCd").toString().contentEquals("99")) {
					throw new Exception("\n====== resultCd : " + mapNext.get("resultCd").toString() + "resultMsg : "
							+ mapNext.get("resultMsg").toString());
				}

				PrBatAptJsVo regPrVo2 = new PrBatAptJsVo();
				regPrVo2.setBatYm(batYm);
				regPrVo2.setItemList(itemList2);
				result_row = this.commDao.regPrAptJsHis(regPrVo2);
				
				wkCnt += result_row;
				batWkIn.setResultCd(mapNext.get("resultCd").toString());
				batWkIn.setResultMsg(mapNext.get("resultMsg").toString());
				batWkIn.setNumOfRows(mapNext.get("numOfRows").toString());
				batWkIn.setTotCnt(Integer.parseInt(mapNext.get("totCnt").toString()));
				batWkIn.setWkCnt(wkCnt);
				batWkIn.setPageNo(Integer.parseInt(mapNext.get("totPage").toString()));
				batWkIn.setModUser("UDT");
				result_row = this.commDao.regBatWkHis(batWkIn);

				if (result_row == 0) {
					throw new Exception(
							String.valueOf(batWkIn.toString()) + "\n====== Exception End pageNo : " + j + " ========");
				}
				log.info(">>>>>>> SUB Commit (sgg) pageNo : " + j);
			} // for end - 2 page reg
			 */
		} // for end -(bjdSgg code)

		// 최근 아파트 시세 저장
		AptVo aptMmIn = new AptVo();
		aptMmIn.setTrGbn("1");  // 거래구분 : 1.아파트, 2.오피스텔
		aptMmIn.setWkGbn(batAllWkVo.getWkGbn()); // 작업구분 : 11.아파트매매, 12아파트전세
		aptMmIn.setContractYmd(contractYm);
		result_row = this.commDao.regPrApt(aptMmIn);

		// 배치작업결과 등록 - 99999 마스터 등록 끝
		batWkIn.setAreaCode("99999");
		batWkIn.setResultCd("00");
		batWkIn.setResultMsg("END");
		batWkIn.setNumOfRows("");
		batWkIn.setTotCnt(result_row);
		batWkIn.setWkCnt(result_row);
		batWkIn.setPageNo(0);
		batWkIn.setModUser("END");
		
		result_row = this.commDao.regBatWkHis(batWkIn);
		
		if (result_row == 0) {
			throw new Exception(String.valueOf(batWkIn.toString()) + "\n====== Exception End ========");
		}

		log.info("==========TB_CM_H_PR_BAT_APTJS END ==================");
	}

}
