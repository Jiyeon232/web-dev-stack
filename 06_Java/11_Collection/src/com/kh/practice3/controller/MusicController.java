package com.kh.practice3.controller;

import java.util.ArrayList;

import com.kh.practice3.model.Music;

public class MusicController {

	private ArrayList<Music> list = new ArrayList<>();
	Music music = new Music();
	
	//1. 특정 곡 추가
	public void addList(String song, String artist) {
		music.setSong(song);
		music.setArtist(artist);
		
		Music m = new Music();
		list.add(music);
		this.music = m;		
		
		// 조건 수정!
		for (int i = 0; i < list.size(); i++) {
			boolean check = list.get(i).getSong().equals(song) && list.get(i).getArtist().equals(artist);
			if (check) {
				System.out.println("추가 실패");
			} else {
				System.out.println("추가 성공");
			}
		}
	}
	
	//2. 전체 곡 목록 출력
	public void printAll() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	//3. 특정 곡 검색
	public void searchMusic(String search) {
		// contains	
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSong().contains(search) || list.get(i).getArtist().contains(search)) {
				System.out.println("(" + list.get(i).getArtist() + " - " 
			                   + list.get(i).getSong() + ")을 검색했습니다.");
			} else {
				System.out.println("검색할 곡을 찾지 못했습니다.");
			}
		}
	}
	
	//4. 특정 곡 수정
	public void updateMusic(String searchSong, String updateSong, String updateArtist) {
		// set
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSong().equals(searchSong)) {
				System.out.println("(" + list.get(i).getArtist() + " - " 
			                + list.get(i).getSong() + ")의 값이 변경되었습니다.");
				list.get(i).setSong(updateSong);
				list.get(i).setArtist(updateArtist);
			} else {
				System.out.println("곡을 수정하지 못했습니다.");
			}
		}
	}
	
	//5. 특정 곡 삭제
	public void removeMusic(String removeSong) {
		// remove
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i).getSong());
			if (list.get(i).getSong().equals(removeSong)) {
				System.out.println("(" + list.get(i).getArtist() + " - " 
			                   + list.get(i).getSong() + ")을 삭제했습니다.");
				list.remove(i);
			} else {
				System.out.println("삭제할 곡이 없습니다.");
			}
		}
	}
	
}