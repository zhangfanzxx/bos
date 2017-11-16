package com.itheima.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:定区
 */
public class FixedArea {
	private Long id; // 主键
	private String fixedAreaName; // 定区名称
	private String fixedAreaLeader;// 定区负责人
	private String telephone;// 联系电话
	private String company; // 所属单位
	private Date operatingTime;// 操作时间
	private String operator; // 操作员
	private String operatingCompany; // 操作单位
	private Set<SubArea> subareas = new HashSet<SubArea>(0);
	private Set<Courier> couriers = new HashSet<Courier>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFixedAreaName() {
		return fixedAreaName;
	}

	public void setFixedAreaName(String fixedAreaName) {
		this.fixedAreaName = fixedAreaName;
	}

	public String getFixedAreaLeader() {
		return fixedAreaLeader;
	}

	public void setFixedAreaLeader(String fixedAreaLeader) {
		this.fixedAreaLeader = fixedAreaLeader;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(Date operatingTime) {
		this.operatingTime = operatingTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Set<SubArea> getSubareas() {
		return subareas;
	}

	public void setSubareas(Set<SubArea> subareas) {
		this.subareas = subareas;
	}

	public Set<Courier> getCouriers() {
		return couriers;
	}

	public void setCouriers(Set<Courier> couriers) {
		this.couriers = couriers;
	}

	public String getOperatingCompany() {
		return operatingCompany;
	}

	public void setOperatingCompany(String operatingCompany) {
		this.operatingCompany = operatingCompany;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
