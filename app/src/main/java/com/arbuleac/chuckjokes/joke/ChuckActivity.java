package com.arbuleac.chuckjokes.joke;

import android.os.Bundle;

import com.arbuleac.chuckjokes.BaseActivity;
import com.arbuleac.chuckjokes.R;
import com.arbuleac.chuckjokes.injection.Injector;
import com.arbuleac.chuckjokes.joke.model.ChuckModel;
import com.arbuleac.chuckjokes.joke.model.RemoteChuckModel;
import com.arbuleac.chuckjokes.joke.presenter.ChuckPresenter;
import com.arbuleac.chuckjokes.joke.view.ChuckDisplayer;
import com.arbuleac.chuckjokes.service.JokesService;

public class ChuckActivity extends BaseActivity {

    private ChuckPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_view);

        ChuckDisplayer displayer = (ChuckDisplayer) findViewById(R.id.chuck_view);
        ChuckModel model = new RemoteChuckModel(Injector.obtain(JokesService.class));
        presenter = new ChuckPresenter(displayer, model);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startPresenting();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopPresenting();
    }
}
