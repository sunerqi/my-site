package cn.sunhl.utils;

import java.util.Random;

/**
 * @Author: sunhailong
 * @Date: 2020/1/13 下午3:23
 * @Desc: 字符串处理类
 */
public class StringUtils {

    /**
     * 获取文件扩展名,返回示例：jpg
     *
     * @param fileName 文件名
     * @return 文件扩展名
     */
    public static String getFileExt(String fileName) {
        int start = 0;
        int end = 0;
        if (fileName == null) {
            return "";
        }
        start = fileName.lastIndexOf(46) + 1;
        end = fileName.length();
        String value = fileName.substring(start, end);
        if (fileName.lastIndexOf(46) > 0) {
            return value.toLowerCase();
        }
        return "";
    }

    /**
     * 将字符串的首字母变成大写
     *
     * @param str 字符串
     * @return
     */
    public static String firstUpper(String str) {
        if (null == str || str.isEmpty()) {
            return "";
        }
        return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
    }

    /**
     * 生成随机数字
     *
     * @param length 数字长度
     * @return
     */
    public static String getRandomCode(int length) {
        Random rd = new Random();
        String n = "";
        int getNum;
        do {
            getNum = Math.abs(rd.nextInt()) % 10 + 48;//产生数字0-9的随机数
            //getNum = Math.abs(rd.nextInt())%26 + 97;//产生字母a-z的随机数
            char num1 = (char) getNum;
            String dn = Character.toString(num1);
            n += dn;
        } while (n.length() < length);
        return n;
    }
}
