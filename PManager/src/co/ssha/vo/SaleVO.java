package co.ssha.vo;

public class SaleVO {
	//필드
	private String prodNo;
	private String prodName;
	private String prodDesc;
	private int sellUnit;
	
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
	public int getSellUnit() {
		return sellUnit;
	}
	public void setSellUnit(int sellUnit) {
		this.sellUnit = sellUnit;
	}
	
	@Override
	public String toString() {
		return "SaleVO [prodNo=" + prodNo + ", prodName=" + prodName + ", prodDesc=" + prodDesc + ", sellUnit="
				+ sellUnit + "]";
	}
}
