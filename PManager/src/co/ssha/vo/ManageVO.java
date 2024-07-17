package co.ssha.vo;

public class ManageVO {
	//필드
	private String prodNo;
	private String prodName;
	private String prodDesc;
	private String prodDate;
	private int price;
	private int indoor;
	private int stock;
	
	//생성자
	
	//메소드
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getProdDate() {
		return prodDate;
	}
	public void setProdDate(String prodDate) {
		this.prodDate = prodDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getIndoor() {
		return indoor;
	}
	public void setIndoor(int indoor) {
		this.indoor = indoor;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return "ManageVO [prodNo=" + prodNo + ", prodName=" + prodName + ", prodDesc=" + prodDesc + ", prodDate="
				+ prodDate + ", price=" + price + ", indoor=" + indoor + ", stock=" + stock + "]";
	}
}
