package com.dagger.base;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.dagger.R;
import com.dagger.di.Injector;
import com.dagger.di.ScreenInjector;
import com.dagger.ui.ScreenNavigator;

import java.util.UUID;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    private String instanceId;
    private Router router;

    private static String INSTANCE_ID_KEY = "instance_id";

    @Inject ScreenInjector screenInjector;
    @Inject ScreenNavigator screenNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        } else {
            instanceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);
        setContentView(layoutRes());

        ViewGroup screenContainer = findViewById(R.id.screen_container);
        if (screenContainer == null)
            throw new IllegalArgumentException("Activity must have a view with Id: screen_container");

        router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
        screenNavigator.initWithRouter(router, initialScreen());
        monitorBackStack();
        super.onCreate(savedInstanceState);
    }


    @LayoutRes
    protected abstract int layoutRes();

    protected abstract Controller initialScreen();

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY, instanceId);
    }

    @Override
    public void onBackPressed() {
        if(!screenNavigator.pop()){
            super.onBackPressed();

        }
    }

    public String getInstanceId() {
        return instanceId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        screenNavigator.clear();
        if (isFinishing()) {
            Injector.clearComponent(this);
        }
    }

    public ScreenInjector getScreenInjector() {
        return screenInjector;

    }




    private void monitorBackStack() {
        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(
                    @Nullable Controller to,
                    @Nullable Controller from,
                    boolean isPush,
                    @NonNull ViewGroup container,
                    @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(
                    @Nullable Controller to,
                    @Nullable Controller from,
                    boolean isPush,
                    @NonNull ViewGroup container,
                    @NonNull ControllerChangeHandler handler) {

                if(!isPush && from != null){
                    Injector.clearComponent(from);
                }
            }
        });
    }
}
