package com.springbootblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootblog.entity.Post;

public interface PostRepo extends JpaRepository<Post, Long> {

}
