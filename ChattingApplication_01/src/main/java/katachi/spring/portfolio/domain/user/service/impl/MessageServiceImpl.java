package katachi.spring.portfolio.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.portfolio.domain.user.model.Message;
import katachi.spring.portfolio.domain.user.service.MessageService;
import katachi.spring.portfolio.repository.MessageMapper;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageMapper messageMapper;
	
	@Override
	public List<Message> getMessageList(int roomId){
		return messageMapper.findMany(roomId);
	}
	
	@Override
	public void postMessage(Message message) {
		messageMapper.insertOne(message);
	}
}
