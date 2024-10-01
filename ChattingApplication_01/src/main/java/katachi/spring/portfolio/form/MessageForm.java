package katachi.spring.portfolio.form;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import katachi.spring.portfolio.domain.user.model.MUser;
import lombok.Data;

@Data
@Component
public class MessageForm {
	private int messageId;
	@NotBlank
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
