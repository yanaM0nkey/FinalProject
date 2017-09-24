package com.gmail.ioanna.finalapp;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.Gravity;
import android.widget.Toast;

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

public class CreateViewModel implements BaseViewModel {

    private Activity activity;
    private DatabaseManager manager;
    private int count;

    public static int percentListSelectedPosition = 0;
    private ArrayList<Percents> percents;

    PercentAdapter percentAdapter;

    public static int statetListSelectedPosition = 0;
    private ArrayList<State> states;

    StateAdapter stateAdapter;

    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> estimatedTime = new ObservableField<>("");
    public ObservableField<String> startDate = new ObservableField<>("");
    public ObservableField<String> dueDate = new ObservableField<>("");

    public CreateViewModel(Activity activity, int count) {
        this.activity = activity;
        this.count = count;
        manager = manager.getInstance(activity);
        percents = new GetPercentsFromJson().getPercents(activity);
        percentAdapter = new PercentAdapter(percents);
        percentListSelectedPosition = findPercentIndexByCode(percents.get(0).getCode());
        states = new GetStateFromJson().getState(activity);
        stateAdapter = new StateAdapter(states);
        statetListSelectedPosition = findStateIndexByCode(states.get(0).getCode());
    }

    @Override
    public void init() {}

    @Override
    public void release() {}

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    public void onButtonClick(){
        Task task = new Task();

        try {
            task.setId(count++);

            if (name.get().equals("") || startDate.get().equals("") || dueDate.get().equals("")) {
                throw new Exception();
            }

            task.setName(name.get());
            task.setPercentOfCompletion(Integer.valueOf(percents.get(percentListSelectedPosition).getPercent()));
            task.setState(states.get(statetListSelectedPosition).getState());
            task.setEstimatedTime(Integer.valueOf(estimatedTime.get()));
            task.setStartDate(startDate.get());
            task.setDueDate(dueDate.get());
            manager.open(true);
            manager.insertTask(task);
            manager.close();
            Intent intent = new Intent(activity, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
        } catch (Exception e){
            showError(activity);
        }


    }

    public static void showError(Activity activity){
        Toast toast = Toast.makeText(activity.getApplicationContext(),
                "Error, check data!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public int findPercentIndexByCode(String code) {
        if (null == code) return 0;
        for (int i = 0; i < percents.size(); i++) {
            if (percents.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return 0;
    }

    public int findStateIndexByCode(String code) {
        if (null == code) return 0;
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i).getCode().equals(code)) {
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
