package com.example.medhelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText emailid, password;
    Button signup;
    TextView logintxt;
    FirebaseAuth mFirebaseAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User_Details");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mFirebaseAuth= FirebaseAuth.getInstance();
        emailid= findViewById(R.id.email);
        password= findViewById(R.id.password);
        signup= findViewById(R.id.signup_bt);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailid.getText().toString();
                final String pass = password.getText().toString();
                if (email.isEmpty()) {
                    emailid.setError("Please Enter The Email");
                    emailid.requestFocus();
                } else if (pass.isEmpty()) {
                    password.setError("Please Enter The Password");
                    password.requestFocus();
                } else if (email.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(Signup.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pass.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(Signup.this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Users_cred user=new Users_cred(email,pass);
                                FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(Signup.this, "Sign Up is Complete", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Signup.this,Login.class));
                                    }
                                });


                            }
                            else
                            {
                                Toast.makeText(Signup.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
                else
                {
                    Toast.makeText(Signup.this, "Error Occurred!!", Toast.LENGTH_SHORT).show();}
            }
        });
        logintxt= findViewById(R.id.login_txt);
        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Login.class));
            }
        });

    }}