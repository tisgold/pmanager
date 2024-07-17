package co.ssha.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.ssha.vo.OrdersVO;

public class OrdersDAO extends DAO {
	//필드
	
	//생성자
	
	//메소드
	// 전체 구매 목록
	public List<OrdersVO> selectOrders() {
		String sql = "select * from prod_orders order by user_id";
		List<OrdersVO> list = new ArrayList<>();
		
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				OrdersVO ovo = new OrdersVO();
				ovo.setUserId(rs.getString("user_id"));
				ovo.setOrderNo(rs.getString("order_no"));
				ovo.setOrderDate(rs.getDate("order_date"));
				ovo.setOrderList(rs.getString("order_list"));
				
				list.add(ovo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // end of selectOrders
	
	// user_id별 구매 목록
	public List<OrdersVO> selectOrder(String userId) {
		String sql = "select * from prod_orders";
		sql += " where user_id = '" + userId + "' order by user_id";
		List<OrdersVO> list = new ArrayList<>();
		
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				OrdersVO ovo = new OrdersVO();
				ovo.setUserId(rs.getString("user_id"));
				ovo.setOrderNo(rs.getString("order_no"));
				ovo.setOrderDate(rs.getDate("order_date"));
				ovo.setOrderList(rs.getString("order_list"));
				
				list.add(ovo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // end of selectOrder
		
	// 구매 시 새 항목 추가
	public boolean insertOrder(OrdersVO ovo) {
		String sql = "insert into prod_orders";
		sql += " (user_id, order_no, order_list)";
		sql += " values (?, ?, ?)";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ovo.getUserId());
			pstmt.setString(2, ovo.getOrderNo());
			pstmt.setString(3, ovo.getOrderList());
			
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} // end of insertOrder
	
	// 구매 항목 삭제
	public boolean deleteOrder(String userId) {
		String sql = "delete from prod_orders";
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
	} // end of deleteOrder
}
