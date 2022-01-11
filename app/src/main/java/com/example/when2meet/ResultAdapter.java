package com.example.when2meet;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private ArrayList<ResultItem> dataList;
    int maxPeople;
    public ResultAdapter(int maxnum) {
        maxPeople = maxnum;
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
        TextView text1, text2, num1, num2;
        ImageView img1, img2;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            text1 = (TextView) itemView.findViewById(R.id.textView1);
            text2 = (TextView) itemView.findViewById(R.id.textView2);
//            num1 = (TextView) itemView.findViewById(R.id.number1);
//            num2 = (TextView) itemView.findViewById(R.id.number2);
            img1 = (ImageView) itemView.findViewById(R.id.imageView1);
            img2 = (ImageView) itemView.findViewById(R.id.imageView2);
        }

        void onBind(ResultItem resultItem) {
            text1.setText(resultItem.gethText1()+":00");
            text2.setText(null);
//            num1.setText(Integer.toString(resultItem.getNumber1()));
//            num2.setText(Integer.toString(resultItem.getNumber2()));
            img1.setColorFilter(Color.parseColor(colorFinder(maxPeople,resultItem.getNumber1())));
            img2.setColorFilter(Color.parseColor(colorFinder(maxPeople,resultItem.getNumber2())));

            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, resultItem.gethText1()+":00"+"에 "+maxPeople+"명 중 "+resultItem.getNumber1()+"명이 가능해요", Toast.LENGTH_SHORT).show();
                }
            });
            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, resultItem.gethText1()+":30"+"에 "+maxPeople+"명 중 "+resultItem.getNumber2()+"명이 가능해요", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public String colorFinder(int max, int num){
        float divide = 255/(max);
        System.out.println(divide);
        String defaultColor = "008800";
        String color = "#" + String.format("%02X", (int)Math.floor(divide * num));
        System.out.println(color + defaultColor);
        String ret = color.concat(defaultColor);
        System.out.println(ret);
        return ret;
    }

    public void onBindViewHolder(ResultAdapter.ViewHolder holder, final int position) {
        holder.onBind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        if (dataList == null)
            return 0;
        return dataList.size();
    }
}
