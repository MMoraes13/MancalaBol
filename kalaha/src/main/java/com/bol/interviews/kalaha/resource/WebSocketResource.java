package com.bol.interviews.kalaha.resource;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Component
@Controller
@MessageMapping("/send/message")
public class WebSocketResource implements Runnable{


    @Autowired
    private SimpMessagingTemplate template;


    public void startWebSocket(){
        new Thread(this).start();
    }


    @PostConstruct
    public void postConstruct() {
        System.out.println("Start websocket");

        startWebSocket();
    }


    @Override
    public void run() {
        int data = 10;

        while (true){

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            template.convertAndSend("/topic/hello", data);
        }


    }


}