package com.fastcampus.biz.board;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// VO(Value Object) 클래스
@Getter
@Setter
public class BoardVO {
	// BOARD 테이블의 컬럼을 참조한 멤버변수 선언
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;

	// 검색 관련 변수
	private String searchCondition;
	private String searchKeyword;

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", cnt=" + cnt + "]";
	}
	
}











