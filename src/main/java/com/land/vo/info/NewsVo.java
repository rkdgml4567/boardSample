package com.land.vo.info;

public class NewsVo {
	private String title;
	private String content;
	private String date;
	private String href;
	private String img;
	private String writingSource;
	
	public NewsVo() {
		super();
	}

	public NewsVo(String title, String content, String date, String href, String img, String writingSource) {
		super();
		this.title = title;
		this.content = content;
		this.date = date;
		this.href = href;
		this.img = img;
		this.writingSource = writingSource;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getWritingSource() {
		return writingSource;
	}

	public void setWritingSource(String writingSource) {
		this.writingSource = writingSource;
	}

	@Override
	public String toString() {
		return "NewsVO [title=" + title + ", content=" + content + ", date=" + date + ", href=" + href + ", img=" + img
				+ ", writingSource=" + writingSource + "]";
	}
	
}