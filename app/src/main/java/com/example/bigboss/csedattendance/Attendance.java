package com.example.bigboss.csedattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity {
    int i=0;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        ListView list=(ListView)findViewById(R.id.list);
       final  ArrayList<String> nme=new ArrayList<>();

        user= FirebaseAuth.getInstance().getCurrentUser();
        final String uid=user.getUid();
        final DatabaseReference db= FirebaseDatabase.getInstance().getReference(uid);
        final TextView abc=(TextView)findViewById(R.id.abc);
        final Button lg=(Button)findViewById(R.id.logout);
        final ProgressBar ld=(ProgressBar)findViewById(R.id.pg123);

        final ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nme);
        list.setAdapter(adapter);

        ld.setVisibility(View.VISIBLE);
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String ch=dataSnapshot.getValue(String.class);

                if(i==0)
                    ch="Absent count:\t"+ch;
                else if(i==1)
                    ch="Name :\t"+ch;
                else if (i==2)
                    ch="No of Days :\t"+ch;
                else if(i==3)
                    ch="Percentage :\t"+ch;
                else if (i==4)
                    ch="Roll Number :\t"+ch;

                nme.add(ch);
                if(i==4)
                    ld.setVisibility(View.GONE);
                i++;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Attendance.this,"Successfully Logged Out",Toast.LENGTH_SHORT)
                        .show();
                startActivity(new Intent(Attendance.this,Login.class));
                finish();
            }
        });
    }
}
