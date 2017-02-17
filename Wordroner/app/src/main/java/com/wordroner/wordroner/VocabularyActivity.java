package com.wordroner.wordroner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VocabularyActivity extends AppCompatActivity {

    private ListView listView;

    private ArrayAdapter<String> dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        listView = (ListView) findViewById(R.id.listView);

        String uid = "none";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference  myRef = database.getReference(uid);

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());

        listView.setAdapter(dataAdapter);

        myRef.child("object").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataAdapter.clear();
                for (DataSnapshot wordData : dataSnapshot.getChildren()) {
                    String word = (String)wordData.child("key").getValue();
                    dataAdapter.add(word);
                }
                dataAdapter.notifyDataSetChanged();
                listView.setSelection(dataAdapter.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}


