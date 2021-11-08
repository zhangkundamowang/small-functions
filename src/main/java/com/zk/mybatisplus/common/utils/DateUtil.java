
package com.zk.mybatisplus.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 日期操作方法集。
 *
 * @author Yang Yihua
 */

public class DateUtil {

	/**
	 * 默认日期格式，默认格式为<b>yyyy年MM月dd日</b>
	 */
	public static String format = "yyyy年MM月dd日";
	public static String DATE_FORMAT = "yyyy年MM月dd日";
	public static String DATE_FORMAT1 = "yyyy-MM-dd";
	public static String DATE_FORMAT2 = "yyyy-MM-dd HH:mm";
	public static String DATE_FORMAT3 = "yyyy-MM-dd HH:mm:ss";
	public static String DATE_FORMAT4 = "yyyyMMddHHmmss";
	public static String DATE_FORMAT5 = "yyyyMMdd";
	/**
	 * 使用默认格式来格式化日期。
	 *
	 * @param date
	 * 需要格式化的日期。
	 *
	 * @return 格式化后的日期字符串。如果date为null，则返回空字符串。
	 */
	public static String formatDate(Date date){
		return formatDate(date, "");
	}

	/**
	 * 使用默认格式来格式化日期。
	 *
	 * @param date
	 * 需要格式化的日期。
	 *
	 * @return 格式化后的日期字符串。如果date为null，则返回空字符串。
	 */
	public static Date formatDateStr(String str,String pattern){
		try {
			SimpleDateFormat formatter  = new SimpleDateFormat(pattern);
			Date  date = formatter.parse(str);
			return date;
		}catch (Exception e){
			return null;
		}
	}

