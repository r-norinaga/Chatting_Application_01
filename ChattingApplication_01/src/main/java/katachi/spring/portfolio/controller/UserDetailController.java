package katachi.spring.portfolio.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.portfolio.domain.user.model.MUser;
import katachi.spring.portfolio.domain.user.service.UserService;
import katachi.spring.portfolio.form.UserDetailForm;




@Controller
@RequestMapping("/user")
public class UserDetailController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	
	@GetMapping("/detail")
	public String getUserDetail(Model model, @ModelAttribute UserDetailForm userDetailForm ,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}

		
		MUser loginUser = userService.getLoginUser(user.getUsername());
		
		userDetailForm = modelMapper.map(loginUser, UserDetailForm.class);
		
		model.addAttribute("userDetailForm", userDetailForm);

		return "actual/user/userDetail";
	}

}
