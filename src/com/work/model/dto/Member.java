package com.work.model.dto;

public class Member {
	/** 아이디 */
	private String memberId;
	/** 비밀번호 */
	private String memberPw;
	/** 이름 */
	private String name;
	/** 휴대폰 */
	private String mobile;
	/** 이메일 */
	private String email;
	/** 가입일 */
	private String entrydate;
	/** 등급 */
	private String grade;
	/** 수량 */
	private int myamount;
	
	/**
	 * 기본생성자
	 */
	public Member() {
		
	}

	/**
	 *  회원 공통 속성 전체 초기화 생성자
	 * @param memberId  아이디
	 * @param memberPw  비밀번호
	 * @param name      이름
	 * @param mobile    휴대폰
	 * @param email		이메일
	 * @param entrydate 가입일
	 * @param grade     등급
	 * @param myamount  수량
	 */
	public Member(String memberId, String memberPw, String name, String mobile, String email, String entrydate,
			String grade, int myamount) {
		
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.entrydate = entrydate;
		this.grade = grade;
		this.myamount = myamount;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the memberPw
	 */
	public String getMemberPw() {
		return memberPw;
	}

	/**
	 * @param memberPw the memberPw to set
	 */
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the entrydate
	 */
	public String getEntrydate() {
		return entrydate;
	}

	/**
	 * @param entrydate the entrydate to set
	 */
	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the myamount
	 */
	public int getMyamount() {
		return myamount;
	}

	/**
	 * @param myamount the myamount to set
	 */
	public void setMyamount(int myamount) {
		this.myamount = myamount;
	}

	@Override
	public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(" 아이디 : " + memberId);
			builder.append("\n");
			builder.append(" 비밀번호 : ");
			builder.append("\n");
			builder.append(" 이름 : " + name);
			builder.append("\n");
			builder.append(" 핸드폰번호 : " + mobile);
			builder.append("\n");
			builder.append(" 이메일 : " + email);
			builder.append("\n");
			builder.append(" 가입일 : " + entrydate);
			builder.append("\n");
			builder.append(" 등급 : " + grade);
			builder.append("\n");
			builder.append(" 누적구매수 : " + myamount);
			return builder.toString();
		}

	
	
	
	

	

}
