package com.example.fito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText mSignupname,mSignupemail, mSignuppassword;
    Button mContinuebtn;
    TextView mAlreadysignin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mSignupname = findViewById(R.id.signupname);
        mSignupemail = findViewById(R.id.signupemail);
        mSignuppassword = findViewById(R.id.signuppassword);
        mContinuebtn = findViewById(R.id.continuebtn);
        mAlreadysignin2 = findViewById(R.id.alreadysignin2);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        ProgressBar progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        mContinuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mSignupemail.getText().toString().trim();
                String password = mSignuppassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mSignupemail.setError("Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mSignuppassword.setError("Required");
                    return;
                }

                if(password.length() <6){
                    mSignuppassword.setError("Password must be more than 6 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Signup.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        }else {
                            Toast.makeText(Signup.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }

    public void login(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public void login1(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}