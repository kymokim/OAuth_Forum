package com.example.OAuth_Forum.comment.entity;

import com.example.OAuth_Forum.article.entity.Article;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "writer")
    private String commentWriter;
    @Column(name = "content")
    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Builder
    public Comment(String commentWriter, String commentContents, Article article){ // 얜 왜 필요함??
        this.commentWriter = commentWriter;
        this.commentContents = commentContents;
        this.article = article;
    }

    public void update(String commentContents){
        this.commentContents = commentContents;
    }

}
