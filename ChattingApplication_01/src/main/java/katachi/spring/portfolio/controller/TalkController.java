package katachi.spring.portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.portfolio.domain.user.model.MUser;
import katachi.spring.portfolio.domain.user.model.Room;
import katachi.spring.portfolio.domain.user.model.RoomUser;
import katachi.spring.portfolio.domain.user.service.RoomService;
import katachi.spring.portfolio.domain.user.service.RoomUserService;
import katachi.spring.portfolio.domain.user.service.UserService;
import katachi.spring.portfolio.form.RoomCreationForm;
import katachi.spring.portfolio.form.RoomSearchForm;

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
	
	@Autowired
	private RoomUserService roomUserService;
	
	@GetMapping("/roomList")
	public String getRoomList(Model model, /** @ModelAttribute("roomList") List<Room> roomList, */ @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
		MUser loginUser = userService.getLoginUser(user.getUsername());
		model.addAttribute("loginUser", loginUser);
		
		List<Room> roomList = roomService.getRoomList(loginUser.getUserId());
		model.addAttribute("roomList", roomList);
		return "actual/room/roomList";

	}
	
	@GetMapping("/searchForARoom")
	public String getSearchForARoom(Model model, @ModelAttribute RoomSearchForm roomSearchForm,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
		MUser loginUser = userService.getLoginUser(user.getUsername());
		model.addAttribute("loginUser", loginUser);
		
		return "actual/room/searchRoom";

	}
	
	@PostMapping("/searchForARoom")
	public String postSearchForARoom(Model model, @ModelAttribute RoomSearchForm roomSearchForm, @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}

		MUser loginUser = userService.getLoginUser(user.getUsername());
		model.addAttribute("loginUser", loginUser);
		
		Room room = modelMapper.map(roomSearchForm, Room.class);
		
		List<Room> roomList = roomService.searchForRooms(room, loginUser.getUserId());
		
		List<Room> roomListEntered = new ArrayList<Room>();
		List<Room> roomListNotEntered = new ArrayList<Room>();
		
		for(Room roomF : roomList) {
			for(RoomUser roomFF : roomF.getRoomUserList()) {
				if(roomFF.getUserId() == loginUser.getUserId()) {
					roomListEntered.add(roomF);
					continue;
				}
			}
			if(roomListNotEntered.contains(roomF) || roomListEntered.contains(roomF)) {
				continue;
			}else {
				roomListNotEntered.add(roomF);
			}
		}
		
		
		
		model.addAttribute("roomListEntered", roomListEntered);
		model.addAttribute("roomListNotEntered", roomListNotEntered);

//		redirectAttributes.addFlashAttribute("roomList", roomList);
		
//		return getRoomList(model, roomList,  user, redirectAttributes);
		return "actual/room/roomSearchResult";
	}
	
	@PostMapping("/enterARoom")
	public String getEnterARoom(Model model, @RequestParam("roomId")int roomId, @RequestParam("userId")int userId, @RequestParam("roomName")String roomName,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
		MUser loginUser = userService.getLoginUser(user.getUsername());
		model.addAttribute("loginUser", loginUser);
		
		
		roomUserService.enterARoom (roomId, userId);
		
		model.addAttribute("roomId", roomId);
		model.addAttribute("userId", userId);
		model.addAttribute("roomName", roomName);
		
		
		return "actual/room/roomEntrance";

	}
	
	
	
	@GetMapping("/createARoom")
	public String getCreateARoom(Model model, @ModelAttribute RoomCreationForm roomCreationForm,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}
		
		MUser loginUser = userService.getLoginUser(user.getUsername());
		model.addAttribute("loginUser", loginUser);
		
		List<MUser> userList = userService.getUsers();
		model.addAttribute("userList", userList);

		return "actual/room/createRoom";

	}
	
/*
	@PostMapping("/createARoom")
	public String postCreateARoom(Model model, @ModelAttribute RoomCreationForm roomCreationForm, @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}

		MUser loginUser = userService.getLoginUser(user.getUsername());
		model.addAttribute("loginUser", loginUser);
		
		Room room = modelMapper.map(roomCreationForm, Room.class);
		
		ArrayList<Integer> userIds = new ArrayList<>() ;
		for(int aUserId:roomCreationForm.getUserIds()) {
			userIds.add(aUserId);
		}

		roomService.registerARoom(room);
		model.addAttribute("room", room);
		
		roomUserService.registerRoomUsers(room.getRoomId(), userIds);
		
		
//		List<Room> roomList = roomService.searchForRooms(room, loginUser.getUserId());
//		
//		model.addAttribute("roomList", roomList);

//		redirectAttributes.addFlashAttribute("roomList", roomList);
		
//		return getRoomList(model, roomList,  user, redirectAttributes);
		return "actual/room/roomSearchResult";
	}
*/
	
	
}
