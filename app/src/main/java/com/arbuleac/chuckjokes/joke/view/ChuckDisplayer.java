package com.arbuleac.chuckjokes.joke.view;


import com.arbuleac.chuckjokes.data.Joke;

public interface ChuckDisplayer {

    void attach(ActionListener listener);

    void detach(ActionListener listener);

    void showJoke(Joke joke);

    void showLoading();

    void hideLoading();

    void showError(String error);

    interface ActionListener {
        void onNextSelected();
    }

}
