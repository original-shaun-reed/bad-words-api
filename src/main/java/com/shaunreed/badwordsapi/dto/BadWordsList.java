package com.shaunreed.badwordsapi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BadWordsList implements Serializable {
    List<String> words;
}
