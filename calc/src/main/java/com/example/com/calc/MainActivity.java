package com.example.com.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRem;
    TextView textResult;
    String num1, num2;
    Integer result;


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

        num1 = edit1.getText().toString();
        num2 = edit2.getText().toString();

        switch (view.getId()){
            case R.id.BtnAdd:
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                break;
            case R.id.BtnSub:
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                break;
            case R.id.BtnMul:
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                break;
            case R.id.BtnDiv:
                if(num2 == "0")
                    return;
                result = Integer.parseInt(num1) / Integer.parseInt(num2);
                break;
            case R.id.BtnRem:
                if(num2 == "0")
                    return;
                result = Integer.parseInt(num1) % Integer.parseInt(num2);
                break;
        }

        textResult.setText("계산 결과 : " + result.toString());

    }
}
