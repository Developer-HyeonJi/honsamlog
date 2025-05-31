package com.honsamlog.domain.comment;

import org.apache.ibatis.annotations.Mapper;

import javax.xml.stream.events.Comment;
import java.util.List;

@Mapper
public interface CommentMapper {
    //댓글 저장
    void save(CommentRequest params);
    //댓글 상세정보 조회
    CommentResponse findById(Long id);
    //댓글 수정
    void update(CommentRequest params);
    //댓글 삭제
    void deleteById(Long id);
    //댓글 리스트 조회
    List<CommentResponse> findAll (CommentSearchDto params);
    //댓글 수 카운팅
    int count(CommentSearchDto params);
}
