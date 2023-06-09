package com.example.OAuth_Forum.article.controller;

//import com.example.OAuth_Forum.common.dto.ResponseMessage;
import com.example.OAuth_Forum.article.dto.RequestArticle;
import com.example.OAuth_Forum.article.dto.ResponseArticle;
import com.example.OAuth_Forum.article.service.ArticleService;
import com.example.OAuth_Forum.auth.security.JwtAuthTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @Autowired
    private final ArticleService articleService;

    @PostMapping("/create")
    public String createArticle(@RequestBody RequestArticle.CreateArticleDto requestDto, HttpServletRequest request) {
        Optional<String> token = jwtAuthTokenProvider.getAuthToken(request);
        articleService.createArticle(requestDto, token);
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("Task created successfully.")
//                .build();
        return "Article created successfully.";
    }

    @GetMapping("/get")
    public List<ResponseArticle.GetAllArticleDto> getAllArticle() {
        List<ResponseArticle.GetAllArticleDto> response = articleService.getAllArticle();
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("Tasks retrieved successfully.")
//                .data(response)
//                .build();
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseArticle.GetArticleDto getArticle(@PathVariable("id") Long id) {
        ResponseArticle.GetArticleDto response = articleService.getArticle(id);
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("Task retrieved successfully.")
//                .data(response)
//                .build();
        return response;
    }

    @PutMapping("/update")
    public String updateArticle(@RequestBody RequestArticle.UpdateArticleDto requestDto, HttpServletRequest request) {
        Optional<String> token = jwtAuthTokenProvider.getAuthToken(request);
        articleService.updateArticle(requestDto, token);
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("Task updated successfully.")
//                .build();
        return "Article updated successfully.";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteArticle(@PathVariable("id") Long id, HttpServletRequest request) {
        Optional<String> token = jwtAuthTokenProvider.getAuthToken(request);
        articleService.deleteArticle(id, token);
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("Task deleted successfully.")
//                .build();
        return "Article deleted successfully.";

    }

    @PostMapping(value = "/uploadImg")
    public String uploadImg(@RequestPart(value = "file", required = false) MultipartFile file,
                            @RequestPart(value = "articleId") Long articleId){
        String url = articleService.uploadImg(file, articleId);
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("Image uploaded successfully.")
//                .data(url)
//                .build();
        return url;
    }
}
