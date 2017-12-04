package com.example.lmachadodefreitas.architecturesample.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import com.example.lmachadodefreitas.architecturesample.ui.activity.MainActivity;

/**
 * Created by l.machado.de.freitas on 02/12/2017.
 */

public class MyLivecycleObserve implements   LifecycleObserver {



    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroyLifecycke() {
        Log.d(MainActivity.class.getSimpleName(), "Lifecycle.Event.ON_DESTROY");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreateLifecycke() {
        Log.d(MainActivity.class.getSimpleName(), "Lifecycle.Event.ON_CREATE");
    }
}
