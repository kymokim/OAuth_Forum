package com.example.OAuth_Forum.comment.service;

import com.example.OAuth_Forum.article.dto.ResponseArticle;
import com.example.OAuth_Forum.article.entity.Article;
import com.example.OAuth_Forum.article.repository.ArticleRepository;
import com.example.OAuth_Forum.auth.repository.AuthRepository;
import com.example.OAuth_Forum.auth.security.JwtAuthToken;
import com.example.OAuth_Forum.auth.security.JwtAuthTokenProvider;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final AuthRepository authRepository;

    public void saveComment(RequestComment.SaveCommentDto saveCommentDto, Optional<String> token) {

        String email = null;
        if (token.isPresent()) {
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            email = jwtAuthToken.getClaims().getSubject();
        }
        String writer = authRepository.findByEmail(email).getUsername();
        Article article = articleRepository.findById(saveCommentDto.getArticleId()).get();
        if (article == null){
            throw new EntityNotFoundException();
        }
        Comment comment = RequestComment.SaveCommentDto.toEntity(saveCommentDto, article, writer);
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

    public void updateComment(RequestComment.UpdateCommentDto updateCommentDto, Optional<String> token) {

        String email = null;
        if (token.isPresent()) {
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            email = jwtAuthToken.getClaims().getSubject();
        }
        String writer = authRepository.findByEmail(email).getUsername();

        Comment originalComment = commentRepository.findById(updateCommentDto.getId()).get();
        if (originalComment.getCommentWriter().equals(writer)) {
            Comment updatedComment = RequestComment.UpdateCommentDto.toEntity(originalComment, updateCommentDto);
            commentRepository.save(updatedComment);
            Article article = articleRepository.findById(updatedComment.getArticle().getId()).get();
            article.setFixedDate(LocalDateTime.now());
            articleRepository.save(article);
        }
        else throw new RuntimeException();
    }

    public void deleteComment(Long id, Optional<String> token) {

        String email = null;
        if (token.isPresent()) {
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            email = jwtAuthToken.getClaims().getSubject();
        }
        String writer = authRepository.findByEmail(email).getUsername();
        Comment comment = commentRepository.findById(id).get();
        if (comment.getCommentWriter().equals(writer)) {
            commentRepository.delete(comment);
        }
    }

}
