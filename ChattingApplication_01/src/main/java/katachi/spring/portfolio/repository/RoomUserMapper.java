package katachi.spring.portfolio.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface RoomUserMapper {
	@Options(useGeneratedKeys=true, keyColumn="room_user_id")
	public void multiRowInsert(@Param("roomId")int roomId, @Param("userIds")ArrayList<Integer> userIds);
	
	public void deletOne(int roomId, int userId);
	
	public void insertOne(int roomId, int userId);
	
}
