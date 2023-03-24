package com.sample.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SampleVO {
	
	private int sample;
	private String sample2;
	private String sample3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sample4;
	private String sample5;
}
