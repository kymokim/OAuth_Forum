package com.example.OAuth_Forum.article.dto;

import com.example.OAuth_Forum.article.entity.Article;
import lombok.Builder;
import lombok.Data;

//import javax.validation.constraints.NotEmpty;

public class RequestArticle { // NotNull 적긴 했는데 오류가 안 날라감
    @Data
    @Builder
    public static class CreateArticleDto {
//        @NotEmpty(message = "No title entered.")
        private String title;
//        @NotEmpty(message = "No content entered.")
        private String content;

        public static Article toEntity(CreateArticleDto createArticleDto){
            return Article.builder()
                    .title(createArticleDto.getTitle())
                    .content(createArticleDto.getContent())
                    .build();
        }
    }


    @Data
    @Builder
    public static class UpdateArticleDto {
//        @NotEmpty(message = "No article id entered.")
        private Long id;
//        @NotEmpty(message = "No title entered.")
        private String title;
//        @NotEmpty(message = "No content entered.")
        private String content;

        public static Article toEntity(Article article, UpdateArticleDto updateArticleDto){
            article.update(updateArticleDto.getTitle(), updateArticleDto.getContent());
            return article;
        }
    }


    //    @Data
//    @Builder
//    public static class UploadImgDto{
//        private Long taskId;
//        private Long usrId;
//    }
}
