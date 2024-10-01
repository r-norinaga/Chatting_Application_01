package katachi.spring.portfolio.domain.user.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class RoomUser {
	private int roomUserId;
	private int roomId;
	private int currentUserId;
	private int userId;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime createdAt;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime deletedAt;
	
	private int deletionFlag;
}
