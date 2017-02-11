package com.arbuleac.chuckjokes.joke.presenter;


import com.arbuleac.chuckjokes.data.Joke;
import com.arbuleac.chuckjokes.joke.model.ChuckModel;
import com.arbuleac.chuckjokes.joke.view.ChuckDisplayer;

public class ChuckPresenter {

    private final ChuckDisplayer displayer;
    private final ChuckModel model;

    public ChuckPresenter(ChuckDisplayer displayer, ChuckModel model) {
        this.displayer = displayer;
        this.model = model;
    }

    public void startPresenting() {
        this.displayer.attach(actionListener);
        this.model.attach(jokeListener);
    }

    public void stopPresenting() {
        this.displayer.detach(actionListener);
        this.model.detach(jokeListener);
    }

    private ChuckDisplayer.ActionListener actionListener = new ChuckDisplayer.ActionListener() {
        @Override
        public void onNextSelected() {
            displayer.showLoading();
            model.getNextJoke();
        }
    };

    private ChuckModel.JokeListener jokeListener = new ChuckModel.JokeListener() {
        @Override
        public void onJokeLoaded(Joke joke) {
            displayer.hideLoading();
            displayer.showJoke(joke);
        }

        @Override
        public void onJokeLoadingError(String message) {
            displayer.hideLoading();
            displayer.showError(message);
        }
    };
}
