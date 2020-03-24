package com.example.medhelp.ui.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medhelp.R;
import com.example.medhelp.ui.alarm.Alarm;

public class AddReminder extends AppCompatActivity {
    EditText timeEditText;
    Button setAlarmButton;
    String time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminder);

        timeEditText = (EditText) findViewById(R.id.time);
        setAlarmButton = (Button) findViewById(R.id.set_alarm);

        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = timeEditText.getText().toString();

                Intent intent = new Intent(getApplicationContext(), Alarm.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 234, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (Integer.parseInt(time) * 1000), pendingIntent);
                Toast.makeText(getApplicationContext(), "Alarm set to after " + time + " seconds", Toast.LENGTH_LONG).show();

            }
        });

    }
}
