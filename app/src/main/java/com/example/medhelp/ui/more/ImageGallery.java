package com.example.medhelp.ui.more;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medhelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImageGallery extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private DatabaseReference mDatabaseReference;
    private List<ImageDetails> mImgDetails;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_gallery);
        mRecyclerView=findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mImgDetails=new ArrayList<>();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    ImageDetails imgDetails=postSnapshot.getValue(ImageDetails.class);
                    mImgDetails.add(imgDetails);

                }
                mAdapter=new ImageAdapter(ImageGallery.this,mImgDetails);
                mRecyclerView.setAdapter(mAdapter);
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImageGallery.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
