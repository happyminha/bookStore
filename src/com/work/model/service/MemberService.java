package com.work.model.service;


import java.util.ArrayList;

import com.work.model.dao.MemberDao;
import com.work.model.dto.Member;
import com.work.util.Utility;

/** 
 * <pre>
 * 회원관리를 위한 서비스(업무처리) 클래스
 * -- 로그인 요청 성공시에 로그인 사용자의 아이디와 등급을 Class 변수에 설정처리
 * -- 로그인 사용자의 서비스 요청시에 인증 정보로 대체 활용 (Web HttpSession 대체활용)
 * </pre> 
 * 
 * @author 김민하
 * @version ver.1.0
 * @since jdk1.8
 * @see com.work.model.dto.Member
 * @see com.work.model.dao.MemberDao
 * @see com.work.model.service.MemberService
 * @see com.work.util.Utility
 */

public class MemberService {
	/** 회원 테이블에 대한 DAO 객체 선언 및 생성 */
	private MemberDao dao = new MemberDao();
	
	/** 로그인 성공한 회원의 아이디를 프로그램 종료시까지 사용하기위한 속성 */
	public static String loginMemberId;
	
	/** 로그인 성공한 회원의 등급을 프로그램 종료시까지 사용하기위한 속성 */
	public static String loginGrade;
	
	
	/**
	 * <pre>
	 * 회원 등록
	 * 회원등록(아이디,비밀번호,이름,휴대폰,이메일) 
	 * -- 일반회원(입력 + 시스템 가입일,등급,[마일리지])
	 * </pre>
	 * 
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param name 이름
	 * @param mobile 휴대폰 
	 * @param email 이메일
	 * @param entrydate 가입일
	 * @param grade     등급
	 * @param myamount  수량
	 */
	public void addMember(String memberId, String memberPw, String name, String mobile, String email,String entrydate,String grade,int myamount) {
		Member dto = new Member(memberId, memberPw, name, mobile, email,entrydate,grade,myamount); // 사용자 입력된 정보로 회원 객체 생성
		dto.setEntrydate(Utility.getCurrentDate()); // 가입일 : 현재날짜
		
		dto.setGrade("일반");
		addMember(dto);
	}
	 
	/**
	 * <pre>
	 * 회원등록
	 * 회원등록(회원객체) : 일반, 우수, 관리자
	 * </pre>
	 * @param dto 회원
	 */
	public void addMember(Member dto) {
		if (dao.existMemberId(dto.getMemberId())) { // 아이디 중복
			System.out.println("[회원 등록 실패] " + dto.getMemberId() + "는 이미 존재하는 아이디입니다.");
			return;
		}
		
		int result = dao.insertMember(dto);
		if (result == 1) {
			System.out.println("[회원 등록 성공] " + dto.getMemberId()  + "님 로그인 후 서비스를 이용하시기 바랍니다.");
		} else {
			System.out.println("[회원 등록 실패]");
		}
	}
	
	/**
	 * <pre>
	 * 책 구매 
	 * 책 구매 (my amount + 1)
	 * </pre>
	 * @param dto 회원
	 */
	public void updateMyAmount() {
		dao.updateMyAmount(loginMemberId);
	}

	/**
	 * 전체회원 조회 (관리자제외)
	 * @return 회원 리스트
	 */
	public ArrayList<Member> getMemberList() {
		return dao.selectList();
	}

	/**
	 * 회원상세조회
	 * @param memberId 아이디
	 * @return 성공시 회원, 실패시 null
	 */
	public Member getMember() {
		return dao.selectOne(loginMemberId);
	}
	
	
	/**
	 * 회원상세조회 (전체)
	 * @param memberId 아이디
	 * @return 성공시 회원, 실패시 null
	 */
	public Member getMember(String memberId) {
		return dao.selectOne(memberId);
	}
	
	
	/**
	 * 등급별 회원조회
	 * @param grade 등급
	 * @return
	 */
	public ArrayList<Member> getGradeMember(String grade) {
		return dao.selectList(grade);
	}
	
	
	

	
	/**
	 * 비밀번호 변경
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param modifiMemberPw 변경암호
	 * @return 성공시 1, 실패시 0
	 */
	public int setMemberPw(String modifiMemberPw) {
		return dao.updateMemberPw(loginMemberId, modifiMemberPw);
	}
	
	
	
	/**
	 * 아이디 찾기
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @return
	 */
	public String findMemberId(String name, String email) {
		return dao.findMemberId(name, email);
	}
	
	
	/**
	 * 비밀번호 찾기
	 * @param memberId 아이디
	 * @param name 아이디
	 * @param mobile 휴대폰 
	 * @return
	 */
	
	public String findMemberPw(String memberId, String name, String mobile) {
		return dao.findMemberPw(memberId,name,mobile);
	}
	
	
	/**
	 * 회원 탈퇴
	 * 
	 * @param memberId 아이디
	 * @return 성공 true, 실패 false
	 */
	public boolean removeMember() {
		return dao.removeMember(loginMemberId);
	}
	
	
	
	
	
	
	
	
	/**
	 * <pre>
	 * 로그인
	 * -- 로그인 성공시 프로그램 종료할때까지 로그인 정보를 사용하기 위한 설정정보 활용하기
	 * 1. 로그인시에 기존에 설정된 로그인 정보 null 초기화
	 * 2. 로그인 정보로 로그인 수행
	 * 3. 로그인 성공 회원의 아이디, 등급 로그인 정보에 설정
	 * 4. 로그인 성공한 회원의 등급 반환
	 * </pre>
	 * 
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 로그인 성공 회원의 등급, 실패시 null
	 */
	public String login(String memberId, String memberPw) {
		clearLogin();
		String grade = dao.login(memberId, memberPw);
		
		if (grade != null) {
			loginMemberId = memberId;
			loginGrade = grade;
			return grade;
		} 
		return null;
	}
	
	/** 로그인 정보 지우기 */
	public void clearLogin() {
		loginMemberId = null;
		loginGrade = null;
	}
	
	/**
	 * <pre>
	 * 로그아웃
	 * -- 로그아웃 요청시 로그인 설정된 정보 초기화(지우기)
	 * </pre> 
	 */
	public void logout() {
		clearLogin();
	}
}




	


