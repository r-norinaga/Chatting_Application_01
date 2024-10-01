package katachi.spring.portfolio.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;



@Controller
public class LoginController {
	
	 /** ログイン画面を表示 */

	 @GetMapping("/login")
	 public String getLogin(@RequestParam(value="error",required=false) String error, Model model,HttpSession session) {
	
		 AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		 System.out.println(ex);
	
		 return "actual/login/login";
	
	 }
	 
	 /** ユーザー一覧画面にリダイレクト */
	 @PostMapping("/login")
	 public	String postLogin() {
		 return	"redirect:/user/list";
	 }
}
