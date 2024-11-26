package katachi.spring.portfolio.domain.user.service;

import java.util.ArrayList;

public interface RoomUserService {

	public void registerRoomUsers(int roomId, ArrayList<Integer> userIds);
	
	public void leaveARoom (int roomId, int userId);
	
	public void enterARoom (int roomId, int userId);
	
}
