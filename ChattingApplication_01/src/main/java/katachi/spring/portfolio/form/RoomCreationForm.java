package katachi.spring.portfolio.form;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import katachi.spring.portfolio.domain.user.model.RoomUser;
import lombok.Data;

@Data
@Component
public class RoomCreationForm {

	private int roomId;
	
	@NotBlank
	private String roomName; 
	
	private List<RoomUser> roomUserList;
	
}
