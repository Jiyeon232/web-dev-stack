package com.kh.practice3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Music implements Comparable<Music> {

	private String artist;
	private String song;

	@Override
	public int compareTo(Music o) {
		// return this.artist.compareTo(o.artist);
		// 비교대상 앞뒤를 바꾸면 reverse 사용하지 않고도 내림차순 정렬 가능!
		return o.artist.compareTo(this.artist);
	}
}