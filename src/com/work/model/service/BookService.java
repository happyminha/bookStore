package com.work.model.service;

import java.util.ArrayList;

import com.work.model.dao.BookDao;
import com.work.model.dto.Book;
import com.work.model.dto.Member;


public class BookService {

	/** 회원 테이블에 대한 DAO 객체 선언 및 생성 */
	private BookDao dao = new BookDao();
	
	/** 로그인 성공한 회원의 아이디를 프로그램 종료시까지 사용하기위한 속성 */
	public static String loginMemberId;
	
	/** 로그인 성공한 회원의 등급을 프로그램 종료시까지 사용하기위한 속성 */
	public static String loginGrade;

	
	
	/**
	 * 책 등록
	 * @param bookName   도서명
	 * @param bookWriter   저자
	 * @param bookCompany   출판사
	 * @param bookPrice     가격
	 * @param bookAmount    재고
	 * @param bookDate      발간일
	 * @param bookContent   개요
	 */
	
	public void addBook(String bookName, String bookWriter , String bookCompany, int bookPrice, int bookAmount,String bookDate,String bookContent) {
		Book dto = new Book(bookName, bookWriter, bookCompany, bookPrice, bookAmount,bookDate,bookContent);
		
		addBook(dto);
	}
	
	
	/**책 등록
	 * @return 등록된 책정보
	 * 
	 * */

	private void addBook(Book dto) {
		
		
		int result = dao.insertBook(dto);
		if (result == 1) {
			System.out.println("[도서 등록 성공] " + dto.getBookName()  + " 도서등록 완료.");
		} else {
			System.out.println("[도서 등록 실패]");
		}
	}
	


	/**
	 * 책 리스트 조회
	 * @return 책 리스트
	 */
	public ArrayList<Book> getbookList() {
		return dao.selectList();
	}
	
	
	/**
	 * 책검색
	 * @return 이름으로 검색한 책정보 출럭
	 */
	public Book getbookSearch(String bookName) {
		return dao.selectOne(bookName);
	}
	

	
	/**
	 * 책구매
	 * @return 이름으로 검색한 책정보 출럭
	 */
	public int updateBook(String bookName) {
		return dao.updateBook(bookName);
	}
	
	/**
	 * 도서재고변경
	 * @return 변경된 재고 수
	 */
	public int updateBookAmount(String bookName,int modifyBookAmount) {
		return dao.updateBookAmount(bookName, modifyBookAmount);
	}
	
	
	/**
	 * 도서 삭제
	 * @param bookName 
	 * @return 기존 등록된 도서 삭제
	 */
	public boolean removeBook(int bookNo) {
		return dao.removeBook(bookNo);
	}
	
	
}
