package com.basequickdevlibs.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUntil {
	public static String timeStampToDate(long timeStamp){
        Date date = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;  
    }
    public static String timeStampT(long timeStamp){
        Date date = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
    public static int getYearByTimeStamp(long timeStamp){  
        String date = timeStampToDate(timeStamp);
        String year = date.substring(0, 4);
        return Integer.parseInt(year);
    }  
  
    public static String getMonthByTimeStamp(long timeStamp){
        String date = timeStampToDate(timeStamp);
        String month = date.substring(5, 7);
        return month;  
    }  
  
    public static String getDayByTimeStamp(long timeStamp){
        String date = timeStampToDate(timeStamp);
        String day = date.substring(8, 10);
        return day;  
    }  
  
    public static String getHourByTimeStamp(long timeStamp){
        String date = timeStampToDate(timeStamp);
        String hour = date.substring(11, 13);
        return hour;  
    }  
  
    public static String getMinuteByTimeStamp(long timeStamp){
        String date = timeStampToDate(timeStamp);
        System.out.println("date>>"+date.toString());
        String minute = date.substring(14, 16);
        return minute;  
    }  
  
    public static String getSecondByTimeStamp(long timeStamp){
        String date = timeStampToDate(timeStamp);
        String second = date.substring(17, 19);
        return second;  
    }
    public static int getDays(long timeStamp) {
        int date = new Date(timeStamp).getDay();
        return date;
    }
    public static String getDay(long timeStamp){
		int date= new Date(timeStamp).getDay();
		String xq="";
		switch (date) {
		case 1:
			xq="星期一";
			break;
            case 2:
            	xq="星期二";
			break;
       case 3:
    	   xq="星期三";
	    break;
       case 4:
    	   xq="星期四";
	break;
      case 5:
    	  xq="星期五";
	break;
    case 6:
    	xq="星期六";
	break;
     case 0:
    	 xq="星期天";
	break;

	
		}
		return xq;
	}
    
    
    /** 
     * <pre> 
     * 根据指定的日期字符串获取星期几 
     * </pre> 
     *  
     *
     * @return week 
     *         星期几(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY) 
     */  
    public static String getWeekByDateStr(String dateStr)
    {  
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int month = Integer.parseInt(dateStr.substring(5, 7));
        int day = Integer.parseInt(dateStr.substring(8, 10));
      
        Calendar c = Calendar.getInstance();
      
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
      
        String week = "";
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);
      
        switch (weekIndex)  
        {  
        case 1:  
            week = "星期日";  
            break;  
        case 2:  
            week = "星期一";  
            break;  
        case 3:  
            week = "星期二";  
            break;  
        case 4:  
            week = "星期三";  
            break;  
        case 5:  
            week = "星期四";  
            break;  
        case 6:  
            week = "星期五";  
            break;  
        case 7:  
            week = "星期六";  
            break;  
        }  
        return week;  
    }  
    //获取时间间隔秒
    public static long getTimeCounts(String tim){
        long time1= Long.parseLong(tim)*1000;
        long time2=new Date().getTime();
        long time=(time2-time1)/1000;
        return time;
    } 
}
