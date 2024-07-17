package co.ssha.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.ssha.vo.SaleVO;

public class SaleDAO extends DAO {
	//필드
	SaleVO saleVO = new SaleVO();
	
	//생성자
	
	//메소드
	// prod_sale 테이블에 새 항목 추가
	public boolean insertSale(SaleVO svo) {
		conn = getConn();
		String sql = "insert into prod_sale (prod_no, prod_name, prod_desc, sell_unit)";
		sql += " values (?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svo.getProdNo());
			pstmt.setString(2, svo.getProdName());
			pstmt.setString(3, svo.getProdDesc());
			pstmt.setInt(4, svo.getSellUnit());
			
			int result = pstmt.executeUpdate(); // 쿼리실행
			if (result == 1) {
				return true; // 정상처리
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 비정상처리
	} // end of insertSale
	
	// prod_sale 테이블에 추가하기 위해 prod_manage 테이블의 정보를 가져온 후 처리 
	public void addSale(String prodNo) {
		if(isExist(prodNo)) {
			System.out.println(prodNo + " 상품은 이미 등록되어 있습니다.");
		}
		else {
			String sql = "select prod_name, prod_desc from prod_manage";
			sql += " where prod_no = '" + prodNo + "'";
			
			SaleVO svo = new SaleVO();
			conn = getConn();
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					svo.setProdNo(prodNo);
					svo.setProdName(rs.getString("prod_name"));
					svo.setProdDesc(rs.getString("prod_desc"));
					svo.setSellUnit(5);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(insertSale(svo)) {
				System.out.println(prodNo + " 상품이 등록되었습니다.");
			}
			else {
				System.out.println("상품번호를 다시 확인 바랍니다.");
			}
		}
		
	} // end of addSale
	
	// prod_sale 테이블에서 prodNo 항목이 있는지 확인 후 삭제
	public void removeSale(String prodNo) {
		if(isExist(prodNo)) {
			conn = getConn();
			String sql = "delete from prod_sale";
			sql += " where prod_no = '" + prodNo + "'";
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 전체 판매 상품 리스트
	public List<SaleVO> selectSale(){
		String sql = "select prod_no, prod_name, prod_desc, sell_unit from prod_sale";
		sql += " order by prod_no";
		List<SaleVO> list = new ArrayList<>();
		
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				SaleVO svo = new SaleVO();
				svo.setProdNo(rs.getString("prod_no"));
				svo.setProdName(rs.getString("prod_name"));
				svo.setProdDesc(rs.getString("prod_desc"));
				svo.setSellUnit(rs.getInt("sell_unit"));
				list.add(svo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // end of selectSale
	
	// prod_sale 테이블에 prodNo가 있는지 확인
	public boolean isExist(String prodNo) {
		String sql = "select prod_no from prod_sale";
		sql += " where prod_no = '" + prodNo + "'";
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} // end of isExist
	
	public int getSalePrice(String prodNo) {
		String sql = "select price from prod_manage";
		sql += " where prod_no = '" + prodNo + "'";
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getInt("price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// prod_manage 테이블에 prod_no = 'prodNo'인 행의 price를 못가져왔을 때 
		return 0;
	}
}
