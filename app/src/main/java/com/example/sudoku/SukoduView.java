package com.example.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/5/26.
 */
public class SukoduView extends View {

    //单元格的宽度和高度
    private float width;
    private float height;

    //所选择的单元格的位置
    private int selectedX;
    private int selectedY;

    private Game game = new Game();

    public SukoduView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return super.onTouchEvent(event);
        }

        selectedX = (int) (event.getX() / width);
        selectedY = (int) (event.getY() / height);

        int used[] = game.getUsedNumberByCoor(selectedX, selectedY);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < used.length; i++) {
            sb.append(used[i]);
//            System.out.println(used[i]);
        }

//        //自定义Dialog
//        //生成一个LayoutInflater对象
//        LayoutInflater inflater = LayoutInflater.from(this.getContext());
//        //使用LayoutInflater对象根据一个布局文件，生成一个View
//        View layoutView = inflater.inflate(R.layout.dialog, null);
//        //从生成好的TextView当中，取出相应的控件
//        TextView textView = (TextView) layoutView.findViewById(R.id.usedTextId);
//        textView.setText(sb.toString());
//        //生成一个对话框的Builder对象
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
//        //设置对话框所要显示的内容
//        builder.setView(layoutView);
//        //生成对话框对象，并将其显示出来
//        AlertDialog dialog = builder.create();
//        dialog.show();

        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //计算当前单元格的宽度和高度
        this.width = w / 9f;
        this.height = h / 9f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //绘制背景
        //生成用于绘制背景色的画笔
        Paint backgroundPaint = new Paint();

//        backgroundPaint.setColor(getResources().getColor(R.color.sudoku_background, null));
//        backgroundPaint.setColor(getResources().getColor(R.color.sudoku_background)); // deprecated
        //设置画笔的颜色
        backgroundPaint.setColor(ContextCompat.getColor(this.getContext(), R.color.sudoku_background));
        //绘制背景色
        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);

        //醒目颜色
        Paint hilitePaint = new Paint();
        hilitePaint.setColor(ContextCompat.getColor(getContext(), R.color.sudoku_hilite));

        Paint lightPaint = new Paint();
        lightPaint.setColor(ContextCompat.getColor(getContext(), R.color.sudoku_light));

        Paint darkPaint = new Paint();
        darkPaint.setColor(ContextCompat.getColor(getContext(), R.color.sudoku_dark));

        //两条颜色深浅的线相隔一像素，造成一种线嵌入布局的感觉（凹槽效果）
        for (int i = 0; i < 9; i++) {
            //横向的单元格线
            canvas.drawLine(0, i * height, getWidth(), i * height, lightPaint);
            canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilitePaint);
            //纵向的单元格线
            canvas.drawLine(i * width, 0, i * width, getHeight(), lightPaint);
            canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilitePaint);
        }

        //深色的线用于分成九个格子
        for (int i = 0; i < 9; i++) {
            if (i % 3 != 0) {
                continue;
            }
            //横向的单元格线
            canvas.drawLine(0, i * height, getWidth(), i * height, darkPaint);
            canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilitePaint);
            //纵向的单元格线
            canvas.drawLine(i * width, 0, i * width, getHeight(), darkPaint);
            canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilitePaint);
        }

        //画数字
        Paint numberPaint = new Paint();
        numberPaint.setColor(Color.BLACK);
        numberPaint.setStyle(Paint.Style.STROKE);
        numberPaint.setTextSize(height * 0.75f);
        numberPaint.setTextAlign(Paint.Align.CENTER);

        //设置显示数字偏移量
        Paint.FontMetrics fm = numberPaint.getFontMetrics();
        float x = width / 2;
        //ascent：基准线到字符上面的距离 为负
        //descent：基准线到字符下面的距离 为正
        float y = height / 2 - (fm.ascent + fm.descent) / 2;
//        canvas.drawText("1", 3 * width + x, y, numberPaint);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                canvas.drawText(game.getTitleString(i, j), i * width + x, j * height + y, numberPaint);
            }
        }

        super.onDraw(canvas);
    }
}
