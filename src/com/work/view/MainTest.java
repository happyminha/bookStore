package com.work.view;

import java.util.ArrayList;

import com.work.model.dto.Board;
import com.work.model.dto.Book;
import com.work.model.dto.Member;
import com.work.model.service.BoardService;
import com.work.model.service.BookService;
import com.work.model.service.MemberService;
import com.work.util.Utility;

public class MainTest {
	public static void main(String[] args) {
		MainTest menu = new MainTest();
		while (true) {
			menu.mainMenu();
		}
	}

	MemberService service = new MemberService();
	BookService bookService = new BookService();
	BoardService boardService = new BoardService();

	/**
	 * <pre>
	 *  
	 * 시작 메뉴 
	 * 
	 * -- 시작화면(메서드)
	 *			1. 로그인
	 *			2. 회원가입
	 *			3. 아이디찾기
	 *			4. 비밀번호찾기
	 *			0. 프로그램종료
	 *			메뉴입력:
	 * </pre>
	 */

	public void mainMenu() {
		printLine();
		System.out.println("\t[엔코아 서점]");
		printLine();
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 아이디찾기");
		System.out.println("4. 비밀번호찾기");
		System.out.println("0. 프로그램종료");
		printLine();
		System.out.print(">> 메뉴입력: ");

		int menuNo = Utility.inputNumber();

		switch (menuNo) {
		case 1:
			loginMenu();
			break;
		case 2:
			joinMemberMenu();
			break;
		case 3:
			findMemberIdMenu();
			break;
		case 4:
			findMemberPwMenu();
			break;
		case 0:
			exitMenu();
			break;
		default:
			System.out.println("[오류] 지원하지 않는 서비스 메뉴번호입니다.");
			break;
		}
	}

	/** 종료 메뉴 */
	private void exitMenu() {
		System.out.println();
		System.out.println("[ 프로그램을 정상 종료하였습니다. ]");
		System.out.println();
		System.exit(0);
	}

	/** 아이디 찾기 메뉴 */
	private void findMemberIdMenu() {
		System.out.println();
		System.out.println("[ 아이디찾기 메뉴 ]");
		System.out.println();

		System.out.print("이름: ");
		String name = Utility.inputString();

		System.out.print("이메일: ");
		String email = Utility.inputString();

		System.out.print("회원님의 아이디는 : ");
		System.out.println(service.findMemberId(name, email));

	}

	/** 비밀번호 찾기 메뉴 */
	private void findMemberPwMenu() {
		System.out.println();
		System.out.println("[ 비밀번호찾기 메뉴 ]");
		System.out.println();

		System.out.print("아이디: ");
		String memberId = Utility.inputString();

		System.out.print("이름: ");
		String name = Utility.inputString();

		System.out.print("휴대폰: ");
		String mobile = Utility.inputString();

		System.out.print("비밀번호 찾기결과 : ");
		System.out.println(service.findMemberPw(memberId, name, mobile));

	}

	/** 회원가입 메뉴 */
	private void joinMemberMenu() {
		System.out.println();
		System.out.println("[ 회원가입 메뉴 ]");

		System.out.print("아이디: ");
		String memberId = Utility.inputString();

		System.out.print("비밀번호: ");
		String memberPw = Utility.inputString();

		System.out.print("이름: ");
		String name = Utility.inputString();

		System.out.print("휴대폰: ");
		String mobile = Utility.inputString();

		System.out.print("이메일: ");
		String email = Utility.inputString();

		service.addMember(memberId, memberPw, name, mobile, email, mobile, email, 0);
	}

	/** 로그인 메뉴 */
	private void loginMenu() {
		System.out.println();
		System.out.println("[ 로그인 메뉴 ]");

		System.out.print("아이디: ");
		String memberId = Utility.inputString();

		System.out.print("비밀번호: ");
		String memberPw = Utility.inputString();

		System.out.println();
		String grade = service.login(memberId, memberPw);
		if (grade != null) {
			if (grade.equals("관리자")) {
				memberControlMenu();
			}
			frontMenu();

		} else {
			System.out.println("[로그인 실패] 회원정보를 다시 확인하시기 바랍니다.");
		}

	}

