package com.work.view;

public class BookMenu {
	public void bookInfoMenu () {
		System.out.println("====================================");
		System.out.println("\t[엔코아 서점]");
		System.out.println("====================================");
		System.out.println("1. 책 목록");  //완료
		System.out.println("2. 책 검색");  //완료
		System.out.println("3. 이전메뉴");
		System.out.println("====================================");
		System.out.print(">> 메뉴입력: ");
		
	}
	
	
	public void bookInfoMenuAdmin () {  //관리자
		System.out.println("====================================");
		System.out.println("\t[도서 관리 메뉴]");
		System.out.println("====================================");
		System.out.println("1. 책 목록");
		System.out.println("2. 책 검색");
		System.out.println("3. 책 등록");
		System.out.println("4. 변경");
		System.out.println("5. 삭제");
		System.out.println("0. 이전");
		System.out.println("====================================");
		System.out.print(">> 메뉴입력: ");
		
	}
	
	public void bookDetail () {  //책 상세조회
		System.out.println("====================================");
		System.out.println("\t[엔코아 서점]");
		System.out.println("====================================");
		System.out.println("1. 구매");
		System.out.println("0. 이전");
		
		System.out.println("====================================");
		System.out.print(">> 메뉴입력: ");
		
	}
	

}
