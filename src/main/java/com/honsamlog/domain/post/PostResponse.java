package com.honsamlog.domain.post;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private int viewCnt;
    private Boolean noticeYn;
    private Boolean DeleteYn;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
