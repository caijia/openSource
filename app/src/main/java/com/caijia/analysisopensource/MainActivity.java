package com.caijia.analysisopensource;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.caijia.analysisopensource.sample.viewmodel.TestViewModel;
import com.caijia.analysisopensource.sample.callback.ApiView;
import com.caijia.analysisopensource.sample.service.model.Response;
import com.caijia.analysisopensource.sample.service.model.TestResult;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ApiView {

    TextView tvResponse;
    Gson gson;

    private TestLifeCycle testLifeCycle;
    private  TestViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new Gson();
        tvResponse = findViewById(R.id.tv_response);

        testLifeCycle = new TestLifeCycle();
        getLifecycle().addObserver(testLifeCycle);

        viewModel = ViewModelProviders.of(this).get(TestViewModel.class);

    }

    public void onRequest(View view) {
        tvResponse.setText("");
        viewModel.getData().observe(this, listResponse -> {
            Log.d("lifecycle",gson.toJson(listResponse));
            tvResponse.setText(gson.toJson(listResponse));
        });
    }

    @Override
    public void onShowData(@Nullable Response<List<TestResult>> result) {
        tvResponse.setText(gson.toJson(result));
    }

    private class TestLifeCycle implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStart(){
            Log.d("lifecycle", "onStart");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop(){
            Log.d("lifecycle", "onStop");
        }

    }

}
