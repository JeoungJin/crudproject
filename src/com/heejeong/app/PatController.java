package com.heejeong.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PatController {
	static Scanner sc = new Scanner(System.in);
	static PatService patService = new PatService();

	public static void main(String[] args) {

		boolean isStop = false;
		while (!isStop) {
			menudisplay();
			int job = sc.nextInt();
			switch(job) {
			case 1 -> {f_insertPatient();}
			case 2 -> {f_updatePatient();}
			case 3 -> {f_selectAllPatient();}
			case 9 -> {isStop = true; break;}
			}
		}

	}


	private static void f_selectAllPatient() {
		List<PatDTO> patlist = patService.selectAllPat();
		PatView.display(patlist);
		
	}



	private static void f_updatePatient() {
		
		System.out.println("==환자 정보 수정 중==");
		System.out.print("수정할 환자 이름 : ");
		String oldname = sc.next();
		System.out.print("수정할 환자 주민번호 : ");
		String oldrrn = sc.next();
		System.out.println("환자가 확인되었습니다.");
		
		System.out.print("환자 이름 재입력: ");
		String patient_name = sc.next();
		System.out.print("환자 주민번호 재입력 : ");
		String rrn = sc.next();
		System.out.print("환자 전화번호 재입력: ");
		String phone = sc.next();
		System.out.print("환자 주소 재입력: ");
		String addr = sc.next();
		
		PatDTO patDTO = PatDTO.builder()
				.patient_name(patient_name)
				.rrn(rrn)
				.phone(phone)
				.addr(addr)
				.build();
		
		int result = patService.updatePat(patDTO, oldname, oldrrn);
		System.out.println("update 완료");
	}

	private static void f_insertPatient() {
		System.out.println("==환자 등록 중==");
		patService.insertPat(makePat());
		PatView.display("추가되었습니다");
		
	}

	private static PatDTO makePat() {
		System.out.print("patient_id : ");
		int patient_id = sc.nextInt();
		System.out.print("patient_name : ");
		String patient_name = sc.next();
		System.out.print("rrn (000000-000000) : ");
		String rrn = sc.next();
		System.out.print("phone (010-1111-1111) : ");
		String phone = sc.next();
		System.out.print("addr : ");
		String addr = sc.next();
		
		
		PatDTO patDTO = PatDTO.builder()
				.patient_id(patient_id)
				.patient_name(patient_name)
				.rrn(rrn)
				.phone(phone)
				.addr(addr)
				.build();

		return patDTO;
		
	}


	private static void menudisplay() {
		System.out.println("===메뉴 입력===");
		System.out.println("1. 환자 등록 2. 환자 정보 수정 3. 환자 모두 조회");
		System.out.println("============");
		System.out.print("입력 >> ");
	}
}