	private void frontMenu() {
		printLine();
		System.out.println("\t[엔코아 서점 서비스 메뉴]");
		System.out.println(MemberService.loginMemberId + "님 [등급: " + MemberService.loginGrade + "]");
		printLine();
		System.out.println("1. 신간도서 top 10");
		System.out.println("2. 책 검색");
		System.out.println("3. 게시판");
		System.out.println("4. 내정보");
		System.out.println("5. 비빌번호 변경");
		System.out.println("6. 회원탈퇴");
		System.out.println("7. 로그아웃");
		System.out.println("0. 프로그램종료");
		printLine();
		System.out.print(">> 메뉴입력: ");

		int menuNo = Utility.inputNumber();

		switch (menuNo) {
		case 1:
			bookListMenu();
			/** 책 리스트 */
			break;
		case 2:
			bookSearchMenu();
			/** 책 검색 */
			break;
		case 3:
			boardMenu();
			/** 게시판 */
			break;
		case 4:
			myInfoMenu();
			/** 내정보 확인 */
			break;
		case 5:
			myInfoModifyMenu();
			/** 내정보 변경 */
			break;
		case 6:
			removeMemberMenu();
			/** 회원탈퇴 */
			break;
		case 7:
			logoutMenu();
			/** 로그아웃 */
			break;
		case 0:
			exitMenu();
			break;
		default:
			System.out.println("[오류] 지원하지 않는 서비스 메뉴번호입니다.");
			break;
		}
	}

	/** 1.책 리스트 메뉴 */
	private void bookListMenu() {
		BookService bookService = new BookService();

		for (Book i : bookService.getbookList()) {
			System.out.println(i.toString());

		}
		frontMenu();
	}

	/** 2.책 검색 메뉴 */
	private void bookSearchMenu() {
		System.out.print("책 이름을 검색하세요: ");
		String bookName = Utility.inputString();

		BookService bookService = new BookService();
		Book book = bookService.getbookSearch(bookName);
		System.out.println(book.toString());

		System.out.print("해당책을 구매하시겠습니까? (1: 구매, 0:취소): ");
		int check = Utility.inputNumber();

		if (check == 1) {
			if (book.getBookAmount() > 0) {
				bookService.updateBook(bookName);
				service.updateMyAmount();
				System.out.println("구매가 완료되었습니다.");
			} else {
				System.out.println("재고가 없습니다.");
			}

		} else {
			System.out.println("구매가 취소되었습니다.");

		}
		frontMenu();
	}

	/** 3. 게시판 메뉴 */

	private void boardMenu() {
		printLine();
		System.out.println("\t[엔코아 서점 게시판]");
		printLine();
		System.out.println("1. 게시글 전체목록");
		System.out.println("2. 작성 ");
		System.out.println("3. 내가 쓴 목록 ");
		System.out.println("0. 이전");
		printLine();
		System.out.print(">> 메뉴입력: ");

		int menuNo = Utility.inputNumber();

		switch (menuNo) {
		case 1:
			boardListMenu();
			/** 게시글 전체 목록 */
			break;
		case 2:
			writeBoardMenu();
			/** 게시글 작성 */
			break;

		case 3:
			myBoardMenu();
			/** 내가 쓴 목록 */
			break;

		case 0:
			frontMenu();
			break;
		default:
			System.out.println("[오류] 지원하지 않는 서비스 메뉴번호입니다.");
			break;
		}
	}

	/** 1.게시글 목록 메뉴 */
	private void boardListMenu() {
		BoardService boardService = new BoardService();
		for (Board i : boardService.getboardList()) {
			System.out.println(i.toString());
		}
		boardMenu();

	}

	/** 2.게시글 작성 메뉴 */
	private void writeBoardMenu() {
		System.out.println();
		System.out.println("[ 게시글 작성 메뉴 ]");

		System.out.print("제목: ");
		String boardTitle = Utility.inputString();


		System.out.print("작성내용: ");
		String boardContent = Utility.inputStrings();

		boardService.Board(boardTitle,boardContent);
		System.out.println();
		boardMenu();

	}

	

	/** 3. 내가 쓴 목록 메뉴 */
	private void myBoardMenu() {

		BoardService boardService = new BoardService();
		ArrayList<Board> board = boardService.getBoard(MemberService.loginMemberId);

		for (Board b : board) {
			System.out.println(b);
		}
		
		
		myBoardDetail();

	}