	/**
	 * 获取当前时间，默认格式yyyy-MM-dd HH:mm:ss
	 */
	public static String getTime(){
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public static String getToday(){
		Date date=new Date();
		String result = "";		
		SimpleDateFormat sdf = null;
		try{
		
			sdf = new SimpleDateFormat(DATE_FORMAT1);
			result = sdf.format(date);
			
		}catch (Exception e){
			result = "";
			
		}finally{			
			
		}
		return result;
	}
	/**
	 * 使用指定的格式来格式化日期。默认格式为<b>yyyy年MM月dd日</b>
	 *
	 * @param formatString
	 * 需要格式化的日期。
	 *
	 * @param format
	 * 指定日期的格式化字符串。如果为空字符串或者null，则使用默认的格式来
	 * 格式化日期。
	 *
	 * @return 格式化后的日期字符串。如果date为null，则返回空字符串。
	 */
	public static String formatDate(Date date, String formatString){
		
		String result = "";		
		SimpleDateFormat sdf = null;
		if(date==null)
		  return result;
		try{
			
			if ((formatString == null) || (formatString.length() <= 0)){
				formatString = format;				
			}
			
			sdf = new SimpleDateFormat(formatString);
			result = sdf.format(date);
			
		}catch (Exception e){
			result = "";
			
		}finally{			
			
		}
		return result;
	}

	/**
	 * 使用默认的字符串格式，解析字符串并生成日期对象。默认格式为<b>yyyy年MM月dd日</b>
	 *
	 * @param parseString
	 * 需要解析的字符串。
	 *
	 * @return 根据指定的字符串格式生成的日期对象。如果parseString为空字符串或者null，
	 * 返回日期对象为null。
	 */
	public static Date parseDate(String parseString){
		return parseDate(parseString, "");
	}

	/**
	 * 根据指定的字符串格式，解析字符串并生成日期对象。
	 *
	 * @param parseString
	 * 需要解析的字符串。
	 *
	 * @param formatString
	 * 指定的字符串格式。
	 *
	 * @return 根据指定的字符串格式生成的日期对象。如果parseString为空字符串或者null，
	 * 返回日期对象为null。如果formatString为空字符串或者null，则使用默认日期格式解析。
	 */
	public static Date parseDate(String parseString, String formatString){
		
		Date result = null;
		SimpleDateFormat sdf = null;
		
		try{
			if ((parseString != null) && (parseString.length() > 0)){ //paesrString����ȷ�ģ�׼������
			
				if ((formatString == null) || (formatString.length() <= 0)){ //û��ָ����ʽ��ʹ��Ĭ�ϸ�ʽ��
					formatString = format;
				}	
				
				sdf = new SimpleDateFormat(formatString);
				result = sdf.parse(parseString);
			}						
			
		}catch (Exception e){
			e.printStackTrace();
			result = null;
			
		}finally{
			
		}
		
		return result;
	}
	
	public static String dateDiff(Date beginDate,Date endDate){
		
		String begin =formatDate(beginDate,"yyyyMMdd");
		
		String end =formatDate(endDate,"yyyyMMdd");
		
		if(begin.equals("")||end.equals("")){
			
			return "";
			
		}
		
		double betweenyears=0.0;
		
		GregorianCalendar begindate = new GregorianCalendar( 
											Integer.parseInt(begin.substring(0,4)),
											Integer.parseInt(begin.substring(4,6))-1,
											Integer.parseInt(begin.substring(6,8)));
		GregorianCalendar enddate = new GregorianCalendar( Integer.parseInt(end.substring(0,4)),
		 													Integer.parseInt(end.substring(4,6))-1,
		 													Integer.parseInt(end.substring(6,8)));

		
		long begintime = begindate.getTime().getTime(); 

		long endtime = enddate.getTime().getTime(); 

		 betweenyears = (((endtime - begintime) / (1000 * 60 * 60 *24) + 0.5)/(30*12)); 
		 
		DecimalFormat df = new DecimalFormat("###0.0");
		
		String result=df.format(betweenyears)+"年";
       
		return result;
		
	}
	

	public static String dateDiffYear(Date beginDate,Date endDate){
		
		String begin =formatDate(beginDate,"yyyyMMdd");
	
		String end =formatDate(endDate,"yyyyMMdd");
	
		if(begin.equals("")||end.equals("")){
		
			return "";
		
		}
	
		double betweenyears=0.0;
	
		GregorianCalendar begindate = new GregorianCalendar( 
											Integer.parseInt(begin.substring(0,4)),
											Integer.parseInt(begin.substring(4,6))-1,
											Integer.parseInt(begin.substring(6,8)));
		GregorianCalendar enddate = new GregorianCalendar( Integer.parseInt(end.substring(0,4)),
															Integer.parseInt(end.substring(4,6))-1,
															Integer.parseInt(end.substring(6,8)));

	
		long begintime = begindate.getTime().getTime(); 

		long endtime = enddate.getTime().getTime(); 

		betweenyears = (((endtime - begintime) / (1000 * 60 * 60 *24) + 0.5)/(30*12)); 
	 
		//DecimalFormat df = new DecimalFormat("###0.0");
		int n = Integer.parseInt(end.substring(0,4)) - Integer.parseInt(begin.substring(0,4));
		//if (Integer.parseInt(end.substring(4,6)) - Integer.parseInt(begin.substring(4,6)) > 0
		//&& Integer.parseInt(end.substring(6,8)) - Integer.parseInt(begin.substring(6,8)) > 0
		//)
		if (Integer.parseInt(end.substring(4,6)) - Integer.parseInt(begin.substring(4,6)) > 0
		)
		{
			n++;
		}
		//String result=df.format(betweenyears)+"年";
		String result = new Integer(n).toString();
   
		return result;
	
	}

	//两个时间之间分钟差
	public static String compareDate(Date d1, Date d2) {
		// TODO Auto-generated method stub
		long dif = d1.getTime() - d2.getTime();
		long day= dif /(24*60*60*1000);
		long hour=( dif /(60*60*1000)-day*24);
		long min=(( dif /(60*1000))-day*24*60-hour*60);
		long allMIN = hour*24+min;
		return String.valueOf(allMIN);
	}

	/**
	 * 从fromDate增加monthNum个月
	 * @param fromDate
	 * @param monthNum
	 * @return
	 */
	public static Date addMonth(Date fromDate, int monthNum){
		Calendar calendar = Calendar.getInstance();
		if (fromDate == null){
			calendar.setTime(new Date());
		}else{
			calendar.setTime(fromDate);
		}
		
		calendar.add(Calendar.MONTH, monthNum);
		
		return calendar.getTime();
	}
	/**
	 * 从fromDate增加hourNum个小时
	 * @param fromDate
	 * @param dateNum
	 * @return
	 */
	public static Date addHour(Date fromDate,int hourNum) {
		Calendar calendar = Calendar.getInstance();
		if (fromDate == null){
			calendar.setTime(new Date());
		}else{
			calendar.setTime(fromDate);
		}
		
		calendar.add(Calendar.HOUR, hourNum);
		
		return calendar.getTime();
	}
	/**
	 * 从fromDate增加monthNum个天
	 * @param fromDate
	 * @param dateNum
	 * @return
	 */
	public static Date addDay(Date fromDate, int dateNum){
		Calendar calendar = Calendar.getInstance();
		if (fromDate == null){
			calendar.setTime(new Date());
		}else{
			calendar.setTime(fromDate);
		}
		
		calendar.add(Calendar.DATE, dateNum);
		
		return calendar.getTime();
	}
	/**
	 * 从fromDate增加monthNum分钟
	 * @param fromDate
	 * @param dateNum
	 * @return
	 */
	public static Date addMinute(Date fromDate,int dateNum) {
		Calendar calendar=Calendar.getInstance();
		if (fromDate==null) {
			calendar.setTime(new Date());
		}else {
			calendar.setTime(fromDate);
		}
		calendar.add(Calendar.MINUTE, dateNum);
		return calendar.getTime();
	}
	/**
	 * 从fromDate增加yearNum年
	 * @param fromDate
	 * @param yearNum
	 * @return
	 */
	public static Date addYear(Date fromDate, int yearNum){
		Calendar calendar = Calendar.getInstance();
		if (fromDate == null){
			calendar.setTime(new Date());
		}else{
			calendar.setTime(fromDate);
		}
		
		calendar.add(Calendar.YEAR, yearNum);
		
		return calendar.getTime();
	}
	public static int getYearOrMonthOrDay(Date fromDate,int flag){
		Calendar calendar = Calendar.getInstance();
		if (fromDate == null){
			calendar.setTime(new Date());
		}else{
			calendar.setTime(fromDate);
		}
		int ret=0;
		switch(flag)
		{
		case 1:
			 ret = calendar.get(Calendar.YEAR);
			 break;
		case 2:
			 ret = calendar.get(Calendar.MONTH)+1;
			 break;
		case 3:
			 ret = calendar.get(Calendar.HOUR_OF_DAY);
			 break;
		}	
		return ret;
	}

	/**
	 * 页面显示 主要去除null 显示"" 2007-6-29
	 *
	 * @param
	 * @return
	 */
	public static final String forPageShow(Object o) {
		String reStr = "";
		if (o == null) {
			reStr = "";
		}
		if ((o != null) && !"null".equals(o.toString())) {
			reStr = o.toString();
		}
		return reStr;
	}

	/**
	 * 就日期的间隔天数
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static final int getDay(Date begin, Date end) throws Exception {
//		/SimpleDateFormat myFormatter = new SimpleDateFormat(DATE_FORMAT);
		   //java.util.Date date= myFormatter.parse("2003-05-1"); 
		   //java.util.Date mydate= myFormatter.parse("1899-12-30");
           if(begin==null||end==null)
           {
        	   return -1;	   
           }
		long  day=(end.getTime()-begin.getTime())/(24*60*60*1000);
		day=day+1;//需要加一天，包括当天
		return Integer.parseInt(""+day);
	}

	/**
	 * 得到间隔日期的分钟数
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static final long getMinute(Date begin, Date end) throws Exception {
		  if(begin==null||end==null)
          {
       	   return -1;	   
          }
	
		long timeOne=end.getTime();
		long timeTwo=begin.getTime();
		long minute=(timeOne-timeTwo)/(1000*60);//ת��minute
		System.out.println("相隔"+minute+"分钟");
		return minute;
	}
	
	/**
	 * 	
		
	 * @param start final String s = "2009-05";
	 * @param end   final String e = "2010-04";
	 * @return
	 * @throws ParseException
	 */
	public static List getIntervalMonthes(final String start, final String end)
			throws ParseException {
		// final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List list=new ArrayList();
		final DateFormat formatter = new SimpleDateFormat("yyyy-MM");
		final Calendar startDate = Calendar.getInstance();
		startDate.setTime(formatter.parse(start));
		final Calendar endDate = Calendar.getInstance();
		endDate.setTime(formatter.parse(end));
		int cnt = 0;
		while (startDate.before(endDate)) {
			System.out.println(startDate.get(Calendar.YEAR) + "-"
					+ (startDate.get(Calendar.MONTH) + 1));
			String date=startDate.get(Calendar.YEAR) + "-"
			+ (startDate.get(Calendar.MONTH) + 1);
			cnt++;
			list.add(date);
			startDate.add(Calendar.MONTH, 1);
		}
		list.add(end);
		return list;
	}

	/**
	 * 将日期组合成日期+1 0:0:0
	 * 加1天
	 * @param date
	 * @return
	 */
	public static Date truncUp(Date date) {
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);//new modify 2010��11��2��
		c.add(Calendar.DATE, 1);//��һ��
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	/**
	 * 将日期组合成 日期+ 0:0:0
	 * @param date
	 * @return
	 */
	public static Date truncDown(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	/**
	 * 得到当天，当周，及当月，当季及当年的开始日期和结束日期
	 * @param date
	 * @param flag
	 * @param format
	 * @return
	 */
	public static String []getBeginAndEnd(Date date,int flag,String format) {
		Calendar cal = Calendar.getInstance(); 
		String []dateArray=new String[2];
		date=date!=null?date:new Date();
		switch(flag)
		{
		case 0://����		  
			  cal.setTime(new Date()); 
			  dateArray[0]=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			  dateArray[1]=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			  break;
		case 1://����
			  cal.setTime(new Date()); 
			  cal.setFirstDayOfWeek(Calendar.MONDAY);
			  cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			  dateArray[0]=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			  
			  cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);		  
			  dateArray[1]=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());			
			  break;
		case 2://����
			  //Calendar cal = Calendar.getInstance(); 
			  cal.setTime(new Date()); 
			  cal.set(Calendar.DAY_OF_MONTH, 1); 		
			  dateArray[0]=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());			  
			  cal.roll(Calendar.DAY_OF_MONTH, -1);  
			  dateArray[1]=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			  break;
		case 3://����		  	
			  dateArray[0]=getCurrentQuarterStartTime(date,format);			  
			  dateArray[1]=getCurrentQuarterEndTime(date,format);
			  break;
		case 4://����
				int currentYear = cal.get(Calendar.YEAR);
				Date d= getYearFirst(currentYear);
			  dateArray[0]=new SimpleDateFormat("yyyy-MM-dd").format(d.getTime());
			  
				 d= getYearLast(currentYear);
				  dateArray[1]=new SimpleDateFormat("yyyy-MM-dd").format(d.getTime());
			
			  break;
		default:
			
		  
		}
		
