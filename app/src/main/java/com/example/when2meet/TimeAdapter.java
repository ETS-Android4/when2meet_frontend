package com.example.when2meet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {
    private ArrayList<ToggleItem> dataList = null;
    public TimeAdapter() {
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        TextView text1,text2;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
        }

        void onBind(ToggleItem toggleItem) {
            text1.setText(toggleItem.getText1());
            text2.setText(toggleItem.getText2());
        }
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.onBind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
