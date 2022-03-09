package com.shaunreed.badwordsapi.service;

import com.shaunreed.badwordsapi.constants.Messages;
import com.shaunreed.badwordsapi.dto.BadWordsRequest;
import com.shaunreed.badwordsapi.dto.BadwordsResponse;
import com.shaunreed.badwordsapi.repository.BadWordsEntity;
import com.shaunreed.badwordsapi.repository.BadWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class BadWordsService implements Messages {
    @Autowired
    BadWordsRepository badWordsRepository;



    public ResponseEntity<BadwordsResponse> getBadWordResponse(BadWordsRequest badwordsRequest) {
        BadwordsResponse response = new BadwordsResponse();

        if (!Objects.isNull(badwordsRequest)) {
            if (!Objects.isNull(badwordsRequest.getRequestMessage())) {
                if (badwordsRequest.getRequestMessage().isEmpty()) {
                    response.setResponseMessage(NO_WORD_MSG);
                    return ResponseEntity.badRequest().body(response);
                } else {
                    response.setResponseMessage(GOOD_WORD_MSG);

                    List<BadWordsEntity> badWordsEntityList = badWordsRepository.findAll();

                    if (badWordsEntityList.size() == 0) {
                        response.setResponseMessage(NO_WORDS_IN_DB);
                        return ResponseEntity.internalServerError().body(response);
                    }

                    for (BadWordsEntity entity: badWordsEntityList) {
                        Boolean doesContainExactWord = doesContainWord(badwordsRequest.getRequestMessage(), entity.getWord());
                        if (doesContainExactWord.booleanValue()) {
                            response.setResponseMessage(BAD_WORD_MSG + " Word used: (" + entity.getWord() + ") ");
                            break;
                        }
                    }

                    return ResponseEntity.ok().body(response);
                }
            }

            response.setResponseMessage(NULL_WORD_MSG);
            return ResponseEntity.badRequest().body(response);
        }

        response.setResponseMessage(EMPTY_REQUEST_MSG);
        return ResponseEntity.badRequest().body(response);
    }

    private Boolean doesContainWord(String requestMessage, String wordFromDb) {
        if (wordFromDb.length() == 1) {
            return requestMessage.contains(wordFromDb);
        }

        return Pattern.compile("\\b"+ wordFromDb +"\\b").matcher(requestMessage).find() ||
                Pattern.compile(".*\\b" + wordFromDb +"\\b.*").matcher(requestMessage).find();
    }

}
