package com.springbootblog.service.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.mbeans.GlobalResourcesLifecycleListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springbootblog.Paylode.PostDto;
import com.springbootblog.Paylode.PostResponse;
import com.springbootblog.entity.Post;
import com.springbootblog.exception.ResourceNotFountExceeption;
import com.springbootblog.repository.PostRepo;
import com.springbootblog.service.PostService;

@Service
public class PostserviceImpl implements PostService{

	private PostRepo postrepo;
	
	
	
	
	public PostserviceImpl(PostRepo postrepo) {
	
		this.postrepo = postrepo;
	}

	//Convert Entity to DTO
		
	private PostDto mapToDto(Post post) {
		PostDto postdto=new PostDto();
		postdto.setContent(post.getContent());
		postdto.setDescription(post.getDescription());
		postdto.setId(post.getId());
		postdto.setTitle(post.getTitle());
		return postdto;
	}
	
	//Convert Dto to Entity
	private Post maptoEntity(PostDto postdto) {
		Post post=new Post();
		post.setContent(postdto.getContent());
		post.setDescription(postdto.getDescription());
		post.setTitle(postdto.getTitle());
	
		return post;
	}	
	
	
		@Override
		public PostDto createPost(PostDto postDto) {
			Post post=maptoEntity(postDto);
			Post newPost= postrepo.save(post);
			
			PostDto postResponse=mapToDto(newPost);

			
		
		
		return postResponse;
	}

	



		@Override
		public PostResponse getAllPost(int pageNo, int pageSize, String sortBy,String sortDir) {
			
			Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
					:Sort.by(sortBy).descending();
			
			PageRequest pageable=PageRequest.of(pageNo, pageSize,sort);
			
			Page<Post> post =postrepo.findAll(pageable);
			List<Post> listOfPosts=post.getContent();
			List<PostDto> content= listOfPosts.stream().map(posts->mapToDto(posts)).collect(Collectors.toList());
		 			
			PostResponse postResponce=new PostResponse();
			postResponce.setContent(content);
			postResponce.setPageNo(post.getNumber());
			postResponce.setPageSize(post.getSize());
			postResponce.setTotalElements(post.getTotalElements());
			postResponce.setTotalPages(post.getTotalPages());
			postResponce.setLast(post.isLast());
			
			return postResponce;
		
	}

		@Override
		public PostDto getPostById(long id) {
			 Post post=postrepo.findById(id).orElseThrow(() -> new ResourceNotFountExceeption("Post", id, "Id"));
			return mapToDto(post);
		}

		@Override
		public PostDto updatePost(PostDto update, long id) {
			
			Post post=postrepo.findById(id).orElseThrow(()->new ResourceNotFountExceeption("post",id,"id"));
			
			
			post.setContent(update.getContent());
			post.setDescription(update.getDescription());
			post.setTitle(update.getTitle());
			
			Post posts=postrepo.save(post);
			return mapToDto(posts);
		}

		@Override
		public void deleteById( long id) {

			Post post=postrepo.findById(id).orElseThrow(()->new ResourceNotFountExceeption("post",id,"id") );
			postrepo.delete(post);
			
		}

}
