package com.work.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.work.model.dto.Book;
import com.work.model.dto.Member;


/**
 * <pre>
 * 도서테이블에 대한 DAO 클래스 
 * -- CRUD
 * 
 * </pre>
 * 
 * @author 김민하
 * @see com.work.model.dto.Book
 * @see com.work.model.service.BookService
 */

public class BookDao {
	/** jdbc driver */
	private String driver = "oracle.jdbc.driver.OracleDriver";
	/** oracle db server */
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	/** oracle account user */
	private String user = "hr2";
	/** oracle account password */
	private String password = "1234";


	
	
	
	/**
	 * 도서 등록
	 * @param dto 회원객체
	 * @return 성공시 1, 실패시 0
	 */
	public int insertBook(Book dto) {
		System.out.println(dto);
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
			
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "INSERT INTO BOOK VALUES(SEQ_BOOK.NEXTVAL,?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getBookName());
			stmt.setString(2, dto.getBookWriter());
			stmt.setString(3, dto.getBookCompany());
			stmt.setInt(4, dto.getBookPrice());
			stmt.setInt(5, dto.getBookAmount());
			stmt.setString(6, dto.getBookDate());
			stmt.setString(7, dto.getBookContent());
			
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
	 * 책 리스트 
	 * 
	 * @return 최신 발간 기준 top10 도서들의 목록
	 */

	public ArrayList<Book> selectList() {
		ArrayList<Book> list = new ArrayList<Book>();
		
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
			rs = stmt.executeQuery("SELECT book_no, book_name, book_writer, book_company, book_price, book_amount, book_date, book_content FROM BOOK WHERE ROWNUM <= 10 ORDER BY BOOK_DATE DESC");

			while (rs.next()) {
				Book dto = new Book();
				dto.setBookNo(rs.getInt("book_no"));
				dto.setBookName(rs.getString("book_name"));
				dto.setBookWriter(rs.getString("book_writer"));
				dto.setBookCompany(rs.getString("book_company"));
				dto.setBookPrice(rs.getInt("book_price"));
				dto.setBookAmount(rs.getInt("book_amount"));
				dto.setBookDate(rs.getString("book_date"));
				dto.setBookContent(rs.getString("book_content"));
			

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
	 * 책이름 검색해서 책 정보 조회
	 * @param bookName 
	 * @return 회원객체, 미존재시 null
	 */
	public Book selectOne(String bookName) {
		
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

			String sql = "SELECT * FROM BOOK WHERE BOOK_NAME = ?";
			stmt = conn.prepareStatement(sql);	
			stmt.setString(1, bookName);  
			
			rs = stmt.executeQuery();	   //preparedStatement 사용시 괄호에 sql을 받지 않는다!!!!!!! 
			
			if(rs.next()) {	
				Book dto = new Book(); 
				dto.setBookNo(rs.getInt("BOOK_NO"));
				dto.setBookName(rs.getString("BOOK_NAME"));
				dto.setBookWriter(rs.getString("BOOK_WRITER"));
				dto.setBookCompany(rs.getString("BOOK_COMPANY"));
				dto.setBookPrice(rs.getInt("BOOK_PRICE"));
				dto.setBookAmount(rs.getInt("BOOK_AMOUNT"));
				dto.setBookDate(rs.getString("BOOK_DATE"));
				dto.setBookContent(rs.getString("BOOK_CONTENT"));
				
				
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
	 *  책 구매 amount 변경
	 * @param bookName 책이름
	 * @return 성공시 1, 실패시 0
	 */
	public int updateBook(String bookName) {
	
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			
			String sql1 = "UPDATE BOOK SET book_amount = book_amount - 1 WHERE book_name = ?";
			stmt = conn.prepareStatement(sql1);	
			stmt.setString(1, bookName);  
			
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
	 * 도서 재고 변경 
	 * @param bookName 도서명
	 * @param modifyBookAmount 변경할 도서 재고
	 * @return 성공시 1, 실패시 0
	 */
	public int updateBookAmount(String bookName, int modifyBookAmount) {
	
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password); 
			
			String sql = "UPDATE BOOK SET book_amount = ? WHERE book_name = ?";
			stmt = conn.prepareStatement(sql);	
			stmt.setInt(1, modifyBookAmount);  
			stmt.setString(2, bookName);
			
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


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** 도서 삭제 */
	
	public boolean removeBook(int bookNo) {
		
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
			String sql = "DELETE FROM BOOK WHERE BOOK_NO =?";
			stmt = conn.prepareStatement(sql);	
			stmt.setInt(1, bookNo);

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


