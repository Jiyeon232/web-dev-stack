package com.kh._abstract;

import com.kh._abstract.step1.BasketBall;
import com.kh._abstract.step1.FootBall;
import com.kh._abstract.step1.Sports;
import com.kh._abstract.step2.BulgogiPizza;
import com.kh._abstract.step2.PineapplePizza;
import com.kh._abstract.step2.Pizza;
import com.kh._abstract.step2.PotatoPizza;

/*
 * 추상클래스(Abstract Class)
 * - 미완성 클래스로 추상 메서드를 포함한 클래스
 * - 미완성 클래스이기 때문에 new 연산자를 통해서 객체 생성 X
 * - 일반적인 필드, 메서드 포함 (변수 + 메서드 + 추상 메서드)
 * - 객체 생성할 수 없지만 참조형 변수 타입으로 사용 가능
 * - 상속 관계로 다형성 적용 가능
 * 
 * 추상메서드(Abstract Method)
 * - 미완성 메서드로 중괄호({})가 구현되지 않는 메서드
 * - 추상 클래스를 상속받는 자식 클래스에서 "반드시" 오버라이딩(재정의)
 * - 오버라이딩(재정의) 해주지 않으면 컴파일 에러
 * - 자식 클래스에 강제성 부여
 * */

public class Application {

	public static void main(String[] args) {
		// 추상 클래스여서 객체 생성 X
		//Sports s = new Sports(5);
		
		// 다형성으로 인해서 부모 타입으로 가능!
		Sports b = new BasketBall(9);
		Sports f = new FootBall(11);
		
		b.rule();
		f.rule();
		
		/*
		 * 브랜드, 가격 자유롭게, 세가지 피자는 다 사용
		 * 
		 * 해당 브랜드의 불고기 피자 가격은 ~원
		 * 피자 반죽과 함께 도우를 빚는다.
		 * 토핑은 불고기를 포함한다.
		 * 피자를 180도에서 10분간 구운다
		 * 피자를 8등분 한다.
		 * 피자를 포장한다.
		 * 
		 * 해당 브랜드의 파인애플 피자 가격은 ~원
		 * 피자 반죽과 함께 도우를 빚는다.
		 * 토핑은 파인애플을 포함한다.
		 * 피자를 180도에서 10분간 구운다
		 * 피자를 8등분 한다.
		 * 피자를 포장한다.
		 * 
		 * 해당 브랜드의 포테이토 피자 가격은 ~원
		 * 피자 반죽과 함께 도우를 빚는다.
		 * 토핑은 포테이토를 포함한다.
		 * 피자를 180도에서 10분간 구운다
		 * 피자를 8등분 한다.
		 * 피자를 포장한다.
		 * 
		 * */
		
		Pizza[] pizza = { // 피자 배열에 넣어서 한꺼번에 처리하기
				new BulgogiPizza(1000, "피자헛"),
				new PineapplePizza(2000, "파파존스"),
				new PotatoPizza(3000, "도미노피자")
		};
		
		for (Pizza p : pizza) {
			System.out.println();
			p.bake();
		}
		
		
		/*
		Pizza bulgogi = new BulgogiPizza(10000, "A피자");
		Pizza Pineapple = new PineapplePizza(20000, "B피자");
		Pizza Potato = new PotatoPizza(30000, "C피자");
		
		Pizza[] pizza = {bulgogi, Pineapple, Potato};
		*/
	}

}
