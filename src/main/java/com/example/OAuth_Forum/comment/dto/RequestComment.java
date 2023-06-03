package com.example.OAuth_Forum.comment.dto;

import com.example.OAuth_Forum.comment.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class RequestComment {

    @Data
    @Builder
    public static class SaveCommentDto {

        private String commentWriter;
        private String commentContents;
        private Long articleId;

        public static Comment toEntity(SaveCommentDto saveCommentDto) {
            return Comment.builder()
                    .commentWriter(saveCommentDto.getCommentWriter())
                    .commentContents(saveCommentDto.getCommentContents())
                    .articleId(saveCommentDto.getArticleId())
                    .build();
        }
    }

    @Data
    @Builder
    public static class UpdateCommentDto{
        private Long id;
        private String commentWriter;
        private String commentContents;
        private Long articleId;

        public static Comment toEntity(Comment comment, UpdateCommentDto updateCommentDto) {
            comment.update(
                      updateCommentDto.getCommentWriter()
                    , updateCommentDto.getCommentContents()
                    , updateCommentDto.getArticleId()
            );
            return comment;
        }
    }

}
