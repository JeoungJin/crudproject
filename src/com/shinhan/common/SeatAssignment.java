package com.shinhan.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SeatAssignment {
	static final int ROWS = 7;
	static final int COLS = 4;
	public static void main(String[] args) {
		List<String> names = new ArrayList<>(
				List.of(
						"김민성", "박채원", "김지민", "최윤정", 
						"김대현", "최희정", "최원정", "한진호",
						"이상현", "최다희", "김정은", "김기도", 
						"안세현", "차민건", "천희찬", "윤민혁",
						"강경민", "이재희", "최은진", "김세연", 
						"류채린", "안가연", "유바다", "조상호",
						"권용희", "유채승", "이가윤", "이정헌"
				));
		// 좌우 이웃 정보를 저장할 Map
		Map<String, Set<String>> prevNeighbors = new HashMap<>();
		// 이름 리스트를 7x4로 가정하고 좌우 이웃 관계 등록
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS - 1; j++) {
				String left = names.get(i * COLS + j);
				String right = names.get(i * COLS + j + 1);

				// computeIfAbsent 사용: 키 없으면 새 HashSet 생성 후 바로 add
				prevNeighbors.computeIfAbsent(left, k -> new HashSet<>()).add(right);
				prevNeighbors.computeIfAbsent(right, k -> new HashSet<>()).add(left);
			}
		}
		List<String> shuffled = new ArrayList<>(names);
		Random rand = new Random();
		// 유효한 자리 찾을 때까지 섞기
		while (true) {
			Collections.shuffle(shuffled, rand);
			boolean isExist = false;
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLS - 1; j++) {
					String a = shuffled.get(i * COLS + j);
					String b = shuffled.get(i * COLS + j + 1);
					if (prevNeighbors.containsKey(a) && prevNeighbors.get(a).contains(b)) {
						isExist = true;	break;
					}
				}
				if (isExist) break;
			}
			if (!isExist) break;
		}

		// 결과 출력
		String[] arr1 = {"➊","❷","➌","➍","➎","➏","➐"};
		String[] arr2 = { "✨1",	"🌟2","💫3","🌈4"};
		for (int i = 0; i < ROWS; i++) {
			System.out.printf("\n\n****************** %s줄 *****************\n", arr1[i]);
			for (int j = 0; j < COLS; j++) {
				System.out.printf("[%s]%-5s",arr2[j], shuffled.get(i * COLS + j));
			}			
		}
	}
}
