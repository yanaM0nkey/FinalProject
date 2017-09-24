package com.gmail.ioanna.finalapp.spinners;


import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.gmail.ioanna.data.dbEntity.Percents;
import com.gmail.ioanna.finalapp.CreateViewModel;
import com.gmail.ioanna.finalapp.EditViewModel;
import com.gmail.ioanna.finalapp.R;
import com.gmail.ioanna.finalapp.databinding.ItemSpinnerPercentsBinding;


import java.util.ArrayList;

public class PercentAdapter extends BaseAdapter implements AdapterView.OnItemSelectedListener {

    ArrayList<Percents> percents;

    public PercentAdapter(ArrayList<Percents> percents) {
        this.percents = percents;
    }

    @Override
    public int getCount() {
        return percents.size();
    }


    @Override
    public Object getItem(int position) {
        if (position >= percents.size())
            return percents.get(0);
        return percents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        PercentItemViewModel viewModel;

        if (convertView == null) {
            ItemSpinnerPercentsBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_spinner_percents, parent, false);

            holder = new ViewHolder(itemBinding);
            holder.view = itemBinding.getRoot();
            holder.view.setTag(holder);
            viewModel = new PercentItemViewModel(percents.get(position));
            holder.binding.setViewModel(viewModel);
        }else {
            holder = (ViewHolder) convertView.getTag();
            holder.binding.getViewModel().setPercents(percents.get(position));
        }
        return holder.view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        CreateViewModel.percentListSelectedPosition = position;
        EditViewModel.percentListSelectedPosition = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    static class ViewHolder {
        private View view;

        private ItemSpinnerPercentsBinding binding;

        ViewHolder(ItemSpinnerPercentsBinding binding) {
            this.view = binding.getRoot();
            this.binding = binding;
        }

    }
}
