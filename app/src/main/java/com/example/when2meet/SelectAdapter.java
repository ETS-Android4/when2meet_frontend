package com.example.when2meet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.when2meet.Retrofit.CallRetrofit;
import com.example.when2meet.Retrofit.Models.Model__Schedule;
import com.example.when2meet.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder>{
    private ArrayList<SelectItem> dataList;
    String userName;
    Long userId;
    public SelectAdapter(String Name, Long id) {
        userName = Name;
        userId = id;
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
        Context context;
        public ViewHolder(View itemView) {
            super(itemView);
            apptext = itemView.findViewById(R.id.appropriateText);
            nametext = itemView.findViewById(R.id.peopleText);
            context = itemView.getContext();
        }
        void onBind(SelectItem selectItem) {
            apptext.setText(selectItem.getAppText());
            nametext.setText(selectItem.getMakerText());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(nametext.getText().toString().contains(userName)){
                        String scheduleId = selectItem.getScheduleId();

                        Call<Model__Schedule> call = RetrofitClient.getApiService().getScheduleWithId(scheduleId);
                        call.enqueue(new Callback<Model__Schedule>() {
                            @Override
                            public void onResponse(Call<Model__Schedule> call, Response<Model__Schedule> response) {
                                if (!response.isSuccessful()) {
                                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                                    return;
                                }
                                Intent intent = new Intent(context, ResultActivity.class);

                                Model__Schedule schedule = response.body();
                                intent.putExtra("name",apptext.getText().toString());

                                String [] startTime = schedule.getStart_time().split(":");
                                intent.putExtra("start",Integer.parseInt(startTime[0]));

                                String [] finishTime = schedule.getEnd_time().split(":");
                                intent.putExtra("finish",Integer.parseInt(finishTime[0]));

                                ArrayList<String> days = schedule.getDays();
                                intent.putExtra("number", days.size());

                                intent.putExtra("userId",userId);

                                intent.putExtra("scheduleId",scheduleId);

                                for(int i=0; i<days.size(); i++){
                                    if(i == 0){
                                        intent.putExtra("text1",days.get(i));
                                    }else if(i == 1){
                                        intent.putExtra("text2",days.get(i));
                                    }else if(i == 2){
                                        intent.putExtra("text3",days.get(i));
                                    }else if(i == 3){
                                        intent.putExtra("text4",days.get(i));
                                    }else{
                                        intent.putExtra("text5",days.get(i));
                                    }
                                }
                                context.startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Model__Schedule> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });

                    }else {
                        //입력 창으로 넘어가기
                        //API
                        String scheduleId = selectItem.getScheduleId();
                        Call<Model__Schedule> call = RetrofitClient.getApiService().getScheduleWithId(scheduleId);
                        call.enqueue(new Callback<Model__Schedule>() {
                            @Override
                            public void onResponse(Call<Model__Schedule> call, Response<Model__Schedule> response) {
                                if (!response.isSuccessful()) {
                                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                                    return;
                                }
                                Intent intent = new Intent(context, TimeActivity.class);

                                Model__Schedule schedule = response.body();
                                intent.putExtra("name",apptext.getText().toString());

                                String [] startTime = schedule.getStart_time().split(":");
                                intent.putExtra("start",Integer.parseInt(startTime[0]));

                                String [] finishTime = schedule.getEnd_time().split(":");
                                intent.putExtra("finish",Integer.parseInt(finishTime[0]));

                                ArrayList<String> days = schedule.getDays();
                                intent.putExtra("number", days.size());

                                intent.putExtra("userId",userId);

                                intent.putExtra("scheduleId",scheduleId);

                                for(int i=0; i<days.size(); i++){
                                    if(i == 0){
                                        intent.putExtra("text1",days.get(i));
                                    }else if(i == 1){
                                        intent.putExtra("text2",days.get(i));
                                    }else if(i == 2){
                                        intent.putExtra("text3",days.get(i));
                                    }else if(i == 3){
                                        intent.putExtra("text4",days.get(i));
                                    }else{
                                        intent.putExtra("text5",days.get(i));
                                    }
                                }
                                context.startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Model__Schedule> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });

                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String [] array;
                    if(nametext.getText().toString().contains(userName)){
                        array = new String[]{"약속 나가기", "약속 삭제하기"};
                    }else {
                        array = new String[]{"약속 삭제하기"};
                    }
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setItems(array, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //array[which] = "클릭한 값";
                            //Toast.makeText(getApplicationContext(), array[which],Toast.LENGTH_LONG).show();
                            String scheduleId = selectItem.getScheduleId();
                            ArrayList<Model__Schedule> schedules = new ArrayList<Model__Schedule>();

                            if(array[which].equals("약속 나가기")){
                                Call<Model__Schedule> call = RetrofitClient.getApiService().deleteSchedule(scheduleId, String.valueOf(userId));
                                call.enqueue(new Callback<Model__Schedule>() {
                                    @Override
                                    public void onResponse(Call<Model__Schedule> call, Response<Model__Schedule> response) {
                                        if (!response.isSuccessful()) {
                                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                                            return;
                                        }

                                        Intent intent = new Intent(context, SelectActivity.class);
                                        intent.putExtra("userId",userId);
                                        intent.putExtra("userName",userName);
                                        context.startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<Model__Schedule> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                            }else{
                                Call<Model__Schedule> call = RetrofitClient.getApiService().deleteSchedule(scheduleId, "-1");
                                call.enqueue(new Callback<Model__Schedule>() {
                                    @Override
                                    public void onResponse(Call<Model__Schedule> call, Response<Model__Schedule> response) {
                                        if (!response.isSuccessful()) {
                                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                                            return;
                                        }

                                        Intent intent = new Intent(context, SelectActivity.class);
                                        intent.putExtra("userId",userId);
                                        intent.putExtra("userName",userName);
                                        context.startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<Model__Schedule> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                            }
                        }
                    });
                    dialog.show();
                    return false;
                }
            });
        }
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
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
