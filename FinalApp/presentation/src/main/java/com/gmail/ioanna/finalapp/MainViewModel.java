package com.gmail.ioanna.finalapp;


import android.app.Activity;
import android.databinding.ObservableField;
import android.util.Log;

import com.gmail.ioanna.data.db.DatabaseManager;
import com.gmail.ioanna.data.dbEntity.Task;
import com.gmail.ioanna.finalapp.base.BaseViewModel;
import com.gmail.ioanna.finalapp.recycleview.TaskAdapter;


import java.util.List;

public class MainViewModel implements BaseViewModel {

    public enum STATE {PROGRESS, DATA}
    public ObservableField<STATE> state = new ObservableField<>(STATE.PROGRESS);

    private Activity activity;
    public TaskAdapter adapter;
    private DatabaseManager manager;


    public MainViewModel(Activity activity) {
        this.activity = activity;
        adapter = new TaskAdapter(activity);
        manager = manager.getInstance(activity);
    }

    @Override
    public void init() {
         /*manager.open(true);
        manager.insertTask(new Task(1,"Task", 0, "New", 25, "17.09.2017", "21.09.2017"));
        manager.insertTask(new Task(2,"Health&Food", 20, "Done", 25, "17.09.2017", "21.09.2017"));
        manager.insertTask(new Task(3,"GAme", 55, "In Progress", 25, "17.09.2017", "21.09.2017"));
        manager.close();*/
    }

    @Override
    public void release() {}

    @Override
    public void resume() {

        manager.open(false);

        List<Task> list = manager.getTasks();
        if(list != null){
            adapter.setItems(list);
            state.set(STATE.DATA);
        }
        for (Task task : list) {
            Log.e("resume()", task.getName());
        }
        manager.close();

    }

    @Override
    public void pause() {}
}
