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
    CheckBox fatigue,nausea,flow,headache,cramping,tender,craving;
    Button surveyHome, surveyDone;
    Switch birthControl;
   // public static final String DATE_DATA = "com.example.finalProject.dateData";
  //  String data;



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
   //     craving = findViewById(R.id.cravingCheck);
     //   tender = findViewById(R.id.tenderCheck);


        surveyDone.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this, CalScreen.class));
               // CheckSymptom();
            }
        });

        surveyHome.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeScreen.class));
            }
        });

    }

            /*public void CheckSymptom(){



            // isChecked() is used to check whether
            // the CheckBox is in true state or not.

            if(tender.isChecked()) {
                data = data + "Tender Breast, ";
                Log.d(TAG, "tender breast checked");
            }
            if(nausea.isChecked()) {
                data = data + "Nausea, ";
                Log.d(TAG, "Nausea checked");
            }
            if(fatigue.isChecked()) {
                data = data + "Fatigue, ";
                Log.d(TAG, "fatigue checked");
            }
            if(headache.isChecked()) {
                data = data + "Headaches, ";
                Log.d(TAG, "headaches checked");
            }
            if(cramping.isChecked()) {
                data = data + "Intense Cramping, ";
                Log.d(TAG, "intense cramping checked");
            }
            if(flow.isChecked()) {
                data = data + "Extremely heavy flow, ";
                Log.d(TAG, "very heavy flow checked");
            }
            if(craving.isChecked()) {
                data = data + "Cravings, ";
                Log.d(TAG, "cravings checked");
            }
            csIntent.putExtra(DATE_DATA, this.data);
        }
    */

}