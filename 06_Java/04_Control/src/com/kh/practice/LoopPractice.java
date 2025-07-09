package com.kh.practice;

import java.util.Arrays;
import java.util.Scanner;

import com.kh.practice.controller.RockPaperScissorController;
import com.kh.practice.model.RockPaperScissor;
import com.kh.practice.view.RockPaperScissorView;

class LoopPractice {
	
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		LoopPractice l = new LoopPractice();
		//l.method1();
		//l.method2();
		//l.method3();
		//l.method4();
		//l.method5();
		//l.method6();
		l.method7(); // 가위바위보 추가 -> NVC 방식
		
	}
 
    /*
        사용자로부터 숫자(1~100) 1개가 입력되었을 때 카운트다운 출력하시오.
        사용자 입력 : 5
        5
        4
        3
        2
        1
     */
	public void method1() {
		System.out.print("1 ~ 100 사이의 숫자 입력 > ");
		int num = Integer.parseInt(sc.nextLine());

		if (1 <= num && num <= 100) {
			for (int i = 5; i > 0; i--) {
				System.out.println(i);
			}
		}
	}

    // 1+(-2)+3+(-4)+...과 같은 식으로 계속 더해나갔을 때, 몇까지 더해야 총합이 100 이상 되는지 출력하시오.
	public void method2() {
		int num = 0;
		int sum = 0;

		while (true) {
			if (sum >= 100) {
				System.out.println(num + "까지 더해야 총합이 100 이상");
				break;
			} else {
				num++;
				if (num % 2 != 0) {
					sum += num;
				} else if (num % 2 == 0) {
					sum -= num;
				}
			}
		}
		
		// sum이 100 미만일 때만 while문이 돌아가도록!
		while (sum < 100) {
			num++;
			if (num % 2 != 0) {
				sum += num;
			} else if (num % 2 == 0) {
				sum -= num;
			}
		}
		System.out.println(num + "까지 더해야 총합이 100 이상");
	}

    /*
        사용자로부터 문자열을 입력 받고 문자열에서 검색될 문자를 입력 받아 해당 문자열에 그 문자가 몇 개 있는지 개수를 출력하세요. 

        문자열 : banana
        문자 : a
        banana 안에 포함된 a 개수 : 3

    */
    public void method3() {
    	System.out.print("문자열 : ");
    	String str = sc.nextLine();
    	System.out.print("문자 : ");
    	char ch = sc.nextLine().charAt(0);

    	int count = 0;
    	for (int i = 0; i < str.length(); i++) {
    		//System.out.println(str.charAt(i));
    		if (str.charAt(i) == ch) {
    			count++;
    		}
    	}
    	System.out.println(str + " 안에 포함된 " + ch + " 개수 : " + count);
    }

    /*
        0이 나올 때까지 숫자를 출력하시오. (random 사용! 0 ~ 10)
        7
        3
        4
        2
        3
        4
        0
     */
	public void method4() {
		while (true) {
			int random = (int) (Math.random() * 11);
			System.out.println(random);
			if (random == 0 ) break; // 조건 하나만 걸어도 됨!
			/*
			if (random != 0) {
				System.out.println(random);
			} else if (random == 0) {
				System.out.println(random);
				break;
			}
			*/
		}
	}

    /*
        주사위를 10번 굴렸을 때 각 눈의 수가 몇 번 나왔는지 출력하세요. (random 사용!)

        1 : 3
        2 : 2
        3 : 1
        4 : 0
        5 : 4
        6 : 0

     */
    public void method5() {
    	/*
    	int count1 = 0;
    	int count2 = 0;
    	int count3 = 0;
    	int count4 = 0;
    	int count5 = 0;
    	int count6 = 0;
    	
    	for (int i = 0; i < 10; i++) {
    		//System.out.println((i+1) + "번째");
    		int random = (int) (Math.random() * 6 + 1);
    		//System.out.println("주사위 눈 : " + random);
    		
        	switch(random) {
        	case 1:
        		count1++;
        		break;
        	case 2:
        		count2++;
        		break;
        	case 3:
        		count3++;
        		break;
        	case 4:
        		count4++;
        		break;
        	case 5:
        		count5++;
        		break;
        	case 6:
        		count6++;
        		break;
        	}
    	}
    	
    	System.out.println("1 : " + count1);
    	System.out.println("2 : " + count2);
    	System.out.println("3 : " + count3);
    	System.out.println("4 : " + count4);
    	System.out.println("5 : " + count5);
    	System.out.println("6 : " + count6);
    	*/
    	
		// 배열 사용
		int[] dice = new int[6]; // dice[0] : 1, dice[1] : 2, ... dice[5] : 6
		for (int i = 0; i < 10; i++) {
			int random = (int) (Math.random() * 6); // 1 ~ 6 -> 0 ~ 5 인덱스를 활용
			dice[random]++;
		}
		
		for (int i = 0; i < dice.length; i++) {
			System.out.println((i+1) + " : " + dice[i]);
		}
		// System.out.println(Arrays.toString(dice));
	}

    /*
        사용자의 이름을 입력하고 컴퓨터와 가위바위보를 하세요. 
        컴퓨터가 가위인지 보인지 주먹인지는 랜덤한 수를 통해서 결정하도록 하고, 사용자에게는 직접 가위바위보를 받으세요.
        사용자가 이겼을 때 반복을 멈추고 몇 번 이기고 몇 번 비기고 몇 번 졌는지 출력하세요.

        당신의 이름을 입력해주세요 : 김미경
        가위바위보 : 가위
        컴퓨터 : 가위
        김미경 : 가위
        비겼습니다.

        가위바위보 : 가위 
        컴퓨터 : 바위
        김미경 : 가위
        졌습니다 ㅠㅠ

        가위바위보 : 보
        컴퓨터 : 바위
        김미경 : 보
        이겼습니다 !
	    비긴 횟수 : 1, 진 횟수 : 1, 이긴 횟수 : 1
    */    
    public void method6() {
    	System.out.print("당신의 이름을 입력해주세요 : ");
    	String name = sc.nextLine();
    	
    	int count1 = 0;
		int count2 = 0;

		// computer의 랜덤값을 "가위", "바위", "보"로 변환 
		while (true) {
			System.out.print("가위바위보 : ");
			String userInput = sc.nextLine();
			
			String[] computer = { "가위", "바위", "보" };
			String comValue = "";
			
			for (int i = 0; i < computer.length; i++) {
				// 컴퓨터 1 : 가위, 2 : 바위, 3 : 보
				int random = (int) (Math.random() * 3 + 1);

				switch (random) {
				case 1:
					comValue = "가위";
					break;
				case 2:
					comValue = "바위";
					break;
				case 3:
					comValue = "보";
					break;
				}
			}

			System.out.println("컴퓨터 : " + comValue);
			System.out.println(name + " : " + userInput);

			if (userInput.equals(comValue)) {
				count1++;
				System.out.println("비겼습니다.\n");
			} else {
				if (userInput.equals("가위") && comValue.equals("보") 
						|| userInput.equals("바위") && comValue.equals("가위")
						|| userInput.equals("보") && comValue.equals("바위")) {
					System.out.println("이겼습니다 !");
					System.out.printf("비긴 횟수 : %d, 진 횟수 : %d, 이긴 횟수 : 1", count1, count2);
					break;
				} else {
					count2++;
					System.out.println("졌습니다 ㅠㅠ\n");
				}
			}
		}
		
		// 시용자가 입력한 값을 숫자로 변환
		while (true) {
			System.out.print("가위바위보 : ");
			String userInput = sc.nextLine();
			
			int random = (int) (Math.random() * 3);
			int inputResult = 3;
			
			switch (userInput) {
			case "가위":
				inputResult = 0;
				break;
			case "바위":
				inputResult = 1;
				break;
			case "보":
				inputResult = 2;
				break;
			}
		
		if(inputResult == random) { // 비겼을 경우
			System.out.println("비겼습니다.\n");
			count1++;
		} else if(
				inputResult == 0 && random == 2
				|| inputResult == 1 && random == 0
				|| inputResult == 2 && random == 1
				) { // 이겼을 경우
			System.out.println("이겼습니다!!");
			System.out.println("비긴 횟수 : " + count1
								+ ", 진 횟수 : " + count2
								+ ", 이긴 횟수 : 1");
			break;
		} else { // 졌을 경우
			System.out.println("졌습니다 ㅠㅠ\n");
			count2++;
			}
		}
			
		// 배열 사용
		String[] rps = { "가위", "바위", "보" };
		int win = 0;
		int lose = 0;
		int draw = 0;
		
		while (true) {
			System.out.print("가위바위보 : ");
			String userInput = sc.nextLine();
			// 컴퓨터 0 : 가위, 1 : 바위, 2 : 보
			int computer = (int) (Math.random() * 3);

			// 컴퓨터는 인덱스로 값을 찾음!
			System.out.println("컴퓨터 : " + rps[computer]);

			System.out.println(name + " : " + userInput);
			

			// 사용자는 값으로 인덱스를 찾으면 어떨까?
			// 배열에서 값으로 인덱스 찾기 -> 사용자가 입력한 값을 숫자로!
			int inputResult = Arrays.asList(rps).indexOf(userInput);
			
			if(inputResult == computer) { // 비겼을 경우
    			System.out.println("비겼습니다.\n");
    			draw++;
    		} else if(
    				inputResult == 0 && computer == 2
    				|| inputResult == 1 && computer == 0
    				|| inputResult == 2 && computer == 1
    				) { // 이겼을 경우
    			System.out.println("이겼습니다!!");
    			win++;
    			System.out.println("비긴 횟수 : " + draw
    								+ ", 진 횟수 : " + lose
    								+ ", 이긴 횟수 : " + win);
    			break;
    		} else { // 졌을 경우
    			System.out.println("졌습니다 ㅠㅠ\n");
    			lose++;
		    }
	    }
    }
    
    public void method7() {
    	// 가위바위보 추가 -> MVC 방식
    	RockPaperScissorView view = new RockPaperScissorView();
    	view.gameStart();
    }
}    