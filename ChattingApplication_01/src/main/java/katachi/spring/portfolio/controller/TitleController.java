package katachi.spring.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/title")
public class TitleController {

	@Autowired
	HttpSession session;

	@Autowired
	HttpServletRequest request;
	
	@GetMapping("/")
	public String getTitle() {

		return "actual/title/titlePage";
	}

	
}
