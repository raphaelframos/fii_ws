package com.raphaelframos.refii.comment;

import com.raphaelframos.refii.common.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @RequestMapping("new/{fundId}")
    public ResponseEntity<ChatResponse> newComment(@PathVariable("fundId") Long fundId, @PathParam("userId") Long userId){
        return ResponseEntity.ok(service.newComment(fundId, userId));
    }

    @PostMapping
    @RequestMapping("create/{fundId}")
    public ResponseEntity<Integer> create(@PathVariable("fundId") Long fundId,
                      @PathParam("message") String message, @PathParam("userId") Long userId){
        return ResponseEntity.ok(service.create(message, userId, fundId));
    }

    @RequestMapping("find/{userId}/{fundId}")
    public ResponseEntity<List<CommentResponse>> myComments(@PathVariable("userId") Long userId, @PathVariable("fundId") Long fundId){
        return ResponseEntity.ok(service.commentsBy(userId, fundId));
    }

    @RequestMapping("{fundId}")
    public List<Comment> findAll(@PathVariable("fundId") Long fundId){
        return service.find(fundId);
    }

    @RequestMapping("")
    public List<Comment> findAll(){
        return service.findAll();
    }
}
