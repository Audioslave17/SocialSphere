package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Story;
import com.example.demo.models.User;

public interface StoryService {

	public Story createStory(Story stpry, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception; 
}
