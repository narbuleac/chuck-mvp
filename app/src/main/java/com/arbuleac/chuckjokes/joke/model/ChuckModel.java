package com.arbuleac.chuckjokes.joke.model;


import com.arbuleac.chuckjokes.data.Joke;

public interface ChuckModel {

    void attach(JokeListener listener);

    void detach(JokeListener listener);

    void getNextJoke();

    interface JokeListener {
        void onJokeLoaded(Joke joke);

        void onJokeLoadingError(String message);
    }
}
