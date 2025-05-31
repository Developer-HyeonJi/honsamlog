package com.honsamlog.domain.comment;

import com.honsamlog.common.dto.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSearchDto extends SearchDto {
    private Long postId;
}
