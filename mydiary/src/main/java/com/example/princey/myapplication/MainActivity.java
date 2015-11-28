package com.example.princey.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    // 각 위젯들과 사용할 변수들을 선언합니다.
    EditText editText;
    TextView textView;
    Button button;
    String fileName, textCom;
    View dialogView;
    DatePicker dp;
    String sdPath;
    File myDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // 위젯들과 변수들을 초기화합니다.
        editText = (EditText) findViewById(R.id.editText1);
        textView = (TextView) findViewById(R.id.textView1);
        button = (Button) findViewById(R.id.btn1);

        sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        myDir = new File(sdPath + "/mydir");
        myDir.mkdir();  // 처음 시작 시 폴더를 생성해줍니다.
        sdPath = sdPath + "/mydir";  // 앞으로 해당 위치에 파일을 입출력 할 것입니다.

        Calendar cal = Calendar.getInstance();   // 처음 실행 시 현재 날짜에 해당하는 일기를 불러옵니다.
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth + 1) + "_"
                + Integer.toString(cDay) + ".txt";
        String str = readDiary(fileName);
        editText.setText(str);
        textCom = Integer.toString(cYear) + "년 " + Integer.toString(cMonth + 1) + "월 "
                + Integer.toString(cDay) + "일";
        textView.setText(textCom);

        // 버튼 클릭 시 작성한 내용을 저장합니다.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outFs = openFileOutput(sdPath + "/" + fileName, Context.MODE_PRIVATE);
                    String str = editText.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + " 이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        // 텍스트 뷰(날짜) 클릭 시 날짜를 선택하는 다이어로그를 만들어줍니다.
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
                        textCom = Integer.toString(year) + "년 " + Integer.toString(monthOfYear + 1) + "월 "
                                + Integer.toString(dayOfMonth) + "일";
                        textView.setText(textCom);

                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

    }

    // 파일 이름에 해당하는 파일을 지정된 위치에서 찾아 오픈합니다.
    String readDiary(String fileName) {
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(sdPath + "/" + fileName);
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

    // 메뉴를 만드는 기본 메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    // 각 메뉴 클릭 시 해당하는 이벤트를 활성화합니다.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.reRead:
                readDiary(fileName);
                return true;
            case R.id.deleteDiary:
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle(textCom + " 일기를 삭제하시겠습니까?");
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File deleteFile = new File(sdPath + "/mydir/" + fileName);
                        deleteFile.delete();
                        editText.setText("");
                        button.setText("저장");
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
                return true;
            case R.id.bigSize:
                editText.setTextSize(25);
                return true;
            case R.id.nomalSize:
                editText.setTextSize(20);
                return true;
            case R.id.smallSize:
                editText.setTextSize(15);
                return true;
        }
        return false;
    }
}