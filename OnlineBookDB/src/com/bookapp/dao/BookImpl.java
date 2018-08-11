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

public class BookImpl implements BookDAO {

	@Override
	public void addBook(Book book) {
		try (Connection connection = ModelDAO.openConnection();
				PreparedStatement statement = connection.prepareStatement("insert into book values(?,?,?,?,?)");) {
			statement.setInt(1, book.getBookid());
			statement.setString(2, book.getTitle());
			statement.setString(3, book.getAuthor());
			statement.setString(4, book.getCategory());
			statement.setInt(5, book.getPrice());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement statement = null;
		boolean result = true;
		try {
			statement = connection.prepareStatement("delete from book where bookid=?");
			statement.setInt(1, bookid);
			int val = statement.executeUpdate();
			if (val == 0) {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement statement = null;
		Book book = new Book();
		try {
			statement = connection.prepareStatement("select * from book where bookid = ?");
			statement.setInt(1, bookid);
			ResultSet rs = statement.executeQuery();
			if (!rs.next()) {
				throw new BookNotFoundException("Sorry book not found");
			}
			book.setBookid(rs.getInt("bookid"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setCategory("category");
			book.setPrice(rs.getInt("price"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public boolean updateBook(int bookid, int price) throws BookNotFoundException {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement statement = null;
		boolean result = true;
		try {
			statement = connection.prepareStatement("update book set price = ? where bookid = ?");
			statement.setInt(1, price);
			statement.setInt(2, bookid);
			int val = statement.executeUpdate();
			if (val == 0) {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Book> getAllBooks() {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement statement = null;
		List<Book> bookList = new ArrayList<>();
		try {
			statement = connection.prepareStatement("select * from book");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBookid(rs.getInt("bookid"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setCategory(rs.getString("category"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException {
		Connection connection = ModelDAO.openConnection();
		String sql = "select * from book where author = ?";
		PreparedStatement statement = null;
		List<Book> bookList = new ArrayList<>();
		try {
			statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, author);
			ResultSet rs = statement.executeQuery();
			if (!rs.next()) {
				throw new AuthorNotFoundException("Author not found");
			}
			rs.beforeFirst();
			while (rs.next()) {
				Book book = new Book();
				book.setBookid(rs.getInt("bookid"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setCategory(rs.getString("category"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException {
		Connection connection = ModelDAO.openConnection();
		String sql = "select * from book where category = ?";
		PreparedStatement statement = null;
		List<Book> bookList = new ArrayList<>();
		try {
			statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, category);
			ResultSet rs = statement.executeQuery();
			if (!rs.next()) {
				throw new CategoryNotFoundException("Sorry the category doesn't exists");
			}
			rs.beforeFirst();
			while (rs.next()) {
				Book book = new Book();
				book.setBookid(rs.getInt("bookid"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setCategory(rs.getString("category"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

}
