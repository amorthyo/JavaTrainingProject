package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		boolean result = true;
		try (Connection connection = ModelDAO.openConnection();
				PreparedStatement statement = connection.prepareStatement("delete from book where bookid=?");) {
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

		Book book = new Book();
		try (Connection connection = ModelDAO.openConnection();
				PreparedStatement statement = connection.prepareStatement("select * from book where bookid = ?");) {
			statement.setInt(1, bookid);
			ResultSet rs = statement.executeQuery();
			if (!rs.next()) {
				throw new BookNotFoundException("Sorry no book available with book id " + bookid);
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
		boolean result = true;
		try (Connection connection = ModelDAO.openConnection();
				PreparedStatement statement = connection
						.prepareStatement("update book set price = ? where bookid = ?");) {
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
		List<Book> bookList = new ArrayList<>();
		try (Connection connection = ModelDAO.openConnection();
				PreparedStatement statement = connection.prepareStatement("select * from book");) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setBookid(resultSet.getInt("bookid"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setCategory(resultSet.getString("category"));
				book.setPrice(resultSet.getInt("price"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException {
		String sql = "select * from book where author = ?";
		List<Book> bookList = new ArrayList<>();
		try (Connection connection = ModelDAO.openConnection();
				PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {
			statement.setString(1, author);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				throw new AuthorNotFoundException("Sorry no book found with author " + author);
			}
			resultSet.beforeFirst();
			while (resultSet.next()) {
				Book book = new Book();
				book.setBookid(resultSet.getInt("bookid"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setCategory(resultSet.getString("category"));
				book.setPrice(resultSet.getInt("price"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException {

		String sql = "select * from book where category = ?";
		List<Book> bookList = new ArrayList<>();
		try (Connection connection = ModelDAO.openConnection();
				PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {
			statement.setString(1, category);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				throw new CategoryNotFoundException("Sorry no book found with category " + category);
			}
			resultSet.beforeFirst();
			while (resultSet.next()) {
				Book book = new Book();
				book.setBookid(resultSet.getInt("bookid"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setCategory(resultSet.getString("category"));
				book.setPrice(resultSet.getInt("price"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

}
