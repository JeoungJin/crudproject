package com.shinhan.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmpDeptJobDTO {

	Integer employee_id; 
	String first_name; 
	String last_name; 
	String department_name; 
	String job_title; 
	
}
