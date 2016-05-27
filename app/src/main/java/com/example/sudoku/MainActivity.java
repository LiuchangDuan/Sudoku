package com.example.sudoku;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(new SukoduView(this));
        if (mContext == null) {
            mContext = getApplicationContext();
        }
    }

    public static Context getContext() {
        return mContext;
    }

}
