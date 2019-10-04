package org.acme.kogito.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {

	private String name;
	private String date;
	private int discount;

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getTime() {
		long time=0l;
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyy");
		try {
			time =  myFormat.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public long getCurrentTime() {
		return new java.util.Date().getTime();
	}
}
