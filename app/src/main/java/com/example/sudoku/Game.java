package com.example.sudoku;

/**
 * Created by Administrator on 2016/5/27.
 */
public class Game {

    //数独初始化数据
    private final String str = "360000000004230800000004200"
            +  "070460003820000014500013020"
            +  "001900000007048300000000045";

    private int[] sudoku = new int[9 * 9];

    public Game() {
        sudoku = fromPuzzleString(str);
    }

    /**
     * 根据坐标 返回数组的数字
     * @param x
     * @param y
     * @return
     */
    private int getNumber(int x, int y) {
        return sudoku[y * 9 + x];
    }

    /**
     * 根据坐标 返回对应数字的字符串
     * @param x
     * @param y
     * @return
     */
    public String getTitleString(int x, int y) {
        int value = getNumber(x, y);
        if (value == 0) {
            return "";
        } else {
            return String.valueOf(value);
        }
    }

    /**
     * 根据字符串数组 生成整型数组
     * @param src
     * @return
     */
    protected int[] fromPuzzleString(String src) {
        int[] sudo = new int[src.length()];
        for (int i = 0; i < sudo.length; i++) {
            sudo[i] = src.charAt(i) - '0';
        }
        return sudo;
    }

}
