package com.in28minutes.rest.webservices.restfulwebservices.post;

import java.util.Date;

public class Post {
	private Integer id;
	
	private String content;
	
	private Date birthDate;

	public Post(Integer id, String content, Date birthDate) {
		super();
		this.id = id;
		this.content = content;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
