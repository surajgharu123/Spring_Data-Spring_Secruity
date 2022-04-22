package com.blog.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BLOGS")
public class BlogApplicationDocumnent {

	@Id
	private Integer id;
	private String title;
	public BlogApplicationDocumnent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BlogApplicationDocumnent(Integer id, String title) {
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
		return "BlogApplicationDocumnent [id=" + id + ", title=" + title + "]";
	}
}
