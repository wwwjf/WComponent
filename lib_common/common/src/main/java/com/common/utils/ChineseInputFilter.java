package com.common.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 过滤中文字符
 *
 * @author wengjianfeng on 2020/3/4.
 * InputFilter[] filters = {new ChineseInputFilter()};
 **/
public class ChineseInputFilter implements InputFilter {
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (TextUtils.isEmpty(source)) {
            return "";
        }

        for (int i = start; i < end; i++) {
            if (stringFilterChinese(source) && !source.toString().contains("。") && !source.toString().contains("，")) {
                return "";
            } else if (CHINESE_RADICAL_DIGISTS.contains(source)) {
                return "";
            }
        }
        return null;
    }


    /**
     * 限制不能能输入汉字，过滤特殊字符
     *
     * @param str 输入值
     * @return true 汉字；false 非汉字
     */
    public boolean stringFilterChinese(CharSequence str) {
        //只允许汉字，正则表达式匹配出所有非汉字
        Pattern p = Pattern.compile(DEFAULT_REGEX_LIMIT_CHINESE);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 默认的筛选条件(正则:中文字符)
     */
    public static String DEFAULT_REGEX_LIMIT_CHINESE = "[\u4E00-\u9FA5]";

    /**
     * 偏旁部首
     */
    public static final String CHINESE_RADICAL_DIGISTS = "[犭凵巛冖氵廴纟讠礻亻钅宀亠忄辶弋饣刂阝冫卩疒艹疋豸冂匸扌丬屮衤勹彳彡]";
}
