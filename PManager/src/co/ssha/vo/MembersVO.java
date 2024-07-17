package co.ssha.vo;

import java.util.Date;

public class MembersVO {
	//필드
	private String userId;
	private String userPw;
	private String userName;
	private String phone;
	private String address;
	private Date genDate;
	private String isManager;
	
	//생성자
	
	//메소드
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getGenDate() {
		return genDate;
	}
	public void setGenDate(Date genDate) {
		this.genDate = genDate;
	}
	public String getIsManager() {
		return isManager;
	}
	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}
	
	@Override
	public String toString() {
		return "MembersVO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", phone=" + phone
				+ ", address=" + address + ", genDate=" + genDate + ", isManager=" + isManager + "]";
	}
}
