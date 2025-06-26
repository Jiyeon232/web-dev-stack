package com.kh.set;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.kh.list.model.Person;

/*
 * TreeSet
 * - 저장과 동시에 자동 오름차순 정렬 (HashSet + 정렬)
 * - HashSet보다 데이터 추가, 삭제에 시간이 더 걸림
 * */
public class B_TreeSet {
	
	public void method1() {
		Set<String> set = new TreeSet<>();
		set.add("전현무");
		set.add("박나래");
		set.add("기안84");
		set.add("키");
		set.add("박나래"); // 여러 번 추가해도 중복 제거!
		
		System.out.println(set);
		System.out.println("몇 명이 들어있나요? " + set.size());
		System.out.println("기안84가 포함되어있나요? " + set.contains("기안84"));
		
		set.remove("박나래"); // 삭제
		System.out.println(set);
		
		set.clear(); // 전체 삭제
		System.out.println("비어있는가? " + set.isEmpty());
	}
	
	public void method2() {
		TreeSet<Person> set = new TreeSet<>();
		set.add(new Person("전현무", "삼성동", 47));
		set.add(new Person("박나래", "이태원동", 39));
		set.add(new Person("기안84", "과천시", 40));
		set.add(new Person("키", "한남동", 33));
		set.add(new Person("키", "한남동", 33));
		
		for (Person p : set) {
			System.out.println(p);
		}
		
		System.out.println(set);
		
		/*
		 * Iterator
		 * - 컬렉션에 저장된 요소를 접근하는데 사용하는 인터페이스
		 * - iterator()를 호출해서 Iterator를 구현한 객체를 얻어서 사용
		 * */
		System.out.println("---");
		Iterator<Person> it = set.iterator();
		
		while (it.hasNext()) { // 읽어올 요소가 있는지 확인
			System.out.println(it.next()); // 다음 요소를 읽어옴
		}
	}

	public static void main(String[] args) {
		B_TreeSet b = new B_TreeSet();
		b.method1();
		b.method2();
	}

}
