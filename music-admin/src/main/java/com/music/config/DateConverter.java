package com.music.config;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换器
 * @author xpq2013@aliyun.com
 * @version 1.0.0
 * @date 2018/5/10
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        String value = s.trim();
        if ("".equals(value)) {
            return null;
        }
        if (s.matches("^\\d{4}-\\d{1,2}$")) {
            return parse(s, "yyyy-MM");
        } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parse(s, "yyyy-MM-dd");
        } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parse(s, "yyyy-MM-dd hh:mm");
        } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parse(s, "yyyy-MM-dd hh:mm:ss");
        } else {
            throw new IllegalArgumentException("日期转换失败！");
        }
    }

    private Date parse(String str, String fmt) {
        DateFormat format = new SimpleDateFormat(fmt);
        Date date = null;
        try {
            date = format.parse(str);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
