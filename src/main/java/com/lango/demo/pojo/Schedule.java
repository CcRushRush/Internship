package com.lango.demo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: cc
 * @version: 2020年1月17日 上午11:06:36
 */
public class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;

	private int sId;
	private Date lastDateTiem;
	private int interval;

	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Schedule(int sId, Date lastDateTiem, int interval) {
		super();
		this.sId = sId;
		this.lastDateTiem = lastDateTiem;
		this.interval = interval;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public Date getLastDateTiem() {
		return lastDateTiem;
	}

	public void setLastDateTiem(Date lastDateTiem) {
		this.lastDateTiem = lastDateTiem;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Override
	public String toString() {
		return "Schedule [sId=" + sId + ", lastDateTiem=" + lastDateTiem + ", interval=" + interval + "]";
	}

}
