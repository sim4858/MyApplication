package com.example.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNum1, edtNum2;
    RadioGroup rGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 엑티비티");


        rGroup = (RadioGroup) findViewById(R.id.rGroup);
        Button btnNewActivity = (Button) findViewById(R.id.btnCalc);

        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNum1 = (EditText) findViewById(R.id.edtNum1);
                edtNum2 = (EditText) findViewById(R.id.edtNum2);
                RadioButton rb = (RadioButton)findViewById(rGroup.getCheckedRadioButtonId());
                String method = rb.getText().toString();
                if(method == "") {
                    Toast.makeText(getApplicationContext(), "계산 방법을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num1 = Integer.parseInt(edtNum1.getText().toString());
                int num2 = Integer.parseInt(edtNum2.getText().toString());
                if(method.equals("나누기") && num2 == 0) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없어요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("Num1", num1);
                intent.putExtra("Num2", num2);
                intent.putExtra("Method", method);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            double hap = data.getDoubleExtra("Hap", 0);
            Toast.makeText(getApplicationContext(), "결과 : " + hap, Toast.LENGTH_SHORT).show();
        }
    }
}
