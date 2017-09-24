package com.gmail.ioanna.finalapp.datePickers;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.EditText;


import com.gmail.ioanna.finalapp.R;

import java.util.Calendar;

public class DueDatePicker extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // определяем текущую дату
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+ 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        // создаем DatePickerDialog и возвращаем его
        Dialog picker = new DatePickerDialog(getActivity(), this,
                year, month, day);

        return picker;
    }
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year,
                          int month, int day) {

        try{
            EditText dueDate = (EditText) getActivity().findViewById(R.id.dueDateEditText);
            dueDate.setText(day + "." + month + "." + year);
        }catch(NullPointerException ex){
            EditText dueDate = (EditText) getActivity().findViewById(R.id.dueDateEditText2);
            dueDate.setText(day + "." + month + "." + year);
        }

    }

}
