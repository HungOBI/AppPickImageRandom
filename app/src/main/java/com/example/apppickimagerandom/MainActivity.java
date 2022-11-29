package com.example.apppickimagerandom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imgRandom,imgSelect,imgChange;
    TextView tv_point;
    String[] arrIMG;
    int resourceImageRandom=-1;
    int REQUEST_CODE=1;
    int img_select;
    int point=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgRandom=findViewById(R.id.imgRandom);
        imgChange=findViewById(R.id.img_change);
        imgSelect=findViewById(R.id.imgSelect);
        tv_point=findViewById(R.id.tv_point);
        randomImage();
        imgChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomImage();
            }
        });
        imgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("arr",arrIMG);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK) {
            int resource= data.getIntExtra("resource",-1);
            imgSelect.setImageResource(resource);
            if(resource==resourceImageRandom){
                Toast.makeText(this, "Bạn đã chọn đúng", Toast.LENGTH_SHORT).show();
                point +=1;
                tv_point.setText(point+"");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        randomImage();
                    }
                }, 1000);
            }else {
                Toast.makeText(this, "Bạn đã chọn sai,chọn lại đi", Toast.LENGTH_SHORT).show();
            }

        }


    }
    private void randomImage() {
        Random random = new Random();
        arrIMG = getResources().getStringArray(R.array.arr_IMG);
        int indexRandom = random.nextInt(arrIMG.length);
        resourceImageRandom = getResources().getIdentifier(arrIMG[indexRandom], "drawable", getPackageName());
        imgRandom.setImageResource(resourceImageRandom);
    }
}