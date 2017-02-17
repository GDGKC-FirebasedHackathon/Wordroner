package com.wordroner.wordroner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Button btn_record = (Button) findViewById(R.id.btn_record);
        final Button btn_voca = (Button) findViewById(R.id.btn_voca);

        //go to record activity
        btn_record.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
                startActivity(intent);
            }
        });

        //go to vocabulary activity
        btn_voca.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VocabularyActivity.class);
                startActivity(intent);
            }
        });
    }


}
