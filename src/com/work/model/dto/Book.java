package com.work.model.dto;

public class Book {
	/** 도서번호*/
	private int bookNo;
	/** 도서명*/
	private String bookName;
	/** 저자*/
	private String bookWriter;
	/** 출판사*/
	private String bookCompany;
	/** 도서가격*/
	private int bookPrice;
	/** 도서재고*/
	private int bookAmount;
	/** 발간일*/
	private String bookDate;
	/** 책 개요*/
	private String bookContent;
	
	
	/**
	 * 기본생성자
	 */
	public Book () {
		
	}
	
	
	/**
	 * 도서 등록 필수 초기화 생성자
	 * @param bookName
	 * @param bookWriter
	 * @param bookCompany
	 * @param bookPrice
	 * @param bookAmount
	 * @param bookDate
	 * @param bookContent
	 */
	public Book(String bookName, String bookWriter, String bookCompany, int bookPrice, int bookAmount, String bookDate,
			String bookContent) {
		
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookCompany = bookCompany;
		this.bookPrice = bookPrice;
		this.bookAmount = bookAmount;
		this.bookDate = bookDate;
		this.bookContent = bookContent;
	}




	/**
	 * 도서 공통 속성 전체 초기화 생성자
	 * @param bookNo    도서번호
	 * @param bookName   도서명
	 * @param bookWriter  저자
	 * @param bookCompany   출판사
	 * @param bookPrice     도서가격
	 * @param bookAmount    도서수량
	 * @param bookDate      출간날짜
	 * @param bookContent   도서내용
	 */
	public Book(int bookNo, String bookName, String bookWriter, String bookCompany, int bookPrice, int bookAmount,
			String bookDate, String bookContent) {
		
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookCompany = bookCompany;
		this.bookPrice = bookPrice;
		this.bookAmount = bookAmount;
		this.bookDate = bookDate;
		this.bookContent = bookContent;
	}


	/**
	 * @return the bookNo
	 */
	public int getBookNo() {
		return bookNo;
	}


	/**
	 * @param bookNo the bookNo to set
	 */
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}


	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}


	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	/**
	 * @return the bookWriter
	 */
	public String getBookWriter() {
		return bookWriter;
	}


	/**
	 * @param bookWriter the bookWriter to set
	 */
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}


	/**
	 * @return the bookCompany
	 */
	public String getBookCompany() {
		return bookCompany;
	}


	/**
	 * @param bookCompany the bookCompany to set
	 */
	public void setBookCompany(String bookCompany) {
		this.bookCompany = bookCompany;
	}


	/**
	 * @return the bookPrice
	 */
	public int getBookPrice() {
		return bookPrice;
	}


	/**
	 * @param bookPrice the bookPrice to set
	 */
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}


	/**
	 * @return the bookAmount
	 */
	public int getBookAmount() {
		return bookAmount;
	}


	/**
	 * @param bookAmount the bookAmount to set
	 */
	public void setBookAmount(int bookAmount) {
		this.bookAmount = bookAmount;
	}


	/**
	 * @return the bookDate
	 */
	public String getBookDate() {
		return bookDate;
	}


	/**
	 * @param bookDate the bookDate to set
	 */
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}


	/**
	 * @return the bookContent
	 */
	public String getBookContent() {
		return bookContent;
	}


	/**
	 * @param bookContent the bookContent to set
	 */
	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" 책등록번호 : ");
		builder.append(bookNo);
		builder.append(", ");
		builder.append(" 책이름 : ");
		builder.append(bookName);
		builder.append(", ");
		builder.append(" 지은이 : ");
		builder.append(bookWriter);
		builder.append(", ");
		builder.append(" 출판사 : ");
		builder.append(bookCompany);
		builder.append("\n");
		builder.append(" 책 가격 : ");
		builder.append(bookPrice);
		builder.append(", ");
		builder.append(" 재고 : ");
		builder.append(bookAmount);
		builder.append(", ");
		builder.append(" 출판일 : ");
		builder.append(bookDate);
		builder.append("\n");
		builder.append("책 줄거리\n");
		builder.append(bookContent);
		return builder.toString();
	}

	
	

}
