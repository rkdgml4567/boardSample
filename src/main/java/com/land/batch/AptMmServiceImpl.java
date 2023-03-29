package com.land.batch;

import com.land.dao.comm.CommDao;
import com.land.util.GetResult;
import com.land.vo.comm.CommVo;
import com.land.vo.prices.PrBatAptMmVo;
import com.land.vo.prices.PrBatVo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scheduleJobAptMm")
public class AptMmServiceImpl implements AptMmService {
  private static final Logger logger = LoggerFactory.getLogger(AptMmService.class);
  
  @Autowired
  private CommDao commDao;
  
  public void saveAptMm() throws Exception {
    System.out.println("==========TB_CM_H_PR_BAT_APTMM START ==================");
    List<CommVo> bjdSggList = this.commDao.getBjdSggList();
    String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
    String contractYm = "202212";
    String batYm = "202303";
    int result_row = 0;
    PrBatVo batWkIn = new PrBatVo();
    batWkIn.setBatYm(batYm);
    batWkIn.setContractYm(contractYm);
    batWkIn.setWkGbn("11");
    PrBatVo batAllWkVo = this.commDao.getBatWk(batWkIn);
    if (batAllWkVo != null && batAllWkVo.getAreaCode().equals("99999")) {
      System.out.println("========================");
      System.out.println("====== : " + batYm + " | " + contractYm);
      System.out.println("====== skip");
      System.out.println("========================");
    } else {
      for (int i = 0; i < bjdSggList.size(); i++) {
        GetResult getXmlConObj = new GetResult();
        String areaCd = ((CommVo)bjdSggList.get(i)).getBjdSggCode();
        batWkIn.setAreaCode(areaCd);
        PrBatVo batWkVo = this.commDao.getBatWk(batWkIn);
        if (batWkVo == null || batWkVo.getTotCnt() <= 0 || batWkVo.getTotCnt() != batWkVo.getWkCnt()) {
          PrBatAptMmVo delVo = new PrBatAptMmVo();
          delVo.setBatYm(batYm);
          delVo.setBjdSggCode(areaCd);
          delVo.setYyyy(contractYm.substring(0, 4));
          delVo.setMm(contractYm.substring(4, 6));
          result_row = this.commDao.delPrAptMmHis(delVo);
          int pageNo = 1;
          int wkCnt = 0;
          batWkIn.setResultCd("");
          batWkIn.setResultMsg("");
          batWkIn.setNumOfRows("");
          batWkIn.setTotCnt(0);
          batWkIn.setWkCnt(0);
          batWkIn.setPageNo(0);
          batWkIn.setModUser("START");
          result_row = this.commDao.regBatWkHis(batWkIn);
          System.out.println(": result_row=" + result_row);
          if (result_row == 0)
            throw new Exception(String.valueOf(batWkIn.toString()) + "\n====== Exception End ========"); 
          HashMap<String, Object> map = getXmlConObj.ParseXml(url, contractYm, pageNo, areaCd);
          System.out.println("map : Integer.parseInt(map.get(\"totCnt\").toString())=" + Integer.parseInt(map.get("totCnt").toString()));
          if (Integer.parseInt(map.get("totCnt").toString()) > 0) {
            List<PrBatAptMmVo> itemList = new ArrayList<>();
            itemList = (List<PrBatAptMmVo>)map.get("itemList");
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
            if (result_row == 0)
              throw new Exception(String.valueOf(batWkIn.toString()) + "\n====== Exception End pageNo : 1 ========"); 
            System.out.println(">>>>>>> SUB Commit (sgg) page : 1");
          } 
          int totPage = Integer.parseInt(map.get("totPage").toString());
          for (int j = 2; j <= totPage; j++) {
            HashMap<String, Object> mapNext = getXmlConObj.ParseXml(url, contractYm, j, areaCd);
            List<PrBatAptMmVo> itemList2 = new ArrayList<>();
            itemList2 = (List<PrBatAptMmVo>)mapNext.get("itemList");
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
            if (result_row == 0)
              throw new Exception(String.valueOf(batWkIn.toString()) + "\n====== Exception End pageNo : " + j + " ========"); 
            System.out.println(">>>>>>> SUB Commit (sgg) pageNo : " + j);
          } 
        } 
      } 
    } 
    batWkIn.setAreaCode("99999");
    batWkIn.setResultCd("00");
    batWkIn.setResultCd("00");
    batWkIn.setResultMsg("END");
    batWkIn.setNumOfRows("");
    batWkIn.setTotCnt(0);
    batWkIn.setWkCnt(0);
    batWkIn.setPageNo(0);
    batWkIn.setModUser("END");
    result_row = this.commDao.regBatWkHis(batWkIn);
    if (result_row == 0)
      throw new Exception(String.valueOf(batWkIn.toString()) + "\n====== Exception End ========"); 
    System.out.println("==========TB_CM_H_PR_BAT_APTMM END ==================");
  }
}
