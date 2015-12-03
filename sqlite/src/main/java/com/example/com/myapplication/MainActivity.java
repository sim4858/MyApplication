package com.example.com.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper;
    EditText edtName, edtNumber;
    ListView edtNameResult, edtNumberResult;
    Button btnInit, btnInsert, btnModify, btnDelete, btnSelect;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        final ArrayList<String> nameList = new ArrayList<String>();
        final ArrayList<String> numberList = new ArrayList<String>();

        final ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nameList);
        final ArrayAdapter<String> numberAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numberList);

        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        edtNameResult = (ListView) findViewById(R.id.edtNameResult);
        edtNumberResult = (ListView) findViewById(R.id.edtNumberResult);
        btnInit = (Button) findViewById(R.id.btnInit);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnModify = (Button) findViewById(R.id.btnModify);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSelect = (Button) findViewById(R.id.btnSelect);

        edtNameResult.setAdapter(nameAdapter);
        edtNumberResult.setAdapter(numberAdapter);

        myHelper = new myDBHelper(this);
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                nameList.clear();
                numberList.clear();
                nameAdapter.notifyDataSetChanged();
                numberAdapter.notifyDataSetChanged();
                sqlDB.close();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameList.contains(edtName.getText().toString()) == true) {
                    Toast.makeText(getApplicationContext(), "데이터가 중복됩니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '" + edtName.getText().toString() + "'," + edtNumber.getText().toString() + ");");
                sqlDB.close();
                nameList.add(edtName.getText().toString());
                numberList.add(edtNumber.getText().toString());
                nameAdapter.notifyDataSetChanged();
                numberAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "입력됨", Toast.LENGTH_SHORT).show();
            }
        });
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameList.contains(edtName.getText().toString()) != true) {
                    Toast.makeText(getApplicationContext(), "그러한 데이터가 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE groupTBL SET gNumber=" + edtNumber.getText().toString() + " WHERE gName='"
                        + edtName.getText().toString() + "';");
                sqlDB.close();
                int index = nameList.indexOf(edtName.getText().toString());
                numberList.set(index, edtNumber.getText().toString());
                numberAdapter.notifyDataSetChanged();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameList.contains(edtName.getText().toString()) != true) {
                    Toast.makeText(getApplicationContext(), "그러한 데이터가 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int index = nameList.indexOf(edtName.getText().toString());
                if(numberList.get(index).equals(edtNumber.getText().toString()) != true) {
                    Toast.makeText(getApplicationContext(), "그러한 데이터가 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM groupTBL WHERE gName='"
                        + edtName.getText().toString() + "';");
                sqlDB.close();
                nameList.remove(index);
                numberList.remove(index);
                nameAdapter.notifyDataSetChanged();
                numberAdapter.notifyDataSetChanged();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameAdapter.notifyDataSetChanged();
                numberAdapter.notifyDataSetChanged();
            }
        });

    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL (gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }

}
