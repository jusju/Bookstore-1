package com.example.Bookstore;

import com.example.Bookstore.domain.BookRepository;

import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import com.example.Bookstore.domain.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			// Your code...add some demo data to db
			log.info("save a couple of books");

			crepository.save(new Category("Novel"));
			crepository.save(new Category("Detective"));
			crepository.save(new Category("Fairytale"));

			brepository.save(new Book("The picture of Doriann Gray", "Oscar Wilde", 2014, "tpodd2014", 10.00,
					crepository.findByName("Novel").get(0)));
			brepository.save(new Book("The Little Mermaid", "Hans Christian Anderson", 1999, "tlm1999", 15.00,
					crepository.findByName("Fairytale").get(0)));
			brepository.save(new Book("Anna Karenina", "Leo Tolstoy", 2010, "ak2010", 10.00,
					crepository.findByName("Novel").get(0)));

			log.info("fetch all students");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

			// Book b1 = new Book("The picture of Doriann Gray", "Oscar Wilde", 2014,
			// "tpodd2014", 10.00);
			// Book b2 = new Book("The Little Mermaid", "Hans Christian Anderson", 1999,
			// "tlm1999", 15.00);
			// Book b3 = new Book("Anna Karenina", "Leo Tolstoy", 2010, "ak2010", 10.00);

		};
	}

}
