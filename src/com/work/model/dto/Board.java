package com.work.model.dto;

public class Board {
	/** 게시글번호 */
	private int boardNo;
	/** 게시글제목 */
	private String boardTitle;
	/** 게시글날짜 */
	private String boardDate;
	/** 게시글 작성자 */
	private String boardWriter;
	/** 게시글내용 */
	private String boardContent;

	/**
	 * 기본생성자
	 */
	public Board() {

	}

	/**
	 * 필수 초기화 데이터
	 * 
	 * @param boardTitle
	 * @param boardDate
	 * @param boardWriter
	 * @param boardContent
	 */
	public Board(String boardTitle, String boardDate, String boardWriter, String boardContent) {

		this.boardTitle = boardTitle;
		this.boardDate = boardDate;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
	}

	/**
	 * 게시판 공통 속성 전체 초기화 생성자
	 * 
	 * @param boardNo      게시글번호
	 * @param boardTitle   게시글제목
	 * @param boardDate    게시글작성날짜
	 * @param boardWriter  게시글작성자
	 * @param boardContent 게시글내용
	 */
	public Board(int boardNo, String boardTitle, String boardDate, String boardWriter, String boardContent) {
		
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardDate = boardDate;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
	}

	/**
	 * @return the boardNo
	 */
	public int getBoardNo() {
		return boardNo;
	}

	/**
	 * @param boardNo the boardNo to set
	 */
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	/**
	 * @return the boardTitle
	 */
	public String getBoardTitle() {
		return boardTitle;
	}

	/**
	 * @param boardTitle the boardTitle to set
	 */
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	/**
	 * @return the boardDate
	 */
	public String getBoardDate() {
		return boardDate;
	}

	/**
	 * @param boardDate the boardDate to set
	 */
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	/**
	 * @return the boardWriter
	 */
	public String getBoardWriter() {
		return boardWriter;
	}

	/**
	 * @param boardWriter the boardWriter to set
	 */
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	/**
	 * @return the boardContent
	 */
	public String getBoardContent() {
		return boardContent;
	}

	/**
	 * @param boardContent the boardContent to set
	 */
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" 글번호 : ");
		builder.append(boardNo);
		builder.append(" 작성자 : ");
		builder.append(boardWriter);
		builder.append(" 작성일 : ");
		builder.append(boardDate);
		builder.append("\n");
		builder.append(" 글제목 : ");
		builder.append(boardTitle);
		builder.append("\n");
		builder.append(boardContent);

		return builder.toString();

	}

}
