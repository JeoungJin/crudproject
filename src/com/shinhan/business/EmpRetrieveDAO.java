package com.shinhan.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shinhan.common.DBUtil;

public class EmpRetrieveDAO {

	public List<EmpDeptJobDTO> selectEmpInfo() {
		List<EmpDeptJobDTO> emplist = new ArrayList<>();
		List<Map<String,Object>> emplist2 = new ArrayList<>();
		
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = """
				select employee_id, first_name, last_name, department_name, job_title 
				from  employees join departments using (department_id) 
                join jobs using (job_id)
                
				""";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				EmpDeptJobDTO emp1 = makeEmp(rs);
				Map<String,Object> emp2 = makeEmp2(rs);
				emplist.add(emp1);
				emplist2.add(emp2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return emplist;
	}

	//Map사용하기 
	private Map<String, Object> makeEmp2(ResultSet rs) throws SQLException {
		Map<String, Object> emp = new HashMap<>();
		emp.put("department_name", rs.getString("department_name"));
		emp.put("employee_id", rs.getInt("employee_id"));
		emp.put("first_name", rs.getString("first_name"));
		emp.put("job_title", rs.getString("job_title"));
		emp.put("last_name", rs.getString("last_name"));		
		return emp;
	}
	//DTO사용하기 
	private EmpDeptJobDTO makeEmp(ResultSet rs) throws SQLException {
		EmpDeptJobDTO emp= EmpDeptJobDTO.builder()
				.department_name(rs.getString("department_name"))
				.employee_id(rs.getInt("employee_id"))
				.first_name(rs.getString("first_name"))
				.job_title(rs.getString("job_title"))
				.last_name(rs.getString("last_name"))
				.build();
		return emp;
	}
	
}






