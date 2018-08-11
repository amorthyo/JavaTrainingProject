package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookDAO{

	@Override
	public void addBook(Book book) {
		try(Connection connection = ModelDAO.openConnection();
			PreparedStatement st = connection.prepareStatement("insert into book values(?,?,?,?,?)");) {
			st.setInt(1, book.getBookid());
			st.setString(2, book.getTitle());
			st.setString(3, book.getAuthor());
			st.setString(4, book.getCategory());
			st.setInt(5, book.getPrice());
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement st = null;
		boolean result = true;
		try {
			st = connection.prepareStatement("delete from book where bookid=?");
			st.setInt(1, bookid);
			int val = st.executeUpdate();
			if(val==0) {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBook(int bookid, int price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> getAllBooks() {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement st = null;
		List<Book> bookList = new ArrayList<>();
		try {
			st = connection.prepareStatement("select * from book");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int bookid = rs.getInt(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				String category = rs.getString(4);
				int price = rs.getInt(5);
				Book book = new Book();
				book.setBookid(bookid);
				book.setTitle(title);
				book.setAuthor(author);
				book.setCategory(category);
				book.setPrice(price);
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException{
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
