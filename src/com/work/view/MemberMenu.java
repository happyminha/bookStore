package com.work.view;

public class MemberMenu {

	public void myInfoMenu() {
		System.out.println("====================================");
		System.out.println("\t[엔코아 서점 내정보]");
		System.out.println("====================================");
		System.out.println("1. 내 정보변경");
		System.out.println("2. 탈퇴 ");
		System.out.println("0. 이전");
		System.out.println("====================================");
		System.out.print(">> 메뉴입력: ");

	}

	
	public void myInfoMenuAdmin() { //관리자
		System.out.println("====================================");
		System.out.println("\t[엔코아 서점 관리자메뉴]");
		System.out.println("====================================");
		System.out.println("1. 회원전체조회");
		System.out.println("2. 회원아이디조회");
		System.out.println("3. 등급별조회 ");
		System.out.println("0. 이전");
		System.out.println("====================================");
		System.out.print(">> 메뉴입력: ");

	}
}
