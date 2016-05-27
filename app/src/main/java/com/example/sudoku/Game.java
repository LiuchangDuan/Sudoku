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

    //用于存储每个单元格已经不可用（存在）的数据
    //第三维存储已经不可用的数据
    private int used[][][] = new int[9][9][];

    public Game() {
        sudoku = fromPuzzleString(str);
        calculateAllUsedNumber();
    }

    /**
     * 用于计算所有单元格对应的不可用数据
     */
    public void calculateAllUsedNumber() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                used[x][y] = calculateUsedNumber(x, y);
            }
        }
    }

    /**
     * 取出某一单元格中已经不可用的数据
     * @param x
     * @param y
     * @return
     */
    public int[] getUsedNumberByCoor(int x, int y) {
        return used[x][y];
    }

    /**
     * 计算某一单元格已经不可以使用的数据
     * （横、竖、小九宫格里已经出现的数据）
     * @param x
     * @param y
     * @return
     */
    public int[] calculateUsedNumber(int x, int y) {

        int tempUsedNumberArray[] = new int[9];

        //得到横向已有的数据
        for (int i = 0; i < 9; i++) {
            if (i == x) {
                continue;
            }
            int t = getNumber(i, y);
            if (t != 0) {
                tempUsedNumberArray[t - 1] = t;
            }
        }

        //得到纵向已有的数据
        for (int i = 0; i < 9; i++) {
            if (i == y) {
                continue;
            }
            int t = getNumber(x, i);
            if (t != 0) {
                tempUsedNumberArray[t - 1] = t;
            }
        }

        //得到（小）9宫格已经有的数据
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (i == x && j == y) {
                    continue;
                }
                int t = getNumber(i ,j);
                if (t != 0) {
                    tempUsedNumberArray[t - 1] = t;
                }
            }
        }

        //去掉c中的0 压缩数据
        int count = 0;
        //计算数组c中非0的个数
        for (int i : tempUsedNumberArray) {
            if (i != 0) {
                count++;
            }
        }
        //定义一个新的不含0的数组
        int[] usedNumberArray = new int[count];
        count = 0;
        for (int i : tempUsedNumberArray) {
            if (i != 0) {
                usedNumberArray[count++] = i;
            }
        }

        return usedNumberArray;
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
