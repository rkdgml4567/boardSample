package com.land.controller.prices;


import com.land.service.comm.CommService;
import com.land.service.prices.AptService;
import com.land.vo.comm.CommVo;
import com.land.vo.prices.AptVo;


import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Log4j2
@Controller
public class AptController {
  //private static final Logger logger = LoggerFactory.getLogger(AptController.class);
  
  @Inject
  AptService aptService;
  
  @Inject
  CommService commService;
  
  @RequestMapping("/prices/apt")
  public String apt(@ModelAttribute("searchVO") AptVo inVo, ModelMap model) throws IOException {
	
    CommVo inLel = new CommVo();
    
    /*Map<String, Object> codeMap1 = this.commService.getBjdCdList(inLel);
    if (inVo.getSearchCd() != null && !inVo.getSearchCd().isEmpty()) {
      inLel.setSearchCd(inVo.getSearchCd());
    } else {
      inLel.setSearchCd(codeMap1.get("firstCd").toString().substring(0, 2));
    } 
    inLel.setSearchLevel("2");
    if (inVo.getSearchCd() != null && !inVo.getSearchCd().isEmpty()) {
      inLel.setSearchCd(inVo.getSearchCd());
    } else {
      inLel.setSearchCd(codeMap1.get("firstCd").toString().substring(0, 2));
    } */
    
    inLel.setSearchLevel("2");
    Map<String, Object> codeMap2 = this.commService.getBjdCdList(inLel);
    
    inLel.setSearchLevel("3");
    if (inVo.getSearchCd() != null && !inVo.getSearchCd().isEmpty()) {
      inLel.setSearchCd(inVo.getSearchCd().substring(0,5));  //법정동코드 5자리 시군구
    } else {
      inLel.setSearchCd(codeMap2.get("firstCd").toString().substring(0, 5));
    } 
    Map<String, Object> codeMap3 = this.commService.getBjdCdList(inLel);
    PaginationInfo paginationInfo = new PaginationInfo();
    paginationInfo.setCurrentPageNo(inVo.getPageIndex());
    paginationInfo.setRecordCountPerPage(inVo.getPageUnit());
    paginationInfo.setPageSize(inVo.getPageSize());
    inVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
    inVo.setLastIndex(paginationInfo.getLastRecordIndex());
    inVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    
    Map<String, Object> map = this.aptService.getAptPrList(inVo);
    
    int totCnt = Integer.parseInt((String)map.get("cnt"));
    
    paginationInfo.setTotalRecordCount(totCnt);
   // model.addAttribute("codeList1", codeMap1.get("list"));
    model.addAttribute("codeList2", codeMap2.get("list"));
    model.addAttribute("codeList3", codeMap3.get("list"));
    model.addAttribute("resultList", map.get("list"));
    model.addAttribute("resultCnt", map.get("cnt"));
    model.addAttribute("paginationInfo", paginationInfo);
    
    log.info("========page return; /prices/apt");
    
    return "prices/apt";
  }
  
  @RequestMapping({"/prices/selectBjdCodeList"})
  public ModelAndView selectBjdCodeList(@ModelAttribute("searchVO") CommVo inVO, ModelMap model) throws Exception {
    Map<String, Object> codeMap = this.commService.getBjdCdList(inVO);
    ModelAndView mav = new ModelAndView("jsonView");
    mav.addObject("codeList", codeMap);
    return mav;
  }
}
