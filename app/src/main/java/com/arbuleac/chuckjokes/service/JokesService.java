package com.arbuleac.chuckjokes.service;


import com.arbuleac.chuckjokes.data.Joke;
import com.arbuleac.chuckjokes.service.response.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JokesService {

    @GET("/jokes/random")
    Call<BaseResponse<Joke>> getRandom();
}
