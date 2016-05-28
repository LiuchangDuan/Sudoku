package com.example.sudoku;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2016/5/28.
 */

//该类用于实现Dialog，实现自定义的对话框功能
public class KeyDialog extends Dialog {

    //用来存放代表对话框当中按钮的对象
    private final View keys[] = new View[9];

    private final int used[];

    /**
     *
     * @param context
     * @param used 保存当前单元格已经使用过的数字
     */
    public KeyDialog(Context context, int[] used) {
        super(context);
        this.used = used;
    }

    /**
     * 当一个Dialog第一次显示的时候
     * 会调用其onCreate()方法
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("KeyDialog");
        setContentView(R.layout.dialog);
        findViews();

        for (int i = 0; i < used.length; i++) {
            if (used[i] != 0) {
                //把已经用过的（数字的按钮）设置成不可见
                keys[used[i] - 1].setVisibility(View.INVISIBLE);
            }
        }

    }

    private void findViews() {
        keys[0] = findViewById(R.id.keypad_1);
        keys[1] = findViewById(R.id.keypad_2);
        keys[2] = findViewById(R.id.keypad_3);
        keys[3] = findViewById(R.id.keypad_4);
        keys[4] = findViewById(R.id.keypad_5);
        keys[5] = findViewById(R.id.keypad_6);
        keys[6] = findViewById(R.id.keypad_7);
        keys[7] = findViewById(R.id.keypad_8);
        keys[8] = findViewById(R.id.keypad_9);
    }

}
