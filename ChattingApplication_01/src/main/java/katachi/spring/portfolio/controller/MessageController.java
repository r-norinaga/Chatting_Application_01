package katachi.spring.portfolio.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.portfolio.domain.user.model.MUser;
import katachi.spring.portfolio.domain.user.model.Message;
import katachi.spring.portfolio.domain.user.service.MessageService;
import katachi.spring.portfolio.domain.user.service.UserService;
import katachi.spring.portfolio.form.MessageForm;

@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageForm messageForm;
	
	@GetMapping("/messageList")
	public String getMessageList(Model model, @RequestParam("roomId")int roomId,  /** @ModelAttribute MessageForm messageForm, */ @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {
		
		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
		MUser loginUser = userService.getLoginUser(user.getUsername());
		
		model.addAttribute("loginUser", loginUser);
		
//		int roomId = (int)redirectAttributes.getFlashAttributes("roomId");
		
		List<Message>messageList = messageService.getMessageList(roomId);
		
		model.addAttribute("messageList", messageList);
		model.addAttribute("messageForm", messageForm);
		model.addAttribute("roomId", roomId);
		
		
		return "actual/message/messageList";
	}
	
//	@PostMapping("/postMessage")
//	public String postMessage(Model model, @ModelAttribute MessageForm messageForm, @AuthenticationPrincipal UserDetails user) {
//
//		if(user != null) {
//			model.addAttribute("loginUserName", user.getUsername());
//		}
//		
//
//		model.addAttribute("messageForm", messageForm);
//		
//
//		return  "redirect/message/messageConfirmation";
////				getMessageConfirmation(model, roomId, user);
//	}
	
	
}
