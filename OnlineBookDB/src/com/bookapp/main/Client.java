package com.bookapp.main;

import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;
import com.bookapp.service.BookService;
import com.bookapp.service.BookServiceImpl;

public class Client {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		BookService bs = new BookServiceImpl();
		int choice = 0;
		String wishContinue = null;

		while (true) {
			System.out.println("****MENU****");
			System.out.println("1. Add new book\n2. Delete Book\n3. Update Book price\n4. Get all Books"
					+ "\n5. Get a bit by ID\n6. Get book by author name\n7. Get book by category\n");
			System.out.print("Please enter your choice(1-7): ");
			choice = scanner.nextInt();
			scanner.nextLine();
			System.out.println("\n");
			switch (choice) {
			case 1:
				// Add book
				Book book = new Book();
				System.out.print("Enter the book id: ");
				book.setBookid(scanner.nextInt());
				scanner.nextLine();
				System.out.println();
				System.out.print("Enter the book title: ");
				book.setTitle(scanner.nextLine());
				System.out.println();
				System.out.print("Enter the author of the book: ");
				book.setAuthor(scanner.nextLine());
				System.out.println();
				System.out.print("Enter the category of the book: ");
				book.setCategory(scanner.next());
				System.out.println();
				System.out.print("Enter the price for the book: ");
				book.setPrice(scanner.nextInt());
				scanner.nextLine();
				bs.addBook(book);
				System.out.println("Insertion Successfull");
				break;

			case 2:
				// Delete Book
				System.out.print("Enter the book id which you want to delete: ");
				try {
					bs.deleteBook(scanner.nextInt());
					System.out.println("Deletion successfull!!!");
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				scanner.nextLine();
				break;

			case 3:
				// Update Book
				System.out.print("Enter the book id for which you want to update the price: ");
				int id = scanner.nextInt();
				System.out.println();
				System.out.print("Enter the new price: ");
				System.out.println();
				int price = scanner.nextInt();
				System.out.println();
				try {
					bs.updateBook(id, price);
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				scanner.nextLine();
				break;

			case 4:
				// Get all books
				for (Book allBook : bs.getAllBooks()) {
					System.out.println(allBook);
				}
				scanner.nextLine();
				break;

			case 5:
				// Get a book by id
				System.out.print("Enter the book id: ");
				try {
					System.out.println(bs.getBookById(scanner.nextInt()));
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				scanner.nextLine();
				break;

			case 6:
				// Get books by author name
				System.out.print("Enter the author name: ");
				try {
					for (Book authorBook : bs.getBookbyAuthor(scanner.nextLine())) {
						System.out.println(authorBook);
					}
				} catch (AuthorNotFoundException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				scanner.nextLine();
				break;

			case 7:
				// Get books by category
				System.out.print("Enter the category type: ");
				try {
					for (Book categoryBook : bs.getBookbycategory(scanner.nextLine())) {
						System.out.println(categoryBook);
					}
				} catch (CategoryNotFoundException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				scanner.nextLine();
				break;

			default:
				System.out.println("Sorry this option is not available!!!");
				break;
			}

			System.out.println();
			System.out.print("Do you want to continue? ");
			wishContinue = scanner.nextLine();
			if (wishContinue.equalsIgnoreCase("no")) {
				System.out.println("      Thank you\t");
				System.out.println("Hope to see you soon");
				break;
			}
		}
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
