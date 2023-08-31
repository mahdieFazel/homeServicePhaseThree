package com.example.homeservicephasethree.service.serviceImpl;

import com.example.homeservicephasethree.base.BaseServiceImpl;
import com.example.homeservicephasethree.entity.Comment;
import com.example.homeservicephasethree.repository.CommentRepository;
import com.example.homeservicephasethree.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl
        extends BaseServiceImpl<Comment, Long, CommentRepository> implements CommentService {
    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }
}
