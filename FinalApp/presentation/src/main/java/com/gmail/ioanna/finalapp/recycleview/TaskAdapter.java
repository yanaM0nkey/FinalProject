package com.gmail.ioanna.finalapp.recycleview;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gmail.ioanna.data.dbEntity.Task;
import com.gmail.ioanna.finalapp.base.BaseAdapter;
import com.gmail.ioanna.finalapp.base.BaseItemViewHolder;


public class TaskAdapter extends BaseAdapter<Task, TaskItemViewModel> {

    Activity activity;
    TaskItemViewModel itemViewModel;

    public TaskAdapter(Activity activity) {
        this.activity = activity;

    }

    @Override
    public BaseItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemViewModel = new TaskItemViewModel(activity);
        return TaskItemViewHolder.create(LayoutInflater.from(parent.getContext()),
                parent, itemViewModel);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
