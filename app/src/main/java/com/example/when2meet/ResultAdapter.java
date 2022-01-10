package com.example.when2meet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private ArrayList<ResultItem> dataList;
    public ResultAdapter() {
    }

    public void submitList(ArrayList<ResultItem> list) {
        dataList = list;
        notifyDataSetChanged();
    }

    public List<ResultItem> getList() {
        return dataList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);
        ResultAdapter.ViewHolder viewHolder = new ResultAdapter.ViewHolder(view);
        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        TextView text1,text2, num1, num2;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            text1 = (TextView) itemView.findViewById(R.id.textView1);
            text2 = (TextView) itemView.findViewById(R.id.textView2);
            num1 = (TextView) itemView.findViewById(R.id.number1);
            num2 = (TextView) itemView.findViewById(R.id.number2);
        }

        void onBind(ResultItem resultItem) {
            System.out.println("onbind");
            text1.setText(resultItem.gethText1());
            System.out.println(text1);
            //text2.setText(resultItem.gethText2());
            text2.setText(null);
            num1.setText(Integer.toString(resultItem.getNumber1()));
            System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            System.out.println(Integer.toString(resultItem.getNumber1()));
            System.out.println(Integer.toString(resultItem.getNumber2()));
            num2.setText(Integer.toString(resultItem.getNumber2()));

        }
    }

    public void onBindViewHolder(ResultAdapter.ViewHolder holder, final int position) {
        System.out.println("onbindviewholder");
        holder.onBind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        if(dataList == null)
            return 0;
        return dataList.size();
    }
}
