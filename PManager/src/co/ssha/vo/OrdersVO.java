package co.ssha.vo;

import java.util.Date;

public class OrdersVO {
	//필드
	private String userId;
	private String orderNo;
	private Date orderDate;
	private String orderList;
	
	//생성자
	
	//메소드
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderList() {
		return orderList;
	}
	public void setOrderList(String orderList) {
		this.orderList = orderList;
	}
	
	@Override
	public String toString() {
		return "OrdersVO [userId=" + userId + ", orderNo=" + orderNo + ", orderDate=" + orderDate + ", orderList="
				+ orderList + "]";
	}
}
