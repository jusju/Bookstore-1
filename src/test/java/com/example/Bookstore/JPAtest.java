package com.example.Bookstore;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

import static org.assertj.core.api.Assertions.*;

/**
 * Testing the web layer
 * 
 * Spring application context is started, but without the server
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAtest {

	@Autowired
	private BookRepository repository;

	@Test
	public void findByLastnameShouldReturnStudent() {
		List<com.example.Bookstore.domain.Book> books = repository.findByTitle("The Little Mermaid");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Hans Christian Anderson");
	}

	@Test
	public void createNewBook() {
		Book book = new Book("The picture of Doriann Gray", "Oscar Wilde", 2014, "tpo014", 10.00,
				new Category("Novella"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void deleteBooks() {
		repository.deleteAll();
		assertThat(repository.count()).isEqualTo(0);
	}

}