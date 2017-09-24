package com.gmail.ioanna.finalapp;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;

import com.gmail.ioanna.data.GetPercentsFromJson;
import com.gmail.ioanna.data.GetStateFromJson;
import com.gmail.ioanna.data.db.DatabaseManager;
import com.gmail.ioanna.data.dbEntity.Percents;
import com.gmail.ioanna.data.dbEntity.State;
import com.gmail.ioanna.data.dbEntity.Task;
import com.gmail.ioanna.finalapp.base.BaseViewModel;
import com.gmail.ioanna.finalapp.datePickers.DueDatePicker;
import com.gmail.ioanna.finalapp.datePickers.StartDatePicker;
import com.gmail.ioanna.finalapp.spinners.PercentAdapter;
import com.gmail.ioanna.finalapp.spinners.StateAdapter;


import java.util.ArrayList;

public class EditViewModel implements BaseViewModel {

    private Activity activity;
    private DatabaseManager manager;

    private int id;

    public static int percentListSelectedPosition = 0;

    private ArrayList<Percents> percents;

    PercentAdapter percentAdapter;

    public static int statetListSelectedPosition = 0;

    private ArrayList<State> states;

    StateAdapter stateAdapter;

    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> state = new ObservableField<>("");
    public ObservableField<String> percentOfCompletion = new ObservableField<>("");
    public ObservableField<String> estimatedTime = new ObservableField<>("");
    public ObservableField<String> startDate = new ObservableField<>("");
    public ObservableField<String> dueDate = new ObservableField<>("");

    Task task;

    public EditViewModel(Activity activity, int id) {
        this.activity = activity;
        this.id = id;
        manager = manager.getInstance(activity);
        percents = new GetPercentsFromJson().getPercents(activity);
        percentAdapter = new PercentAdapter(percents);
        states = new GetStateFromJson().getState(activity);
        stateAdapter = new StateAdapter(states);

        manager.open(false);
        task = manager.getTaskById(id);
        manager.close();

        percentListSelectedPosition = findPercentIndexByValue(String.valueOf(task.getPercentOfCompletion()));
        statetListSelectedPosition = findStateIndexByValue(task.getState());
    }

    @Override
    public void init() {}

    @Override
    public void release() {}

    @Override
    public void resume() {
        name.set(task.getName());
        percentOfCompletion.set(percents.get(percentListSelectedPosition).getPercent());
        state.set(states.get(statetListSelectedPosition).getState());
        estimatedTime.set(String.valueOf(task.getEstimatedTime()));
        startDate.set(task.getStartDate());
        dueDate.set(task.getDueDate());
    }

    @Override
    public void pause() {}

    public void onButtonClick(){
        Task task = new Task();

        try {

            if (name.get().equals("") || startDate.get().equals("") || dueDate.get().equals("")) {
                throw new Exception();
            }

            task.setId(id);
            task.setName(name.get());
            task.setPercentOfCompletion(Integer.valueOf(percents.get(percentListSelectedPosition).getPercent()));
            task.setState(states.get(statetListSelectedPosition).getState());
            task.setEstimatedTime(Integer.valueOf(estimatedTime.get()));
            task.setStartDate(startDate.get());
            task.setDueDate(dueDate.get());

            Log.e("listpercent",percents.get(percentListSelectedPosition).getPercent());
            Log.e("liststate",states.get(statetListSelectedPosition).getState() );

            manager.open(true);
            manager.updateTask(task);
            manager.close();

            Intent intent = new Intent(activity, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
        }catch(Exception e){
            CreateViewModel.showError(activity);
        }

    }


    public int findPercentIndexByValue(String value){
        if (null == value) return 0;
        for (int i = 0; i < percents.size(); i++) {
            if (percents.get(i).getPercent().equals(value)) {
                return i;
            }
        }
        return 0;
    }

    public int findStateIndexByValue(String value){
        if (null == value) return 0;
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i).getState().equals(value)) {
                return i;
            }
        }
        return 0;
    }


    public void startDateClick(){
        DialogFragment dateDialog = new StartDatePicker();
        dateDialog.show(activity.getFragmentManager(), "datePicker");
    }

    public void dueDateClick(){
        DialogFragment dateDialog = new DueDatePicker();
        dateDialog.show(activity.getFragmentManager(), "datePicker");
    }
}
