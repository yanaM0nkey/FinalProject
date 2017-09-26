package com.gmail.ioanna.domain.interactions;


import android.app.Activity;
import android.util.Log;

import com.gmail.ioanna.data.dbEntity.State;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class GetStateFromJson {

    public ArrayList<State> getState(Activity activity) {

        String json;

        try {
            InputStream stream = activity.getAssets().open("state.json");
           // Log.e("ex1", stream.toString());
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            Log.e("ex2", ex.toString());
            return null;
        }

        State[] states = new Gson().fromJson(json, State[].class);
        return new ArrayList<>(Arrays.asList(states));
    }
}
