package com.wordroner.wordroner.api.model;

import java.util.List;

/**
 * Created by HeeIll on 2017-02-17.
 */

public class WordList {
    public String metdada;

    @Override
    public String toString() {
        return "WordList{" +
                "metdada='" + metdada + '\'' +
                ", results=" + results +
                '}';
    }

    public static class Word {
        public String id;
        public String language;

        @Override
        public String toString() {
            return "Word{" +
                    "id='" + id + '\'' +
                    ", language='" + language + '\'' +
                    '}';
        }
    }

    public List<Word> results;
}
