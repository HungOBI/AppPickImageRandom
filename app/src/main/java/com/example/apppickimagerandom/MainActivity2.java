package com.example.apppickimagerandom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Arrays;
import java.util.Collections;


public class MainActivity2 extends AppCompatActivity {
    TableLayout tableLayout;
    String[] arrIMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tableLayout=(TableLayout)findViewById(R.id.table_layout);
        Intent intent=getIntent();
        arrIMG = intent.getStringArrayExtra("arr");
        if (arrIMG == null || arrIMG.length == 0)
            return;
        Collections.shuffle(Arrays.asList(arrIMG));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int k = 0;
        int column = 3;
        int row = (int) Math.ceil(arrIMG.length / 3);

        for (int i = 0; i < row; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < column; j++) {
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(width / 5,width / 3);
                ImageView imageView = new ImageView(this);
                int resourceImage = getResources().getIdentifier(arrIMG[k], "drawable", getPackageName());
                imageView.setImageResource(resourceImage);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent();
                        intent1.putExtra("resource", resourceImage);
                        setResult(RESULT_OK, intent1);
                        finish();
                    }
                });
                layoutParams.gravity =  Gravity.CENTER;
                imageView.setLayoutParams(layoutParams);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                tableRow.addView(imageView);
                k++;
            }
            tableLayout.addView(tableRow);
        }
    }


}