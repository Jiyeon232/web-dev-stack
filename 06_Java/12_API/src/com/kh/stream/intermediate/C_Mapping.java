package com.kh.stream.intermediate;

import java.util.Arrays;
import java.util.List;

import com.kh.stream.model.Student;

// 매핑 - 중간 처리 기능으로 스트림의 요소를 다른 요소로 대체하는 역할
public class C_Mapping {
	
	// mapXXX : 요소를 대체하는 요소로 구성된 새로운 스트림 생성
	public void method1() {
		
		List<Student> students = Arrays.asList(
				new Student("오재덕", 20, "남자", 80, 90),
				new Student("이승민", 19, "남자", 75, 80),
				new Student("이환희", 19, "여자", 50, 100),
				new Student("박기민", 17, "남자", 70, 90),
				new Student("조규상", 18, "남자", 75, 85),
				new Student("이상엽", 20, "남자", 100, 60),
				new Student("성예찬", 19, "남자", 70, 95));
		
		students
			.stream()
			.map(student -> student.getName())
			.forEach(name -> System.out.println(name));
		
		// 수학 점수 평균
		double result = students.stream()
				                .mapToInt(student -> student.getMath())
				                .average().getAsDouble();

		System.out.println(result);
		
	}
	
	// flatMapXXX : 하나의 요소를 복수의 요소들로 구성된 새로운 스트림 생성
	public void method2() {
		List<String> list = Arrays.asList("Java Lambda", "Stream Filtering Sorted Mapping");
		
		list.stream()
			/*.flatMap((String str) -> {
				String[] arr = str.split(" ");
				return Arrays.stream(arr);
			})*/
			.flatMap(str -> Arrays.stream(str.split(" ")))
			//.forEach(str -> System.out.println(str));
			.forEach(System.out::println); // 위에 해당 라인 함축된 표현!
	}
	
	/*
	 * asDoubleStream(), asLongStream(), boxed()
	 * - asDoubleStream : IntStream, LongStream을 DoubleStream으로 변환
	 * - asLongStream : IntStream을 LongStream으로 변환
	 * - boxed : int, long, double 요소를 Integer, Long, Double 요소로 박싱
	 * */
	public void method3() {
		int[] arr1 = { 1, 2, 3, 4, 5 };
		double[] arr2 = { 1.1, 2.2, 3.3, 4.4, 5.5 };
		
		Arrays.stream(arr1)
			.asDoubleStream()
			.forEach(num -> System.out.println(num));
		
		Arrays.stream(arr2)
			.boxed()
			.forEach(System.out::println);
	}

	public static void main(String[] args) {
		C_Mapping c = new C_Mapping();
		//c.method1();
		//c.method2();
		c.method3();
	}

}
