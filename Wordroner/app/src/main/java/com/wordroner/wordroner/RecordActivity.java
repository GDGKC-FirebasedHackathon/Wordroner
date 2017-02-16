package com.wordroner.wordroner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        final Button btn_record = (Button) findViewById(R.id.btn_record);

        btn_record.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                String words[] = {"123","123","a","the","cat","cat","cat","dog","apple","a"}; //this is for test

                //
                //add google speech API here
                //



                WordMap wordmap = new WordMap(words);


                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");


                myRef.child("object").setValue(wordmap.ExtractWords());
                //myRef.child("words").child("3").setValue("hello world2");


                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}
