package com.honsamlog.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@Getter
//Lombok 라이브러리에서 제공하는 기능
@AllArgsConstructor
public class MessageDto {
    private String message;
    private String redirectUri;
    private RequestMethod method;
    private Map<String, Object> data;
}
