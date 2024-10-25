package katachi.spring.portfolio.rest;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.portfolio.domain.user.model.MUser;
import katachi.spring.portfolio.domain.user.model.Message;
import katachi.spring.portfolio.domain.user.service.MessageService;
import katachi.spring.portfolio.domain.user.service.UserService;
import katachi.spring.portfolio.form.MessageForm;

@RestController
@RequestMapping("/message")
public class MessageRestController {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageForm messageForm;
	
	

	 @Autowired
	 private MessageSource messageSource;
	     
	 /** ユーザーを登録 */
	 @PostMapping("/postMessage/rest")
	 public RestResult postMessage(Model model, @ModelAttribute @Validated MessageForm messageForm, BindingResult bindingResult, @AuthenticationPrincipal UserDetails user, Locale locale, RedirectAttributes redirectAttributes) {
	 	// 入力チェック結果
		 
		 System.out.println(messageForm.getContent());
		 
		 String spacedMessageContent = messageForm.getContent().replace(" ", "");
		 spacedMessageContent = spacedMessageContent.replace("　", "");
		 
		 if(spacedMessageContent.equals("")) {
			 bindingResult.rejectValue("content", "space");
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
		 
		 // formをMUserクラスに変換
		 Message postedMessage = modelMapper.map(messageForm, Message.class);
		 redirectAttributes.addFlashAttribute("roomId", postedMessage.getRoomId());
		 // ユーザー登録
		 messageService.postMessage(postedMessage);
		
		  
		  // 結果の返却
		 return new RestResult(0, null);
	 }
	 
	 @PutMapping("/deleteMyMessage")
	 public int deleteMyMessage(Model model, @RequestParam("deletedMessageId")int deletedMessageId, @RequestParam("roomId")int roomId, @RequestParam("roomName")String roomName, @ModelAttribute MUser loginUser, BindingResult bindingResult, @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {
		 
//		 if(user != null) {
//				model.addAttribute("loginUserName", user.getUsername());
//			}
//			
//			
//			
//			model.addAttribute("loginUser", loginUser);
		 
			messageService.deleteMyMessage(deletedMessageId, loginUser.getUserId());
			model.addAttribute("roomId", roomId);
			model.addAttribute("roomName", roomName);

			
		  // 結果の返却
		 return roomId;
	 }
	
//	@PutMapping("/postMessage")
//	public String postMessage(Model model, @ModelAttribute MessageForm messageForm, BindingResult bindingResult, @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {
//		
//		if(user != null) {
//			model.addAttribute("loginUserName", user.getUsername());
//		}
//		
//		redirectAttributes.addFlashAttribute("messageForm", messageForm);
//		Message message = modelMapper.map(messageForm, Message.class);
//		messageService.postMessage(message);
//				
//		return "0";
//	}
}
