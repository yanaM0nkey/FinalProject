package com.gmail.ioanna.finalapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gmail.ioanna.finalapp.base.BaseAppCompatActivity;
import com.gmail.ioanna.finalapp.databinding.ActivityMainBinding;


public class MainActivity extends BaseAppCompatActivity {

    private int count;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MainViewModel viewModel = new MainViewModel(this);
        this.viewModel = viewModel;
        binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);

        binding.setModel(viewModel);
        setSupportActionBar(binding.toolbar);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(viewModel.adapter);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        count = binding.recyclerView.getAdapter().getItemCount();
        Log.e("main_create", String.valueOf(count));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, CreateActivity.class);
        Log.e("count", String.valueOf(count));
        intent.putExtra("count", count);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
