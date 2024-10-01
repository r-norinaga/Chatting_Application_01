package katachi.spring.portfolio.domain.user.service;

import katachi.spring.portfolio.domain.user.model.MUser;


public interface UserService {
	
	public void signup(MUser user);
	public boolean overlappingCheckUserName(String userName);
	public boolean overlappingCheckEmailAddress(String emailAddress);
	
	public boolean overlappingCheckUserIdAndUserName(int userId, String userName);
	public boolean overlappingCheckUserIdAndEmailAddress(int userId, String emailAddress);
	/** ログインユーザー取得 */
	public	 MUser getLoginUser(String userName);
	public void updateUserOne(int userId, String userName, String emailAddress, String password, String passwordConfrimation);
	public String getUserPassword(int userId);
	
	
}
