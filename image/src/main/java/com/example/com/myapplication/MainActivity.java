package com.example.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch swtStart;
    TextView text2;
    RadioGroup rGroup1;
    RadioButton rJel, rKit, rLol;
    ImageView imgAnd;
    Button btnEnd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("안드로이드 사진 보기");

        swtStart = (Switch) findViewById(R.id.SwtStart);
        text2 = (TextView) findViewById(R.id.Text2);
        rGroup1 = (RadioGroup) findViewById(R.id.Rgroup1);

        rJel = (RadioButton) findViewById(R.id.RdoJel);
        rKit = (RadioButton) findViewById(R.id.RdoKit);
        rLol = (RadioButton) findViewById(R.id.RdoLol);

        imgAnd = (ImageView) findViewById(R.id.ImgAnd);

        btnEnd = (Button) findViewById(R.id.BtnEnd);
        btnBack = (Button) findViewById(R.id.BtnBack);

//        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
//                if (chkAgree.isChecked() == true) {
//                    text2.setVisibility(View.VISIBLE);
//                    rGroup1.setVisibility(View.VISIBLE);
//                    btnOK.setVisibility(View.VISIBLE);
//                    imgPet.setVisibility(View.VISIBLE);
//                } else {
//                    text2.setVisibility(View.INVISIBLE);
//                    rGroup1.setVisibility(View.INVISIBLE);
//                    btnOK.setVisibility(View.INVISIBLE);
//                    imgPet.setVisibility(View.INVISIBLE);
//                }
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view){
        // Button button = (Button)findViewById(view.getId());
        switch (view.getId()){
            case R.id.BtnEnd:finish();
                case R.id.RdoJel:
                    imgAnd.setImageResource(R.drawable.dog);
                    break;
                case R.id.RdoKit:
                    imgAnd.setImageResource(R.drawable.cat);
                    break;
                case R.id.RdoLol:
                    imgAnd.setImageResource(R.drawable.rabbit);
                    break;
            case R.id.BtnBack:
                swtStart.setChecked(false);
                text2.setVisibility(View.INVISIBLE);
                rGroup1.setVisibility(View.INVISIBLE);
                btnBack.setVisibility(View.INVISIBLE);
                btnEnd.setVisibility(View.INVISIBLE);
                imgAnd.setVisibility(View.INVISIBLE);
                imgAnd.setImageResource(0);
                rJel.setChecked(false);
                rKit.setChecked(false);
                rLol.setChecked(false);
                break;
            case R.id.SwtStart:
                if(swtStart.isChecked() == true) {
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    btnBack.setVisibility(View.VISIBLE);
                    btnEnd.setVisibility(View.VISIBLE);
                    imgAnd.setVisibility(View.VISIBLE);
                }
                else {
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    btnBack.setVisibility(View.INVISIBLE);
                    btnEnd.setVisibility(View.INVISIBLE);
                    imgAnd.setVisibility(View.INVISIBLE);
                    imgAnd.setImageResource(0);
                    rJel.setChecked(false);
                    rKit.setChecked(false);
                    rLol.setChecked(false);
                }
                break;
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
