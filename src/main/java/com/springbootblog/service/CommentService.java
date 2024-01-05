package com.springbootblog.service;

import java.util.List;

import com.springbootblog.Paylode.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto,long postId);
	public List<CommentDto> getAllCommentsByPostId(long postId);
	public CommentDto getCoomentbyId(long commentId,long postId);
		public CommentDto updateCommentById(long postId,long commentId,CommentDto commentdto);
		public void deleteCommentById(long postId,long commentId, CommentDto commentDto);
}
		