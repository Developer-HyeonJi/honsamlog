package com.honsamlog.domain.comment;

import com.honsamlog.common.paging.Pagination;
import com.honsamlog.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    @Transactional
    public Long saveComment(final CommentRequest params) {
        commentMapper.save(params);
        return params.getId();
    }

    public CommentResponse findCommentById(final Long id) {
        return commentMapper.findById(id);
    }

    @Transactional
    public Long updateComment(final CommentRequest params) {
        commentMapper.update(params);
        return params.getId();
    }

    @Transactional
    public Long deleteComment(final Long id) {
        commentMapper.deleteById(id);
        return id;
    }

    public PagingResponse<CommentResponse> findAllComment(final CommentSearchDto params){
        int count = commentMapper.count(params);
        if(count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, params);
        List<CommentResponse> list;
        try {
            list = commentMapper.findAll(params);
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // 또는 return ResponseEntity로 감싸서 에러 응답
        }
        return new PagingResponse<>(list, pagination);

    }

}
