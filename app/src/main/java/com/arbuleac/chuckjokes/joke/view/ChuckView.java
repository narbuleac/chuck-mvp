package com.arbuleac.chuckjokes.joke.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arbuleac.chuckjokes.R;
import com.arbuleac.chuckjokes.data.Joke;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChuckView extends LinearLayout implements ChuckDisplayer {

    @BindView(R.id.joke)
    TextView joke;
    @BindView(R.id.loading)
    ProgressBar loading;
    private ActionListener actionListener;

    public ChuckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.merge_joke_view, this);
        ButterKnife.bind(this);
    }

    @Override
    public void attach(ActionListener listener) {
        this.actionListener = listener;
    }

    @Override
    public void detach(ActionListener listener) {
        this.actionListener = null;
    }

    @Override
    public void showJoke(Joke joke) {
        this.joke.setText(joke.getJoke());
    }

    @Override
    public void showLoading() {
        this.loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.loading.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.next)
    void onNextClicked() {
        if (actionListener != null) {
            actionListener.onNextSelected();
        }
    }
}
