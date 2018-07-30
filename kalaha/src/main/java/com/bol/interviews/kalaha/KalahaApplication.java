package com.bol.interviews.kalaha;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KalahaApplication {

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}


}