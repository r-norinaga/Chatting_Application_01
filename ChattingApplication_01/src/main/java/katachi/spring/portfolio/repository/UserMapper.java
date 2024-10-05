package katachi.spring.portfolio.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.portfolio.domain.user.model.MUser;

@Mapper
public interface UserMapper {
	
	public void insertOne(MUser user);
	
	public String overlappingCheckUserName(String userName);
	
	public String overlappingCheckEmailAddress(String emailAddress);
	
	public MUser overlappingCheckUserIdAndUserName(int userId, String userName);
	
	public MUser overlappingCheckUserIdAndEmailAddress(int userId, String emailAddress);
	
	
	
	 /** ログインユーザー取得 */
	 public MUser findLoginUser(String userName);
	
	 public String findUserPassword(int userId);
	 
	 public void updateUser(int userId, String userName, String emailAddress, String password, String passwordConfirmation);
	 
	 public List<MUser> findMany();
}
