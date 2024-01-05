package com.springbootblog.Paylode;

import java.util.Set;

import org.hibernate.annotations.NotFound;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


public class PostDto {

	private Long id;
	@NotEmpty
	@Size(min = 2,message = "minimum 2 charecters")
	private String title;
	@NotEmpty
	@Size(min=1,message = "min 10 char")
	private String description;
	@NotEmpty
	private String content;
	private Set<CommentDto> comment;
	
	
	public Set<CommentDto> getComment() {
		return comment;
	}
	public void setComment(Set<CommentDto> comment) {
		this.comment = comment;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
