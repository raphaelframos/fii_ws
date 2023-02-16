package com.raphaelframos.refii.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("refii/comments/")
public class CommentController {

    @Autowired
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @RequestMapping("my/{userId}")
    public List<Comment> myComments(@PathVariable("userId") Long userId, @PathParam("fundId") Long fundId){
        return service.commentsBy(userId, fundId);
    }

    @RequestMapping("{fundId}")
    public List<Comment> findAll(@PathVariable("fundId") Long fundId){
        return service.find(fundId);
    }
}
