package co.ssha.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.ssha.vo.MembersVO;

public class MembersDAO extends DAO {
	//필드
	MembersVO memberVO = new MembersVO();
	private boolean isManager = false;
	
	//생성자
	
	//메소드
	// 전체 회워 리스트
	public List<MembersVO> selectMembers() {
		String sql = "select * from prod_members order by user_id";
		List<MembersVO> list = new ArrayList<>();
		
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MembersVO memvo = new MembersVO();
				memvo.setUserId(rs.getString("user_id"));
				memvo.setUserPw(rs.getString("user_pw"));
				memvo.setUserName(rs.getString("user_name"));
				memvo.setPhone(rs.getString("phone"));
				memvo.setAddress(rs.getString("address"));
				memvo.setGenDate(rs.getDate("gen_date"));
				memvo.setIsManager(rs.getString("is_manager"));
				list.add(memvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // end of selectMembers
	
	// 관리자 계정 생성
	public boolean insertManager(MembersVO memvo) {
		String sql = "insert into prod_members ";
		sql += "(user_id, user_pw, user_name, phone, address, is_manager)";
		sql += " values(?, ?, ?, ?, ?, ?)";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memvo.getUserId());
			pstmt.setString(2, memvo.getUserPw());
			pstmt.setString(3, memvo.getUserName());
			pstmt.setString(4, memvo.getPhone());
			pstmt.setString(5, memvo.getAddress());
			pstmt.setString(6, "1");
			
			int result = pstmt.executeUpdate();
			if(result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} // end of insertManager
	
	// 일반 유저 계정 생성
	public boolean insertMember(MembersVO memvo) {
		String sql = "insert into prod_members ";
		sql += "(user_id, user_pw, user_name, phone, address, is_manager)";
		sql += " values(?, ?, ?, ?, ?, ?)";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memvo.getUserId());
			pstmt.setString(2, memvo.getUserPw());
			pstmt.setString(3, memvo.getUserName());
			pstmt.setString(4, memvo.getPhone());
			pstmt.setString(5, memvo.getAddress());
			pstmt.setString(6, "0");
			
			int result = pstmt.executeUpdate();
			if(result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} // end of insertMember
	
	// 계정 삭제
	public boolean deleteMember(String userId) {
		String sql = "delete from prod_members";
		sql += " where user_id = '" + userId + "'";
		conn = getConn();
		try {
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} // end of deleteMember
	
	// 아이디와 비밀번호 확인
	public boolean isMember(String id, String pw) {
		// id, pw를 where 조건문으로 쿼리 후 결과 값으로 판단
		String sql = "select user_id, user_pw, is_manager from prod_members";
		sql += " where user_id = '" + id + "'";
		sql += " and user_pw = '" + pw + "'";
		
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs.next()) {
				if(rs.getString("is_manager").equals("1")) {
					isManager = true;
				}
				else {
					isManager = false;
				}
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} // end of isMember
	
	// 계정 생성시 아이디가 중복되는지 확인
	public boolean isNewId(String id) {
		String sql = "select user_id from prod_members";
		sql += " where user_id = '" + id + "'";
		
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(!rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} // end of isNewId
	
	// 매니저인지 확인
	public boolean isManager() {
		return isManager;
	}
}
