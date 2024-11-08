package katachi.spring.portfolio.form;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
@Component
public class RoomSearchForm {
//	private int roomId;
	@NotBlank
	private String roomName; 
}
