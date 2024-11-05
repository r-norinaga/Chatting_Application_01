package katachi.spring.portfolio.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import katachi.spring.portfolio.domain.user.model.Room;

@Mapper
public interface RoomMapper {
	public List<Room> findMany(int userId);
	
	public List<Room> findRooms(@Param("room")Room room, @Param("userId")int userId);
	
	
	public void insertOne(@Param("room")Room room);
	
	public String findARoomName(int roomId);
}
