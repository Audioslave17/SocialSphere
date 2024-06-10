package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Chat;
import com.example.demo.models.Message;
import com.example.demo.models.User;

public interface MessageService {

	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
