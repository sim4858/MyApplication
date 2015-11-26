package com.example.com.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Integer[] posterID = {
            R.drawable.akej, R.drawable.aktus, R.drawable.clswjfgksrmawktl, R.drawable.dnflrktkfkdgkstlrks,
            R.drawable.dnjfemdnjwl, R.drawable.dnlgjagkstkdrusfp, R.drawable.ehfdusqusdl, R.drawable.ejvhs,
            R.drawable.fjqmfpxj, R.drawable.ghkdwpfmfdnlgkdu, R.drawable.gjdrjrpdlavkdlsjf, R.drawable.godnseo,
            R.drawable.qnekdrjfo, R.drawable.rhkdgo, R.drawable.rjadmstkwpemf, R.drawable.rkarl, R.drawable.rmshadlek,
            R.drawable.rudwn, R.drawable.sotkfkdsoruxdp, R.drawable.tjsl, R.drawable.votusdhkd, R.drawable.xmrwhd,
            R.drawable.xnfkdlvm, R.drawable.zkxm
    };
    String[] posterName = {"마더", "마션", "친절한금자씨", "우리가사랑한시간", "월드워지", "위험한상견례",
    "돌연변이", "더폰", "러브레터", "황제를위하여", "헝거게임파이널", "해운대", "부당거래", "광해",
    "검은사제들", "감기", "그놈이다", "경주", "내사랑내곁에", "써니", "패션왕", "특종", "투라이프", "카트"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그리드뷰 영화 포스터");

        final GridView gv = (GridView) findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);

    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;

        @Override
        public int getCount() {
            return 24;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            ImageView imageViewIte;
            TextView textViewTitle;
            if(row == null) {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(R.layout.grid_row, parent, false);
            }

            textViewTitle = (TextView) row.findViewById(R.id.gTextView);
            imageViewIte = (ImageView) row.findViewById(R.id.gImageView);

            imageViewIte.setScaleType(ImageView.ScaleType.FIT_CENTER);

            imageViewIte.setImageResource(posterID[position]);
            textViewTitle.setText(posterName[position]);

            final int pos = position;
            imageViewIte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle(posterName[pos]);
                    dlg.setIcon(R.drawable.ic_launcher);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });


            return row;
        }

        public MyGridAdapter(Context c) {
            context = c;
        }
    }

}
