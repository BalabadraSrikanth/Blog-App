package com.springbootblog.service.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springbootblog.Paylode.CommentDto;
import com.springbootblog.entity.Comment;
import com.springbootblog.entity.Post;
import com.springbootblog.exception.BlogApiException;
import com.springbootblog.exception.ResourceNotFountExceeption;
import com.springbootblog.repository.CommentRepo;
import com.springbootblog.repository.PostRepo;
import com.springbootblog.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepo commentrepo;
	private PostRepo postRepo;
	private ModelMapper mapper;
	
	
	public CommentServiceImpl(CommentRepo commentrepo, PostRepo postRepo, ModelMapper mapper) {
		super();
		this.commentrepo = commentrepo;
		this.postRepo = postRepo;
		this.mapper=mapper;
	}

	@Override
	public CommentDto createComment(CommentDto commentDto, long postId) {

	Comment comment=mapToEntity(commentDto);
	//retriving Post entity by if
	Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFountExceeption("Post",postId,"id") ); 
		
	//set Post to commentv entity
	comment.setPost(post);
	
	//comment entity to db
	Comment newComment= commentrepo.save(comment);
	
	return mapToDto(newComment);
	
	}

	// Converting Entity to Dto by using MadelMapper Library
	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto=mapper.map(comment, CommentDto.class);
				//		commentDto.setBody(comment.getBody());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
		
		
		
		return commentDto;
	}
	
	// Converting DTo to Entity by using MadelMapper Library
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment=mapper.map(commentDto,Comment.class);
//		comment.setBody(commentDto.getBody());
//		comment.setEmail(commentDto.getEmail());
//		comment.setName(commentDto.getName());
//		comment.setId(commentDto.getId());
		return comment;
	}

	@Override
	public List<CommentDto> getAllCommentsByPostId(long postId) {
		
List<Comment> comments=commentrepo.findByPostId(postId);

		return comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList()); 
	}

	@Override
	public CommentDto getCoomentbyId(long commentId, long postId) {

		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFountExceeption("Post",postId,"id") ); 
		Comment comment=commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFountExceeption("comment",commentId,"id"));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment doesnot belong to post");
		}
		return mapToDto(comment);
	}

	@Override
	public CommentDto updateCommentById(long postId, long commentId, CommentDto commentdto) {
		//checking The comment by using findbyid and storing in comment
		Comment comment=commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFountExceeption("comment",commentId,"id") );
		//checking the post bi id 
		Post post=postRepo.findById(postId).orElseThrow( ()-> new ResourceNotFountExceeption("Post",postId,"id"));
		// if the comment is not present in post id it return error 
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
			
		}
		comment.setBody(commentdto.getBody());
		comment.setEmail(commentdto.getEmail());
		comment.setName(commentdto.getName());
		
		//saving the changes
		Comment updatedComment=commentrepo.save(comment);
		return mapToDto(updatedComment);
	}

	@Override
	public void deleteCommentById(long postId, long commentId, CommentDto commentDto) {
		Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFountExceeption("post",postId,"Id") );
		Comment comment=commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFountExceeption("Comment",commentId,"Id"));
		
		if(!comment.getPost().getId().equals(post.getId())){
			throw new BlogApiException(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, "comment not related to this post");
		}
		commentrepo.delete(comment);
		
		
	}
}
