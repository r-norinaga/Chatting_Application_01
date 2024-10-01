package katachi.spring.portfolio.form;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
public class UserDetailForm {
	private int userId;
	
	@NotBlank
	@Email
	@Size(min = 1, max = 100)
	private String emailAddress;
	
	@NotBlank
	@Size(min = 1, max = 100)
	private String userName;

	@NotBlank
	@Pattern(regexp="^[!-~]+$")
	@Size(min = 1, max = 100)
	private String currentPassword;
	
	@NotBlank
	@Pattern(regexp="^[!-~]+$")
	@Size(min = 1, max = 100)
	private String newPassword;
	
	@NotBlank
	@Pattern(regexp="^[!-~]+$")
	@Size(min = 1, max = 100)
	private String newPasswordConfirmation;
}
