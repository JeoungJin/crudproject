package com.heejeong.app;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatDTO {
	private int patient_id; //환자ID
	private String patient_name; //환자이름
	private String rrn; //주민번호
	private String birth; //생년월일
	private int age; //나이
	private String gender; //성별
	private String phone; //전화번호
	private String addr; //주소
	
	
	
	@Override
	public String toString() {
		return "[!!]등록번호=" + patient_id + ", 이름=" + patient_name + ", 주민번호=" + rrn + 
				", 생년월일=" + birth + "(" + age + "/" + gender+ ") 전화번호=" + phone + ", 주소=" + addr;
	}

}


