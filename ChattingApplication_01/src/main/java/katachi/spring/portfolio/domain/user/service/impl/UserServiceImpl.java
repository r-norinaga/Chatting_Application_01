package katachi.spring.portfolio.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import katachi.spring.portfolio.domain.user.model.MUser;
import katachi.spring.portfolio.domain.user.service.UserService;
import katachi.spring.portfolio.repository.UserMapper;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void signup(MUser user) {
		String rawPassword = user.getPassword();
		user.setPassword(passwordEncoder.encode(rawPassword));
		userMapper.insertOne(user);
	}
	
	@Override
	public boolean overlappingCheckUserName(String userName) {
		if(userMapper.overlappingCheckUserName(userName) == null) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public boolean overlappingCheckEmailAddress(String emailAddress) {
		if(userMapper.overlappingCheckEmailAddress(emailAddress) == null) {
			return false;
		}else {
			return true;
		}
	}
	
	
	@Override
	public boolean overlappingCheckUserIdAndUserName(int userId, String userName) {
		if(userMapper.overlappingCheckUserIdAndUserName(userId, userName) == null) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public boolean overlappingCheckUserIdAndEmailAddress(int userId, String emailAddress) {
		if(userMapper.overlappingCheckUserIdAndEmailAddress(userId, emailAddress) == null) {
			return false;
		}else {
			return true;
		}
	}
	
	
	 /** ログインユーザー情報取得 */
	 @Override
	 public MUser getLoginUser(String userName) {
		 return userMapper.findLoginUser(userName);
	 }
	 
	 @Override
	 @Transactional
	 public void updateUserOne(int userId, String userName, String emailAddress, String password, String passwordConfirmation) {
		 String rawPassword = password;
		 String encodedPassword = passwordEncoder.encode(rawPassword);
//		 String rawPasswordConfirmation = passwordConfirmation;
//		 passwordConfirmation =passwordEncoder.encode(rawPasswordConfirmation);
		 userMapper.updateUser(userId, userName, emailAddress, encodedPassword, encodedPassword);
	 }
	 
	 @Override
	 public String getUserPassword(int userId) {
		return userMapper.findUserPassword(userId);
	 }
	
	@Override
	public List<MUser> getUsers(){
		return userMapper.findMany();
	}
 
}
