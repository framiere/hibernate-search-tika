package fr.ramiere.hibernate.search;

import static com.google.common.collect.Sets.newHashSet;
import static fr.ramiere.hibernate.search.BookLocation.PRIMEFACES_DOC;
import static org.apache.commons.io.FileUtils.readFileToByteArray;
import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

public class InvalidBookTest extends BookTest {
	@Test(timeout = 10000)
	public void primeFaces() throws Exception {
		insertPrimeFaces();
		index();
		assertThat(search("JSF component suite")) //
				.hasSize(1) //
				.onProperty("title") //
				.contains("Primefaces");
	}

	void insertPrimeFaces() throws IOException {
		Book book = new Book();
		book.setId(dbId++);
		book.setPublicationDate(new Date());
		book.setTitle("Primefaces");
		book.setSubtitle("USER’S GUIDE");
		book.setDocument(readFileToByteArray(new File(PRIMEFACES_DOC)));
		book.setAuthors(newHashSet(createAuthor("Çağatay Çivici")));
		em.persist(book);
		em.flush();
		em.clear();
	}
}
