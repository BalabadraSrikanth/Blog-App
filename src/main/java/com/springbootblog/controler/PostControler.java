package com.springbootblog.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootblog.Paylode.PostDto;
import com.springbootblog.Paylode.PostResponse;
import com.springbootblog.service.ServiceImpl.PostserviceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostControler {
	
	@Autowired
	private PostserviceImpl postService;
	
	public PostControler(PostserviceImpl postService) {
		super();
		this.postService = postService;
	}

	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
	}
	
		@GetMapping("/getall")
	public PostResponse getAllPost(
			@RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){
		return postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
		}
		
		@GetMapping("/get/{id}")
		public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
			return ResponseEntity.ok(postService.getPostById(id));
		}
		
		@PreAuthorize("hasRole('ADMIN')")
		@PutMapping("/update/{id}")
		public ResponseEntity<PostDto> updatePost( @PathVariable("id") long id,@Valid @RequestBody PostDto postdto){
			return new ResponseEntity<>(postService.updatePost(postdto, id),HttpStatus.CREATED);
		}
		
		@PreAuthorize("hasRole('ADMIN')")
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> delatePost(@PathVariable("id") long id){
			postService.deleteById(id);
			return new ResponseEntity<>("Deleted Sucessfully",HttpStatus.OK);
		}
	}

