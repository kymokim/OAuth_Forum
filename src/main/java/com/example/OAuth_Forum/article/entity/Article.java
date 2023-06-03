package com.example.OAuth_Forum.article.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "article")
@Entity
@Getter
@NoArgsConstructor
@Data
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

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
}
