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

public class Register extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        auth=FirebaseAuth.getInstance();

        final EditText mailid,passrd;
        final ProgressBar pg=(ProgressBar)findViewById(R.id.pb);
        mailid=(EditText)findViewById(R.id.mail);
        passrd=(EditText)findViewById(R.id.registerpassword);

        final Button registerbtn=(Button)findViewById(R.id.registerbutton);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail=mailid.getText().toString().trim();
                String pass=passrd.getText().toString().trim();

                if(TextUtils.isEmpty(mail)) {
                    Toast.makeText(Register.this, "Enter the Mail ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)) {
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.length()<6) {
                    Toast.makeText(Register.this, "Password should of minimum length 6", Toast.LENGTH_SHORT).show();
                    return;
                }

                pg.setVisibility(View.VISIBLE);

                auth.createUserWithEmailAndPassword(mail,pass)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pg.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT)
                                    .show();
                            startActivity(new Intent(Register.this,CompleteReg.class));
                            finish();

                        }
                        else {
                            Toast.makeText(Register.this,task.getException().getMessage(), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
            }
        });

    }
}
