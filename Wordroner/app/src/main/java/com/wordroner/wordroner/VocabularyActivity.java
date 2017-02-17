package com.wordroner.wordroner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private DatabaseReference myRef;
    private ArrayAdapter<String> dataAdapter;
    private String uid = "none";
    private FirebaseUser user;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        listView = (ListView) findViewById(R.id.listView);
        btn_back = (Button) findViewById(R.id.btn_back);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        myRef = database.getReference(uid);

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());

        listView.setAdapter(dataAdapter);

        myRef.child("object").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataAdapter.clear();
                for (DataSnapshot wordData : dataSnapshot.getChildren()) {
                    String word = (String) wordData.child("key").getValue();
                    dataAdapter.add(word);
                }
                dataAdapter.notifyDataSetChanged();
                listView.setSelection(dataAdapter.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        btn_back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String selected_item = (String) adapterView.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), WordDefinition.class);
                intent.putExtra("text", selected_item);
                Log.d("하이", "onCreate: " + selected_item + intent.getStringExtra("text"));
                startActivity(intent);
                finish();
               /*
                try {
                    textView1.setText(dic.ShowDefinitions(selected_item));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
*/

            }
        });
    }
    public void addRef(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(uid);
    }

}


