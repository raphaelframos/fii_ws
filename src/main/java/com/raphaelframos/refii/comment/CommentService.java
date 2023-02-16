package com.raphaelframos.refii.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> commentsBy(Long userId, Long fundId) {
        return repository.findMyComments(userId, fundId);
    }

    public List<Comment> find(Long fundId) {
        return repository.find(fundId);
    }
}
