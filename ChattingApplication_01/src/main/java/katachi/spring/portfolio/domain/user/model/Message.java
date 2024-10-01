package katachi.spring.portfolio.domain.user.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class Message {
	@Id
	private int messageId;
	private String content;
	private String image;
	private int roomId;
	private int userId;
	private MUser mUser;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime createdAt;

	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime updatedAt;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime deletedAt;
	
	private int deletionFlag;
}
