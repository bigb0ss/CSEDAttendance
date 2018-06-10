package com.example.bigboss.csedattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ProgressBar pb=(ProgressBar)findViewById(R.id.prgbar);
        final EditText username=(EditText)findViewById(R.id.loginuser);
        final EditText password=(EditText)findViewById(R.id.loginpassword);
        Button login=(Button)findViewById(R.id.login1);

        auth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String user=username.getText().toString().trim();
            String pass=password.getText().toString().trim();
            if(TextUtils.isEmpty(user)){
                Toast.makeText(Login.this,"Enter Kongu ID",Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(pass)){
                Toast.makeText(Login.this,"Enter password",Toast.LENGTH_SHORT).show();
                return;
            }

            pb.setVisibility(View.VISIBLE);
            auth.signInWithEmailAndPassword(user,pass)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pb.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(Login.this, Attendance.class));
                                    finish();
                                } else {
                                    Toast.makeText(Login.this,"Invalid Email/Password",Toast.LENGTH_SHORT)
                                            .show();

                                }
                            }

                        });
            }
        });
    }
}
