package com.example.OAuth_Forum.comment.entity;

import com.example.OAuth_Forum.article.entity.Article;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "comment")
@Entity
@Getter
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long articleId;

    private String commentWriter;

    private LocalDateTime creationDate = LocalDateTime.now();

    private String commentContents;

    @Builder
    public Comment(String commentWriter, String commentContents, Long articleId){ // 얜 왜 필요함??
        this.commentWriter = commentWriter;
        this.commentContents = commentContents;
        this.articleId = articleId;
    }

    public void update(String commentWriter, String commentContents, Long articleId){
        this.commentWriter = commentWriter;
        this.commentContents = commentContents;
        this.articleId = articleId;
    }

}
