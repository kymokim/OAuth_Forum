package com.example.OAuth_Forum.article.repository;

import com.example.OAuth_Forum.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByFixedDateDesc();
}