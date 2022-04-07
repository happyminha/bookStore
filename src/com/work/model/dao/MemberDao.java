package com.work.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.work.model.dto.Member;

/**
 * <pre>
 * 회원테이블에 대한 DAO 클래스 
 * -- CRUD
 * -- 회원등록, 조회, 전제조회, 변경, 삭제
 * </pre>
 * 
 * @author 김민하
 * @see com.work.model.dto.Member
 * @see com.work.model.service.MemberService
 */

public class MemberDao {
	/** jdbc driver */
	private String driver = "oracle.jdbc.driver.OracleDriver";
	/** oracle db server */
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	/** oracle account user */
	private String user = "hr2";
	/** oracle account password */
	private String password = "1234";

	/**
	 * 전체회원조회 (관리자제외)
	 * 
	 * @return 회원리스트
	 */
	public ArrayList<Member> selectList() {
		ArrayList<Member> list = new ArrayList<Member>();

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM MEMBER WHERE NOT GRADE IN ('관리자')");

			while (rs.next()) {
				Member dto = new Member();
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPw(rs.getString("member_pw"));
				dto.setName(rs.getString("name"));
				dto.setMobile(rs.getString("mobile"));
				dto.setEmail(rs.getString("email"));
				dto.setEntrydate(rs.getString("entrydate"));
				dto.setGrade(rs.getString("grade"));
				dto.setMyamount(rs.getInt("my_amount"));
			

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	
	
	/**
	 * 회원상세조회(아이디):회원
	 * @param memberId 아이디
	 * @return 회원객체, 미존재시 null
	 */
	public Member selectOne(String memberId) {
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			stmt = conn.createStatement();	
			
			String sql = "SELECT * FROM MEMBER WHERE member_id = '" + memberId + "'";
			rs = stmt.executeQuery(sql);	
			
			if(rs.next()) {	
				Member dto = new Member(); 
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPw(rs.getString("member_pw"));
				dto.setName(rs.getString("name"));
				dto.setMobile(rs.getString("mobile"));
				dto.setEmail(rs.getString("email"));
				dto.setEntrydate(rs.getString("entrydate"));
				dto.setGrade(rs.getString("grade"));
				dto.setMyamount(rs.getInt("my_amount"));
				
				
				return dto;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	/**
	 * 등급별 전체회원 조회(관리자제외)
	 * @param gade 등급
	 * @return 등급회원 리스트
	 */
	public ArrayList<Member> selectList(String grade) {
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			stmt = conn.createStatement();	
			
			String sql = "SELECT * FROM MEMBER WHERE grade = '" + grade + "'";
			rs = stmt.executeQuery(sql);	
			
			while(rs.next()) {	
				Member dto = new Member(); 
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPw(rs.getString("member_pw"));
				dto.setName(rs.getString("name"));
				dto.setMobile(rs.getString("mobile"));
				dto.setEmail(rs.getString("email"));
				dto.setEntrydate(rs.getString("entrydate"));
				dto.setGrade(rs.getString("grade"));
				dto.setMyamount(rs.getInt("my_amount"));
				
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}	
	
	/**
	 * 비밀번호 변경 (내정보변경)
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param modifyMemberPw 변경비밀번호
	 * @return 성공시 1, 실패시 0
	 */
	public int updateMemberPw(String memberId, String modifyMemberPw) {
	
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			
			String sql = "UPDATE MEMBER SET member_pw = ? WHERE member_id = ?";
			stmt = conn.prepareStatement(sql);	
			stmt.setString(1, modifyMemberPw);  
			stmt.setString(2, memberId);
			
			return stmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}


	/**
	 * 책 구매 (my amount +1)
	 * @param memberId
	 * @param memberPw
	 * @param modifyMemberPw
	 * @return 성공시 1, 실패시 0
	 */
	public int updateMyAmount(String memberId) {
	
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			
			String sql = "UPDATE MEMBER SET my_amount = my_amount+1 WHERE member_id = ?";
			stmt = conn.prepareStatement(sql);	
			stmt.setString(1, memberId);
			stmt.executeUpdate();
			

			String sql2 = "SELECT * FROM MEMBER WHERE member_id = ?";
			stmt = conn.prepareStatement(sql2);	
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();	

			Member dto = new Member(); 
			if(rs.next()) {	
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPw(rs.getString("member_pw"));
				dto.setName(rs.getString("name"));
				dto.setMobile(rs.getString("mobile"));
				dto.setEmail(rs.getString("email"));
				dto.setEntrydate(rs.getString("entrydate"));
				dto.setGrade(rs.getString("grade"));
				dto.setMyamount(rs.getInt("my_amount"));
				
			}
			
			if(dto.getMyamount() >= 5) {
				String sql3 = "UPDATE MEMBER SET grade = '우수' WHERE member_id = ?";
				stmt = conn.prepareStatement(sql3);	
				stmt.setString(1, memberId);
				return stmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
	/**
	 * 회원 등록
	 * @param dto 회원객체
	 * @return 성공시 1, 실패시 0
	 */
	public int insertMember(Member dto) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
			
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			stmt.setString(2, dto.getMemberPw());
			stmt.setString(3, dto.getName());
			stmt.setString(4, dto.getMobile());
			stmt.setString(5, dto.getEmail());
			stmt.setString(6, dto.getEntrydate());
			stmt.setString(7, dto.getGrade());
			stmt.setInt(8, dto.getMyamount());
			
			
			return stmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}	
	
	/**
	 * 아이디 존재 유무 조회
	 * @param memberId 아이디
	 * @return 성공시 true, 실패시 false
	 */
	public boolean existMemberId(String memberId) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			String sql = "SELECT 1 FROM MEMBER WHERE member_id = ?";
			stmt = conn.prepareStatement(sql);	
			stmt.setString(1, memberId);
			
			rs = stmt.executeQuery();	
			return rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	
	
	/**
	 * 로그인
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 성공시 해당회원 등급, 실패시 null
	 */
	public String login(String memberId, String memberPw) {
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			String sql = "select grade from member where member_id=? and member_pw=?";
			stmt = conn.prepareStatement(sql);	
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
			
			rs = stmt.executeQuery();	
			if (rs.next()) {
				return rs.getString("grade");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return null;
	}


	/**
	 * 아이디찾기
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @return 이름, 휴대폰으로 아이디찾기
	 */
	public String findMemberId(String name, String email) {
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			String sql = "select member_id from member where name=? and email=?";
			stmt = conn.prepareStatement(sql);	
			stmt.setString(1, name);
			stmt.setString(2, email);
			
			rs = stmt.executeQuery();	
			if (rs.next()) {
				return rs.getString("member_id");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return null;
	}
	
	
	/**
	 * 비밀번호찾기
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @return 이름, 휴대폰으로 비번찾기
	 */
	public String findMemberPw(String memberId, String name, String mobile) {
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			String sql = "select member_pw from member where member_id=? and name=? and mobile=?";
			stmt = conn.prepareStatement(sql);	
			stmt.setString(1, memberId);
			stmt.setString(2, name);
			stmt.setString(3, mobile);
			
			rs = stmt.executeQuery();	
			if (rs.next()) {
				return rs.getString("member_pw");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return null;
	}
	
	
	/**
	 * 회원탈퇴
	 * @param memberId 아이디
	 * @param 
	 * @return 해당 아이디로 회원 탈퇴
	 */
	
public boolean removeMember(String memberId) {
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			String sql = "DELETE FROM MEMBER WHERE MEMBER_ID =?";
			stmt = conn.prepareStatement(sql);	
			stmt.setString(1, memberId);

			rs = stmt.executeQuery();	
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}
	
}
