package com.example.OAuth_Forum.article.repository;

import com.example.OAuth_Forum.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
//    List<Task> findByTaskOrderGreaterThanEqual(Long taskOrder);
//    @Query("SELECT MAX(t.taskOrder) FROM Task t")
//    Optional<Long> findMaxTaskOrder();

}