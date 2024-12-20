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
	public void registerRoomUsers(int roomId, ArrayList<Integer> userIds) {
	 roomUserMapper.multiRowInsert(roomId, userIds);	
	}
	
	@Override
	public void leaveARoom (int roomId, int userId) {
		roomUserMapper.deletOne(roomId, userId);
	}
	
	@Override
	public void enterARoom (int roomId, int userId) {
		roomUserMapper.insertOne(roomId, userId);
	}
	
}
