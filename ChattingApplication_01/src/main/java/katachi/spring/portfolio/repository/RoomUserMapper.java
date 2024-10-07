package katachi.spring.portfolio.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomUserMapper {

	public void insertUsers(ArrayList<Integer> userIds);
	
}
