package com.arbuleac.chuckjokes.joke.model;


import com.arbuleac.chuckjokes.data.Joke;
import com.arbuleac.chuckjokes.service.JokesService;
import com.arbuleac.chuckjokes.service.response.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteChuckModel implements ChuckModel {

    private final JokesService service;
    private JokeListener jokeListener;

    public RemoteChuckModel(JokesService service) {
        this.service = service;
    }

    @Override
    public void attach(JokeListener listener) {
        this.jokeListener = listener;
    }

    @Override
    public void detach(JokeListener listener) {
        this.jokeListener = null;
    }

    @Override
    public void getNextJoke() {
        service.getRandom().enqueue(new Callback<BaseResponse<Joke>>() {
            @Override
            public void onResponse(Call<BaseResponse<Joke>> call, Response<BaseResponse<Joke>> response) {
                if (jokeListener == null) {
                    return;
                }
                BaseResponse<Joke> jokeResponse = response.body();
                if (response.isSuccessful() && jokeResponse != null && "success".equals(jokeResponse.getType())) {
                    jokeListener.onJokeLoaded(jokeResponse.getValue());
                    return;
                }
                jokeListener.onJokeLoadingError("Chuck is tired, try later!");
            }

            @Override
            public void onFailure(Call<BaseResponse<Joke>> call, Throwable t) {
                jokeListener.onJokeLoadingError("Chuck is tired, try later!");
            }
        });
    }
}
