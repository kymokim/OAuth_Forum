package com.example.OAuth_Forum.article.dto;

import com.example.OAuth_Forum.article.entity.Article;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ResponseArticle {

    @Builder
    @Getter
    public static class GetArticleDto {
        private Long id;
        private LocalDateTime creationDate;
        private String title;
        private String content;

        public static GetArticleDto toDto(Article article){
            return GetArticleDto.builder()
                    .id(article.getId())
                    .creationDate(article.getCreationDate())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class GetAllArticleDto{
        private Long id;
        private LocalDateTime creationDate;
        private String title;
        private String content;

        public static GetAllArticleDto toDto(Article article){
            return GetAllArticleDto.builder()
                    .id(article.getId())
                    .creationDate(article.getCreationDate())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .build();
        }
    }
}

