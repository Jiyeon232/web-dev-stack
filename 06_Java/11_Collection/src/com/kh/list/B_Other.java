package com.kh.list;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class B_Other {

	/*
	 * Vector - 동기화된(synchronized) 메서드로 구성 (개념은 스레드에서~)
	 * */
	public void method1() {
		Vector<Integer> v = new Vector<>();
		v.add(0);
		v.add(1);
		v.add(2);
		v.add(3);
		v.add(4);
		v.add(5);
		v.add(6);
		v.add(7);
		v.add(8);
		v.add(9);
		v.add(10);
		System.out.println(v);
		// capacity : 내부 배열 크기, size : 실제 배열 데이터 수
		System.out.println("size : " + v.size()); // 11
		System.out.println("capacity : " + v.capacity()); // 20
		
		v.trimToSize(); // capacity 사이즈를 size에 맞게 줄임!
		
		System.out.println("size : " + v.size()); // 11
		System.out.println("capacity : " + v.capacity()); // 11
	}
	
	// 스택(Stack) : 마지막에 저장한 것을 제일 먼저 꺼내는 LIFO(Last In First Out)
	public void method2() {
		Stack<Integer> s = new Stack<>();
		s.push(0); // push : 데이터 추가
		s.push(1);
		s.push(2);
		s.push(3);
		s.pop(); // pop : 마지막에 추가된 데이터 삭제
		s.push(4);
		s.pop();
		s.push(5);
		s.push(6);
		s.push(7);
		s.pop();
		System.out.println(s); // [0, 1, 2, 5, 6]
	}
	
	// 큐(Queue) : 처음에 저장한 것을 제일 먼저 꺼내는 FIFO(First In First Out)
	public void method3() {
		// 큐는 인터페이스!
		Queue<Integer> q = new LinkedList<>();
		q.offer(0); // 데이터 추가
		q.offer(1);
		q.offer(2);
		q.offer(3);
		q.poll(); // 먼저 추가된 데이터 삭제
		q.add(4); // 데이터 추가
		q.remove(); // 데이터 삭제
		q.add(5);
		q.add(6);
		q.add(7);
		q.remove();
		System.out.println(q); // [3, 4, 5, 6, 7]
	}
	
	// Deque - 양 방향, Stack/Queue 둘 다 사용 가능
	public void method4() {
		Deque<Integer> d = new LinkedList<>();
		
		// 데이터 추가
		d.offerLast(1);
		d.offerLast(2);
		d.offerLast(3);
		
		// Stack - 마지막 데이터부터 삭제
		d.pollLast(); // 3 삭제
		
		// Queue - 첫번째 데이터부터 삭제
		d.pollFirst(); // 1 삭제
		
		d.offerFirst(0); // 앞에서부터 추가
		
		System.out.println(d);
	}
	
	public static void main(String[] args) {
		B_Other b = new B_Other();
		//b.method1();
		//b.method2();
		//b.method3();
		b.method4();
	}

}
