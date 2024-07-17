package co.ssha.control;

import java.util.List;
import java.util.Scanner;

import co.ssha.dao.MembersDAO;
import co.ssha.dao.OrdersDAO;
import co.ssha.vo.MembersVO;

public class MembersControl {
	//필드
	MembersDAO memdao = new MembersDAO();
	OrdersDAO odao = new OrdersDAO();
	Scanner scanner = new Scanner(System.in);
	private boolean isDeleteMember = false;
	private String id = null;
	//생성자
	
	//메소드
	// prod_members 항목 출력
	public void membersList() {
		List<MembersVO> members = memdao.selectMembers();
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("아이디       이름         전화번호           주소               생성일자       관리자");
		System.out.println("------------------------------------------------------------------------------");
		for(MembersVO memvo : members) {
			System.out.printf("%-10s %-10s %-16s %-16s %10s %3s\n", memvo.getUserId(), memvo.getUserName(),
					memvo.getPhone(), memvo.getAddress(), memvo.getGenDate(), memvo.getIsManager());
		}
	} // end of membersList
	
	// 관리자 계정 생성
	public void addManager() {
		boolean run = true;
		while(run) {
			System.out.print("생성할 아이디 입력> ");
			String userId = scanner.nextLine();
			// prod_members 테이블의 user_id에 입력된 값이 있는지 확인
			if(memdao.isNewId(userId)) {
				System.out.print("비밀번호 입력> ");
				String userPw = scanner.nextLine();
				System.out.print("이름 입력> ");
				String userName = scanner.nextLine();
				System.out.print("전화번호 입력> ");
				String phone = scanner.nextLine();
				System.out.print("주소 입력> ");
				String address = scanner.nextLine();
				
				MembersVO memvo = new MembersVO();
				memvo.setUserId(userId);
				memvo.setUserPw(userPw);
				memvo.setUserName(userName);
				memvo.setPhone(phone);
				memvo.setAddress(address);
				
				if(memdao.insertManager(memvo)) {
					System.out.println(userId + " 계정이 관리자 권한으로 등록되었습니다.");
					setId(userId);
					run = false;
				}
				else {
					System.out.println("처리중 예외가 발생했습니다.");
				}
			}
			else {
				System.out.println("이미 중복된 계정이 있습니다!");
			}
		}
	} // end of addManager
	
	// 관리자가 계정 삭제
	public boolean removeMember() {
		System.out.print("삭제할 계정 입력> " );
		String userId = scanner.nextLine();
		if(userId.equals("admin")) {
			System.out.println("admin 계정은 삭제할 수 없습니다!!");
			return false;
		}
		if(memdao.deleteMember(userId)) {
			id = userId;
			System.out.println(userId + " 계정이 삭제되었습니다.");
			System.out.println();
			setDeleteMember(true);
			return true;
		}
		else {
			System.out.println("계정을 다시 확인 바랍니다.");
			return false;
		}
	} // end of removeMember
	
	// 유저 계정 생성
	public void addMember() {
		boolean run = true;
		while(run) {
			System.out.print("생성할 아이디 입력> ");
			String userId = scanner.nextLine();
			// prod_members 테이블의 user_id에 입력된 값이 있는지 확인
			if(memdao.isNewId(userId)) {
				System.out.print("비밀번호 입력> ");
				String userPw = scanner.nextLine();
				System.out.print("이름 입력> ");
				String userName = scanner.nextLine();
				System.out.print("전화번호 입력> ");
				String phone = scanner.nextLine();
				System.out.print("주소 입력> ");
				String address = scanner.nextLine();
				
				MembersVO memvo = new MembersVO();
				memvo.setUserId(userId);
				memvo.setUserPw(userPw);
				memvo.setUserName(userName);
				memvo.setPhone(phone);
				memvo.setAddress(address);
				
				if(memdao.insertMember(memvo)) {
					System.out.println(userId + " 계정이 등록되었습니다.");
					setId(userId);
					run = false;
				}
				else {
					System.out.println("처리중 예외가 발생했습니다.");
				}
			}
			else {
				System.out.println("이미 중복된 계정이 있습니다!");
			}
		}
	} // end of addMember
	
	// 유저가 회원 탈퇴하면서 자신의 계정 삭제
	public void deleteMember(String id) {
		System.out.print("회원 탈퇴 하시겠습니까? (확인: Y) > ");
		String str = scanner.nextLine();
		if(str.equalsIgnoreCase("y")) {
			if(odao.deleteOrder(id)) {
				System.out.println(id + " 계정의 구매내역을 삭제했습니다.");
			}
			if(memdao.deleteMember(id)) {
				System.out.println(id + " 계정이 삭제되었습니다.");
				System.out.println();
				setDeleteMember(true);
			}
			else {
				System.out.println("처리중 예외가 발생했습니다.");
			}
		}
		else {
			return;
		}
	} // end of deleteMember

	public boolean isDeleteMember() {
		return isDeleteMember;
	}

	public void setDeleteMember(boolean isDeleteMember) {
		this.isDeleteMember = isDeleteMember;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
