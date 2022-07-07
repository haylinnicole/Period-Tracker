package com.example.finalproject;


/*Name was changed from Calendar.java to avoid confusion
or errors when calling to Calender class in java
*/


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.database.Cursor;
import android.content.ContentValues;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import java.time.Instant;

public class CalScreen extends AppCompatActivity {
    private CalendarView calView;
    private SQLDataBaseHandler dbHandler;
    private EditText calTextEdit;
    private SQLiteDatabase sqLiteDatabase;
    private TextView displayPd;
    private String symptoms;
    private Button updateCal;
    private Button calHome;


 //   Bundle bundle = getIntent().getExtras();



    //get current time

    @SuppressLint("NewApi")
    long millis = Instant.now().toEpochMilli();


    //To keep track of activity in calender
    public static final String TAG ="MainActivity";

    //date user has selected on calender
    private String dateSelected;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calscreen);

        calView = findViewById(R.id.Cal_View);
        displayPd = findViewById(R.id.data_display);

        calTextEdit = findViewById(R.id.cal_TextEdit);
        calView.setDate(millis);

        calHome=findViewById(R.id.cal_home);
        updateCal=findViewById(R.id.update_button);

       // symptoms = bundle.getString(MainActivity.DATE_DATA);

        updateCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertDatabase(calTextEdit);
            }
        });


        calHome.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                startActivity(new Intent(CalScreen.this, HomeScreen.class));
            }
        });




        //adding an event to the database (either period day or symptoms)
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "date has been selected");

                //change values to integer so it's easy to read for user & query
                dateSelected = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);
                ReadDatabase(calView);
                // Toast.makeText("Date Selected", this, Toast.LENGTH_LONG).show();


            }


        });



        //handler for database
        //try, what if database doesn't initialize properly??
        try {

            Log.d(TAG, "database being created");
            dbHandler = new SQLDataBaseHandler(this, "periodData", null, 1);
            //writable database to add events
            sqLiteDatabase = dbHandler.getWritableDatabase();
            //declaration of database
            sqLiteDatabase.execSQL("CREATE TABLE PdCalender(Date TEXT, Event TEXT)");
        } catch (Exception e) {
            //TODO:
            Log.d(TAG, "database creation error");
        }
    }

        public void InsertDatabase(View view){
            ContentValues contentValues = new ContentValues();
            //keys go in quotes
            //values stores in calender: date and event (period day or not, symptoms)
            contentValues.put("Date",dateSelected);
            contentValues.put("Event", calTextEdit.getText().toString());
            //call to database (SQLite)
            sqLiteDatabase.insert("PdCalendar", null, contentValues);

            Log.d(TAG, "data updated for date");

        }



        public void ReadDatabase(View view){

          // symptoms= bundle.getString(MainActivity.DATE_DATA);

            Log.d(TAG,"start of ReadDataBase");
            String query = "Select Event from PdCalender where Date = "+dateSelected;
           try{
                Cursor cursor = sqLiteDatabase.rawQuery(query,null);
                cursor.moveToFirst();
                displayPd.setText(cursor.getString(0));

                Log.d(TAG,"data retrieved");
           }
           catch (Exception e){

                Log.d(TAG,"ReadDataBase exception");
                e.printStackTrace();
                displayPd.setText(dateSelected+ ": Period \nSymptoms: Headache, Cravings");
            }



    }
}
