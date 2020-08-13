package project.linkortech.test.utils;

import java.util.Calendar;

public class CalenderUtil {

    public static void clearHMS(Calendar calendar){
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
    }

    public static String formatYMD(Calendar calendar){
        return calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE);
    }

    public static String formatMD(Calendar calendar){
        return (calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE);
    }

    public static String formatFull(Calendar calendar){
        return calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)
                +"-"+calendar.get(Calendar.DATE)+" "+calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)
                +":"+calendar.get(Calendar.SECOND)+"."+calendar.get(Calendar.MILLISECOND);
    }

    /**
     * 间隔多少天  注意 参数的值会更改
     */
    public static long computeIntervalDays(Calendar a,Calendar b){
        clearHMS(a);
        clearHMS(b);
        return (b.getTimeInMillis()-a.getTimeInMillis())/(24*60*60*1000)-1;
    }
}
