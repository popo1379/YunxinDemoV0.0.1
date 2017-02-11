package com.palmintelligence.administrator.yunxindemov001.addfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.palmintelligence.administrator.yunxindemov001.R;

/**
 * Created by Administrator on 2017/2/7 0007.
 */
public class AddSuccess extends AppCompatActivity {
private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_success);
        textView=(TextView)findViewById(R.id.friend_id);


        Intent intent = this.getIntent();
       textView.setText(intent.getStringExtra("ID"));

    }
}