package katachi.spring.portfolio.form;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Component
public class RoomCreationForm {

	private int roomId;
	
	@NotBlank
	private String roomName; 
	
	private int[] userIds;
	
//	private List<RoomUser> roomUserList;
//	
//	private List<MUser> mUserList;
	
}
