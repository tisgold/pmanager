package co.ssha.control;

import java.util.List;
import java.util.Scanner;

import co.ssha.dao.ManageDAO;
import co.ssha.dao.SaleDAO;
import co.ssha.vo.ManageVO;
import co.ssha.vo.SaleVO;

public class ManageControl {
	//필드
	ManageDAO mdao = new ManageDAO();
	SaleDAO sdao = new SaleDAO();
	Scanner scanner = new Scanner(System.in);
	String prodNo;
	
	//생성자
	
	//메소드
	// prod_manage 상품 리스트 출력
	public void productsList() {
		List<ManageVO> manage = mdao.selectManage();
		System.out.println();
		System.out.println("----------------------------------------------------------------");
		System.out.println("상품번호     상품이름      생산연월     가격(1Kg)   재고(kg)    상품설명");
		System.out.println("----------------------------------------------------------------");
		for(ManageVO mvo : manage) {
			System.out.printf("%-10s %-10s %-10s %-10d %-10d %-20s\n", mvo.getProdNo(), mvo.getProdName(),
					mvo.getProdDate(), mvo.getPrice(), mvo.getStock(), mvo.getProdDesc());
		}
		System.out.println();
		System.out.print("[판매등록> '상품번호'  |  이전메뉴> 'Enter키'] > ");
		String prodNo = scanner.nextLine();
		if(prodNo == null || prodNo.trim().isEmpty()) {
			return;
		}
		else{
			sdao.addSale(prodNo);
		}
	} // end of productsList
	
	// prod_manage에 새 상품 추가
	public void addProduct() {
		System.out.print("상품이름 입력> ");
		String prodName = scanner.nextLine();
		System.out.print("상품설명 입력> ");
		String prodDesc = scanner.nextLine();
		System.out.print("생산연월 입력> ");
		String prodDate = scanner.nextLine();
		System.out.print("가격(1Kg) 입력> ");
		int price = scanner.nextInt();
		String word = scanner.nextLine();
		System.out.print("재고(Kg) 입력> ");
		int stock = scanner.nextInt();
		word = scanner.nextLine();
		
		ManageVO mvo = new ManageVO();
		mvo.setProdName(prodName);
		mvo.setProdDesc(prodDesc);
		mvo.setProdDate(prodDate);
		mvo.setPrice(price);
		mvo.setIndoor(0);
		mvo.setStock(stock);
		
		if(mdao.insertProduct(mvo)) {
			System.out.println(prodName + " 상품이 등록되었습니다.");
		}
		else {
			System.out.println("처리중 예외가 발생했습니다.");
		}
	} // end of addProduct
	
	// 상품 정보 변경
	public void modifyProduct() {
		System.out.print("변경할 상품번호 입력> ");
		String prodNo = scanner.nextLine();
		System.out.print("상품이름 입력> ");
		String prodName = scanner.nextLine();
		System.out.print("상품설명 입력> ");
		String prodDesc = scanner.nextLine();
		System.out.print("생산연월 입력> ");
		String prodDate = scanner.nextLine();
		System.out.print("가격(1Kg) 입력> ");
		int price = scanner.nextInt();
		String word = scanner.nextLine();
		System.out.print("입고(Kg) 입력> ");
		int indoor = scanner.nextInt();
		word = scanner.nextLine();
		
		ManageVO mvo = new ManageVO();
		mvo.setProdNo(prodNo);
		mvo.setProdName(prodName);
		mvo.setProdDesc(prodDesc);
		mvo.setProdDate(prodDate);
		mvo.setPrice(price);
		mvo.setIndoor(indoor);
		mvo.setStock(mdao.getStock(prodNo) + indoor);
		//prodNo의 현재 재고량 확인 후 indoor 더하기
		
		if(mdao.updateProduct(mvo)) {
			System.out.println("상품 수정이 완료되었습니다.");
		}
		else {
			System.out.println("처리중 예외가 발생했습니다.");
		}
	} // end of modifyProduct
	
	// 상품 삭제
	public void removeProduct() {
		System.out.print("삭제할 상품번호 입력> ");
		String prodNo = scanner.nextLine();
		
		if(mdao.deleteProduct(prodNo)) {
			System.out.println("상품이 삭제되었습니다.");
		}
		else {
			System.out.println("처리중 예외가 발생했습니다.");
		}
	} // end of removeProduct
	
	// prod_sale 테이블에 항목 추가
	public void addSale() {
		SaleVO svo = new SaleVO();
		svo.setProdNo(prodNo);
	} // end of addSale
}
