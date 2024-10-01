package katachi.spring.portfolio.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.portfolio.domain.user.model.MUser;
import katachi.spring.portfolio.domain.user.model.Room;
import katachi.spring.portfolio.domain.user.service.RoomService;
import katachi.spring.portfolio.domain.user.service.UserService;

@Controller
@RequestMapping("/talk")
public class TalkController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private MessageController messageController;
	
	@GetMapping("/roomList")
	public String getRoomList(Model model, /** @ModelAttribute TalkRoomForm talkRoomForm, */  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
		MUser loginUser = userService.getLoginUser(user.getUsername());
		
		List<Room> roomList = roomService.getRoomList(loginUser.getUserId());
		
		model.addAttribute("roomList", roomList);
		return "actual/room/roomList";

	}
	
	@PostMapping("/roomList")
	public String postRoomList(Model model, @RequestParam("roomId")int roomId, @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}

		redirectAttributes.addFlashAttribute("roomId", roomId);
		
//		return "redirect:/message/messageList";

		return messageController.getMessageList(model, roomId, user, redirectAttributes);
	}
	
	@GetMapping("/searchForARoom")
	public String getSearchForARoom(Model model, /** @ModelAttribute TalkRoomForm talkRoomForm, */  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
		MUser loginUser = userService.getLoginUser(user.getUsername());
		
		List<Room> roomList = roomService.getRoomList(loginUser.getUserId());
		
		model.addAttribute("roomList", roomList);
		return "actual/room/roomList";

	}
	
	
	
	
	
}
