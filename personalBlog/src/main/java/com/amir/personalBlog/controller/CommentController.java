package com.amir.personalBlog.controller;

import com.amir.personalBlog.model.Comment;
import com.amir.personalBlog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public String showAllComments(Model model) {
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        return "admin/comments";
    }

    @GetMapping("/create")
    public String showCreateCommentForm(Model model) {
        model.addAttribute("comment", new Comment());
        return "admin/create-comment";
    }

    @PostMapping("/create")
    public String createComment(@ModelAttribute("comment") Comment comment) {
        commentService.saveComment(comment);
        return "redirect:/admin/comments";
    }

    @GetMapping("/edit/{id}")
    public String showEditCommentForm(@PathVariable Long id, Model model) {
        Comment comment = commentService.getCommentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment Id:" + id));
        model.addAttribute("comment", comment);
        return "admin/edit-comment";
    }

    @PostMapping("/edit/{id}")
    public String editComment(@PathVariable Long id, @ModelAttribute("comment") Comment comment) {
        comment.setId(id);
        commentService.saveComment(comment);
        return "redirect:/admin/comments";
    }

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/admin/comments";
    }
}
