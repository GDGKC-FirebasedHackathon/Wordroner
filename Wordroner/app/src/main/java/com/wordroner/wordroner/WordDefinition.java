package com.wordroner.wordroner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import static com.wordroner.wordroner.R.id.button3;

/**
 * Created by seohwan on 2017. 2. 18..
 */

public class WordDefinition extends AppCompatActivity  {
    Dictionary dic = new Dictionary();
    TextView textView1 ;
    Button btn_back1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);
        Intent intent = getIntent();
        String word = intent.getStringExtra("text");
        btn_back1 = (Button) findViewById(button3);
        textView1= (TextView) findViewById(R.id.textView1);
        try {
            textView1.setText(dic.ShowDefinitions(word));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        btn_back1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VocabularyActivity.class);
                startActivity(intent);
                finish();
            }
        });





        //go to record activity

    }


}
