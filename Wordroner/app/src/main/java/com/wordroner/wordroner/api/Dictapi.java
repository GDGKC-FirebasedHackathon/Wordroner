package com.wordroner.wordroner.api;

import com.wordroner.wordroner.api.model.WordList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by HeeIll on 2017-02-17.
 */

public class DictApi {

    public Retrofit makeRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://od-api.oxforddictionaries.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<WordList> getWordList(String keyword) {
        return makeRetrofit().create(Api.class).findKeyworkd(keyword);
    }

    interface Api {

        @Headers(value = {
                "Accept:application/json",
                "app_id:32d2d75c",
                "app_key:24f2eb16bf1cf1e81751f567a02dc2c7",
        })
        @GET("entries/en/{word_id}")
        Call<WordList> findKeyworkd(@Path("word_id")String keyword);
    }
}
