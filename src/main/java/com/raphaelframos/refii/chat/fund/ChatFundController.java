package com.raphaelframos.refii.chat.fund;

import com.raphaelframos.refii.chat.ChatService;
import com.raphaelframos.refii.common.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("refii/chat/")
public class ChatFundController {

    @Autowired
    private final ChatService service;

    public ChatFundController(ChatService service) {
        this.service = service;
    }

    @RequestMapping("fund/{userId}/{position}")
    public ChatResponse newFund(@PathVariable("userId") Long userId,
            @PathVariable("position") int position,
            @RequestParam("message") String value
    ){
        return service.newFund(value, position, userId);
    }
}
