package com.land.vo.info;

public class KeywordPagingVo {
	int page;
	String keyword;
	
	public KeywordPagingVo(int page, String keyword) {
		this.page = page;
		this.keyword = keyword;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
