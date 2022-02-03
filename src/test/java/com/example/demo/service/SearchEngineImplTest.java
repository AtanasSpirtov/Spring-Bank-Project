package com.example.demo.service;

import com.example.demo.service.SearchEngine.SearchEngineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SearchEngineImplTest {

    @Autowired
    SearchEngineService searchEngineService;

    @Test
    void searchByKeyWordExact() {
        List<String> expected = new ArrayList<>();
        expected.add("accounts");
        assertThat(expected).isEqualTo(searchEngineService.searchByKeyWord("accounts"));
    }

    @Test
    void searchByKeyWordWithOneMistake() {
        List<String> expected = new ArrayList<>();
        expected.add("accounts");
        assertThat(expected).isEqualTo(searchEngineService.searchByKeyWord("ackounts"));
    }

    @Test
    void searchByKeyWordWithTwoMistakes() {
        List<String> expected = new ArrayList<>();
        expected.add("accounts");
        assertThat(expected).isEqualTo(searchEngineService.searchByKeyWord("ackounps"));
    }
    @Test
    void searchByKeyWordWithManyMistakes()
    {
        List<String> expected = new ArrayList<>();
        assertThat(searchEngineService.searchByKeyWord("akkuunps")).isEqualTo(expected);
        assertThat(searchEngineService.searchByKeyWord("akkounps")).isEqualTo(expected);
    }
    @Test
    void searchWithEmptyInput()
    {
        List<String> expected = new ArrayList<>();
        assertThat(searchEngineService.searchByKeyWord("")).isEqualTo(expected);
    }

}
