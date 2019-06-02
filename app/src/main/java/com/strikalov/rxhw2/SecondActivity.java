package com.strikalov.rxhw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SecondActivity extends AppCompatActivity {

    private static String TAG = "MyLogger";

    private Observable<String> observable;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        observable = Observable.just("Hello world");
    }

    @OnClick(R.id.button_spam)
    void onClickSpamButton(){
        Log.i(TAG, "onClickSpamButton()");

        disposable = observable.subscribe(
                string -> {
                    Log.i(TAG, string);
                },
                throwable -> {
                    Log.i(TAG, "OnError: " + throwable);
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable != null){
            disposable.dispose();
        }
    }
}
