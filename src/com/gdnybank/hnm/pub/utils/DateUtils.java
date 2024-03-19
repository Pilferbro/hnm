package com.gdnybank.hnm.pub.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

	/**
	 * 返回date加上count天所对应的日期,count可以为正或负数
	 * @param date 输入日期
	 * @param count 增减天数
	 * @return
	 */
	public static Date addDay(Date date, int count) {
		return compute(date, count, 5);
	}

	/**
	 * 返回date加上count周所对应的日期,count可以为正或负数
	 * @param date 输入日期
	 * @param count 增减天数
	 * @return
	 */
	public static Date addWeek(Date date, int count) {
		return  compute(date, count, 3);
	}

	/**
	 * 返回date加上count月所对应的日期,count可以为正或负数
	 * @param date 输入日期
	 * @param count 增减天数
	 * @return
	 */
	public static Date addMonth(Date date, int count) {
		return compute(date, count,2);
	}

	/**
	 * 返回date加上count月所对应的日期,count可以为正或负数
	 * @param date 输入日期
	 * @param count 增减分钟数
	 * @return
	 */
	public static Date addMinute(Date date, int count) {
		return compute(date, count,12);
	}

	/**
	 * 返回date加上count月所对应的日期,count可以为正或负数
	 * @param date 输入日期
	 * @param count 增减小时数
	 * @return
	 */
	public static Date addHour(Date date, int count) {
		return compute(date, count,10);
	}

	/**
	 * 把日期由字符串转化为Date类型
	 * @param str 日期字符串
	 * @param format 字符串的格式,如yyyyMMdd
	 * @return
	 */
	public static Date parse(String str, String format) {

		if(str==null) {
            return null;
        }

		SimpleDateFormat fm = new SimpleDateFormat(format);
		Date curDate;

		try {
			curDate = fm.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("can not parse ["+str+"] to a 'Date' using format["+format+"]ڸ�ʽ����ȷ");
		}

		return curDate;
	}

	/**
	 * 把Date转化为String
	 * @param date Date实例
	 * @param format 字符串的格式,如yyyyMMdd
	 * @return
	 */
	public static String format(Date date, String format) {
		if(date==null){
			return "";
		}

		SimpleDateFormat fm = new SimpleDateFormat(format);
		return fm.format(date);
	}

	/**
	 * 把Date转化为String
	 * @param date Date实例
	 * @param format 字符串的格式,如yyyyMMdd
	 * @param 语言环境
	 * @return
	 */
	public static String format(Date date, String format,Locale locale) {
		if(date==null){
			return "";
		}

		SimpleDateFormat fm = new SimpleDateFormat(format, locale);
		return fm.format(date);
	}

	private  static Date compute(Date date, int count, int periodFlag) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(periodFlag, count);
		return gc.getTime();
	}

	public static int getWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public static String getWeekStr(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		String week = "";
		switch(w){
		case 1:week="星期一"; break;
		case 2:week="星期二"; break;
		case 3:week="星期三"; break;
		case 4:week="星期四"; break;
		case 5:week="星期五"; break;
		case 6:week="星期六"; break;
		case 0:week="星期日"; break;
		default:
		}
		return week;
	}

	public static boolean synchronizedTime(Date time){
		try {
			String dateStr = DateUtils.format(time, "yyyy-MM-dd");
			String timeStr = DateUtils.format(time, "HH:mm:ss");

			System.out
					.println("调整前的系统时间为["
							+ DateUtils.format(new Date(),
									"yyyy-MM-dd HH:mm:ss") + "]");
			Process p = Runtime.getRuntime().exec("cmd.exe /c date " + dateStr);
			int r = p.waitFor();
			if (r == 0) {
				System.out.println("调整日期成功!");
			} else {
				System.out.println("调整日期失败!");
			}
			p = Runtime.getRuntime().exec("cmd.exe /c time " + timeStr);
			r = p.waitFor();
			if (r == 0) {
				System.out.println("调整时间成功!");
			} else {
				System.out.println("调整时间失败!");
			}
			System.out
					.println("较准后的系统时间为["
							+ DateUtils.format(new Date(),
									"yyyy-MM-dd HH:mm:ss") + "]");

			return true;
		} catch (IOException e) {

			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断字符串是否为日期格式
	 * @param str - 字符串
	 * @param format - 字符串的格式,如yyyyMMdd
	 * @return
	 */
	public static boolean isDate(String str, String format){
		boolean flag = true;
		DateFormat df = new SimpleDateFormat(format);
		df.setLenient(false);
		Date  d = null;
		try{
			d = df.parse(str);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return flag;
	}

	/**
	 * 判断两个时间段是否交叉
	 * @param beginTime1 时段1开始时间
	 * @param endTime1	 时段1结束时间
	 * @param beginTime2 时段2开始时间
	 * @param endTime2	 时间2结束时间
	 * @return 	-2 时段1与时段2相互独立，时段1在时段2前
	 * 		   	-1 时段1与时段2有交叉，时段1开始时间在时段2开始时间前
	 * 			 0 时段1在时段2范围内
	 * 			 3 时段2在时段1范围内
	 * 			 4 时段2与时段1重叠
	 * 			 1 时段1与时段2有交叉，时段2开始时间在时段1开始时间前
	 * 			 2 时段1与时段2相互独立，时段2在时段1前
	 */
	public static int containOrBeIncluded(String beginTime1,String endTime1,String beginTime2,String endTime2){
		if(endTime1.compareTo(beginTime2)<=0){
			return -2;
		}else if(beginTime1.compareTo(beginTime2)<0
				&& endTime1.compareTo(beginTime2)>0
				&& endTime1.compareTo(endTime2)<=0){
			return -1;
		}else if(beginTime1.compareTo(beginTime2)==0
				&& endTime1.compareTo(endTime2)==0){
			return 4;
		}else if(beginTime1.compareTo(beginTime2)>=0
				&& endTime1.compareTo(endTime2)<=0){
			return 0;
		}else if(beginTime1.compareTo(beginTime2)<=0
						&& endTime1.compareTo(endTime2)>=0){
			return 3;
		}else if(beginTime2.compareTo(beginTime1)<0
				&& endTime2.compareTo(beginTime1)>0
				&& endTime2.compareTo(endTime1)<=0){
			return 1;
		}else if(endTime2.compareTo(beginTime1)<=0){
			return 2;
		}
		return 4;
	}

	/**
	 * 比较两个日期时间的差是否大于等于指定差值
	 * @param date1 日期时间1
	 * @param date2 日期时间2
	 * @param difference 差值(单位：毫秒)
	 * @return
	 */
	public static boolean compareDatesDifference(Date date1, Date date2, long difference){
		boolean flag = false;
		try{
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			long timediff = time2-time1;
			if(timediff>=difference){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return flag;
	}

	/**
	 * 比较某日期是否在日期范围内
	 * @param compareDate 比较日期
	 * @param beginDate 日期范围开始日期
	 * @param endDate 日期范围结束日期
	 * @return
	 */
	public static boolean compareDateInScope(String compareDate, String beginDate, String endDate){
		if(beginDate.compareTo(compareDate)>0 || endDate.compareTo(compareDate)<0){
			return false;
		}
		return true;
	}

	/**
	 * 判断日期大小
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
  public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
        	return 2;
        }
       // return 0;
    }
  /**
   * 计算两个日期之间相差的天数
   * @param smdate 较小的时间
   * @param bdate  较大的时间
   * @return 相差天数
   * @throws ParseException
   */
  public static String daysBetween(Date smdate,Date bdate) {
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
      try {
		  smdate=sdf.parse(sdf.format(smdate));
		  bdate=sdf.parse(sdf.format(bdate));
	      Calendar cal = Calendar.getInstance();
	      cal.setTime(smdate);
	      long time1 = cal.getTimeInMillis();
	      cal.setTime(bdate);
	      long time2 = cal.getTimeInMillis();
	      long between_days=(time2-time1)/(1000*3600*24);
	      return between_days+"";
	} catch (ParseException e) {
		e.printStackTrace();
		return null;
	}
  }

    /**
	  *字符串的日期格式的计算
	  */
	public static String daysBetween(String smdate,String bdate) {
		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
	    try {
				cal.setTime(sdf.parse(smdate));
				long time1 = cal.getTimeInMillis();
		        cal.setTime(sdf.parse(bdate));
		        long time2 = cal.getTimeInMillis();
		        long between_days=(time2-time1)/(1000*3600*24);

		         return String.valueOf(between_days);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}

	}
	//算出某年所有周末
		 public static List<String> getWeekends(int year){
			  List<String> list = new ArrayList<String>();
			  final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			  Calendar cal = Calendar.getInstance(Locale.CHINA);
			  cal.set(year, 0, 1);

			  for(int day = 1; day <= cal.getActualMaximum(Calendar.DAY_OF_YEAR); day++){
			   cal.set(Calendar.DAY_OF_YEAR, day);

			   int weekDay = cal.get(Calendar.DAY_OF_WEEK);
			   if(weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY){
			    list.add(sdf.format(cal.getTime()));
			   }
			  }

			return list;
		}
}
