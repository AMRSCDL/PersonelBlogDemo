package com.amir.personalBlog.repository;

import com.amir.personalBlog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
