package com.example.when2meet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.when2meet.Retrofit.CallRetrofit;
import com.example.when2meet.Retrofit.Models.Model__PostSchedule;
import com.example.when2meet.Retrofit.Models.Model__Schedule;
import com.example.when2meet.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    TextView text1 = null, text2 = null, text3 = null, text4 = null, text5 = null;
    TextView timetext1 = null, timetext2 = null;
    Button button1, button3;
    int i=0;
    Button timeButton;
    int hour1 = 0, hour2 = 24, minute1, minute2;
    EditText appointName;
    Long userId;
    String scheduleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Context context;
        text1 = findViewById(R.id.textView_date1);
        text2 = findViewById(R.id.textView_date2);
        text3 = findViewById(R.id.textView_date3);
        text4 = findViewById(R.id.textView_date4);
        text5 = findViewById(R.id.textView_date5);
        timetext1 = findViewById(R.id.timedata1);
        timetext2 = findViewById(R.id.timedata2);
        button1 = findViewById(R.id.button1);
        button3 = findViewById(R.id.button3);
        timeButton = findViewById(R.id.buttontime1);
        appointName = findViewById(R.id.editText);
        Intent intent = getIntent();
        userId = intent.getExtras().getLong("userId");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> dayarray = new ArrayList<String>();
                for(int l=1; l<=i; l++){
                    if(l==1){
                        dayarray.add(text1.getText().toString());
                    }else if(l==2){
                        dayarray.add(text2.getText().toString());
                    }else if(l==3){
                        dayarray.add(text3.getText().toString());
                    }
                    else if(l==4){
                        dayarray.add(text4.getText().toString());
                    }else{
                        dayarray.add(text5.getText().toString());
                    }
                }
                Model__PostSchedule schedule = new Model__PostSchedule();
                schedule.setDays(dayarray);
                schedule.setTitle(appointName.getText().toString());
                schedule.setStart_time(Integer.toString(hour1)+":00");
                schedule.setEnd_time(Integer.toString(hour2)+":00");

                CallRetrofit retrofitClient = new CallRetrofit();
                ArrayList<String> schId = new ArrayList<String>();

                Call<Model__PostSchedule> call = RetrofitClient.getApiService().postSchedule(schedule);
                call.enqueue(new Callback<Model__PostSchedule>() {
                    @Override
                    public void onResponse(Call<Model__PostSchedule> call, Response<Model__PostSchedule> response) {
                        if (!response.isSuccessful()) {
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                            return;
                        }
                        Model__PostSchedule schedule = response.body();

                        scheduleId = schedule.getId();
                        Intent intent = new Intent(DetailActivity.this, TimeActivity.class);
                        intent.putExtra("name",appointName.getText().toString());
                        intent.putExtra("start",hour1);
                        intent.putExtra("finish",hour2);
                        intent.putExtra("number",i);
                        intent.putExtra("userId",userId);
                        intent.putExtra("scheduleId",scheduleId);
                        if(text1 != null)
                        {
                            intent.putExtra("text1",text1.getText().toString());
                        }
                        if(text2 != null)
                        {
                            intent.putExtra("text2",text2.getText().toString());
                        }
                        if(text3 != null)
                        {
                            intent.putExtra("text3",text3.getText().toString());
                        }
                        if(text4 != null)
                        {
                            intent.putExtra("text4",text4.getText().toString());
                        }
                        if(text5 != null)
                        {
                            intent.putExtra("text5",text5.getText().toString());
                        }
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Model__PostSchedule> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        text1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);

                dialog.setMessage("정말로 삭제하시겠습니까?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeleteText(1);
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
                return false;
            }
        });
        text2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);

                dialog.setMessage("정말로 삭제하시겠습니까?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeleteText(2);
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
                return false;
            }
        });
        text3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);

                dialog.setMessage("정말로 삭제하시겠습니까?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeleteText(3);
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
                return false;
            }
        });
        text4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);

                dialog.setMessage("정말로 삭제하시겠습니까?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeleteText(4);
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
                return false;
            }
        });
        text5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);

                dialog.setMessage("정말로 삭제하시겠습니까?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeleteText(5);
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
                return false;
            }
        });
    }
    public void DeleteText(int row){
        int n;
        DeleteDate(row);
        i--;
        for(n=row; n<5; n++)
        {
            if(n==1) {
                text1.setText(text2.getText().toString());
            }else if(n==2) {
                text2.setText(text3.getText().toString());
            }else if(n==3) {
                text3.setText(text4.getText().toString());
            }else if(n==4) {
                text4.setText(text5.getText().toString());
            }
        }
        if(i!=5){
            button1.setVisibility(View.VISIBLE);
        }
        DeleteDate(5);
    }
    public void DeleteDate(int daterow) {
        if(daterow==1) {
            text1.setText(null);
        }else if(daterow==2) {
            text2.setText(null);
        }else if(daterow==3) {
            text3.setText(null);
        }else if(daterow==4) {
            text4.setText(null);
        }else if(daterow==5) {
            text5.setText(null);
        }
    }
    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        Calendar c = Calendar.getInstance();
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month++;
        i++;
        String date = year + "-" + String.format("%02d",month) + "-" + dayOfMonth;
        if(i==1) {
            if(!checkValid(date)){
                date = null;
                i--;
                Toast.makeText(DetailActivity.this, "중복인 날짜가 있어요.", Toast.LENGTH_SHORT).show();
            }
            text1.setText(date);
        }else if(i==2) {
            if(!checkValid(date)){
                date = null;
                i--;
                Toast.makeText(DetailActivity.this, "중복인 날짜가 있어요.", Toast.LENGTH_SHORT).show();
            }
            text2.setText(date);
        }else if(i==3) {
            if(!checkValid(date)){
                date = null;
                i--;
                Toast.makeText(DetailActivity.this, "중복인 날짜가 있어요.", Toast.LENGTH_SHORT).show();
            }
            text3.setText(date);
        }else if(i==4) {
            if(!checkValid(date)){
                date = null;
                i--;
                Toast.makeText(DetailActivity.this, "중복인 날짜가 있어요.", Toast.LENGTH_SHORT).show();
            }
            text4.setText(date);
        }else if(i==5) {
            if(!checkValid(date)){
                date = null;
                i--;
                Toast.makeText(DetailActivity.this, "중복인 날짜가 있어요.", Toast.LENGTH_SHORT).show();
            }
            text5.setText(date);
            if(i==5) {
                button1.setVisibility(View.GONE);
            }
        }else{
            //nothing yet
        }
    }
    public boolean checkValid(String dateinput) {
        for(int k=1; k<i; k++){
            if(k==1) {
                if(dateinput.equals(text1.getText().toString())){
                    return false;
                }
            }else if(k==2) {
                if(dateinput.equals(text2.getText().toString())){
                    return false;
                }
            }else if(k==3) {
                if(dateinput.equals(text3.getText().toString())){
                    return false;
                }
            }else if(k==4) {
                if(dateinput.equals(text4.getText().toString())){
                    return false;
                }
            }else if(k==5) {
                if(dateinput.equals(text5.getText().toString())){
                    return false;
                }
            }
        }
        return true;
    }
    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour1 = selectedHour;
                minute1 = selectedMinute;
                if(hour1>=hour2){
                    hour1 = 0;
                    Toast.makeText(DetailActivity.this, "시작 시간이 종료 시간 보다 느려 0시로 설정했어요.", Toast.LENGTH_SHORT).show();
                }
                timetext1.setText(String.format(Locale.getDefault(), "%02d:%02d",hour1, 0));
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour1, 0, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void popTimePicker2(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour2 = selectedHour;
                minute2 = selectedMinute;
                if(hour1>=hour2){
                    hour2 = 24;
                    Toast.makeText(DetailActivity.this, "종료 시간이 시작 시간 보다 빨라 24시로 설정했어요.", Toast.LENGTH_SHORT).show();
                }
                timetext2.setText(String.format(Locale.getDefault(), "%02d:%02d",hour2, 0));
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour2, 0, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}