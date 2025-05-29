package com.honsamlog.domain.post;

import com.honsamlog.common.dto.SearchDto;
import com.honsamlog.common.paging.Pagination;
import com.honsamlog.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    @Transactional
    public Long savePost(final PostRequest params) {
        postMapper.save(params);
        return params.getId();
    }

    public PostResponse findPostById(final Long id) {
        return postMapper.findById(id);
    }

    @Transactional
    public Long updatePost(final PostRequest params) {
        postMapper.update(params);
        return params.getId();
    }

    public Long deletePost(final Long id) {
        postMapper.deleteById(id);
        return id;
    }

    public PagingResponse<PostResponse> findAllPost(final SearchDto params) {
        int count = postMapper.count(params);
        //조건에 맞는 데이터가 없는 경우, 비어있는 리스트와 null을 담아 전송
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        //pagination 객체를 ㄹ생성해서 정보 계산 후 pramas에 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        //계산된 페이지 정보의 일부를 list 데이터 조회 후 반환
        List<PostResponse> list = postMapper.findAll(params);
        return new PagingResponse<>(list, pagination);


    }
}

