package katachi.spring.portfolio.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.portfolio.domain.user.model.MUser;
import katachi.spring.portfolio.domain.user.service.UserService;
import katachi.spring.portfolio.form.SignupForm;

@Controller
@RequestMapping("/signup")
public class SignupController {
	
	@Autowired
	private ModelMapper modelMapper;
	
//	@Autowired
//	private SignupForm signupForm;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/signup")
	public String getSignup(Model model, @ModelAttribute SignupForm signupForm ,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}


		return "actual/user/signup";
	}
	
	@PostMapping(value="/signup", params="confirmation")
	public String postSignup(Model model, @ModelAttribute @Validated SignupForm signupForm, BindingResult bindingResult,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
		
		String spaceUserName = signupForm.getUserName().replace("　", "");
		spaceUserName = spaceUserName.replace(" ", "");
		String spaceEmailAddress = signupForm.getEmailAddress().replace("　", "");
		spaceEmailAddress = spaceEmailAddress.replace(" ", "");
		String spacePassword = signupForm.getPassword().replace("　", "");
		spacePassword = spacePassword.replace(" ", "");
		String spacePasswordConfirmation = signupForm.getPasswordConfirmation().replace("　", "");
		spacePasswordConfirmation = spacePasswordConfirmation.replace(" ", "");
		
		
		if(spaceUserName.equals("")) {
			bindingResult.rejectValue("userName", "space");
		}
		
		if(spaceEmailAddress.equals("")) {
			bindingResult.rejectValue("emailAddress", "space");
		}
		
		if(spacePassword.equals("")) {
			bindingResult.rejectValue("password", "space");
		}
		
		if(spacePasswordConfirmation.equals("")) {
			bindingResult.rejectValue("passwordConfirmation", "space");
		}
		
		
		if(spaceUserName != null) {
			if(userService.overlappingCheckUserName(spaceUserName)) {
				bindingResult.rejectValue("userName", "nameDuplicate");
			}
			
		}
		
		if(spaceEmailAddress != null) {
			if(userService.overlappingCheckEmailAddress(spaceEmailAddress)) {
				bindingResult.rejectValue("emailAddress", "nameDuplicate");
			}
			
		}
		
		if(spacePassword.equals(spacePasswordConfirmation) == false) {
			bindingResult.rejectValue("password", "discrepancy");
		}
		
		
		if(bindingResult.hasErrors()) {
			return getSignup(model, signupForm, user, redirectAttributes);
		}
		
		
		
		
		
//		this.signupForm = signupForm;
		
		redirectAttributes.addFlashAttribute("signupForm", signupForm);
//		model.addAttribute("signupForm", signupForm);

		return "redirect:/signup/signupConfirmation";
	}
	
	@GetMapping("/signupConfirmation")
	public String getSignupConfirmation(Model model, @ModelAttribute SignupForm signupForm,  BindingResult bindingResult,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
//		model.addAttribute("signupForm", redirectAttributes.getAttribute("signupForm"));
		
		
		System.out.println(signupForm);
		return "actual/user/signupConfirmation";
	}
	
	@PostMapping(value="/signupConfirmation", params="completion")
	public String postSignupConfirmation(Model model, @ModelAttribute @Validated SignupForm signupForm, BindingResult bindingResult,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
//		if(bindingResult.hasErrors()) {
//			return getSignup(model, signupForm, user, redirectAttributes);
//		}
		
		
//		this.signupForm = signupForm;
		
		redirectAttributes.addFlashAttribute("signupForm", signupForm);
//		model.addAttribute("signupForm", signupForm);

		return "redirect:/signup/signupCompletion";
	}

	@GetMapping("/signupCompletion")
	public String getSignupCompletion(Model model, @ModelAttribute SignupForm signupForm, BindingResult bindingResult,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
		model.addAttribute("signupForm", redirectAttributes.getAttribute("signupForm"));
		
		MUser user2 = modelMapper.map(signupForm, MUser.class);
		System.out.println(user2);
		userService.signup(user2);
		return "actual/user/signupCompletion";
//		return postSignupCompletion(model, signupForm, bindingResult, user, redirectAttributes);
	}
	
	@PostMapping("/signupCompletion")
	public String postSignupCompletion(Model model, @ModelAttribute @Validated SignupForm signupForm, BindingResult bindingResult,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
//		if(bindingResult.hasErrors()) {
//			return getSignup(model, signupForm, user, redirectAttributes);
//		}
		
		
//		this.signupForm = signupForm;
		
//		redirectAttributes.addFlashAttribute("signupForm", signupForm);
//		model.addAttribute("signupForm", signupForm);
		
//		User user2 = modelMapper.map(signupForm, User.class);
//		userService.signup(user2);
		return "actual/user/signupCompletion";
	}
	
}
