package com.example.lmachadodefreitas.architecturesample.ui.activity;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.lmachadodefreitas.architecturesample.R;
import com.example.lmachadodefreitas.architecturesample.model.Species;
import com.example.lmachadodefreitas.architecturesample.ui.MyLivecycleObserve;
import com.example.lmachadodefreitas.architecturesample.ui.adapter.SpeciesAdapter;
import com.example.lmachadodefreitas.architecturesample.viewmodel.SpeciesViewModel;

public class MainActivity extends AppCompatActivity implements LifecycleOwner ,
        LifecycleObserver,SwipeRefreshLayout.OnRefreshListener {

    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private SpeciesViewModel viewModel;
    private MyLivecycleObserve observe;
    private ListView lstSpecies;

    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(SpeciesViewModel.class);
        viewModel.init(false);
        lstSpecies = (ListView) findViewById(R.id.lstFilms);


        observe = new  MyLivecycleObserve();
        subscribeUi(viewModel);
        getLifecycle().addObserver(observe);

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    /**
     * It must be overriden by parent classes if manual swipe is enabled.
     */
    @Override
    public void onRefresh() {
        viewModel.init(true);
        subscribeUi(viewModel);
        getLifecycle().addObserver(observe);
    }

    private void subscribeUi(SpeciesViewModel  viewModel) {
        // Update the list when the data changes
        viewModel.getSpecies().observe(this, new Observer<Species>() {
            @Override
            public void onChanged(@Nullable Species  species) {
                if (species != null) {
                    lstSpecies.setAdapter(new SpeciesAdapter(getApplicationContext(), species.results));
                    swipeLayout.setRefreshing(false);
                }

            }
        });
    }


}
