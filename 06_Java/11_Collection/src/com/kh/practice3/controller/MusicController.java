package com.kh.practice3.controller;

import java.util.ArrayList;
import java.util.Collections;

import com.kh.practice3.compare.SongAscending;
import com.kh.practice3.model.Music;

public class MusicController {

	private ArrayList<Music> list = new ArrayList<>();
	Music music = new Music();
	
	//1. 특정 곡 추가
	public void addList(String song, String artist) {
		music.setSong(song);
		music.setArtist(artist);
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSong().equals(song) && list.get(i).getArtist().equals(artist)) {
				System.out.println("추가 실패");
				return;
			}
		}
		
		// + 추가 조건 - 공백 입력한 경우 추가 실패!
		if (song.trim().equals("") || artist.trim().equals("")) {
			System.out.println("추가 실패");
			return;
		}
		
		System.out.println("추가 성공");
		
		Music m = new Music();
		list.add(music);
		this.music = m;
		
		/*
		// 리턴타입 - boolean
		for (Music music : list) {
			if (music.getSong().equals(song) && music.getArtist().equals(artist)) {
				return false
			}
		}
		return list.add(new Music(song, artist));
		*/
	}
	
	//2. 전체 곡 목록 출력
	public void printAll() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		// + 추가 조건 - 리스트가 비어있을 때
		if (list.isEmpty()) {
			System.out.println("곡이 하나도 없습니다.");
		}

		// 리턴 타입을 ArrayList<Music> 로 하고 list를 리턴 받는 것도 가능!
		// return list;
	}
	
	//3. 특정 곡 검색
	public void searchMusic(String search) {
		// contains
		// 키워드 검색 - 비교 대상을 다 소문자(or 대문자)로 바꿔서 비교 --> toLowerCase() / toUpperCase()
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSong().toLowerCase().contains(search.toLowerCase()) 
					|| list.get(i).getArtist().toLowerCase().contains(search.toLowerCase())) {
				System.out.println("(" + list.get(i).getArtist() + " - " 
			                   + list.get(i).getSong() + ")을 검색했습니다.");
			} else {
				System.out.println("검색할 곡을 찾지 못했습니다.");
			}
		}
		
		// + 추가 조건 - 리스트가 비어있을 때
		if (list.isEmpty()) {
			System.out.println("검색할 곡을 찾지 못했습니다.");
		}
		
		ArrayList<Music> result = new ArrayList<>();
		// result에 곡 리스트를 담아서 return.add(music) & return result 하는 방법!
		
	}
	
	// 곡 검색 시 중복된 경우 (+추가)
	public ArrayList<Music> checkMusic(String song) {
		ArrayList<Music> result = new ArrayList<>();
		for (Music music : list) {
			if (music.getSong().equals(song)) {
				result.add(music);
			}
		}
		return result;
	}
	
	//4. 특정 곡 수정 -> 1개인 경우, 여러 개인 경우
	public Music updateMusic(String searchSong, String searchArtist, String updateSong, String updateArtist) {
		// set
		/*for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSong().equals(searchSong)) {
				System.out.println("(" + list.get(i).getArtist() + " - " 
			                + list.get(i).getSong() + ")의 값이 변경되었습니다.");
				list.get(i).setSong(updateSong);
				list.get(i).setArtist(updateArtist);
			} else {
				System.out.println("곡을 수정하지 못했습니다.");
			}
		}*/
		
		// + 추가 조건
		// 리턴타입 - Music, 향상된 for문 사용, Application에서 조건문 걸어 출력!
		for (Music music : list) {
			
			boolean checkSong = music.getSong().equals(searchSong);
			boolean checkArtist = music.getArtist().equals(searchArtist);
		
			if (searchArtist == null && checkSong || checkSong && checkArtist) {
				// 기존 리스트에서 수정할 값이 있는 경우 수정 못하게!
				if (music.getSong().equals(updateSong) && music.getArtist().equals(updateArtist)) {
					return null;
				}
				return list.set(list.indexOf(music), new Music(updateArtist, updateSong));
			}
		}
		return null;
		
	}
	
	//5. 특정 곡 삭제
	public Music removeMusic(String song, String artist) {
		// remove
		/*
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i).getSong());
			if (list.get(i).getSong().equals(song)) {
				System.out.println("(" + list.get(i).getArtist() + " - " 
			                   + list.get(i).getSong() + ")을 삭제했습니다.");
				list.remove(i);
			} else {
				System.out.println("삭제할 곡이 없습니다.");
			}
		}
		*/
		
		// 리턴타입 - Music 
		for (Music music : list) {
			if (artist == null && music.getSong().equals(song)
					|| music.getSong().equals(song) && music.getArtist().equals(artist)) {
				return list.remove(list.indexOf(music));
			}
		}
		return null;
	}
	
	// 가수명 내림차순
	public ArrayList<Music> descArtist() {
		// 복제된 리스트를 내림차순 정렬! 원래 리스트는 변동 X
		ArrayList<Music> clone = (ArrayList<Music>) list.clone();
		Collections.sort(clone);
		//Collections.reverse(clone);
		return clone;
	}
	
	// 곡명 오름차순
	public ArrayList<Music> accSong(){
		ArrayList<Music> clone = (ArrayList<Music>) list.clone();
		Collections.sort(clone, new SongAscending());
		return clone;
	}
}