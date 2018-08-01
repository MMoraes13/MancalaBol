package com.bol.interviews.kalaha.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.stereotype.Controller;



@Controller
public class WebSocketResource {
	
	private final SimpMessagingTemplate template;
	
	@Autowired
	WebSocketResource (SimpMessagingTemplate template) {
		this.template = template;
	}
	
	@MessageMapping("/refresh")
	public void onReceivedMessage (String message) {
		this.template.convertAndSend( "/game",message);
	}
    


}