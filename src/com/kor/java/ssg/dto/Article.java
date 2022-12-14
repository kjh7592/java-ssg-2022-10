package com.kor.java.ssg.dto;

public class Article {
	public int id;
	public String regDate;
	public String title;
	public String body;
	public int hit;
	
	public Article(int id, String regDate, String title, String body) {
		this(id, regDate, title, body, 0);
	}
	
	public Article(int id, String regDate, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}
	
	public void incresehit() {
		hit++;
	}
}