package com.example.com.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    TextView textResult;
    String inputNum1, inputNum2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("초간단 계산기");

        edit1 = (EditText) findViewById(R.id.Edit1);
        edit2 = (EditText) findViewById(R.id.Edit2);
        textResult = (TextView) findViewById(R.id.TextResult);
    }

    public void onClick(View view){
        // Button button = (Button)findViewById(view.getId());

        inputNum1 = edit1.getText().toString();
        inputNum2 = edit2.getText().toString();

        if(inputNum1.isEmpty() || inputNum2.isEmpty()) {
            Toast.makeText(getApplicationContext(), "입력해 주세요... ㅜㅜ", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1 = Double.parseDouble(inputNum1);
        double num2 = Double.parseDouble(inputNum2);

        switch (view.getId()){
            case R.id.BtnAdd: result = num1 + num2;
                break;
            case R.id.BtnSub: result =  num1 - num2;
                break;
            case R.id.BtnMul: result =  num1 * num2;
                break;
            case R.id.BtnDiv:
                if(num2 == 0.0) {
                    Toast.makeText(getApplicationContext(), "0은 안돼요 ㅜㅜ", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = num1 / num2;
                break;
            case R.id.BtnRem:
                if(num2 == 0.0) {
                    Toast.makeText(getApplicationContext(), "0은 안돼요 ㅜㅜ", Toast.LENGTH_SHORT).show();
                    return;
                }
                result =  num1 % num2;
                break;
        }

        textResult.setText("계산 결과 : " + result.toString());
    }
}
