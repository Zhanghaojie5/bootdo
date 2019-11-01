package com.bootdo.school.domain;

import java.io.Serializable;

public class SchoolPO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	//名称
	private String name;
	//地址
	private String address;
	//介绍
	private String schoolIntroduce;
	//经度
	private double latitude;
	//纬度
	private double longitude;
	//删除标志
	private int delTag;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSchoolIntroduce() {
		return schoolIntroduce;
	}
	public void setSchoolIntroduce(String schoolIntroduce) {
		this.schoolIntroduce = schoolIntroduce;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getDelTag() {
		return delTag;
	}
	public void setDelTag(int delTag) {
		this.delTag = delTag;
	}
	
	
}
