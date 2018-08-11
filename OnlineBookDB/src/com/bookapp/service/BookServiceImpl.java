package com.bookapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.bookapp.bean.Book;
import com.bookapp.dao.BookDAO;
import com.bookapp.dao.BookImpl;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookServiceImpl implements BookService{
	
	BookDAO bookDAO = new BookImpl();

	@Override
	public void addBook(Book book) {
		bookDAO.addBook(book);
		
	}

	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		if(!bookDAO.deleteBook(bookid)) {
			throw new BookNotFoundException("Invalid id");
		}
		return false;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		return null;
	}

	@Override
	public boolean updateBook(int bookid, int price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDAO.getAllBooks().stream()
		.sorted((book1, book2)->book1.getTitle().compareTo(book2.getTitle()))
		.collect(Collectors.toList());
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
