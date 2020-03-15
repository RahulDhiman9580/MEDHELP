package com.example.medhelp.ui.more;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medhelp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Upload extends AppCompatActivity {
    private static final int  CHOOSE_IMAGE=1;
    private Button chooseImg, btUpload;
    private TextView viewGallery ;
    private ImageView imgPreview;
    private EditText imgDescription;
    private ProgressBar progressBar;
    private Uri imgUrl;
    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;
    private StorageTask mUploadTask;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_layout);
        progressBar= findViewById(R.id.uploadProgress);
        chooseImg=(Button)findViewById(R.id.choose);
        btUpload=(Button)findViewById(R.id.upload);
        viewGallery=(TextView)findViewById(R.id.txt_view);
        imgPreview=(ImageView)findViewById(R.id.img_view);
        imgDescription=(EditText)findViewById(R.id.description);
        progressBar.setVisibility(View.GONE);
        mStorageReference= FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseReference= FirebaseDatabase.getInstance().getReference("uploads");

        viewGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Upload.this,ImageGallery.class));

            }
        });

        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask!=null && mUploadTask.isInProgress())
                {
                    Toast.makeText(Upload.this, "Upload in Progress", Toast.LENGTH_SHORT).show();
                }
                else{
                    uploadImg();
                }
            }
        });


        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChoosen();
            }
        });
    }

    private void showFileChoosen()
    {
    Intent intent= new  Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(intent,CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data != null && data.getData()  != null)
        {
            imgUrl = data.getData();
            Picasso.get().load(imgUrl.toString()).into(imgPreview);

        }
        else {
            Toast.makeText(Upload.this, "Picasso not working", Toast.LENGTH_SHORT).show();
        }
        }


       private void uploadImg(){
        if(imgUrl!=null)
        {
            final StorageReference fileReference=mStorageReference.child(System.currentTimeMillis()+".");
            mUploadTask=fileReference.putFile(imgUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler =new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }},500);
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            ImageDetails details =new ImageDetails(imgDescription.getText().toString(),uri.toString());
                            String uploadID= mDatabaseReference.push().getKey();
                            mDatabaseReference.child(uploadID).setValue(details);
                            Toast.makeText(Upload.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            imgPreview.setImageResource(R.drawable.preview);
                            imgDescription.setText("");
                        }
                    });



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Upload.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress=(100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int)progress);
                }
            });
        }else {
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
            }}

        }


