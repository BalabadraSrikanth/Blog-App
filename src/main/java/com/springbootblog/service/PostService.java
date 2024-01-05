package com.springbootblog.service;


import com.springbootblog.Paylode.PostDto;
import com.springbootblog.Paylode.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto);
	PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);
	PostDto getPostById(long id);
	PostDto updatePost(PostDto postdto,long id);
	void deleteById(long id);
}
