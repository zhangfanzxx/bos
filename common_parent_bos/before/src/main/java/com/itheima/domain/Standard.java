package com.itheima.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @description:收派标准
 */
public class Standard {
	private Long id; // 主键
	private String name; // 标准名称
	private Integer minWeight; // 最小重量
	private Integer maxWeight; // 最大重量
	private Integer minLength; // 最小长度
	private Integer maxLength; // 最大重量
	private Date operatingTime;// 操作时间
	private String operator; // 操作员
	private String operatingCompany; // 操作单位

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

	public Integer getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(Integer minWeight) {
		this.minWeight = minWeight;
	}

	public Integer getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Integer maxWeight) {
		this.maxWeight = maxWeight;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public String getOperatingCompany() {
		return operatingCompany;
	}

	public void setOperatingCompany(String operatingCompany) {
		this.operatingCompany = operatingCompany;
	}

	@Override
	public String toString() {
		return "Standard [id=" + id + ", name=" + name + ", minWeight="
				+ minWeight + ", maxWeight=" + maxWeight + ", minLength="
				+ minLength + ", maxLength=" + maxLength + ", operatingTime="
				+ operatingTime + ", operator=" + operator
				+ ", operatingCompany=" + operatingCompany + "]";
	}

}