		return dateArray;
	}
	/**
	 * 当前季度的开始时间，即2012-01-1 00:00:00
	 *
	 * @return
	 */
	public  static String getCurrentQuarterStartTime(Date date,String formate) {
        Calendar c = Calendar.getInstance(); 
        c.setTime(date); 
        int currentMonth = c.get(Calendar.MONTH) + 1; 
        try { 
            if (currentMonth >= 1 && currentMonth <= 3) 
                c.set(Calendar.MONTH, 0); 
            else if (currentMonth >= 4 && currentMonth <= 6) 
                c.set(Calendar.MONTH, 3); 
            else if (currentMonth >= 7 && currentMonth <= 9) 
                c.set(Calendar.MONTH, 4); 
            else if (currentMonth >= 10 && currentMonth <= 12) 
                c.set(Calendar.MONTH, 9); 
            c.set(Calendar.DATE, 1); 
            return new SimpleDateFormat(formate).format(c.getTime());
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return ""; 
    }
	/**
	 * 当前季度的开始时间，即2012-01-1 00:00:00
	 *
	 * @return
	 */
	public  static String getCurrentQuarterEndTime(Date date,String formate) {
        Calendar c = Calendar.getInstance(); 
        c.setTime(date); 
        int currentMonth = c.get(Calendar.MONTH) + 1; 
        try { 
        	  if (currentMonth >= 1 && currentMonth <= 3) { 
                  c.set(Calendar.MONTH, 2); 
                  c.set(Calendar.DATE, 31); 
              } else if (currentMonth >= 4 && currentMonth <= 6) { 
                  c.set(Calendar.MONTH, 5); 
                  c.set(Calendar.DATE, 30); 
              } else if (currentMonth >= 7 && currentMonth <= 9) { 
                  c.set(Calendar.MONTH,8); 
                  c.set(Calendar.DATE, 30); 
              } else if (currentMonth >= 10 && currentMonth <= 12) { 
                  c.set(Calendar.MONTH, 11); 
                  c.set(Calendar.DATE, 31); 
              } 
            return new SimpleDateFormat(formate).format(c.getTime());
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return ""; 
    }
	/**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}
	/**
	 * 获取某年最后一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearLast(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		
		return currYearLast;
	}
	public static Date parseLong(String l) throws Exception{
		  	SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    Long time=new Long(l);  
		    String d = format.format(time);  
		    Date date=format.parse(d);  
		    return date;
	}
	/**
	 * 将16进制转化为日期格式
	 * 3 时间数据解析（共四个字节）高位在前低位在后
	 时间格式：32个bit。31-26bit: Year(2000)，25-22bit: Month,，21-17bit: Day，16-12bit: Hour，11-6bit: Minute，5-0bit: Second

	 例：接到数据为FB 0E F7 4A（示例数据粉红色部分），则该时间数为0x4AF70EFB，换算为二进制则为01001010 11110111  00001110  11111011。
	 按照时间格式分离为：010010  1011  11011  10000  111011  111011。则年为010010即为18，表示2018。月份为1011即为11，表示11月。
	 日为11011即为27。时为10000即为16，表示16时。分和秒则为111011,即为59。
	 所以该数据表示为2018-11-27 16:59:59.
	 https://blog.csdn.net/u010137760/article/details/52610442
	 * @param l
	 * @return
	 * @throws Exception
	 */
	public static String getDateFromHex(String hex) throws Exception{
		String binaryStr=Integer.toBinaryString(parse("0x"+hex));//16����ת��Ϊ2����
		if(binaryStr.length()<32)
		{
			binaryStr="0"+binaryStr;
		}
		/*Map<String,Integer> map=new HashMap<String,Integer>(); �ο�����Ҫɾ��
		map.put("year", 6);//���� 010010
		map.put("month", 4);//���� 1011
		map.put("day", 5);//11011
		map.put("hour", 5);//10000
		map.put("minute", 6);//111011
		map.put("second", 6);//111011
		*/
		StringBuffer dateStr=new StringBuffer();
		int year=Integer.valueOf(binaryStr.substring(0, 6),2);
		int month=Integer.valueOf(binaryStr.substring(6, 6+4),2);
		int day=Integer.valueOf(binaryStr.substring(6+4, 6+4+5),2);
		int hour=Integer.valueOf(binaryStr.substring(6+4+5, 6+4+5+5),2);
		int minute=Integer.valueOf(binaryStr.substring(6+4+5+5, 6+4+5+5+6),2);
		int second=Integer.valueOf(binaryStr.substring(6+4+5+5+6),2);
		return dateStr.append("20"+year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second).toString();
		
	}
	static int parse(String s) throws NumberFormatException
	{
		if (!s.startsWith("0x"))
			throw new NumberFormatException();
		int number = 0, n = 0;
		for (int i = 2; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '1':
				n = 1;
				break;
			case '2':
				n = 2;
				break;
			case '3':
				n = 3;
				break;
			case '4':
				n = 4;
				break;
			case '5':
				n = 5;
				break;
			case '6':
				n = 6;
				break;
			case '7':
				n = 7;
				break;
			case '8':
				n = 8;
				break;
			case '9':
				n = 9;
				break;
			case '0':
				n = 0;
				break;
			case 'a':
			case 'A':
				n = 10;
				break;
			case 'b':
			case 'B':
				n = 11;
				break;
			case 'c':
			case 'C':
				n = 12;
				break;
			case 'd':
			case 'D':
				n = 13;
				break;
			case 'e':
			case 'E':
				n = 14;
				break;
			case 'f':
			case 'F':
				n = 15;
				break;
			default:
				throw new NumberFormatException();
			}
			number = number * 16 + n;
		}
		return number;	
	}
	//获取时间区间
	public static Map<String, Object> getTimeInterval(String type){
		Map<String, Object> map=new HashMap<String, Object>();
		String format=null;
		int num=0;
		Date beginDate=null;
		String[] label=null;
		Date today=new Date();
		if (StringUtils.equalsIgnoreCase(type, "day")||!StringUtils.isNotBlank(type)) {
			//按天
			num=24;
			format="%H";
			beginDate=DateUtil.addHour(today, -num+1);
			beginDate.setMinutes(0);
			beginDate.setSeconds(0);
			label=new String[num];
			int index=0;
			for (int i = num; i >0; i--) {
				//创建标签
				label[i-1]=DateUtil.formatDate(DateUtil.addHour(today, -index), "HH");
				index++;
			}
		}else if (StringUtils.equalsIgnoreCase(type, "month")||StringUtils.equalsIgnoreCase(type, "week")) {
			//按月和周
			num=StringUtils.equalsIgnoreCase(type, "month")?30:7;
			format="%Y-%m-%d";
			beginDate=DateUtil.addDay(today, -num+1);
			beginDate.setHours(0);
			beginDate.setMinutes(0);
			beginDate.setSeconds(0);
			label=new String[num];
			int index=0;
			for (int i = num; i >0; i--) {
				//创建标签
				label[i-1]=DateUtil.formatDate(DateUtil.addDay(today, -index), DateUtil.DATE_FORMAT1);
				index++;
			}
		}else if (type.equalsIgnoreCase("year")) {
			//按年
			num=12;
			format="%Y-%m";
			beginDate=DateUtil.addMonth(today, -num+1);
			beginDate.setDate(1);
			beginDate.setHours(0);
			beginDate.setMinutes(0);
			beginDate.setSeconds(0);
			label=new String[num];
			int index=0;
			for (int i = num; i >0; i--) {
				//创建标签
				label[i-1]=DateUtil.formatDate(DateUtil.addMonth(today, -index), "yyyy-MM");
				index++;
			}
		}
		map.put("num", num);//获取区间条数
		map.put("format", format);//数据库dateformat格式
		map.put("beginDate", beginDate);//查询开始日期
		map.put("label", label);//时间标签数组
		return map;
	}

	/**
	 * 返回一年前的日期
	 * @param
	 * @return
	 */
	public static String agoYearDay(){
		Date now = new Date();//获取当前时间    
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(calendar.getTime());
		return d;
	}

	/**
	 * 返回一月前的日期
	 * @param
	 * @return
	 */
	public static String agoMonthDay(){
		Date now = new Date();//获取当前时间    
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		if (Calendar.MONTH==1){//为1月时，上个月为去年12月
			calendar.add(Calendar.YEAR,-1);
			calendar.add(Calendar.MONTH,11);
		}else {
			calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
		}
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(calendar.getTime());
		return d;
	}

	/**
	 * 返回一周前的日期
	 * @param
	 * @return
	 */
	public static String agoWeekDay(){
		Date now = new Date();//获取当前时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		if (Calendar.DAY_OF_MONTH<=7){
			if (Calendar.MONTH==3 || Calendar.MONTH==5 || Calendar.MONTH==7 || Calendar.MONTH==8 || Calendar.MONTH==10 || Calendar.MONTH==12){
				calendar.add(Calendar.MONTH,-1);
				calendar.add(Calendar.DAY_OF_MONTH,23);
			}else if (Calendar.MONTH==1){
				calendar.add(Calendar.YEAR,-1);
				calendar.add(Calendar.MONTH,11);
				calendar.add(Calendar.DAY_OF_MONTH,24);
			}else {
				calendar.add(Calendar.MONTH,-1);
				calendar.add(Calendar.DAY_OF_MONTH,24);
			}
		}else {
			calendar.add(Calendar.DAY_OF_MONTH, -7);//当前时间减去7天，即一周前的时间
		}
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(calendar.getTime());
		return d;
	}

	/**
	 * 返回24小时前的日期
	 * @param
	 * @return
	 */
	public static String agoOneDay(){
		Date now = new Date();////获取当前时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		if (Calendar.DAY_OF_MONTH==1){//为1日时，24小时前为上个月
			if (Calendar.MONTH==3 || Calendar.MONTH==5 || Calendar.MONTH==7 || Calendar.MONTH==8 || Calendar.MONTH==10 || Calendar.MONTH==12){
				calendar.add(Calendar.MONTH,-1);
				calendar.add(Calendar.DAY_OF_MONTH,29);
			}else if (Calendar.MONTH==1){
				calendar.add(Calendar.YEAR,-1);
				calendar.add(Calendar.MONTH,11);
				calendar.add(Calendar.DAY_OF_MONTH,30);
			}else {
				calendar.add(Calendar.MONTH,-1);
				calendar.add(Calendar.DAY_OF_MONTH,30);
			}
		}else {
			calendar.add(Calendar.DAY_OF_MONTH, -1);//当前时间前去一天，即24小时前的时间
		}
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(calendar.getTime());
		return d;
	}

	/**
	 * 返回1个小时前的日期
	 * @param
	 * @return
	 */
	public static Date agoHalfHour(){
		Date now = new Date();//获取当前时间    
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.HOUR, -1);//往前1个小时
		return calendar.getTime();
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	public static void main(String[] args) throws Exception {

		String time = getDate("yyyyMMddHHmmss");
		byte[] messageReturn = ConversionUtil.strHexByte(time);
		System.out.println(messageReturn);
		System.out.println(time);
		System.out.println(Arrays.toString(messageReturn));
	}
}
