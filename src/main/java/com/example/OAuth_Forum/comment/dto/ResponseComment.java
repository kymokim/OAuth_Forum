package com.example.OAuth_Forum.comment.dto;

import com.example.OAuth_Forum.article.dto.ResponseArticle;
import com.example.OAuth_Forum.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ResponseComment {

    @Builder
    @Getter
    public static class GetCommentDto {
        private Long id;
        private LocalDateTime creationDate;
        private String commentWriter;
        private String commentContents;
        private Long articleId;

        public static GetCommentDto toDto(Comment comment) {
            return GetCommentDto.builder()
                    .id(comment.getId())
                    .creationDate(comment.getCreationDate())
                    .commentWriter(comment.getCommentWriter())
                    .commentContents(comment.getCommentContents())
                    .articleId(comment.getArticle().getId())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class GetAllCommentDto {
        private Long id;
        private LocalDateTime creationDate;
        private String commentWriter;
        private String commentContents;
        private Long articleId;

        public static GetAllCommentDto toDto(Comment comment){
            return GetAllCommentDto.builder()
                    .id(comment.getId())
                    .creationDate(comment.getCreationDate())
                    .commentWriter(comment.getCommentWriter())
                    .commentContents(comment.getCommentContents())
                    .articleId(comment.getArticle().getId())
                    .build();
        }

    }

    @Builder
    @Getter
    public static class articleIdCommentDto {
        private Long id;
        private LocalDateTime creationDate;
        private String commentWriter;
        private String commentContents;

        public static articleIdCommentDto toDto(Comment comment){
            return articleIdCommentDto.builder()
                    .id(comment.getId())
                    .creationDate(comment.getCreationDate())
                    .commentWriter(comment.getCommentWriter())
                    .commentContents(comment.getCommentContents())
                    .build();
        }

    }
}
