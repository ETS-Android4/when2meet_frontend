package com.example.when2meet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder>{
    private ArrayList<SelectItem> dataList;
    String userName;
    public SelectAdapter(String Name) {
        userName = Name;
    }

    public void submitList(ArrayList<SelectItem> list) {
        dataList = list;
        notifyDataSetChanged();
    }

    public List<SelectItem> getList() {
        return dataList;
    }

    public SelectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_item, parent, false);
        SelectAdapter.ViewHolder viewHolder = new SelectAdapter.ViewHolder(view);
        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView apptext,nametext;
        public ViewHolder(View itemView) {
            super(itemView);
            apptext = itemView.findViewById(R.id.appropriateText);
            nametext = itemView.findViewById(R.id.peopleText);
            System.out.println("Name!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(userName);
        }
        void onBind(SelectItem selectItem) {
            System.out.println("onbind");
            apptext.setText(selectItem.getAppText());
            nametext.setText(selectItem.getMakerText());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(nametext.getText().toString().contains(userName)){
                        System.out.println("name exist!!!!!!!!!!!!!!!");
                    }else {
                        System.out.println("click");
                    }
                }
            });
        }
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        System.out.println("onbindviewholder");
        holder.onBind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        if(dataList == null){
            return 0;
        }
        return dataList.size();
    }
}
