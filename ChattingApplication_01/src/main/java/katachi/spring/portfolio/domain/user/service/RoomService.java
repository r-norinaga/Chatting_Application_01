package katachi.spring.portfolio.domain.user.service;

import java.util.List;

import katachi.spring.portfolio.domain.user.model.Room;

public interface RoomService {
	public List<Room> getRoomList(int userId);
	public List<Room> searchForRooms(Room room, int userId);
	public void registerARoom(Room room);
	public String getARoomName(int roomId);
}
