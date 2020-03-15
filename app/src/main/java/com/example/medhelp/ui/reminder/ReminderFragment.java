package com.example.medhelp.ui.reminder;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.medhelp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class ReminderFragment extends Fragment  {
    private FloatingActionButton mAddReminderButton;
    private Toolbar mToolbar;
    AlarmCursorAdapter mCursorAdapter;
    ListView reminderListView;
    ProgressDialog prgDialog;
     private static final int VEHICLE_LOADER = 0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*reminderViewModel =
                ViewModelProviders.of(this).get(ReminderViewModel.class);
        View root = inflater.inflate(R.layout.reminder_main, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        reminderViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }*/
        View root = inflater.inflate(R.layout.reminder_main, container, false);
       // mToolbar = (Toolbar) root.findViewById(R.id.toolbar);
     //   setSupportActionBar(mToolbar);
       // mToolbar.setTitle(R.string.app_name);


        reminderListView = (ListView) root.findViewById(R.id.list);
        View emptyView = root.findViewById(R.id.empty_view);
        reminderListView.setEmptyView(emptyView);

        mCursorAdapter = new AlarmCursorAdapter(getActivity(), null);
        reminderListView.setAdapter(mCursorAdapter);

        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), AddReminderActivity.class);
           /*   Uri currentVehicleUri = ContentUris.withAppendedId(AlarmReminderContract.AlarmReminderEntry.CONTENT_URI, id);

                // Set the URI on the data field of the intent
                intent.setData(currentVehicleUri);

                ReminderData data =new ReminderData();*/
                startActivity(intent);


            }
        });


        mAddReminderButton = (FloatingActionButton)root.findViewById(R.id.fab);

        mAddReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddReminderActivity.class);
                startActivity(intent);
            }
        });

      //  getLoaderManager().initLoader(VEHICLE_LOADER, null, (Objects.requireNonNull(getActivity())));

        return  root;
    }

  /*  public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
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

*/



}



