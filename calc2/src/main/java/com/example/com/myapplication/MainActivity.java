package com.example.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    String num1, num2;
    TextView textResult;
    Integer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = (EditText) findViewById(R.id.Edit1);
        edit2 = (EditText) findViewById(R.id.Edit2);

        textResult = (TextView) findViewById(R.id.TextResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view) {
        num1 = edit1.getText().toString();
        num2 = edit2.getText().toString();
        int intNum1, intNum2;
        if(num1 == "" || num2 == "") {
            Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        intNum1 = Integer.parseInt(num1);
        intNum2 = Integer.parseInt(num2);
        switch(view.getId()) {
            case R.id.BtnAdd:
                result = intNum1 + intNum2;
                textResult.setText("계산 결과 : " + result.toString());
                break;
            case R.id.BtnSub:
                result = intNum1 - intNum2;
                textResult.setText("계산 결과 : " + result.toString());
                break;
            case R.id.BtnMul:
                result = intNum1 * intNum2;
                textResult.setText("계산 결과 : " + result.toString());
                break;
            case R.id.BtnDiv:
                if(intNum2 == 0){
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없어요", Toast.LENGTH_SHORT).show();
                    return;
                }
                Double result2 = (double)intNum1 / intNum2;
                textResult.setText("계산 결과 : " + result2.toString());
                break;
        }
    }

    public void onClickNum(View view) {

        Button temp = (Button)view;
        if(edit1.isFocused() == true) {
            num1 = edit1.getText().toString() + temp.getText().toString();
            edit1.setText(num1);
        }

        else if(edit2.isFocused() == true) {
            num2 = edit2.getText().toString() + temp.getText().toString();
            edit2.setText(num2);
        }

        else {
            Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}
