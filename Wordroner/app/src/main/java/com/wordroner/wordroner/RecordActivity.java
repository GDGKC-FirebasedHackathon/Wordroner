package com.wordroner.wordroner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        final Button btn_record = (Button) findViewById(R.id.btn_record);

        btn_record.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                String words[] = {"123","123","a","the","cat","cat","cat","dog","apple","a"};
                WordMap wordmap = new WordMap(words);

                //
                //add google speech API here
                //

                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}
