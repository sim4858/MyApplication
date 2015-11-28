package com.example.princey.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button button;
    String fileName;
    View dialogView;
    DatePicker dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText1);
        textView = (TextView) findViewById(R.id.textView1);
        button = (Button) findViewById(R.id.btn1);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth + 1) + "_"
                + Integer.toString(cDay) + ".txt";
        String str = readDiary(fileName);
        editText.setText(str);
        fileName = Integer.toString(cYear) + "년 " + Integer.toString(cMonth + 1) + "월 "
                + Integer.toString(cDay) + "일";
        textView.setText(fileName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = editText.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + " 이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogView = (View) View.inflate(MainActivity.this, R.layout.calendar, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("날짜를 선택하세요.");
                dlg.setView(dialogView);
                dp = (DatePicker) dialogView.findViewById(R.id.datePicker1);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = dp.getYear();
                        int monthOfYear = dp.getMonth();
                        int dayOfMonth = dp.getDayOfMonth();
                        fileName = Integer.toString(year) + "_" + Integer.toString(monthOfYear + 1) + "_"
                                + Integer.toString(dayOfMonth) + ".txt";
                        String str = readDiary(fileName);
                        editText.setText(str);
                        fileName = Integer.toString(year) + "년 " + Integer.toString(monthOfYear + 1) + "월 "
                                + Integer.toString(dayOfMonth) + "일";
                        textView.setText(fileName);

                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

    }

    String readDiary(String fileName) {
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fileName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            button.setText("수정");
        }catch(IOException e) {
            editText.setHint("일기 없음");
            button.setText("저장");
        }
        return diaryStr;
    }

}