	/** 4.게시판 상세 메뉴 */
	private void myBoardDetail() {
		System.out.println("====================================");
		System.out.println("\t[엔코아 서점 게시판 상세]");
		System.out.println("====================================");
		System.out.println("1. 수정");
		System.out.println("2. 삭제 ");
		System.out.println("0. 이전");
		System.out.println("====================================");
		System.out.print(">> 메뉴입력: ");

		int menuNo = Utility.inputNumber();

		switch (menuNo) {
		case 1:
			System.out.println("수정할 게시글 번호를 입력해주세요:");
			int boardNo = Utility.inputNumber();
			boardModifyMenu(boardNo);
			/** 나의 게시글 수정 */
			break;
			
		case 2:
			System.out.println("삭제할 게시글 번호를 입력해주세요:");
			boardNo = Utility.inputNumber();
			deleteBoardMenu(boardNo);
			break;
			/** 나의 게시글 삭제*/
		case 0:
			boardMenu();
			break;
		default:
			System.out.println("[오류] 지원하지 않는 서비스 메뉴번호입니다.");
			break;
		}

	}

	/** 1. 나의 게시글 수정메뉴 */
	private void boardModifyMenu(int boardNo) {
		
		

		System.out.print("변경할 게시글 제목: ");
		String boardTitle = Utility.inputString();

		System.out.print("변경할 게시글 내용: ");
		String boardContent = Utility.inputStrings();

		int result = boardService.updateBoard(boardTitle, boardContent, boardNo);
		if (result == 1)  {
			System.out.println("게시글 수정이 완료되었습니다.");
		}else {
			System.out.println("수정 불가한 게시글입니다.");
		}
		
		boardMenu();
	}
	
	
	/** 2. 나의 게시글 삭제메뉴 */

	private void deleteBoardMenu(int boardNo) {
		

		System.out.println("정말 삭제하시겠습니까? (1: 삭제, 0:취소)");

		int check = Utility.inputNumber();

		if (check == 1) {

			boardService.removeMyBoard(check);

			System.out.println("삭제완료 되었습니다.");
		} else {
			System.out.println("삭제가 취소되었습니다.");
		}
		boardMenu();

	}
	
	
	
	
	
	
	
	
	

	/** 4.내 정보확인 메뉴 */

	private void myInfoMenu() {
		MemberService memberService = new MemberService();

		System.out.println(memberService.getMember().toString());
		frontMenu();

	}

	/** 5.비밀번호변경 메뉴 */

	private void myInfoModifyMenu() {
		System.out.print("변경할 패스워드: ");
		String modifyMemberPw = Utility.inputString();

		service.setMemberPw(modifyMemberPw);
		frontMenu();

	}

	/** 6. 회원탈퇴 메뉴 */

	private void removeMemberMenu() {
		service.removeMember();
		System.out.println("탈퇴완료 되었습니다.");

	}

	/** 7. 로그아웃 [시작메뉴] 메뉴 */
	private void logoutMenu() {
		System.out.println("[ 로그아웃이 정상 처리되었습니다. ]");
		service.logout();
		mainMenu();
	}

	/**
	 * <pre>
	 * 5. 회원관리 [관리자] 메뉴
	 * - 로그인 성공시에 Service에 설정해 놓은 로그인 정보의 등급에 따라서 서비스 제공여부 검증
	 * </pre>
	 */
	private void memberControlMenu() {

		printLine();
		System.out.println("[관리자] 회원관리 메뉴]");
		printLine();
		System.out.println("1. 회원전체조회");
		System.out.println("2. 회원아이디조회");
		System.out.println("3. 회원등급별전체조회");
		System.out.println("4. 이전메뉴[회원서비스메인메뉴]");
		System.out.println("5. 도서관리");
		System.out.println("9. 로그아웃[메인메뉴]");
		System.out.println("0. 프로그램종료");
		printLine();
		System.out.print(">> 메뉴입력: ");

		int menuNo = Utility.inputNumber();
		switch (menuNo) {
		case 1:
			MemberListMenu();
			break;
		case 2:
			MemberIdSearchMenu();
			break;
		case 3:
			MemberGradeSearchMenu();
			break;
		case 4:
			frontMenu();
			break;
		case 5:
			bookInfoMenuAdmin();
			break;
		case 9:
			logoutAdminMenu();
			break;
		case 0:
			AdminexitMenu();
			break;
		default:
			break;
		}

		frontMenu();
	}

	/** 1. 회원 전체 조회 메뉴 */
	private void MemberListMenu() {
		MemberService service = new MemberService();

		for (Member i : service.getMemberList()) {
			System.out.println(i.toString());

		}
		memberControlMenu();
	}

	/** 2.회원 아이디 조회 메뉴 */

