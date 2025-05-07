package com.heejeong.app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.common.DBUtil;


public class PatDAO {
	//환자 등록
	public int insertPat(PatDTO pat) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				insert into PATIENTID(
				PATIENT_ID,
				PATIENT_NAME,
				RRN,
				BIRTH,
				AGE,
				GENDER,
				PHONE,
				ADDR)
				values(?, ?, ?, ?, ?, ?, ?, ?)
				""";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, pat.getPatient_id());
			st.setString(2, pat.getPatient_name());
			st.setString(3, pat.getRrn());
			st.setString(4, pat.getBirth());
	        st.setInt(5, pat.getAge());
	        st.setString(6, pat.getGender());
	        st.setString(7, pat.getPhone());
	        st.setString(8, pat.getAddr());
			
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}
		return result;
	}
	
	//환자 정보 수정
	public int updatePat(PatDTO pat, String oldname, String oldrrn) {
		System.out.println(pat + " " + oldname + " " + oldrrn);
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				update patientid set   
					patient_name = ?,   
					rrn = ?,
					phone = ?,
					addr = ?    
				where  patient_name = ? and trim(rrn) = ?   
				""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, pat.getPatient_name());
			st.setString(2, pat.getRrn());
			st.setString(3, pat.getPhone());
			st.setString(4, pat.getAddr());
			st.setString(5, oldname);
			st.setString(6, oldrrn);
			
			String debugSQL = DBUtil.buildDebugSQL(sql, 
					pat.getPatient_name(), pat.getRrn(),
					pat.getPhone(),pat.getAddr(),oldname,oldrrn);
			System.out.println("실행 SQL: " + debugSQL);
			
			result = st.executeUpdate();
			System.out.println("executeUpdate수행함");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}
		System.out.println(result);
		return result;
	}
	
	 
	
	
//	public static String buildDebugSQL(String sqlTemplate, Object... params) {
//        for (Object param : params) {
//            String replacement;
//
//            if (param == null) {
//                replacement = "NULL";
//            } else if (param instanceof String) {
//                replacement = "'" + ((String) param).replace("'", "''") + "'";
//            } else if (param instanceof java.sql.Date || param instanceof java.util.Date) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                replacement = "'" + sdf.format((Date) param) + "'";
//            } else if (param instanceof Timestamp) {
//                replacement = "'" + param.toString() + "'";
//            } else if (param instanceof Boolean) {
//                replacement = ((Boolean) param) ? "1" : "0";
//            } else {
//                replacement = param.toString();
//            }
//
//            // 첫 번째 ?만 치환
//            sqlTemplate = sqlTemplate.replaceFirst("\\?", replacement);
//        }

        //return sqlTemplate;
    //}
	
	
	//3.환자 모두 조회
	public List<PatDTO> selectAllPat() {
		List<PatDTO> patlist = new ArrayList<PatDTO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet  rs = null;
		String sql = "select * from patientID";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				PatDTO pat = makePat(rs);
				patlist.add(pat);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return patlist;
	}

	private PatDTO makePat(ResultSet rs) throws SQLException {
		PatDTO pat = PatDTO.builder()
				.patient_id(rs.getInt("patient_id"))
				.patient_name(rs.getString("patient_name"))
				.rrn(rs.getString("rrn"))
				.birth(rs.getString("birth"))
				.age(rs.getInt("age"))
				.gender(rs.getString("gender"))
				.phone(rs.getString("phone"))
				.addr(rs.getString("addr"))
				.build();
		return pat;
	}
}

