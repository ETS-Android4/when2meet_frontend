package com.example.when2meet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {
    private ArrayList<ToggleItem> dataList;
    public TimeAdapter() {
    }

    public void submitList(ArrayList<ToggleItem> list) {
        dataList = list;
        notifyDataSetChanged();
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
            text1 = (TextView) itemView.findViewById(R.id.textView1);
            text2 = (TextView) itemView.findViewById(R.id.textView2);
        }

        void onBind(ToggleItem toggleItem) {
            System.out.println("onbind");
            text1.setText(toggleItem.getText1());
            System.out.println(text1);
            //text2.setText(toggleItem.getText2());
            text2.setText(null);
        }
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        System.out.println("onbindviewholder");
        holder.onBind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
