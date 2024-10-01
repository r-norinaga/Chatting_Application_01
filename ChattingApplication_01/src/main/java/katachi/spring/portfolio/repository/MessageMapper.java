package katachi.spring.portfolio.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.portfolio.domain.user.model.Message;

@Mapper
public interface MessageMapper {
	public List<Message> findMany(int roomId);
	public void insertOne(Message message);
}
