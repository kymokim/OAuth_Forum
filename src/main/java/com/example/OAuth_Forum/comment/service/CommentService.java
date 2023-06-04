package com.example.OAuth_Forum.comment.service;

import com.example.OAuth_Forum.article.dto.ResponseArticle;
import com.example.OAuth_Forum.article.entity.Article;
import com.example.OAuth_Forum.article.repository.ArticleRepository;
import com.example.OAuth_Forum.comment.dto.RequestComment;
import com.example.OAuth_Forum.comment.dto.ResponseComment;
import com.example.OAuth_Forum.comment.entity.Comment;
import com.example.OAuth_Forum.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public void saveComment(RequestComment.SaveCommentDto saveCommentDto) {

        Article article = articleRepository.findById(saveCommentDto.getArticleId()).get();
        if (article == null){
            throw new EntityNotFoundException();
        }
        Comment comment = RequestComment.SaveCommentDto.toEntity(saveCommentDto, article);
        commentRepository.save(comment);
        article.addComments(comment);

        // 게시글의 시간을 댓글 수정 시간으로 변경
        article.setFixedDate(LocalDateTime.now());
        articleRepository.save(article);
    }

    public List<ResponseComment.GetAllCommentDto> getAllComment() {
        List<Comment> tasks = commentRepository.findAll();
        List<ResponseComment.GetAllCommentDto> list = new ArrayList<>();
        tasks.stream().forEach(task -> list.add(ResponseComment.GetAllCommentDto.toDto(task)));
        return list;
    }

    public ResponseComment.GetCommentDto getComment(Long id){

        Comment comment = commentRepository.findById(id).get();
        return ResponseComment.GetCommentDto.toDto(comment);
    }

    public List<ResponseComment.articleIdCommentDto> getByAId(Long articleId){

        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        List<ResponseComment.articleIdCommentDto> dtoList = new ArrayList<>();
        comments.stream().forEach(comment -> dtoList.add(ResponseComment.articleIdCommentDto.toDto(comment)));
        return dtoList;
    }

    public void updateComment(RequestComment.UpdateCommentDto updateCommentDto) {

        Comment originalComment = commentRepository.findById(updateCommentDto.getId()).get();
        Comment updatedComment = RequestComment.UpdateCommentDto.toEntity(originalComment, updateCommentDto);
        commentRepository.save(updatedComment);

        Article article = articleRepository.findById(updatedComment.getArticle().getId()).get();
        article.setFixedDate(LocalDateTime.now());
        articleRepository.save(article);
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).get();
        commentRepository.delete(comment);
    }

}
