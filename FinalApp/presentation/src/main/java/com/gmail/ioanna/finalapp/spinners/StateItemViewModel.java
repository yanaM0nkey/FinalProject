package com.gmail.ioanna.finalapp.spinners;


import android.databinding.ObservableField;

import com.gmail.ioanna.data.dbEntity.State;

public class StateItemViewModel {

    public ObservableField<State> states = new ObservableField<>();

    public StateItemViewModel(State states) {
        this.states.set(states);
    }

    public void setState(State states) {
        this.states.set(states);
    }
}
