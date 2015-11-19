package com.example.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by com on 2015-11-19.
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Second 엑티비티");


        Intent inIntent = getIntent();
        double num1 = (double)inIntent.getIntExtra("Num1", 0);
        double num2 = (double)inIntent.getIntExtra("Num2", 0);
        double result = 0.0;
        String method = inIntent.getStringExtra("Method");
        switch(method) {
            case "더하기":
                result = num1 + num2;
                break;
            case "빼기":
                result = num1 - num2;
                break;
            case "곱하기":
                result = num1 * num2;
                break;
            case "나누기":
                result = num1 / num2;
                break;
        }

        final double hapValue = result;
        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("Hap", hapValue);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });

    }
}
