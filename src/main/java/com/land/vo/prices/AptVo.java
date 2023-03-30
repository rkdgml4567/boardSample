package com.land.vo.prices;

public class AptVo {
  private String trGbn;
  
  private String bjdCode;
  
  private String contractYmd;
  
  private String kaptName;
  
  private long trAmt;
  
  private long depositAmt;
  
  private long rentAmt;
  
  private float areaUnit;
  
  private String batYm;
  
  private String modUser;
  
  private String modDate;
  
  private String searchTrGbn;
  
  private String searchAmtGbn;
  
  private String searchUnitGbn;
  
  private String searchCd;
  
  private String searchNm;
  
  private long minAmt;
  
  private long maxAmt;
  
  private float minUnit;
  
  private float maxUnit;
  
  public String getTrGbn() {
    return this.trGbn;
  }
  
  public void setTrGbn(String trGbn) {
    this.trGbn = trGbn;
  }
  
  public String getBjdCode() {
    return this.bjdCode;
  }
  
  public void setBjdCode(String bjdCode) {
    this.bjdCode = bjdCode;
  }
  
  public String getContractYmd() {
    return this.contractYmd;
  }
  
  public void setContractYmd(String contractYmd) {
    this.contractYmd = contractYmd;
  }
  
  public String getKaptName() {
    return this.kaptName;
  }
  
  public void setKaptName(String kaptName) {
    this.kaptName = kaptName;
  }
  
  public long getTrAmt() {
    return this.trAmt;
  }
  
  public void setTrAmt(long trAmt) {
    this.trAmt = trAmt;
  }
  
  public long getDepositAmt() {
    return this.depositAmt;
  }
  
  public void setDepositAmt(long depositAmt) {
    this.depositAmt = depositAmt;
  }
  
  public long getRentAmt() {
    return this.rentAmt;
  }
  
  public void setRentAmt(long rentAmt) {
    this.rentAmt = rentAmt;
  }
  
  public float getAreaUnit() {
    return this.areaUnit;
  }
  
  public void setAreaUnit(float areaUnit) {
    this.areaUnit = areaUnit;
  }
  
  public String getBatYm() {
    return this.batYm;
  }
  
  public void setBatYm(String batYm) {
    this.batYm = batYm;
  }
  
  public String getModUser() {
    return this.modUser;
  }
  
  public void setModUser(String modUser) {
    this.modUser = modUser;
  }
  
  public String getModDate() {
    return this.modDate;
  }
  
  public void setModDate(String modDate) {
    this.modDate = modDate;
  }
  
  public String getSearchTrGbn() {
    return this.searchTrGbn;
  }
  
  public void setSearchTrGbn(String searchTrGbn) {
    this.searchTrGbn = searchTrGbn;
  }
  
  public String getSearchAmtGbn() {
    return this.searchAmtGbn;
  }
  
  public void setSearchAmtGbn(String searchAmtGbn) {
    this.searchAmtGbn = searchAmtGbn;
  }
  
  public String getSearchUnitGbn() {
    return this.searchUnitGbn;
  }
  
  public void setSearchUnitGbn(String searchUnitGbn) {
    this.searchUnitGbn = searchUnitGbn;
  }
  
  public String getSearchNm() {
    return this.searchNm;
  }
  
  public void setSearchNm(String searchNm) {
    this.searchNm = searchNm;
  }
  
  public long getMinAmt() {
    return this.minAmt;
  }
  
  public void setMinAmt(long minAmt) {
    this.minAmt = minAmt;
  }
  
  public long getMaxAmt() {
    return this.maxAmt;
  }
  
  public void setMaxAmt(long maxAmt) {
    this.maxAmt = maxAmt;
  }
  
  public float getMinUnit() {
    return this.minUnit;
  }
  
  public void setMinUnit(float minUnit) {
    this.minUnit = minUnit;
  }
  
  public float getMaxUnit() {
    return this.maxUnit;
  }
  
  public void setMaxUnit(float maxUnit) {
    this.maxUnit = maxUnit;
  }
  
  private int pageIndex = 1;
  
  private int pageUnit = 10;
  
  private int pageSize = 10;
  
  private int firstIndex = 1;
  
  private int lastIndex = 1;
  
  private int recordCountPerPage = 10;
  
  public int getPageIndex() {
    return this.pageIndex;
  }
  
  public void setPageIndex(int pageIndex) {
    this.pageIndex = pageIndex;
  }
  
  public int getPageUnit() {
    return this.pageUnit;
  }
  
  public void setPageUnit(int pageUnit) {
    this.pageUnit = pageUnit;
  }
  
  public int getPageSize() {
    return this.pageSize;
  }
  
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  
  public int getFirstIndex() {
    return this.firstIndex;
  }
  
  public void setFirstIndex(int firstIndex) {
    this.firstIndex = firstIndex;
  }
  
  public int getLastIndex() {
    return this.lastIndex;
  }
  
  public void setLastIndex(int lastIndex) {
    this.lastIndex = lastIndex;
  }
  
  public int getRecordCountPerPage() {
    return this.recordCountPerPage;
  }
  
  public void setRecordCountPerPage(int recordCountPerPage) {
    this.recordCountPerPage = recordCountPerPage;
  }
  
  public String getSearchCd() {
    return this.searchCd;
  }
  
  public void setSearchCd(String searchCd) {
    this.searchCd = searchCd;
  }
}
