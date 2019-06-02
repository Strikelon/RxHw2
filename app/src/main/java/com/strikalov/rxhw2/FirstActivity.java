package com.strikalov.rxhw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FirstActivity extends AppCompatActivity {

    private static String TAG = "MyLogger";

    private Observable<Long> observable;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        observable = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io());
    }

    @OnClick(R.id.button_subscribe)
    void onSubscribeClick(){
        Log.i(TAG, "onSubscribeClick");

        disposable = observable.subscribe(
                value -> {
                    Log.i(TAG, "OnNext" + value + " Thread: " + Thread.currentThread().getName() );
                },
                throwable -> {
                    Log.e(TAG, "OnError" + throwable);
                },
                () -> {
                    Log.e(TAG, "OnComplete");
                }
        );


    }

    @OnClick(R.id.button_unsubscribe)
    void onUnSubscribeClick(){
        Log.i(TAG, "onUnSubscribeClick");

        if(disposable != null){
            disposable.dispose();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable != null){
            disposable.dispose();
        }
    }
}
