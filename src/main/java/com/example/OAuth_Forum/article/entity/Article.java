package com.example.OAuth_Forum.article.entity;

import com.example.OAuth_Forum.comment.entity.Comment;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "article")
@Entity
@Getter
@NoArgsConstructor
@Data
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private LocalDateTime fixedDate = LocalDateTime.now();

    private LocalDateTime creationDate = LocalDateTime.now();

    private String title;

    private String content;

//    private String imgUrl;

    @Builder
    public Article(String title, String content){ // 얜 왜 필요함??
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setFixeDate(LocalDateTime fixedDate) {
        this.fixedDate = fixedDate;
    }

}
