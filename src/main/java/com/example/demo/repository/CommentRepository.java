package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
