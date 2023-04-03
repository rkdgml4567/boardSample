package com.land.vo.board;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expireDate;
	private String btype;
}
