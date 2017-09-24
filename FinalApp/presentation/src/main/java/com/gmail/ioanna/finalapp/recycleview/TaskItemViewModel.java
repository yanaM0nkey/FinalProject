package com.gmail.ioanna.finalapp.recycleview;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;

import com.gmail.ioanna.data.dbEntity.Task;
import com.gmail.ioanna.finalapp.EditActivity;
import com.gmail.ioanna.finalapp.base.BaseItemViewModel;


public class TaskItemViewModel extends BaseItemViewModel<Task> {

    Activity activity;

    public TaskItemViewModel(Activity activity) {
        this.activity = activity;
    }

    private ObservableInt id = new ObservableInt();
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> state = new ObservableField<>("");
    public ObservableField<String> percentOfCompletion = new ObservableField<>("");
    public ObservableField<String> estimatedTime = new ObservableField<>("");
    public ObservableField<String> startDate = new ObservableField<>("");
    public ObservableField<String> dueDate = new ObservableField<>("");


    @Override
    public void setItem(Task item, int position) {
        id.set(item.getId());
        name.set(item.getName());
        percentOfCompletion.set("completion: " + item.getPercentOfCompletion() + "%");
        state.set(item.getState());
        estimatedTime.set("estimated time: " + item.getEstimatedTime() + " hours");
        startDate.set("start date: " + item.getStartDate());
        dueDate.set("due date: " + item.getDueDate());
    }

    public void onButtonClick(){
        Log.e("AAA", "Click");
        Intent intent = new Intent(activity, EditActivity.class);
        intent.putExtra("id", id.get());
        Log.e("AAA", String.valueOf(id.get()));
        activity.startActivity(intent);
    }

}
