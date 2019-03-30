package com.wanghang.code.lock.reentrantLock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderCodeGenerator {
	
	private int i=0;
	public String createOrderNum() {
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
		return simpleDateFormat.format(date)+ ++i;
	}
}
