package com.ticket.damaitickettest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String convertLongTimeToString(long time){
        Date date = new Date(time);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
