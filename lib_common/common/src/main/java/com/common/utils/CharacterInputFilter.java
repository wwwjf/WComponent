package com.common.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 只允许输入数字和字母
 *
 * @author wengjianfeng on 2020/3/4.
 **/
public class CharacterInputFilter implements InputFilter {

    /**
     * 数字和字母
     */
    private static final String CHARACTER_DIGISTS = "^[A-Za-z0-9]+$";


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (TextUtils.isEmpty(source)) {
            return "";
        }
        Pattern r = Pattern.compile(CHARACTER_DIGISTS);
        Matcher m = r.matcher(source);
        System.out.println(m.matches());
        if (!m.matches()) {
            return "";
        }
        return null;
    }
}
