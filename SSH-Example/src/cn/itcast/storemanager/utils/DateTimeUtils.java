package cn.itcast.storemanager.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//日期工具类
//获取日期、日期转字符串、字符串转日期、各种格式的日期或者时间
public class DateTimeUtils {

    //获取当前日期时间的字符串
    public static String getCurrentDateTimeString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

}
