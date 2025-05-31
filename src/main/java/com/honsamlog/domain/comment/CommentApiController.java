package com.honsamlog.domain.comment;


import com.honsamlog.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    //신규 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public CommentResponse saveComment(@PathVariable("postId") final Long postId, @RequestBody final CommentRequest params) {
        Long id = commentService.saveComment(params);
        return commentService.findCommentById(id);
    }

    //댓글 리스트 조회
    @GetMapping("/posts/{postId}/comments")
    public PagingResponse<CommentResponse> findAllComment(@PathVariable("postId") final Long postId, final CommentSearchDto params) {
        return commentService.findAllComment(params);
    }

    //댓글 상세정보 조회
    @GetMapping("/posts/{postId}/comments/{id}")
    public CommentResponse findCommentById(@PathVariable("postId") final Long postId, @PathVariable("id") final Long id) {
        return commentService.findCommentById(id);
    }

    //기존 댓글 수정
    @PatchMapping("/posts/{postId}/comments/{id}")
    public CommentResponse updateComment(@PathVariable("postId") final Long postId, @PathVariable("id") final Long id, @RequestBody final CommentRequest params) {
        commentService.updateComment(params);
        return commentService.findCommentById(id);
    }

    //댓글 삭제
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public Long deleteComment(@PathVariable("postId") final Long postId, @PathVariable("id") final Long id) {
        return commentService.deleteComment(id);
    }
}
