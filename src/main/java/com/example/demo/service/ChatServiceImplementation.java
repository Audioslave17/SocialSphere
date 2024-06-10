package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Chat;
import com.example.demo.models.User;
import com.example.demo.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService{

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Chat createChat(User reqUser, User user2) {
		Chat isExist = chatRepository.findChatByUserId(reqUser, user2);
		if(isExist!=null) {
			return isExist;
		}
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimeStamp(LocalDateTime.now());
		
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		Optional<Chat> opt = chatRepository.findById(chatId);
		if(opt.isEmpty()) {
			throw new Exception("chat not found with Id - "+chatId);
		}
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		return chatRepository.findByUserId(userId);
	}

}
