package com.wordroner.wordroner;

import com.wordroner.wordroner.api.DictApi;
import com.wordroner.wordroner.api.model.WordList;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        WordList words = new DictApi().getWordList("Ace").execute().body();
        System.out.println(words.toString());
    }
}