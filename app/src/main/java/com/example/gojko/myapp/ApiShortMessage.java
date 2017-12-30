package com.example.gojko.myapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiShortMessage {
    String BASE_URL = "https://short-messages-web-api.azurewebsites.net/api/ShortMessages/";

    @GET("{consumerKey}")
    Call<Messages> getShortMessage(@Path("consumerKey") String consumerKey);
}