	private void MemberIdSearchMenu() {
		System.out.print("조회할 회원 아이디를 입력하세요: ");
		String memberId = Utility.inputString();

		MemberService service = new MemberService();
		Member mem = service.getMember(memberId);
		System.out.println(mem.toString());
		memberControlMenu();
	}

	/** 3.회원 등급별 전체 조회 */
	private void MemberGradeSearchMenu() {
		System.out.print("조회할 등급을 입력하세요: ");
		String grade = Utility.inputString();

		MemberService service = new MemberService();

		for (Member i : service.getGradeMember(grade)) {
			System.out.println(i.toString());

		}
		memberControlMenu();
	}

	/** 5.도서관리 */
	private void bookInfoMenuAdmin() {
		printLine();
		System.out.println("\t[도서 관리 메뉴]");
		printLine();
		System.out.println("1. 도서 목록");
		System.out.println("2. 도서 검색");
		System.out.println("3. 도서 등록");
		System.out.println("4. 도서 재고변경");
		System.out.println("5. 삭제");
		System.out.println("0. 이전");
		printLine();
		System.out.print(">> 메뉴입력: ");

		int menuNo = Utility.inputNumber();
		switch (menuNo) {
		case 1:
			bookListAdminMenu();
			break;
		case 2:
			bookSearchAdminMenu();
			break;
		case 3:
			bookAddAdminMenu();
			break;
		case 4:
			updateBookAdminMenu();
			break;
		case 5:
			deleteBookAdminMenu();
			break;
		case 0:
			memberControlMenu();
			break;
		default:
			break;
		}

		bookInfoMenuAdmin();
	}

	/** 1.책 목록 메뉴 */
	private void bookListAdminMenu() {
		BookService bookService = new BookService();

		for (Book i : bookService.getbookList()) {
			System.out.println(i.toString());

		}
		bookInfoMenuAdmin();
	}

	/** 2.책 검색 메뉴 */
	private void bookSearchAdminMenu() {
		System.out.print("책 이름을 검색하세요: ");
		String bookName = Utility.inputString();

		BookService bookService = new BookService();
		Book book = bookService.getbookSearch(bookName);
		System.out.println(book.toString());
		bookInfoMenuAdmin();

	}

	/** 3.책 등록 메뉴 */
	private void bookAddAdminMenu() {
		System.out.println();
		System.out.println("[ 도서 등록 메뉴 ]");

		System.out.print("도서명: ");
		String bookName = Utility.inputString();

		System.out.print("저자: ");
		String bookWriter = Utility.inputString();

		System.out.print("출판사: ");
		String bookCompany = Utility.inputString();

		System.out.print("가격: ");
		int bookPrice = Utility.inputNumber();

		System.out.print("재고: ");
		int bookAmount = Utility.inputNumber();

		System.out.print("발간일: ");
		String bookDate = Utility.inputString();

		System.out.print("개요: ");
		String bookContent = Utility.inputStrings();

		bookService.addBook(bookName, bookWriter, bookCompany, bookPrice, bookAmount, bookDate, bookContent);

	}

	/** 4. 도서 재고 변경 메뉴 */

	public void updateBookAdminMenu() {

		System.out.print("재고 변경할 도서이름: ");
		String bookName = Utility.inputString();

		System.out.print("변경할 재고 수: ");
		int modifyBookAmount = Utility.inputNumber();

		bookService.updateBookAmount(bookName, modifyBookAmount);
		System.out.println("도서 재고 변경이 완료되었습니다.");

	}

	/** 5. 도서 삭제 메뉴 */

	public void deleteBookAdminMenu() {

		System.out.print("삭제할 도서 번호를 입력하세요: ");
		int bookNo = Utility.inputNumber();

		if (bookService.removeBook(bookNo)) {
			System.out.println("삭제완료 되었습니다.");
		} else {
			System.out.println("삭제불가한 도서입니다.");
		}

	}

	/** 9.로그아웃 메뉴 */
	private void logoutAdminMenu() {
		System.out.println("[ 로그아웃이 정상 처리되었습니다. ]");
		service.logout();
		mainMenu();
	}

	/** 0. 종료 메뉴 */
	private void AdminexitMenu() {
		System.out.println();
		System.out.println("[ 프로그램을 정상 종료하였습니다. ]");
		System.out.println();
		System.exit(0);
	}

	/** 구분선 출력(공통) */
	public void printLine() {
		System.out.println("====================================");
	}

}
