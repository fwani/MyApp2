package com.example.fwani.myapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GradeCalc extends AppCompatActivity {
    EditText korScore, mathScore, engScore;
    Button b1, b2;
    TextView totScore, avgScore;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_calc);
        setTitle("학점 계산기");

        korScore = (EditText)findViewById(R.id.korScore);
        mathScore = (EditText)findViewById(R.id.mathScore);
        engScore = (EditText)findViewById(R.id.engScore);
        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        totScore = (TextView)findViewById(R.id.totScore);
        avgScore = (TextView)findViewById(R.id.avgScore);
        imageView = (ImageView)findViewById(R.id.gradeView);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcGrade();
                imageView.setVisibility(View.VISIBLE);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                Toast.makeText(getApplicationContext(),"초기화되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void calcGrade(){
        int kor, math, eng, tot;
        double avg;
        String korStr, mathStr, engStr;

        korStr = korScore.getText().toString();
        if(korStr.equals("")){
            kor = 0;
            korScore.setText(""+kor);
        }else{
            kor = Integer.parseInt(korStr);
        }

        mathStr = mathScore.getText().toString();
        if(mathStr.equals("")){
            math = 0;
            mathScore.setText(""+math);
        }else{
            math = Integer.parseInt(mathStr);
        }

        engStr = engScore.getText().toString();
        if(engStr.equals("")){
            eng = 0;
            engScore.setText(""+eng);
        }else{
            eng = Integer.parseInt(engStr);
        }

        tot = kor + math + eng;
        avg = tot / 3;

        totScore.setText(tot+"점");
        avgScore.setText(avg+"점");

        if(avg >= 90){
            imageView.setImageResource(R.drawable.image_a);
        }else if(avg < 90 && avg >=80){
            imageView.setImageResource(R.drawable.image_b);
        }else if(avg < 80 && avg >=70){
            imageView.setImageResource(R.drawable.image_c);
        }else if(avg < 70 && avg >=50){
            imageView.setImageResource(R.drawable.image_d);
        }else{
            imageView.setImageResource(R.drawable.image_f);
        }
    }

    public void clear(){
        korScore.setText("");
        mathScore.setText("");
        engScore.setText("");
        totScore.setText("0점");
        avgScore.setText("0점");
        imageView.setVisibility(View.INVISIBLE);
    }
}
