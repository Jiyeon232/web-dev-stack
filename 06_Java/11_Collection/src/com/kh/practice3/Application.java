package com.kh.practice3;

import java.util.Scanner;

import com.kh.practice3.controller.MusicController;

public class Application {
	
	private Scanner sc = new Scanner(System.in);
	private MusicController mc = new MusicController();

	public static void main(String[] args) {
		
		Application app = new Application();
		app.menu();

	}
	
	public void menu() {
		try {
			boolean check = true;
			while(check) {
				System.out.println("===== 메인메뉴 =====");
				System.out.println("1. 특정 곡 추가");
				System.out.println("2. 전체 곡 목록 출력");
				System.out.println("3. 특정 곡 검색");
				System.out.println("4. 특정 곡 수정");
				System.out.println("5. 특정 곡 삭제");
				System.out.println("6. 종료");
				System.out.print("메뉴 번호 입력 : ");
				switch(Integer.parseInt(sc.nextLine())) {
					case 1:
						addList();
						break;
					case 2:
						printAll();
						break;
					case 3:
						searchMusic();
						break;
					case 4:
						updateMusic();
						break;
					case 5:
						removeMusic();
						break;
					case 6:
						System.out.println("종료");
						check = false;
						break;
					default: 
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			}
		} catch(Exception e) {
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			menu();
		}
	}
	
	//1. 특정 곡 추가
	public void addList() {
		/*
		 * ****** 특정 곡 추가 ******
		 * 곡명 : 
		 * 가수명 : 
		 * 
		 * 곡 추가 성공하면 "추가 성공"
		 *       실패하면 "추가 실패" -> 기존 똑같은 곡과 가수가 있을 시
		 * */
		System.out.println("****** 특정 곡 추가 ******");
		System.out.print("곡명 : ");
		String song = sc.nextLine();
		System.out.print("가수명 : ");
		String artist = sc.nextLine();
		
		mc.addList(song, artist);
	}
	
	//2. 전체 곡 목록 출력
	public void printAll() {
		/*
		 * ****** 전체 곡 목록 출력 ******
		 * */
		System.out.println("****** 전체 곡 목록 출력 ******");
		mc.printAll();
	}
	
	//3. 특정 곡 검색
	public void searchMusic() {
		/*
		 * ****** 특정 곡 검색(가수/곡) ******
		 * 키워드 검색 : 
		 * 
		 * 검색할 곡이 있다면 "(가수 - 곡)을 검색했습니다."
		 *          없다면 "검색할 곡을 찾지 못했습니다."
		 * */
		System.out.println("****** 특정 곡 검색(가수/곡) ******");
		System.out.print("키워드 검색 : ");
		String search = sc.nextLine();
		
		mc.searchMusic(search);
	}
	
	//4. 특정 곡 수정
	public void updateMusic() {
		/*
		 * ****** 특정 곡 수정 ******
		 * 검색할 곡명 : 
		 * 수정할 곡명 : 
		 * 수정할 가수명 : 
		 * 
		 * 수정에 성공한다면 "(바꾸기 전 가수 - 바꾸기 전 곡)의 값이 변경되었습니다."
		 *      실패한다면 "곡을 수정하지 못했습니다."
		 * */
		System.out.println("****** 특정 곡 수정 ******");
		System.out.print("검색할 곡명 : ");
		String searchSong = sc.nextLine();
		System.out.print("수정할 곡명 : ");
		String updateSong = sc.nextLine();
		System.out.print("수정할 가수명 : ");
		String updateArtist = sc.nextLine();
		
		mc.updateMusic(searchSong, updateSong, updateArtist);
	}
	
	//5. 특정 곡 삭제
	public void removeMusic() {
		/*
		 * ****** 특정 곡 삭제 ******
		 * 삭제할 곡명 : 
		 * 
		 * 삭제에 성공한다면 "(삭제한 가수 - 삭제한 곡)을 삭제했습니다."
		 *      실패한다면 "삭제할 곡이 없습니다."
		 * */
		System.out.println("****** 특정 곡 삭제 ******");
		System.out.print("삭제할 곡명 : ");
		String removeSong = sc.nextLine();
		
		mc.removeMusic(removeSong);
	}

}