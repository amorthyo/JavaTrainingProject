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
		BookService bookService = new BookServiceImpl();
		int choice = 0;
		String wishContinue = null;

		while (true) {
			System.out.println("****MENU****");
			System.out
					.println("1. Add new book\n"
								+ "2. Delete Book\n"
								+ "3. Update Book price\n"
								+ "4. Get all Books\n"
								+ "5. Get a book by ID\n"
								+ "6. Get book by author name\n"
								+ "7. Get book by category\n");
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
				bookService.addBook(book);
				System.out.println("Insertion Successfull");
				break;

			case 2:
				// Delete Book
				System.out.print("Enter the book id which you want to delete: ");
				try {
					bookService.deleteBook(scanner.nextInt());
					System.out.println();
					System.out.println("Deletion successfull!!!");
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
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
				scanner.nextLine();
				System.out.println();
				try {
					bookService.updateBook(id, price);
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 4:
				// Get all books
				for (Book allBook : bookService.getAllBooks()) {
					System.out.println(allBook);
				}
				// scanner.nextLine();
				break;

			case 5:
				// Get a book by id
				System.out.print("Enter the book id: ");
				try {
					System.out.println(bookService.getBookById(scanner.nextInt()));
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
				}
				scanner.nextLine();
				break;

			case 6:
				// Get books by author name
				System.out.print("Enter the author name: ");
				try {
					for (Book authorBook : bookService.getBookbyAuthor(scanner.nextLine())) {
						System.out.println(authorBook);
					}
				} catch (AuthorNotFoundException e) {
					System.out.println(e.getMessage());
				}
				// scanner.nextLine();
				break;

			case 7:
				// Get books by category
				System.out.print("Enter the category type: ");
				try {
					for (Book categoryBook : bookService.getBookbycategory(scanner.nextLine())) {
						System.out.println(categoryBook);
					}
				} catch (CategoryNotFoundException e) {
					System.out.println(e.getMessage());
				}
				// scanner.nextLine();
				break;

			default:
				System.out.println("Sorry this option is not available!!!");
				break;
			}

			System.out.println();
			System.out.print("Do you want to continue(yes or no)? ");
			wishContinue = scanner.nextLine();
			System.out.println();
			if (wishContinue.equalsIgnoreCase("no")) {
				System.out.println("      Thank you\t");
				System.out.println("Hope to see you soon");
				break;
			}
		}
		scanner.close();

	}

}
