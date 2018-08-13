package com.bookapp.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bookapp.bean.Book;
import com.bookapp.dao.BookDAO;
import com.bookapp.dao.BookImpl;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookServiceImpl implements BookService {

	BookDAO bookDAO = new BookImpl();

	@Override
	public void addBook(Book book) {
		bookDAO.addBook(book);

	}

	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		if (!bookDAO.deleteBook(bookid)) {
			throw new BookNotFoundException("Invalid id");
		}
		return false;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		return bookDAO.getBookById(bookid);
	}

	@Override
	public boolean updateBook(int bookid, int price) throws BookNotFoundException {
		if (!bookDAO.updateBook(bookid, price)) {
			throw new BookNotFoundException("Invalid id");
		}
		return false;
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDAO.getAllBooks().stream().sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException {
		try {
			return bookDAO.getBookbyAuthor(author).stream()
					.sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new AuthorNotFoundException("Sorry author not found!!!");
		}
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException {
		try {
			return bookDAO.getBookbycategory(category).stream()
					.sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new CategoryNotFoundException("Sorry this category doesn't exist!!!");
		}

	}

}
