package com.example.fwani.myapp2;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

public class RestBook extends AppCompatActivity {
    Switch aSwitch;
    DatePicker datePicker;
    TimePicker timePicker;
    Button before, next;
    FrameLayout frameLayout;
    LinearLayout btnLayout;
    GridLayout layout3, layout4;
    TextView bookTime, t_adult, t_youth, t_kid, t_date, t_time;
    EditText e_adult, e_youth, e_kid;
    Chronometer chronometer;


    int frameNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_book);
        setTitle("레스토랑 예약시스템");

        aSwitch = (Switch)findViewById(R.id.switch1);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        before = (Button)findViewById(R.id.beforeBtn);
        next = (Button)findViewById(R.id.nextBtn);
        frameLayout = (FrameLayout)findViewById(R.id.frameLayout);
        btnLayout = (LinearLayout)findViewById(R.id.btnLayout);
        layout3 = (GridLayout) findViewById(R.id.layout3);
        layout4 = (GridLayout) findViewById(R.id.layout4);
        bookTime = (TextView)findViewById(R.id.bookTime);
        chronometer = (Chronometer)findViewById(R.id.chrono);
        e_adult = (EditText)findViewById(R.id.adultEditText);
        e_youth = (EditText)findViewById(R.id.youthEditText);
        e_kid = (EditText)findViewById(R.id.kidEditText);
        t_adult =(TextView)findViewById(R.id.adultView);
        t_youth =(TextView)findViewById(R.id.youthView);
        t_kid =(TextView)findViewById(R.id.kidView);
        t_date = (TextView)findViewById(R.id.dateView);
        t_time = (TextView)findViewById(R.id.timeView);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(aSwitch.isChecked()){
                    frameLayout.setVisibility(View.VISIBLE);
                    btnLayout.setVisibility(View.VISIBLE);
                    datePicker.setVisibility(View.VISIBLE);
                    bookTime.setVisibility(View.VISIBLE);
                    chronometer.setVisibility(View.VISIBLE);
                    chronometer.start();
                    before.setEnabled(false);
                    next.setEnabled(true);
                    frameNum = 1;
                }else{
                    frameLayout.setVisibility(View.INVISIBLE);
                    btnLayout.setVisibility(View.INVISIBLE);
                    datePicker.setVisibility(View.INVISIBLE);
                    timePicker.setVisibility(View.INVISIBLE);
                    layout3.setVisibility(View.INVISIBLE);
                    layout4.setVisibility(View.INVISIBLE);
                    bookTime.setVisibility(View.INVISIBLE);
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.setVisibility(View.INVISIBLE);
                    frameNum = 0;
                    clearLayout3();
                    clearLayout4();
                }
            }
        });
    }
    public void onMyClick(View v){
        if(v.getId() == next.getId()){
            if(frameNum == 1){
                frameNum++;
                datePicker.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);
                before.setEnabled(true);
            }else if(frameNum == 2){
                frameNum++;
                timePicker.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.VISIBLE);
            }else if(frameNum == 3){
                frameNum++;
                layout3.setVisibility(View.INVISIBLE);
                layout4.setVisibility(View.VISIBLE);
                initLayout4();
                next.setEnabled(false);
            }

        }else if(v.getId() == before.getId()){
            if(frameNum == 4){
                frameNum--;
                layout4.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.VISIBLE);
                next.setEnabled(true);
            }else if(frameNum == 3){
                frameNum--;
                layout3.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);
            }else if(frameNum == 2){
                frameNum--;
                timePicker.setVisibility(View.INVISIBLE);
                datePicker.setVisibility(View.VISIBLE);
                before.setEnabled(false);
            }
        }
    }

    public void initLayout4(){
        String adStr, yoStr, kiStr;
        String date, time;
        adStr = e_adult.getText().toString();
        if(adStr.equals("")){
            t_adult.setText(0+"명");
        }else{
            t_adult.setText(adStr+"명");
        }
        yoStr = e_youth.getText().toString();
        if(yoStr.equals("")){
            t_youth.setText(0+"명");
        }else{
            t_youth.setText(yoStr+"명");
        }
        kiStr = e_kid.getText().toString();
        if(kiStr.equals("")){
            t_kid.setText(0+"명");
        }else{
            t_kid.setText(kiStr+"명");
        }
        date = datePicker.getYear() + "년" + (datePicker.getMonth()+1) + "월" + datePicker.getDayOfMonth() + "일";
        time = timePicker.getCurrentHour() + "시" + timePicker.getCurrentMinute() + "분";
        t_date.setText(date);
        t_time.setText(time);
    }

    public void clearLayout12(){
    }
    public void clearLayout3(){
        e_adult.setText(null);
        e_youth.setText(null);
        e_kid.setText(null);
    }
    public void clearLayout4(){
        t_date.setText("년/월/일");
        t_time.setText("시/분");
        t_adult.setText("0명");
        t_youth.setText("0명");
        t_kid.setText("0명");
    }
}
