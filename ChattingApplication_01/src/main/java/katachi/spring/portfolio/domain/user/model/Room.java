package katachi.spring.portfolio.domain.user.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Room {
	@Id
	private int roomId;
	private String roomName;
	
	private int deletionFlag;
	
	private List<RoomUser> roomUserList;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime createdAt;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime deletedAt;
}
