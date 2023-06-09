package com.example.OAuth_Forum.comment.controller;

import com.example.OAuth_Forum.article.dto.ResponseArticle;
import com.example.OAuth_Forum.auth.security.JwtAuthTokenProvider;
import com.example.OAuth_Forum.comment.dto.RequestComment;
import com.example.OAuth_Forum.comment.dto.ResponseComment;
import com.example.OAuth_Forum.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @Autowired
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @PostMapping("/create")
    public String createComment(@RequestBody RequestComment.SaveCommentDto commentDto, HttpServletRequest request) {
        Optional<String> token = jwtAuthTokenProvider.getAuthToken(request);
        commentService.saveComment(commentDto, token);
        return "Comment created successfully";
    }

//    @GetMapping("/get")
//    public List<ResponseComment.GetAllCommentDto> getAllComment() {
//        List<ResponseComment.GetAllCommentDto> response = commentService.getAllComment();
//        return response;
//    }
//
//    @GetMapping("/get/{id}")
//    public ResponseComment.GetCommentDto getComment(@PathVariable("id") Long id) {
//        ResponseComment.GetCommentDto response = commentService.getComment(id);
//        return response;
//    }
//
//    @GetMapping("/getByAId/{articleId}")
//    public List<ResponseComment.articleIdCommentDto> getByArticleId(@PathVariable("articleId") Long articleId) {
//        List<ResponseComment.articleIdCommentDto> response = commentService.getByAId(articleId);
//        return response;
//    }


    @PutMapping("/update")
    public String updateComment(@RequestBody RequestComment.UpdateCommentDto commentDto, HttpServletRequest request){
        Optional<String> token = jwtAuthTokenProvider.getAuthToken(request);
        commentService.updateComment(commentDto, token);
        return "Comment updated successfully.";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteComment(@PathVariable("id") Long id, HttpServletRequest request) {
        Optional<String> token = jwtAuthTokenProvider.getAuthToken(request);
        commentService.deleteComment(id, token);
        return "Comment delete successfully.";
    }

}
