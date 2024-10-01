package katachi.spring.portfolio.domain.user.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class MUser {
	@Id
	private int userId;
	private String emailAddress;
	private String userName;
	private String password;
	private String passwordConfirmation;
	private String role;
	private int deletionFlag;
	private String image;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime createdAt;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime updatedAt;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private LocalDateTime deletedAt;
}
