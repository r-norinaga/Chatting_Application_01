package katachi.spring.portfolio.domain.user.service;

import java.util.List;

import katachi.spring.portfolio.domain.user.model.Room;

public interface RoomService {
	public List<Room> getRoomList(int userId);
}
