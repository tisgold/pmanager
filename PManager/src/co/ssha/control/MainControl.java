package co.ssha.control;

import java.util.Scanner;

import co.ssha.dao.MembersDAO;

public class MainControl {
	//필드
	MembersDAO membersDAO = new MembersDAO();
	ManageControl manageControl = new ManageControl();
	SaleControl saleControl = new SaleControl();
	MembersControl membersControl = new MembersControl();
	OrdersControl ordersControl = new OrdersControl();
	
	Scanner scanner = new Scanner(System.in);
	
	private boolean isTrue = true;
	private int tMenu; // Top 메뉴
	private int mMenu; // Managers 메뉴
	private int uMenu; // Users 메뉴
	private String id; // 로그인 계정 정보

	//생성자
	
	//메소드
	// 프로그램 시작 메뉴
	public void mainMenu() {
		while(isTrue) {
			tMenu = 0;
			System.out.println("[[상품 관리 및 구매 프로그램]]");
			System.out.println();
			System.out.println("##########################");
			System.out.println("1.로그인  2.계정생성  3.종료");
			System.out.println("##########################");
			System.out.print("선택>> ");
			try {
				int num = Integer.parseInt(scanner.nextLine());
				if((num >= 1) && num <= 3) {
					tMenu = num;
				}
				else {
					System.out.println("1 ~ 3 까지의 숫자를 입력하세요.");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("1 ~ 3 까지의 숫자를 입력하세요.");
			}
			
			switch(tMenu) {
			case 1:
				memberLogin();				
				break;
			case 2:
				createMember();
				break;
			case 3:
				System.out.println("프로그램을 종료합니다!");
				isTrue = false;
			}
		}
	}
	
	// 로그인 메뉴
	public void memberLogin() {
		int count = 0;
		while(count < 3) {
			System.out.print("아이디 입력> "); // 아이디 비밀번호 확인
			id = scanner.nextLine();
			System.out.print("비밀번호 입력> ");
			String pw = scanner.nextLine();
			if(membersDAO.isMember(id, pw)) {
				System.out.println(id + "님 로그인 되었습니다!");
				//관리자인지 일반 유저인지 확인 후 메뉴 띄움 isManager()
				if(membersDAO.isManager()) {
					managersMenu(); // 매니저일 경우 호출
				}
				else {
					usersMenu(); // 일반 유저일 경우 호출
				}
				break;
			}
			else {
				count++;
				if(count >= 3) {
					System.out.println("로그인 시도 회수(3)를 초과했습니다.");
					System.out.println();
					return;
				}
				else {
					System.out.println("아이디와 비밀번호를 다시 확인 바랍니다");
				}
			}
		}
	}
	
	// 계정 생성
	public void createMember() {
		membersControl.addMember();
		id = membersControl.getId();
		usersMenu();
	}
	
	// 매니저 메뉴
	public void managersMenu() {
		boolean runM = true;
		mMenu = 0;
		while(runM) {
			System.out.println();
			System.out.println("######################################");
			System.out.println("1.상품관리  2.판매관리  3.계정관리  4.종료");
			System.out.println("######################################");
			System.out.print("선택>> ");
			try {
				int num = Integer.parseInt(scanner.nextLine());
				if((num >= 1) && num <= 4) {
					mMenu = num;
				}
				else {
					System.out.println("1 ~ 4 까지의 숫자를 입력하세요.");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("1 ~ 4 까지의 숫자를 입력하세요.");
			}
			
			switch(mMenu) {
			case 1:
				boolean runC1 = true;
				while(runC1) {
					int sMenu = 0;
					System.out.println();
					System.out.println("===================================================");
					System.out.println("1.상품목록  2.상품등록  3.상품수정  4.상품삭제  5.메인메뉴");
					System.out.println("===================================================");
					System.out.print("선택>> ");
					try {
						int num = Integer.parseInt(scanner.nextLine());
						if((num >= 1) && num <= 5) {
							sMenu = num;
						}
						else {
							System.out.println("1 ~ 5 까지의 숫자를 입력하세요.");
						}
					}
					catch (NumberFormatException e) {
						System.out.println("1 ~ 5 까지의 숫자를 입력하세요.");
					}
					
					switch(sMenu) {
					case 1:
						manageControl.productsList();
						break;
					case 2:
						manageControl.addProduct();
						break;
					case 3:
						manageControl.modifyProduct();
						break;
					case 4:
						manageControl.removeProduct();
						break;
					case 5:
						runC1 = false;
					}
				}
				break;
			case 2:
				boolean runC2 = true;
				while(runC2) {
					int sMenu = 0;
					System.out.println();
					System.out.println("==================================");
					System.out.println("1.판매상품내역  2.판매내역  3.메인메뉴");
					System.out.println("==================================");
					System.out.print("선택>> ");
					try {
						int num = Integer.parseInt(scanner.nextLine());
						if((num >= 1) && num <= 3) {
							sMenu = num;
						}
						else {
							System.out.println("1 ~ 3 까지의 숫자를 입력하세요.");
						}
					}
					catch (NumberFormatException e) {
						System.out.println("1 ~ 3 까지의 숫자를 입력하세요.");
					}

					switch(sMenu) {
					case 1:
						saleControl.salesList();
						break;
					case 2:
						ordersControl.ordersList();
						break;
					case 3:
						runC2 = false;
					}
				}
				break;
			case 3:
				boolean runC3 = true;
				while(runC3) {
					int sMenu = 0;
					System.out.println();
					System.out.println("===============================================");
					System.out.println("1.계정목록  2.계정삭제  3.관리자 계정등록  4.메인메뉴");
					System.out.println("===============================================");
					System.out.print("선택>> ");
					try {
						int num = Integer.parseInt(scanner.nextLine());
						if((num >= 0) && num <= 4) {
							sMenu = num;
						}
						else {
							System.out.println("1 ~ 4 까지의 숫자를 입력하세요.");
						}
					}
					catch (NumberFormatException e) {
						System.out.println("1 ~ 4 까지의 숫자를 입력하세요.");
					}

					switch(sMenu) {
					case 1:
						membersControl.membersList();
						break;
					case 2:
						if(membersControl.removeMember()) {
							if(membersControl.getId().equals(id)) {
								runM = false;
							}
							id = null;
							break;
						}
						break;
					case 3:
						membersControl.addManager();
						break;
					case 4:
						runC3 = false;
					}
				}
				break;
			case 4:
				System.out.println("프로그램을 종료합니다!");
				isTrue = false;
				runM = false;
			}
		}
	}
	
	// 일반 사용자 메뉴
	public void usersMenu() {
		boolean runU = true;
		while(runU) {
			uMenu = 0;
			System.out.println();
			System.out.println("=============================================");
			System.out.println("1.상품목록  2.구매  3.구매내역  4.회원탈퇴  5.종료");
			System.out.println("=============================================");
			System.out.print("선택>> ");
			try {
				int num = Integer.parseInt(scanner.nextLine());
				if((num >= 1) && num <= 5) {
					uMenu = num;
				}
				else {
					System.out.println("1 ~ 5 까지의 숫자를 입력하세요.");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("1 ~ 5 까지의 숫자를 입력하세요.");
			}
			
			switch(uMenu) {
			case 1:
				saleControl.salesList();
				break;
			case 2:
				ordersControl.addOrder(id);
				break;
			case 3:
				ordersControl.orderList(id);
				break;
			case 4:
				membersControl.deleteMember(id);
				if(membersControl.isDeleteMember()) {
					id = null;
					runU = false;
				}
				break;
			case 5:
				tMenu = 3;
				System.out.println();
				runU = false;
			}
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
