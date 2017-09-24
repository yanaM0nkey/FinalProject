package com.gmail.ioanna.finalapp.recycleview;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gmail.ioanna.data.dbEntity.Task;
import com.gmail.ioanna.finalapp.base.BaseItemViewHolder;
import com.gmail.ioanna.finalapp.databinding.ItemRecycleviewBinding;


public class TaskItemViewHolder extends BaseItemViewHolder<Task,
        TaskItemViewModel, ItemRecycleviewBinding> {


    public TaskItemViewHolder(ItemRecycleviewBinding dataBinding, TaskItemViewModel viewModel) {
        super(dataBinding, viewModel);
        dataBinding.setViewModel(viewModel);
    }

    public static TaskItemViewHolder create(LayoutInflater inflater, ViewGroup parent,
                                            TaskItemViewModel viewModel){
        ItemRecycleviewBinding binding = ItemRecycleviewBinding.inflate(inflater,parent,false);
        return new TaskItemViewHolder(binding, viewModel);
    }

}
