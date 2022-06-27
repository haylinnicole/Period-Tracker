package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    public static final String TAG ="Survey";
    CheckBox fatigue,nausea,flow,headache,cramping;
    Button surveyHome, surveyDone;
    Switch birthControl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "inside onCreate method");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fatigue = findViewById(R.id.FatigueCheck);
        nausea = findViewById(R.id.NauseaCheck);
        flow = findViewById(R.id.FlowCheck);
        headache = findViewById(R.id.HeadacheCheck);
        cramping = findViewById((R.id.IntenseCrampCheck));
        surveyDone = findViewById(R.id.survey_done_button);
        surveyHome = findViewById(R.id.survey_home_button);
        birthControl = findViewById(R.id.BirthControl);


        surveyHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Home.class));
            }
        });

    /*
     public void showToast(View view) {
        Log.d(TAG, "Hi I will now show a Toast!");
        Toast.makeText(this, "Hellooo",Toast.LENGTH_SHORT).show();


    }

     */
    }
}