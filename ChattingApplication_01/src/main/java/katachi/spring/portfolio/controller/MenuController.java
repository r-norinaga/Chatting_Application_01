package katachi.spring.portfolio.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@GetMapping("/overallMenu")
	public String getOverallMenu(Model model,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}


		return "actual/menu/overallMenu";
	}
	
	
	
	@GetMapping("/userMenu")
	public String getUserMenu(Model model,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}


		return "actual/menu/userMenu";
	}
	
	@GetMapping("/talkMenu")
	public String getTalkMenu(Model model,  @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		if(user != null) {
			model.addAttribute("loginUserName", user.getUsername());
		}


		return "actual/menu/talkMenu";
	}
}
