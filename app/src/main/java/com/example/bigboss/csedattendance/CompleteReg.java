package com.example.bigboss.csedattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompleteReg extends AppCompatActivity {

    DatabaseReference db;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_reg);

        final EditText n=(EditText)findViewById(R.id.cname);
       final  EditText r=(EditText)findViewById(R.id.croll);
        Button complete=(Button)findViewById(R.id.creg);

        user=FirebaseAuth.getInstance().getCurrentUser();
        final String uid=user.getUid().toString();
        db= FirebaseDatabase.getInstance().getReference();
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=n.getText().toString().trim();
                final  String roll=r.getText().toString().trim();
                db.child(uid).child("Name").setValue(name);
                db.child(uid).child("Roll number").setValue(roll);
                db.child(uid).child("No of days").setValue("0");
                db.child(uid).child("Absent count").setValue("0");
                db.child(uid).child("Percentage").setValue("0");

                Toast.makeText(CompleteReg.this,"Procedures Completed Successfully",Toast.LENGTH_SHORT)
                        .show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(CompleteReg.this,MainActivity.class));
                finish();

            }
        });
    }
}
