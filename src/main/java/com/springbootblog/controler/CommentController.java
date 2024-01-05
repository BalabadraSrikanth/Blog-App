package com.springbootblog.controler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootblog.Paylode.CommentDto;
import com.springbootblog.service.ServiceImpl.CommentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {

	private CommentServiceImpl commnetService;

	public CommentController(CommentServiceImpl commnetService) {
		super();
		this.commnetService = commnetService;
	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(
			@PathVariable(value="postId") long postId,
			@Valid	@RequestBody CommentDto commentDto){
		return new ResponseEntity<>(commnetService.createComment(commentDto, postId),HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentByPost(@PathVariable(value="postId") long postId){
		
		return commnetService.getAllCommentsByPostId(postId);
	}
	
	@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable(value="postId") long postId,
										@PathVariable(value="id") long commentId){
		CommentDto commentDto=commnetService.getCoomentbyId(commentId, postId); 
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.CREATED);
	
	}
	@PostMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> updateCommentById(
			@PathVariable(value = "postId") long postId,
														@PathVariable(value="id") long commentId,
														@Valid	@RequestBody CommentDto commrntDto){
		CommentDto comment=commnetService.updateCommentById(postId, commentId, commrntDto);
		return new ResponseEntity<CommentDto>(comment,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public String deleteCommentById( @RequestBody CommentDto commentDto, 
			@PathVariable(value = "postId") long postId , @PathVariable(value = "commentId") long commentId) {
		//CommentDto comment=
				commnetService.deleteCommentById(postId, commentId, commentDto);
		return "commentDeleted Sucessfully";
	}
			
}
