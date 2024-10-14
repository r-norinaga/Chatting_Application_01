package katachi.spring.portfolio.form;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Component
public class RoomCreationForm {

	private int roomId;
	
	@NotBlank
	private String roomName; 
	
	@NotEmpty
	private int[] userIds;
	
	private int logedinUserId;
	
	private String logedinUserName;
	
//	private List<RoomUser> roomUserList;
//	
//	private List<MUser> mUserList;
	
}
