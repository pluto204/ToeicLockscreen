package com.hai.vocalockscreen;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

/**
 * Created by HP on 4/8/2016.
 */

public class DisplayService extends Service{

    WindowManager window;
    TextView voca, phienamText;
    Button one, two, three, four;
    int dapanDung;
    int tuIndex;
    int[] nghiaIndex;

    //new variable
    public int SIZE = 0;
    DictionaryHelper myDbHelper;
    Cursor c = null;
    private String DB_TABLE = null;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();


        window = (WindowManager)getSystemService(WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.FILL_PARENT,
                WindowManager.LayoutParams.FILL_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.x = 0;
        params.y = 0;
        params.gravity = Gravity.CENTER| Gravity.CENTER;

        LayoutInflater li = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        final RelativeLayout theme = (RelativeLayout)li.inflate(R.layout.voca_lockscreen, null);
        window.addView(theme, params);

        Random r = new Random();
        int index = r.nextInt(5);

        switch (index){
            case 0:
                theme.setBackgroundResource(R.drawable.theme1);
                break;
            case 1:
                theme.setBackgroundResource(R.drawable.theme2);
                break;
            case 2:
                theme.setBackgroundResource(R.drawable.theme3);
                break;
            case 3:
                theme.setBackgroundResource(R.drawable.theme4);
                break;
            case 4:
                theme.setBackgroundResource(R.drawable.theme5);
                break;
        }

        switch (MainActivity.CHU_DE_TU_VUNG){
            case 0:
                DB_TABLE = "Hopdong";
                break;
            case 1:
                DB_TABLE = "SuDamBao";
                break;
            case 2:
                DB_TABLE = "ThiTruong";
                break;
            case 3:
                DB_TABLE = "CongViec";
                break;
            case 4:
                DB_TABLE = "GiaiThuong";
                break;
            case 5:
                DB_TABLE = "LuongThuong";
                break;
            case 6:
                DB_TABLE = "MayTinh";
                break;
            case 7:
                DB_TABLE = "MuaSam";
                break;
            case 8:
                DB_TABLE = "NganHang";
                break;
            case 9:
                DB_TABLE = "AmNhac";
                break;
            case 10:
                DB_TABLE = "BaoTang";
                break;
            case 11:
                DB_TABLE = "ChoThue";
                break;
            case 12:
                DB_TABLE = "DuocKhoa";
                break;
            case 13:
                DB_TABLE = "Media";
                break;
            case 14:
                DB_TABLE = "NhaSi";
                break;
            case 15:
                DB_TABLE = "SucKhoe";
                break;
            case 16:
                DB_TABLE = "TuThien";
                break;
            default:
                break;
        }

        //database
        myDbHelper = new DictionaryHelper(DisplayService.this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        SIZE = getCount();
        voca = (TextView)theme.findViewById(R.id.voca);
        phienamText = (TextView)theme.findViewById(R.id.phienam);
        one = (Button)theme.findViewById(R.id.one);
        two = (Button)theme.findViewById(R.id.two);
        three = (Button)theme.findViewById(R.id.three);
        four = (Button)theme.findViewById(R.id.four);

        one.setBackgroundResource(R.drawable.button);
        two.setBackgroundResource(R.drawable.button);
        three.setBackgroundResource(R.drawable.button);
        four.setBackgroundResource(R.drawable.button);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nghiaIndex[0] == tuIndex){
                    window.removeView(theme);
                    stopSelf();
                } else{
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vib.vibrate(300);
                }
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nghiaIndex[1] == tuIndex){
                    window.removeView(theme);
                    stopSelf();
                } else{
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vib.vibrate(300);
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nghiaIndex[2] == tuIndex){
                    window.removeView(theme);
                    stopSelf();
                } else{
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vib.vibrate(300);
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nghiaIndex[3] == tuIndex){
                    window.removeView(theme);
                    stopSelf();
                } else{
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vib.vibrate(300);
                }
            }
        });

        //tao ngau nhien 1 tu
        r = new Random();
        tuIndex = r.nextInt(SIZE);
        voca.setText(getSringDB(tuIndex, 1));
        phienamText.setText(getSringDB(tuIndex, 2));

        //tao ngau nhien dap an dung
        r = new Random();
        dapanDung = r.nextInt(4);
        nghiaIndex = new int[SIZE];
        nghiaIndex[dapanDung] = tuIndex;

        //tao cac dap an sai
        int[] temp = new int[3];
        temp[0] = rdNumber(tuIndex, tuIndex, tuIndex, SIZE);
        temp[1] = rdNumber(tuIndex, tuIndex, temp[0], SIZE);
        temp[2] = rdNumber(tuIndex, temp[0], temp[1], SIZE);
        int k = 0;
        for(int i = 0; i < 4; i++){
            if(i != dapanDung){
                nghiaIndex[i] = temp[k];
                k++;
            }
        }

        //hien 4 dap an
        one.setText(getSringDB(nghiaIndex[0], 3));
        two.setText(getSringDB(nghiaIndex[1], 3));
        three.setText(getSringDB(nghiaIndex[2], 3));
        four.setText(getSringDB(nghiaIndex[3], 3));
    }

    int rdNumber(int num1, int num2, int num3, int size){
        Random r = new Random();
        int num = r.nextInt(size);
        while (num == num1 || num == num2 || num == num3){
            r = new Random();
            num = r.nextInt(size);
        }
        return num;
    }

    private String getSringDB(int index, int column){
        index = index + 1;
        c = myDbHelper.dictDB.rawQuery("SELECT * FROM " + DB_TABLE + " WHERE id=" + index, null);
//        c = myDbHelper.dictDB.rawQuery("SELECT * FROM Hopdong WHERE id=" + index, null);
        c.moveToFirst();
        return c.getString(column);
    }

    private int getCount(){
        Cursor mCount= myDbHelper.dictDB.rawQuery("select * from " + DB_TABLE, null);
//        Cursor mCount= myDbHelper.dictDB.rawQuery("select * from Hopdong", null);
        return mCount.getCount();
    }

}

