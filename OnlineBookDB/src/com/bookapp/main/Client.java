package com.bookapp.main;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.service.BookService;
import com.bookapp.service.BookServiceImpl;

public class Client {

	public static void main(String[] args) {

		BookService bs = new BookServiceImpl();
		/*
		 * Book book = new Book(); book.setBookid(1); book.setTitle("Java Cookbook");
		 * book.setAuthor("Oriely"); book.setCategory("Programming");
		 * book.setPrice(2000);
		 */
		// bs.addBook(book);

		/*
		 * try { bs.deleteBook(1); } catch (BookNotFoundException e) {
		 * System.out.println(e.getMessage()); e.printStackTrace(); }
		 */

		/*
		 * for(Book book : bs.getAllBooks()) { System.out.println(book); }
		 */

		/*
		 * try { for(Book book : bs.getBookbyAuthor("Oriely")) {
		 * System.out.println(book); } } catch (AuthorNotFoundException e) {
		 * System.out.println(e.getMessage()); e.printStackTrace(); }
		 */

		/*
		 * try { System.out.println(bs.getBookById(2)); } catch (BookNotFoundException
		 * e) { System.out.println(e.getMessage()); e.printStackTrace(); }
		 */

		/*
		 * try { bs.updateBook(2, 5000); } catch (BookNotFoundException e) {
		 * System.out.println(e.getMessage()); e.printStackTrace(); }
		 */

	}

}
