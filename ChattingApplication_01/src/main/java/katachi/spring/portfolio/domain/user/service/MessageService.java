package katachi.spring.portfolio.domain.user.service;

import java.util.List;

import katachi.spring.portfolio.domain.user.model.Message;

public interface MessageService {
	public List<Message> getMessageList(int roomId);
	public void postMessage(Message message);
}
