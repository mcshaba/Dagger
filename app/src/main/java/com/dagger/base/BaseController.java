package com.dagger.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;
import com.dagger.di.Injector;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseController extends Controller {

    private final CompositeDisposable disposable = new CompositeDisposable(); // add multiple

    private boolean injected = false;
    private Unbinder unbinder;

    @Override
    protected void onContextAvailable(@NonNull Context context) {

        //Controller instances are retained across config changes, so this method can be called more than once. this makes
        //sure we don't waste any time injecting more than once, though technically it wouldn't change functionality.
        if(!injected){
            Injector.inject(this);
            injected = true;
        }
        super.onContextAvailable(context);
    }


    @NonNull
    @Override
    protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container){
        View view = inflater.inflate(layoutRes(), container, false);

        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        disposable.addAll(subscriptions());
        return view;
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        disposable.clear();
        if(unbinder != null){
            unbinder.unbind();
            unbinder = null;
        }
        super.onDestroyView(view);
    }

    protected  void onViewBound(View view){

    }

    protected Disposable[] subscriptions(){
        return new Disposable[0];
    }

    @LayoutRes
    protected abstract int layoutRes();
}
