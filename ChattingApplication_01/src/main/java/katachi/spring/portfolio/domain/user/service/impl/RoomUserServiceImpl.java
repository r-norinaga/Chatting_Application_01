package katachi.spring.portfolio.domain.user.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.portfolio.domain.user.service.RoomUserService;
import katachi.spring.portfolio.repository.RoomUserMapper;

@Service
public class RoomUserServiceImpl implements RoomUserService {
	
	@Autowired
	private RoomUserMapper roomUserMapper;
	
	@Override
	public void registerUsers(int roomId, ArrayList<Integer> userIds) {
	 roomUserMapper.multiRowInsert(roomId, userIds);	
	}
}
