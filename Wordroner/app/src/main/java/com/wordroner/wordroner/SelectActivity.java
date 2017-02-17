package com.wordroner.wordroner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class SelectActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_select);

            final Button btn_goBack = (Button) findViewById(R.id.btn_goBack);
            final Button btn_saveWord = (Button) findViewById(R.id.btn_saveWord);

            btn_saveWord.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                }
            });
            btn_goBack.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
                    startActivity(intent);
                }
            });

            //using listview
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        ArrayList<String> words = new ArrayList<String>(
                    Arrays.asList("123", "123", "a", "the", "cat", "cat", "cat", "dog", "apple", "a", "starbucks"));
        WordMap wordmap = new WordMap(words);

        //        ArrayList<Member> list = new ArrayList<Member>();

        List<Map.Entry<String, Integer>> list=wordmap.ExtractWords();

        recyclerView.setAdapter(new RecordAdapter(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));



      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,, list);

        //    listView.setAdapter(adapter);
    /*
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // listView에 반영된 item을 Click할 경우 다음 동작을 수행
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position); // 선택한 값을 String 문자열로 받아들여 Toast 출력
                    Toast.makeText(getApplicationContext(), item + " is selected!", Toast.LENGTH_SHORT).show();
                }

            });
    */


            String uid = "none";
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                uid = user.getUid();
            }

            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(uid);


            myRef.child("object").setValue(wordmap.ExtractWords());
            //myRef.child("words").child("3").setValue("hello world2");


    }


}