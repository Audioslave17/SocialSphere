package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels,Integer>{
	public List<Reels> findByUserId(Integer userId);
}
