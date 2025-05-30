package com.honsamlog.domain.comment;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {
    private Long id;
    private Long postId;
    private String content;
    private String writer;
}
