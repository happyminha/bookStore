package com.work.model.service;

import java.util.ArrayList;

import com.work.model.dao.BoardDao;

import com.work.model.dto.Board;
import com.work.model.dto.Book;
import com.work.model.dto.Member;
import com.work.util.Utility;


public class BoardService {
	/** 회원 테이블에 대한 DAO 객체 선언 및 생성 */
	private BoardDao dao = new BoardDao();
	
	/** 로그인 성공한 회원의 아이디를 프로그램 종료시까지 사용하기위한 속성 */
	public static String loginMemberId;
	
	/** 로그인 성공한 회원의 등급을 프로그램 종료시까지 사용하기위한 속성 */
	public static String loginGrade;


	/**
	 * 전체게시판 조회
	 * @return 게시판 목록
	 */
	public ArrayList<Board> getboardList() {
		return dao.selectList();
	}

	
	
	
	
	/**
	 * 내가쓴 게시판 글 조회
	 * @param boardWriter 작성자
	 * @return 성공시 회원, 실패시 null
	 */
	public ArrayList<Board> getBoard(String boardWriter) {
		return dao.selectBoardList(boardWriter);
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 게시글 등록
	 * @param boardTitle  게시글제목
	 * @param boardDate    게시글작성날짜
	 * @param boardWriter   게시글작성자
	 * @param boardContent   게시글내용
	 
	 */
	
	public void Board(String boardTitle, String boardContent ) {
		Board dto = new Board(0,boardTitle, Utility.getCurrentDate(), MemberService.loginMemberId, boardContent);
		
		addBoard(dto);
	}

	/**게시글 등록
	 * @return 게시글 등록확인
	 * 
	 * */

	private void addBoard(Board dto) {
		int result = dao.insertBoard(dto);
		if (result == 1) {
			System.out.println("[게시글 작성이 완료되었습니다] " );
		} else {
			System.out.println("[게시글 작성 실패]");
		}
		
	}


	/**
	 * 게시글 변경
	 * @return 변경된 재고 수
	 */
	public int updateBoard(String boardTitle, String boardContent, int boardNo) {
		return dao.updateBoard(boardTitle, boardContent,boardNo );
	}

	
	/**
	 * 게시글 삭제
	 * @param boardDate
	 * @return 기존 등록된 나의 게시글 삭제 
	 */
	public boolean removeMyBoard(int boardNo) {
		return dao.removeMyBoard(boardNo);
	}
	
	
	
	
	
	

	}

