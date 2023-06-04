package com.example.OAuth_Forum.article.dto;

import com.example.OAuth_Forum.article.entity.Article;
import com.example.OAuth_Forum.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResponseArticle {

    @Builder
    @Getter
    public static class GetArticleDto {
        private Long id;
        private LocalDateTime creationDate;
        private String title;
        private String content;
        private List<CommentListDto> commentList;

        public static GetArticleDto toDto(Article article){
            List<CommentListDto> commentList = new ArrayList<>();
            if (!article.getCommentList().isEmpty())
//                commentList.addAll(article.getCommentList());
                article.getCommentList().stream().forEach(comment -> commentList.add(CommentListDto.toDto(comment)));
            return GetArticleDto.builder()
                    .id(article.getId())
                    .creationDate(article.getCreationDate())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .commentList(commentList)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class CommentListDto{
        private Long id;
        private LocalDateTime creationDate;
        private String writer;
        private String content;

        public static CommentListDto toDto(Comment comment){
            return CommentListDto.builder()
                    .id(comment.getId())
                    .creationDate(comment.getCreationDate())
                    .writer(comment.getCommentWriter())
                    .content(comment.getCommentContents())
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

