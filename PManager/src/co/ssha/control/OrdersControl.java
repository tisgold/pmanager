package co.ssha.control;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import co.ssha.dao.ManageDAO;
import co.ssha.dao.OrdersDAO;
import co.ssha.vo.OrdersVO;

public class OrdersControl {
	//필드
	ManageDAO mdao = new ManageDAO();
	OrdersDAO odao = new OrdersDAO();
	OrdersVO ovo = new OrdersVO();
	Scanner scanner = new Scanner(System.in);
	LocalDateTime now = LocalDateTime.now();
	
	//생성자
	
	//메소드
	// prod_orders 항목 추력
	public void ordersList() {
		List<OrdersVO> orders = null;
		System.out.print("[모든고객> 'A'  |  특정고객> 'ID'  |  이전메뉴> 'Enter키'] > ");
		String id = scanner.nextLine();
		if(id == null || id.trim().isEmpty()) {
			return;
		}
		else if(id.equalsIgnoreCase("a")) {
			orders = odao.selectOrders();
		}
		else{
			orders = odao.selectOrder(id);
		}
		System.out.println("-----------------------------------------------------");
		System.out.println("아이디       주문번호         주문날짜       주문항목");
		System.out.println("-----------------------------------------------------");
		for(OrdersVO ovo : orders) {
			System.out.printf("%-10s %-14s %-12s %-20s\n", ovo.getUserId(),
					ovo.getOrderNo(), ovo.getOrderDate(), ovo.getOrderList());
		}
	} // end of ordersList
	
	// prod_orders 의 user_id 의 항목 출력
	public void orderList(String id) {
		List<OrdersVO> orders = odao.selectOrder(id);
		System.out.println("-----------------------------------------");
		System.out.println("주문번호         주문날짜       주문항목");
		System.out.println("-----------------------------------------");
		for(OrdersVO ovo : orders) {
			System.out.printf("%-14s %-12s %-20s\n", ovo.getOrderNo(),
					ovo.getOrderDate(), ovo.getOrderList());
		}
	} // end of orderList
	
	// prod_orders에 user_id가 구매한 항목 추가
	public void addOrder(String id) {
		// 상품번호 입력에서 Enter키를 누르면 구매 취소
		System.out.println("[구매를 취소하시려면 상품번호 입력에서 Enter키를 누르세요]");
		System.out.print("구매할 상품번호 입력> ");
		String prodNo = scanner.nextLine();
		if(prodNo == null || prodNo.trim().isEmpty()) {
			return;
		}
		else {
			System.out.print("구매할 상품개수 입력> ");
			int num = scanner.nextInt();
			// prodNo의 상품을 원하는 개수만큼 구매(재고량보다 구매 수량이 작을 시)
			int sellNum = mdao.getStock(prodNo) / 5;
			if(sellNum < num) {
				System.out.println("최대 " + sellNum + "개 까지만 구매 가능합니다.");
			}
			else {
				if(mdao.updateStock(prodNo, num * 5)) {
					String orderList = mdao.selectName(prodNo) + " 5Kg " + num + "개";
					ovo.setUserId(id);
					ovo.setOrderNo(getOrderNo());
					ovo.setOrderList(orderList);
					if(odao.insertOrder(ovo)) {
						System.out.println(prodNo + " " + num + "개를 구매했습니다.");
					}
					else {
						System.out.println("처리중 예외가 발생했습니다!");
					}
				}
				else {
					System.out.println("처리중 예외가 발생했습니다!");
				}
			}
		}
	} // end of addOrder
	
	// 주문번호 생성기
	public String getOrderNo() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		return now.format(format);
	} // end of getOrderNo
}
