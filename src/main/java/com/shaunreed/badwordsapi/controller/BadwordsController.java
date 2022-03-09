package com.shaunreed.badwordsapi.controller;


import com.shaunreed.badwordsapi.dto.BadWordsRequest;
import com.shaunreed.badwordsapi.dto.BadwordsResponse;
import com.shaunreed.badwordsapi.service.BadWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/words/v1/")
public class BadwordsController {
    @Autowired
    BadWordsService badwordsService;

    @PostMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findBadWord(@RequestBody BadWordsRequest request) throws Exception {
        ResponseEntity<BadwordsResponse> response = badwordsService.getBadWordResponse(request);
        return  ResponseEntity.ok().body(response);
    }
}
