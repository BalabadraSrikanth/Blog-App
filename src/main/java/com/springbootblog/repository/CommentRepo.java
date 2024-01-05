package com.springbootblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootblog.entity.Comment;
@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
List<Comment> findByPostId(long postId);
}
