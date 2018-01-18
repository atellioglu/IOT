package utc.bab.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	@RequestMapping("")
	public String helloWorld() {
		return "Hello Spring";
	}
	

}
