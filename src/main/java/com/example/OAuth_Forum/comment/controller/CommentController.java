package com.example.OAuth_Forum.comment.controller;

import com.example.OAuth_Forum.article.dto.ResponseArticle;
import com.example.OAuth_Forum.comment.dto.RequestComment;
import com.example.OAuth_Forum.comment.dto.ResponseComment;
import com.example.OAuth_Forum.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @PostMapping("/save")
    public String saveComment(@RequestBody RequestComment.SaveCommentDto commentDto) {
        commentService.saveComment(commentDto);
        return "Comment created successfully";
    }

    @GetMapping("/getM")
    public List<ResponseComment.GetAllCommentDto> getAllComment() {
        List<ResponseComment.GetAllCommentDto> response = commentService.getAllComment();
        return response;
    }

    @GetMapping("/getM/{id}")
    public ResponseComment.GetCommentDto getComment(@PathVariable("id") Long id) {
        ResponseComment.GetCommentDto response = commentService.getComment(id);
        return response;
    }

    @GetMapping("/getMA/{articleId}")
    public List<ResponseComment.articleIdCommentDto> articleIdComment(@PathVariable("articleId") Long articleId) {
        List<ResponseComment.articleIdCommentDto> response = commentService.articleIdComment(articleId);
        return response;
    }


    @PutMapping("/updateM")
    public String updateComment(@RequestBody RequestComment.UpdateCommentDto commentDto){
        commentService.updateComment(commentDto);
        return "Comment updated successfully.";
    }

    @DeleteMapping("/deleteM/{id}")
    public String deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return "Comment delete successfully.";
    }

}
