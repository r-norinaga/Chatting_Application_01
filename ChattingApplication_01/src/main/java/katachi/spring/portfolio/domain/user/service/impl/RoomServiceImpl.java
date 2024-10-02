package katachi.spring.portfolio.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.portfolio.domain.user.model.Room;
import katachi.spring.portfolio.domain.user.service.RoomService;
import katachi.spring.portfolio.repository.RoomMapper;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	RoomMapper roomMapper;
	
	@Override
	public List<Room> getRoomList(int userId){
		return roomMapper.findMany(userId);
	}
}