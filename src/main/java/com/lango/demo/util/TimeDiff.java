package com.lango.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/** 
 * @author: cc 
 * @version: 2020年1月17日 上午11:48:03 
 */
public class TimeDiff {
	public static Long minuteDiff(Date last){
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String time_1= dfs.format(new Date());

		String time_2 = dfs.format(last);

		Date begin = null;
		Date end = null;
		try {
			begin = dfs.parse(time_1);
			end = dfs.parse(time_2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (end.getTime()-begin.getTime())/60000;  // 单位为分钟
	}

}
