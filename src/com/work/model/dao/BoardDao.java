package com.work.model.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.work.model.dto.Board;
import com.work.model.dto.Book;
import com.work.model.dto.Member;

/**
 * <pre>
 * 게시판테이블에 대한 DAO 클래스 
 * -- CRUD
 * 
 * </pre>
 * 
 * @author 김민하
 * @see com.work.model.dto.Board;
 * @see com.work.model.service.BookService
 */


public class BoardDao {
	private static int num = 200 ;
	/** jdbc driver */
	private String driver = "oracle.jdbc.driver.OracleDriver";
	/** oracle db server */
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	/** oracle account user */
	private String user = "hr2";
	/** oracle account password */
	private String password = "1234";

	
	/**
	 * 게시판 전체 조회
	 * 
	 * @return 전체 게시판 목록
	 */
	
	
	public ArrayList<Board> selectList() {
		ArrayList<Board> list = new ArrayList<Board>();
		
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
			rs = stmt.executeQuery("SELECT * from board");

			while (rs.next()) {
				Board dto = new Board();
				dto.setBoardNo(rs.getInt("BOARD_NO"));
				dto.setBoardTitle(rs.getString("BOARD_TITLE"));
				dto.setBoardDate(rs.getString("BOARD_DATE"));
				dto.setBoardWriter(rs.getString("BOARD_WRITER"));
				dto.setBoardContent(rs.getString("BOARD_CONTENT"));
			
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
	 * 게시글 등록
	 * @param dto 회원객체
	 * @return 성공시 1, 실패시 0
	 */
	public int insertBoard(Board dto) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
			
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "INSERT INTO BOARD VALUES(?,?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, num++);
			stmt.setString(2, dto.getBoardTitle());
			stmt.setString(3, dto.getBoardDate());
			stmt.setString(4, dto.getBoardWriter());
			stmt.setString(5, dto.getBoardContent());
			
			
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
	 * 내가 쓴 게시글 목록 조회
	 * @param memberId 아이디
	 * @return 회원객체, 미존재시 null
	 */
	public ArrayList<Board> selectBoardList(String boardWriter) {
		ArrayList<Board> list = new ArrayList<Board>();
		
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
			rs = stmt.executeQuery("SELECT * FROM Board WHERE BOARD_WRITER = '" + boardWriter + "'" );

			while (rs.next()) {
				Board dto = new Board();
				dto.setBoardNo(rs.getInt("BOARD_NO"));
				dto.setBoardTitle(rs.getString("BOARD_TITLE"));
				dto.setBoardDate(rs.getString("BOARD_DATE"));
				dto.setBoardWriter(rs.getString("BOARD_WRITER"));
				dto.setBoardContent(rs.getString("BOARD_CONTENT"));
			
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
	 * 게시글 수정
	 * @param boardTitle 게시글제목
	 * @param bookContent 게시글내용
	 * @return 성공시 1, 실패시 0
	 */
	public int updateBoard(String boardTitle, String boardContent,int boardNo) {
	
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			
			String sql = "UPDATE BOARD SET board_title = ? , board_content =? WHERE board_no = ?";
			stmt = conn.prepareStatement(sql);	
			stmt.setString(1, boardTitle);  
			stmt.setString(2, boardContent);
			stmt.setInt(3, boardNo);
			
			
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


	
	/** 게시글 삭제  */
	
	public boolean removeMyBoard(int boardNo) {
		
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
			String sql = "DELETE FROM BOARD WHERE BOARD_NO =?";
			stmt = conn.prepareStatement(sql);	
			stmt.setInt(1, boardNo);

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
	
	
	
	
	
	
	
	
	
	
	

