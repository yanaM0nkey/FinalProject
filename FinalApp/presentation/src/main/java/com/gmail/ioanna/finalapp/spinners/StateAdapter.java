package com.gmail.ioanna.finalapp.spinners;


import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.gmail.ioanna.data.dbEntity.State;
import com.gmail.ioanna.finalapp.CreateViewModel;
import com.gmail.ioanna.finalapp.EditViewModel;
import com.gmail.ioanna.finalapp.R;
import com.gmail.ioanna.finalapp.databinding.ItemSpinnerStateBinding;


import java.util.ArrayList;

public class StateAdapter extends BaseAdapter implements AdapterView.OnItemSelectedListener {

    ArrayList<State> states;

    public StateAdapter(ArrayList<State> states) {
        this.states = states;
    }

    @Override
    public int getCount() {
        return states.size();
    }


    @Override
    public Object getItem(int position) {
        if (position >= states.size())
            return states.get(0);
        return states.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        StateItemViewModel viewModel;

        if (convertView == null) {
            ItemSpinnerStateBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_spinner_state, parent, false);

            holder = new ViewHolder(itemBinding);
            holder.view = itemBinding.getRoot();
            holder.view.setTag(holder);
            viewModel = new StateItemViewModel(states.get(position));
            holder.binding.setViewModel(viewModel);
        }else {
            holder = (ViewHolder) convertView.getTag();
            holder.binding.getViewModel().setState(states.get(position));
        }
        return holder.view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        CreateViewModel.statetListSelectedPosition = position;
        EditViewModel.statetListSelectedPosition = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}


    static class ViewHolder {
        private View view;

        private ItemSpinnerStateBinding binding;

        ViewHolder(ItemSpinnerStateBinding binding) {
            this.view = binding.getRoot();
            this.binding = binding;
        }

    }
}
