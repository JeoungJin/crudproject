package com.shinhan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.shinhan.common.DBUtil;

public class StockDAO {
	public  void f_getAllStock(List<StockSDTO> stockList) {
		String sql = "insert into stock values(?,?)";
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		try {
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			int[] resultArr = null;
			
			for(StockSDTO stock:stockList) {
				st.setString(1, stock.getStock_code());
				st.setString(2, stock.getStock_name());
				st.addBatch();
				st.clearParameters();
			}
			resultArr = st.executeBatch();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}
		
	}
}
