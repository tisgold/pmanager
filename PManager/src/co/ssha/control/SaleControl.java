package co.ssha.control;

import java.util.List;
import java.util.Scanner;

import co.ssha.dao.SaleDAO;
import co.ssha.vo.SaleVO;

public class SaleControl {
	//필드
	SaleDAO sdao = new SaleDAO();
	Scanner scanner = new Scanner(System.in);
	
	//생성자
	
	//메소드
	// prod_sale 항목 출력
	public void salesList() {
		List<SaleVO> sales = sdao.selectSale();
		System.out.println("-----------------------------------------------------------------");
		System.out.println("판매번호     상품이름      상품설명                  주문(Kg)   가격");
		System.out.println("-----------------------------------------------------------------");
		for(SaleVO svo : sales) {
			System.out.printf("%-10s %-10s %-20s %-9d %-10d\n", svo.getProdNo(), svo.getProdName(),
				svo.getProdDesc(), svo.getSellUnit(), sdao.getSalePrice(svo.getProdNo()) * svo.getSellUnit());
		}
	}
}
