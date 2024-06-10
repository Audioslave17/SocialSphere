package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Chat;
import com.example.demo.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
}
