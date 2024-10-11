package katachi.spring.portfolio.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.portfolio.domain.user.model.Room;
import katachi.spring.portfolio.domain.user.service.RoomService;
import katachi.spring.portfolio.domain.user.service.RoomUserService;
import katachi.spring.portfolio.domain.user.service.UserService;
import katachi.spring.portfolio.form.RoomCreationForm;

@RestController
@RequestMapping("/room")
public class RoomRestController {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomUserService roomUserService;
	
	@Autowired
	private RoomCreationForm roomCreationForm;
	
	

	 @Autowired
	 private MessageSource messageSource;
	     
	 /** ユーザーを登録 */
	 @PostMapping("/createARoom/rest")
	 public RestResult postMessage(Model model, @ModelAttribute @Validated RoomCreationForm roomCreationForm, BindingResult bindingResult, @AuthenticationPrincipal UserDetails user, Locale locale, RedirectAttributes redirectAttributes) {
	 	// 入力チェック結果
		 
//		 System.out.println(roomCreationForm.getContent());
		 
		 final int[] userIds = roomCreationForm.getUserIds();
		 
		 if(userIds.length <= 0) {
			 bindingResult.rejectValue("userIds", "NotChosen");
		 }
		 
		 if (bindingResult.hasErrors()) {
			 // チェック結果:NG
			 Map<String, String> errors = new HashMap<>();
			 // エラーメッセージ取得
		             
			 for(FieldError error : bindingResult.getFieldErrors()) {
				 String message = messageSource.getMessage(error, locale);
				 errors.put(error.getField(), message);
			 }
	// エラー結果の返却
			 return new RestResult(90, errors);
		 }
		 
		 Room room = modelMapper.map(roomCreationForm, Room.class);
		 roomService.registerARoom(room);
		 model.addAttribute("room", room); 
		 
		 final ArrayList<Integer> userIdList = new ArrayList<Integer>();
		 for(int userId : userIds) {
			 userIdList.add(userId);
		 }
		 redirectAttributes.addFlashAttribute("roomId", room.getRoomId());
		 // ユーザー登録
		 roomUserService.registerRoomUsers(room.getRoomId(), userIdList);
		
		  
		  // 結果の返却
		 return new RestResult(0, null);
	 }
}
