package katachi.spring.portfolio.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.portfolio.domain.user.model.Room;

@Mapper
public interface RoomMapper {
	public List<Room> findMany(int userId);
}
