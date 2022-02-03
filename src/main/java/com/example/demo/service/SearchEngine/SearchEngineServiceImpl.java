package com.example.demo.service.SearchEngine;

import com.example.demo.service._BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchEngineServiceImpl extends _BaseService implements SearchEngineService {

    @Override
    public List<String> searchByKeyWord(String word) {
        List<String> wordsInDatabase;
        List<String> possibleWords = new ArrayList<>();
        wordsInDatabase =  em.createNativeQuery("Select words from key_words_for_search_engine").getResultList();

        wordsInDatabase.parallelStream().forEach(wordInDatabase -> {
            int wordMistakes = 0;
            for (int j = 0; j < wordInDatabase.length(); j++) {
                if(word.length() != wordInDatabase.length())
                {
                    //makes word not to be added to possibleWords
                    wordMistakes = 3;
                    break;
                }
                if (wordInDatabase.charAt(j) != word.charAt(j) && wordMistakes++ >= 2) {
                    break;
                }
            }
            if (wordMistakes <= 2) {
                possibleWords.add(wordInDatabase);
            }
        });
        return possibleWords;
    }
}
