package com.example.OAuth_Forum.article.controller;

//import com.example.OAuth_Forum.common.dto.ResponseMessage;
import com.example.OAuth_Forum.article.dto.RequestArticle;
import com.example.OAuth_Forum.article.dto.ResponseArticle;
import com.example.OAuth_Forum.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private final ArticleService articleService;

    @PostMapping("/create")
    public String createArticle(@RequestBody RequestArticle.CreateArticleDto requestDto) {
        articleService.createArticle(requestDto);
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
    public String updateArticle(@RequestBody RequestArticle.UpdateArticleDto requestDto) {
        articleService.updateArticle(requestDto);
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("Task updated successfully.")
//                .build();
        return "Article updated successfully.";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteArticle(id);
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("Task deleted successfully.")
//                .build();
        return "Article deleted successfully.";

    }

    //    @PostMapping("/uploadImg")
//    public ResponseEntity<ResponseMessage> uploadImg(@RequestPart(value = "file", required = false) MultipartFile file,
//                                                     @RequestPart(value = "uploadImgDto") RequestArticle.UploadImgDto dto){
//        String url = articleService.uploadImg(file, dto.getTaskId());
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("Image uploaded successfully.")
//                .data(url)
//                .build();
//        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
//    }
}
