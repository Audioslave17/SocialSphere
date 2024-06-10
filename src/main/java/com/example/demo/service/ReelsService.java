package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Reels;
import com.example.demo.models.User;

public interface ReelsService {

	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReel(Integer userId) throws Exception;
}
