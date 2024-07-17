package co.ssha.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.ssha.vo.ManageVO;

public class ManageDAO extends DAO {
	//필드
	SaleDAO sdao = new SaleDAO();
	
	//생성자
	
	//메소드
	// 전체 상품 리스트
	public List<ManageVO> selectManage() {
		String sql = "select * from prod_manage order by prod_no";
		List<ManageVO> list = new ArrayList<>();
		
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ManageVO mvo = new ManageVO();
				mvo.setProdNo(rs.getString("prod_no"));
				mvo.setProdName(rs.getString("prod_name"));
				mvo.setProdDesc(rs.getString("prod_desc"));
				mvo.setProdDate(rs.getString("prod_date"));
				mvo.setPrice(rs.getInt("price"));
				mvo.setIndoor(rs.getInt("indoor"));
				mvo.setStock(rs.getInt("stock"));
				list.add(mvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // end of selectList
			
	// 새 상품 입력
	public boolean insertProduct(ManageVO mvo) {
		String sql = "insert into prod_manage ";
		sql += "(prod_no, prod_name, prod_desc, prod_date, price, indoor, stock)";
		sql += " values (prod_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, mvo.getProdNo());
			pstmt.setString(1, mvo.getProdName());
			pstmt.setString(2, mvo.getProdDesc());
			pstmt.setString(3, mvo.getProdDate());
			pstmt.setInt(4, mvo.getPrice());
			pstmt.setInt(5, mvo.getIndoor());
			pstmt.setInt(6, mvo.getStock());
			
			int result = pstmt.executeUpdate(); // 쿼리실행
			if (result == 1) {
				return true; // 정상처리
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 비정상처리
	} // end of insertProduct
	
	// prodNo 상품 수정
	public boolean updateProduct(ManageVO mvo) {
		String sql = "update prod_manage";
		sql += " set prod_name = nvl('" + mvo.getProdName() + "', prod_name)";
		sql += ", prod_desc = nvl('" + mvo.getProdDesc() + "', prod_desc)";
		sql += ", prod_date = nvl('" + mvo.getProdDate() + "', prod_date)";
		sql += ", price = nvl('" + mvo.getPrice() + "', price)";
		sql += ", indoor = nvl('" + mvo.getIndoor() + "', indoor)";
		sql += ", stock = nvl('" + mvo.getStock() + "', stock)";
		sql += " where prod_no = '" + mvo.getProdNo() + "'";
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
	} // end of updateStudent
	
	// prodNo 상품 삭제
	public boolean deleteProduct(String prodNo) {
		sdao.removeSale(prodNo); // prod_sale 테이블에서 있는지 확인 후 먼저 제거
		
		String sql = "delete from prod_manage";
		sql += " where prod_no = '" + prodNo + "'";
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
	} // end of removeProduct
	
	// prodNo의 현재 재고량 확인
	public int getStock(String prodNo) {
		String sql = "select stock from prod_manage";
		sql += " where prod_no = '" + prodNo + "'";
		
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getInt("stock");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	} // end of getStock
	
	// 물품 판매시 재고량 수정
	public boolean updateStock(String prodNo, int num) {
		int newStock = getStock(prodNo) - num; 
		String sql = "update prod_manage set stock = " + newStock;
		sql += " where prod_no = '" + prodNo + "'";
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
	} // end of updateStock
	
	// prodNo 에 해당하는 상품 확인
	public String selectName(String prodNo) {
		String sql = "select prod_name from prod_manage";
		sql += " where prod_no = '" + prodNo + "'";
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getString("prod_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	} // end of selectName
}
