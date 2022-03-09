package com.shaunreed.badwordsapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BadWordsRequest implements Serializable {
    private String requestMessage;
}
