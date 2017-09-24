package com.gmail.ioanna.finalapp.spinners;


import android.databinding.ObservableField;

import com.gmail.ioanna.data.dbEntity.Percents;

public class PercentItemViewModel {

    public ObservableField<Percents> percents = new ObservableField<>();

    public PercentItemViewModel(Percents percents) {
        this.percents.set(percents);
    }

    public void setPercents(Percents percents) {
        this.percents.set(percents);
    }
}
