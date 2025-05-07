package com.heejeong.app;

import java.util.List;

public class PatView {

	public static void display(List<PatDTO> patlist) {
		System.out.println("환자 여러건 조회");
		patlist.stream().forEach(pat -> System.out.println(pat));
	}
	
	public static void display(PatDTO pat) {
		System.out.println(pat);
	}
	
	public static void display(String message) {
		System.out.println("**알림** "+ message);
	}

}
