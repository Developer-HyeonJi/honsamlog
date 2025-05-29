package com.honsamlog.domain.post;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    //게시글 저장
    void save(PostRequest params);
    //게시글 상세 정보 조회
    PostResponse findById(Long id);
    //게시글 수정
    void update(PostRequest params);
    //게시글 삭제
    void deleteById(Long id);
    //게시글 리스트 조회
    List<PostResponse> findAll();
    //게시글 수 카운팅
    //int count();
}
