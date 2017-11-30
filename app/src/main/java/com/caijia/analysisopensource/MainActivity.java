package com.caijia.analysisopensource;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResponse;

    private TestLifeCycle testLifeCycle;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResponse = findViewById(R.id.tv_response);

        testLifeCycle = new TestLifeCycle();
        getLifecycle().addObserver(testLifeCycle);

        imageView = findViewById(R.id.shape_view);

    }

    public void onRequest(View view) {
        tvResponse.setText("");
        imageView.setImageResource(R.drawable.ic_launcher_background);
    }

    private class TestLifeCycle implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStart() {
            Log.d("lifecycle", "onStart");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop() {
            Log.d("lifecycle", "onStop");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause() {
            Log.d("lifecycle", "onPause");
        }
    }

}
