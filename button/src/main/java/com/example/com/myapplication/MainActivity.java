package com.example.com.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view){
       // Button button = (Button)findViewById(view.getId());
        switch (view.getId()){
            case R.id.button0: startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("www.nate.com")));
                break;
            case R.id.button1: startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:911")));
                break;
            case R.id.button2: startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/external/images/media")));
                break;
            case R.id.button3: finish();
                break;
        }

    }

}
