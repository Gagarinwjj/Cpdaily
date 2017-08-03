package com.wisedu.cpdaily.utils;

import android.text.TextUtils;

/**
 * 表情工具，参考http://www.cnblogs.com/hahahjx/p/4522913.html
 */
public class EmojiUtils {

    // 转义时标识
    private static final char unicode_separator = '&';
    private static final char unicode_prefix = 'u';
    private static final char separator = ':';

    private static boolean isEmoji(int codePoint) {
        return (codePoint >= 0x2600 && codePoint <= 0x27BF) // 杂项符号与符号字体
                || codePoint == 0x303D
                || codePoint == 0x2049
                || codePoint == 0x203C
                || (codePoint >= 0x2000 && codePoint <= 0x200F)//
                || (codePoint >= 0x2028 && codePoint <= 0x202F)//
                || codePoint == 0x205F //
                || (codePoint >= 0x2065 && codePoint <= 0x206F)//
                /* 标点符号占用区域 */
                || (codePoint >= 0x2100 && codePoint <= 0x214F)// 字母符号
                || (codePoint >= 0x2300 && codePoint <= 0x23FF)// 各种技术符号
                || (codePoint >= 0x2B00 && codePoint <= 0x2BFF)// 箭头A
                || (codePoint >= 0x2900 && codePoint <= 0x297F)// 箭头B
                || (codePoint >= 0x3200 && codePoint <= 0x32FF)// 中文符号
                || (codePoint >= 0xD800 && codePoint <= 0xDFFF)// 高低位替代符保留区域
                || (codePoint >= 0xE000 && codePoint <= 0xF8FF)// 私有保留区域
                || (codePoint >= 0xFE00 && codePoint <= 0xFE0F)// 变异选择器
                || codePoint >= 0x10000; // Plane在第二平面以上的，char都不可以存，全部都转
    }

    /**
     * 将带有emoji字符的字符串转换成可见字符标识
     */
    public static String convert(String src) {
        if (TextUtils.isEmpty(src)) {
            return "";
        }
        int length = src.length();//长度,1个emoji字符=2个长度
        //int cpCount = src.codePointCount(0, length);//有几个字符
        StringBuilder sb = new StringBuilder(length);
        for (int index = 0; index < length; index++) {
            int codePoint = src.codePointAt(index);
            if (isEmoji(codePoint)) {
                String hash = Integer.toHexString(codePoint);
                sb.append(unicode_separator)
                        .append(hash.length())
                        .append(unicode_prefix)
                        .append(separator)
                        .append(hash);
            } else {
                sb.append((char) codePoint);
            }
        }
        return sb.toString();
    }

}