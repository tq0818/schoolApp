package com.yuxin.wx.utils;

import java.text.DecimalFormat;

public class FloatFormatUtil {
    /**
     *
     * @param f
     * @param b
     * @return
     */
    public static float format(float f,int b){
        if (b != 10 && b != 100 && b != 1000 && b != 1000) {
            System.out.println("参数 b 必须是10的倍数，且不为0");
            return f;
        }
        //(float)(Math.round(totalPrice*1000))/1000
        return (float)(Math.round(f*b)/b);
    }

    /**
     *
     * @param f
     * @param format
     * @return
     */
    public static String format(float f,String format){
        DecimalFormat decimalFormat=new DecimalFormat(format);
        return decimalFormat.format(f);
    }

    public static String format(double f,String format){
        DecimalFormat decimalFormat=new DecimalFormat(format);
        return decimalFormat.format(f);
    }
}
