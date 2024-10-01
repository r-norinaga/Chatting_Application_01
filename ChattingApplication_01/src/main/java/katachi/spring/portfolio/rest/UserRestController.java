package katachi.spring.portfolio.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.portfolio.controller.UserDetailController;
import katachi.spring.portfolio.domain.user.service.UserService;
import katachi.spring.portfolio.form.UserDetailForm;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserDetailController userDetailController;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PutMapping("/update")
	public String updateUser(Model model, @ModelAttribute @Validated UserDetailForm userDetailForm, BindingResult bindingResult, @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {
		
		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
//		MUser loginUser = userService.getLoginUser(user.getUsername());
//		
//		userDetailForm = modelMapper.map(loginUser, UserDetailForm.class);
		
		String spaceUserName = userDetailForm.getUserName().replace("　", "");
		spaceUserName = spaceUserName.replace(" ", "");
		String spaceEmailAddress = userDetailForm.getEmailAddress().replace("　", "");
		spaceEmailAddress = spaceEmailAddress.replace(" ", "");
		String spaceCurrentPassword = userDetailForm.getCurrentPassword().replace("　", "");
		spaceCurrentPassword = spaceCurrentPassword.replace(" ", "");
		String spaceNewPassword = userDetailForm.getNewPassword().replace("　", "");
		spaceNewPassword = spaceNewPassword.replace(" ", "");
		String spaceNewPasswordConfirmation = userDetailForm.getNewPasswordConfirmation().replace("　", "");
		spaceNewPasswordConfirmation = spaceNewPasswordConfirmation.replace(" ", "");
		
		
		if(spaceUserName.equals("")) {
			bindingResult.rejectValue("userName", "space");
		}
		
		if(spaceEmailAddress.equals("")) {
			bindingResult.rejectValue("emailAddress", "space");
		}
		
		if(spaceCurrentPassword.equals("")) {
			bindingResult.rejectValue("currentPassword", "space");
		}
		
		if(spaceNewPassword.equals("")) {
			bindingResult.rejectValue("newPassword", "space");
		}
		
		if(spaceNewPasswordConfirmation.equals("")) {
			bindingResult.rejectValue("newPasswordConfirmation", "space");
		}
		
		
		if(spaceUserName != null) {
			if(userService.overlappingCheckUserIdAndUserName(userDetailForm.getUserId(), spaceUserName)) {
				bindingResult.rejectValue("userName", "nameDuplicate");
			}
			
		}
		
		if(spaceEmailAddress != null) {
			if(userService.overlappingCheckUserIdAndEmailAddress(userDetailForm.getUserId(), spaceEmailAddress)) {
				bindingResult.rejectValue("emailAddress", "nameDuplicate");
			}
			
		}
		
		if(spaceNewPassword.equals(spaceNewPasswordConfirmation) == false) {
			bindingResult.rejectValue("newPassword", "discrepancy");
		}
		
		
		if(bindingResult.hasErrors()) {
			return userDetailController.getUserDetail(model, userDetailForm, user, redirectAttributes);
		}
		
		
		
		
		
//		this.signupForm = signupForm;
		
		redirectAttributes.addFlashAttribute("userDetailForm", userDetailForm);
//		model.addAttribute("signupForm", signupForm);
		
		
		if(passwordEncoder.matches(spaceCurrentPassword, userService.getUserPassword(userDetailForm.getUserId()))) {
			userService.updateUserOne(userDetailForm.getUserId(), spaceUserName, spaceEmailAddress, spaceNewPassword, spaceNewPasswordConfirmation);
			
		} else {
			bindingResult.rejectValue("currentPassword", "different");
			if(bindingResult.hasErrors()) {
				return userDetailController.getUserDetail(model, userDetailForm, user, redirectAttributes);
			}
		}
		
		
		return "0";
	}
	
}
