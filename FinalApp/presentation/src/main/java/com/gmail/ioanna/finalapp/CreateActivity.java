package com.gmail.ioanna.finalapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gmail.ioanna.finalapp.base.BaseAppCompatActivity;
import com.gmail.ioanna.finalapp.databinding.ActivityCreateBinding;


public class CreateActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        int count = getIntent().getExtras().getInt("count");
        CreateViewModel viewModel = new CreateViewModel(this, count);
        this.viewModel = viewModel;

        ActivityCreateBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_create);
        binding.setModel(viewModel);

        binding.spinner1Percents.setAdapter(viewModel.percentAdapter);
        binding.spinner1Percents.setOnItemSelectedListener(viewModel.percentAdapter);
        binding.spinner1Percents.setSelection(viewModel.percentListSelectedPosition);

        binding.spinner1State.setAdapter(viewModel.stateAdapter);
        binding.spinner1State.setOnItemSelectedListener(viewModel.stateAdapter);
        binding.spinner1State.setSelection(viewModel.statetListSelectedPosition);

        super.onCreate(savedInstanceState);

    }
}
