package com.itheima.domain;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:订单实体类
 */
@XmlRootElement(name="order")
public class Order {
	private Long id;// 主键
	private String orderNum;// 订单号
	private String telephone; // 来电号码
	private Long customer_id; // 客户编号
	private String sendName; // 寄件人姓名
	private String sendMobile;// 寄件人电话
	private String sendCompany;// 寄件人公司
	private Area sendArea; // 寄件人省市区信息
	private String sendAddress;// 寄件人详细地址信息
	private String recName;// 收件人姓名
	private String recMobile;// 收件人电话
	private String recCompany;// 收件人公司
	private Area recArea; // 收件人省市区信息
	private String recAddress;// 收件人详细地址信息
	private String sendProNum; // 快递产品类型编号：速运当日、速运次日、速运隔日
	private String goodsType;// 托寄物类型：文件、衣服 、食品、电子商品
	private String payTypeNum;// 支付类型编号：寄付日结、寄付月结、到付
	private Double weight;// 托寄物重量
	private String remark; // 备注
	private String sendMobileMsg; // 给快递员捎话
	// 分单类型 1 自动分单 2 人工分单
	private String orderType;
	// 订单状态 1 待取件 2 运输中 3 已签收 4 异常
	private String status;
	// 下单时间
	private Date orderTime;
	// 运单
	private WayBill wayBill;
	// 工单
	private Set<WorkBill> workBills = new HashSet<WorkBill>(0);
	private Courier courier;

	public Long getId() {
		return id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSendMobile() {
		return sendMobile;
	}

	public void setSendMobile(String sendMobile) {
		this.sendMobile = sendMobile;
	}

	public String getSendCompany() {
		return sendCompany;
	}

	public void setSendCompany(String sendCompany) {
		this.sendCompany = sendCompany;
	}

	public Area getSendArea() {
		return sendArea;
	}

	public void setSendArea(String sendArea) {
		Area area =new Area();
		String[] split = sendArea.split("/");
		if(split.length==3){
			area.setProvince(split[0]);
			area.setCity(split[1]);
			area.setDistrict(split[2]);
		}
		this.sendArea=area;

	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getRecName() {
		return recName;
	}

	public void setRecName(String recName) {
		this.recName = recName;
	}

	public String getRecMobile() {
		return recMobile;
	}

	public void setRecMobile(String recMobile) {
		this.recMobile = recMobile;
	}

	public String getRecCompany() {
		return recCompany;
	}

	public void setRecCompany(String recCompany) {
		this.recCompany = recCompany;
	}

	public Area getRecArea() {
		return recArea;
	}

	public void setRecArea(String recArea) {
		Area area =new Area();
		String[] split = recArea.split("/");
		if(split.length==3){
			area.setProvince(split[0]);
			area.setCity(split[1]);
			area.setDistrict(split[2]);
		}
		this.recArea=area;
	}

	public String getRecAddress() {
		return recAddress;
	}

	public void setRecAddress(String recAddress) {
		this.recAddress = recAddress;
	}

	public String getSendProNum() {
		return sendProNum;
	}

	public void setSendProNum(String sendProNum) {
		this.sendProNum = sendProNum;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getPayTypeNum() {
		return payTypeNum;
	}

	public void setPayTypeNum(String payTypeNum) {
		this.payTypeNum = payTypeNum;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public WayBill getWayBill() {
		return wayBill;
	}

	public void setWayBill(WayBill wayBill) {
		this.wayBill = wayBill;
	}

	public Set<WorkBill> getWorkBills() {
		return workBills;
	}

	public void setWorkBills(Set<WorkBill> workBills) {
		this.workBills = workBills;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSendMobileMsg() {
		return sendMobileMsg;
	}

	public void setSendMobileMsg(String sendMobileMsg) {
		this.sendMobileMsg = sendMobileMsg;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNum=" + orderNum + ", telephone="
				+ telephone + ", customer_id=" + customer_id + ", sendName="
				+ sendName + ", sendMobile=" + sendMobile + ", sendCompany="
				+ sendCompany + ", sendArea=" + sendArea + ", sendAddress="
				+ sendAddress + ", recName=" + recName + ", recMobile="
				+ recMobile + ", recCompany=" + recCompany + ", recArea="
				+ recArea + ", recAddress=" + recAddress + ", sendProNum="
				+ sendProNum + ", goodsType=" + goodsType + ", payTypeNum="
				+ payTypeNum + ", weight=" + weight + ", remark=" + remark
				+ ", sendMobileMsg=" + sendMobileMsg + ", orderTime="
				+ orderTime + ", wayBill=" + wayBill + ", workBills="
				+ workBills + "]";
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
