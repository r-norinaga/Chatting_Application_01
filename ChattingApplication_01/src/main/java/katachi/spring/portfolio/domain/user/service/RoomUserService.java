package katachi.spring.portfolio.domain.user.service;

import java.util.ArrayList;

public interface RoomUserService {

	public void registerUsers(int roomId, ArrayList<Integer> userIds);
	
}
