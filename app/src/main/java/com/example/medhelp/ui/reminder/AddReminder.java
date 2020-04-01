package com.example.medhelp.ui.reminder;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.example.medhelp.R;
import com.example.medhelp.ui.alarm.Alarm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class AddReminder extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

        private FloatingActionButton mAddReminderButton;
        private Toolbar mToolbar;
        AlarmCursorAdapter mCursorAdapter;
        AlarmReminderDbHelper alarmReminderDbHelper = new AlarmReminderDbHelper(this);
        ListView reminderListView;
        ProgressDialog prgDialog;

        private static final int VEHICLE_LOADER = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminder);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.app_name);


        reminderListView = (ListView) findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        reminderListView.setEmptyView(emptyView);

        mCursorAdapter = new AlarmCursorAdapter(this, null);
        reminderListView.setAdapter(mCursorAdapter);

        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Intent intent = new Intent(AddReminder.this, AddReminderActivity.class);

        Uri currentVehicleUri = ContentUris.withAppendedId(AlarmReminderContract.AlarmReminderEntry.CONTENT_URI, id);

        // Set the URI on the data field of the intent
        intent.setData(currentVehicleUri);

        startActivity(intent);

        }
        });


        mAddReminderButton = (FloatingActionButton) findViewById(R.id.fab);

        mAddReminderButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), AddReminderActivity.class);
        startActivity(intent);
        }
        });

        getLoaderManager().initLoader(VEHICLE_LOADER, null, this);


        }

@Override
public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
        AlarmReminderContract.AlarmReminderEntry._ID,
        AlarmReminderContract.AlarmReminderEntry.KEY_TITLE,
        AlarmReminderContract.AlarmReminderEntry.KEY_DATE,
        AlarmReminderContract.AlarmReminderEntry.KEY_TIME,
        AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT,
        AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO,
        AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE,
        AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE

        };

        return new CursorLoader(this,   // Parent activity context
        AlarmReminderContract.AlarmReminderEntry.CONTENT_URI,   // Provider content URI to query
        projection,             // Columns to include in the resulting Cursor
        null,                   // No selection clause
        null,                   // No selection arguments
        null);                  // Default sort order

        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);

        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);

        }
        }
