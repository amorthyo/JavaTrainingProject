package com.bookapp.main;

import com.bookapp.bean.Book;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.service.BookService;
import com.bookapp.service.BookServiceImpl;

public class Client {

	public static void main(String[] args) {
	
		/*Book book = new Book();
		book.setBookid(1);
		book.setTitle("Java Cookbook");
		book.setAuthor("Oriely");
		book.setCategory("Programming");
		book.setPrice(2000);*/
		
		BookService bs = new BookServiceImpl();
//		bs.addBook(book);
		
		/*try {
			bs.deleteBook(1);
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}*/
		
		for(Book book : bs.getAllBooks()) {
			System.out.println(book);
		}
		
		
	}

}
