package com.example.OAuth_Forum.article.entity;

import com.example.OAuth_Forum.comment.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "fix_date")
    private LocalDateTime fixedDate = LocalDateTime.now();

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

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

    public void addComments(Comment comment){
        this.commentList.add(comment);
    }
}
