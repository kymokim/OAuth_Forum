package com.example.OAuth_Forum.article.service;

import com.example.OAuth_Forum.article.dto.ResponseArticle;
import com.example.OAuth_Forum.article.repository.ArticleRepository;
//import com.example.OAuth_Forum.common.exception.error.NotFoundTaskException;
//import com.example.OAuth_Forum.common.service.S3Service;
import com.example.OAuth_Forum.article.dto.RequestArticle;
import com.example.OAuth_Forum.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService { // 오류 던지긴 하는데 안 날라옴

    private final ArticleRepository articleRepository;
//    private final S3Service s3Service;

    public void createArticle(RequestArticle.CreateArticleDto createArticleDto){

        Article article = RequestArticle.CreateArticleDto.toEntity(createArticleDto);
        articleRepository.save(article);
    }

    public List<ResponseArticle.GetAllArticleDto> getAllArticle(){

        List<Article> tasks = articleRepository.findAll();
        List<ResponseArticle.GetAllArticleDto> list = new ArrayList<>();
        tasks.stream().forEach(task -> list.add(ResponseArticle.GetAllArticleDto.toDto(task)));
        return list;
    }

    public ResponseArticle.GetArticleDto getArticle(Long id){

        Article article = articleRepository.findById(id).get();
        return ResponseArticle.GetArticleDto.toDto(article);
    }

    public void updateArticle(RequestArticle.UpdateArticleDto updateArticleDto) {

        Article originalArticle = articleRepository.findById(updateArticleDto.getId()).get();
//        if(originalArticle == null) { throw new NotFoundTaskException(); }
        Article updatedArticle = RequestArticle.UpdateArticleDto.toEntity(originalArticle, updateArticleDto);
        articleRepository.save(updatedArticle);
    }


    public void deleteArticle(Long id) {

        Article article = articleRepository.findById(id).get();
        articleRepository.delete(article);
    }

    //    public String uploadImg(MultipartFile file, long taskId){
//        Task article = taskRepository.findById(taskId).get();
//
////        if (!article.getImgUrl().isEmpty())
////            s3Service.deleteFile(article.getImgUrl());
//
//        String url = "";
//        try {
//            url = s3Service.upload(file,"article");
//        }
//        catch (IOException e){
//            System.out.println("S3 upload failed.");
//        }
//
//        article.setImgUrl(url);
//        taskRepository.save(article);
//        return url;
//    }
}
