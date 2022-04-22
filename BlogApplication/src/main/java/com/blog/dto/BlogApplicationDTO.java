package com.blog.dto;


public class BlogApplicationDTO {
	
	private Integer id;
	private String title;
	public BlogApplicationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BlogApplicationDTO(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "BlogApplicationDTO [id=" + id + ", title=" + title + "]";
	}
	
	
	
}
