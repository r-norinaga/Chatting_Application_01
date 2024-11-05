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
	
	@Override
	public List<Room> searchForRooms(Room room, int userId) {
		return roomMapper.findRooms(room, userId);
	}
	
	@Override
	public void registerARoom(Room room) {
		roomMapper.insertOne(room);
	}
	
	@Override
	public String getARoomName(int roomId) {
		return roomMapper.findARoomName(roomId);
	}
	
	
}
