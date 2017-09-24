package com.gmail.ioanna.finalapp;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gmail.ioanna.finalapp.base.BaseAppCompatActivity;
import com.gmail.ioanna.finalapp.databinding.ActivityEditBinding;


public class EditActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        int id = getIntent().getExtras().getInt("id");

        EditViewModel viewModel = new EditViewModel(this, id);
        this.viewModel = viewModel;
        ActivityEditBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_edit);
        binding.setModel(viewModel);

        binding.spinner2Percents.setAdapter(viewModel.percentAdapter);
        binding.spinner2Percents.setOnItemSelectedListener(viewModel.percentAdapter);
        binding.spinner2Percents.setSelection(viewModel.percentListSelectedPosition);

        binding.spinner2State.setAdapter(viewModel.stateAdapter);
        binding.spinner2State.setOnItemSelectedListener(viewModel.stateAdapter);
        binding.spinner2State.setSelection(viewModel.statetListSelectedPosition);

        super.onCreate(savedInstanceState);
    }
}
