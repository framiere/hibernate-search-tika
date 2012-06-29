package fr.ramiere.hibernate.search;

import static com.google.common.collect.Sets.newHashSet;
import static fr.ramiere.hibernate.search.BookLocation.HIBERNATE_SEARCH;
import static org.apache.commons.io.FileUtils.readFileToByteArray;
import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

/**
 * This class
 * <ul>
 * <li>starts up hibernate</li>
 * <li>create the "hibernate search" book</li>
 * <li>then run tests on this book, everything is fine</li>
 * </ul>
 * When the {@link #primeFaces()} test is run an {@link OutOfMemoryError} occurs.
 * <p>
 * That is a very strange behavior as the {@link ByteArrayBridgeTest} shows that the conversion is done correctly
 */
public class ValidBookTest extends BookTest {
	@Test
	public void beforeAndAfterIndexing() throws Exception {
		createHibernateSearch();
		assertThat(search("lucene")) //
				.isEmpty();
		index();
		assertThat(search("lucene")) //
				.hasSize(1) //
				.onProperty("title") //
				.contains("Hibernate Search");
	}

	@Test
	public void stemming() throws Exception {
		createHibernateSearch();
		index();
		assertThat(search("search")) //
				.hasSize(1) //
				.onProperty("title") //
				.contains("Hibernate Search");
	}

	@Test
	public void authors() throws Exception {
		createHibernateSearch();
		index();
		assertThat(search("THIS_IS_AN_AUTHOR")) //
				.hasSize(1) //
				.onProperty("title") //
				.contains("Hibernate Search");
	}

	void createHibernateSearch() throws IOException {
		Book book = new Book();
		book.setId(1);
		book.setPublicationDate(new Date());
		book.setTitle("Hibernate Search");
		book.setSubtitle("Apache Lucene™ Integration");
		book.setDocument(readFileToByteArray(new File(HIBERNATE_SEARCH)));
		book.setAuthors((newHashSet( //
				createAuthor("xzy Emmanuel Bernard"), //
				createAuthor("Hardy Ferentschik"), //
				createAuthor("Gustavo Fernandes"), //
				createAuthor("Sanne Grinovero"), //
				createAuthor("Nabeel Ali Memon"), //
				createAuthor("THIS_IS_AN_AUTHOR") //
		)));
		em.persist(book);
		em.flush();
		em.clear();
	}

}
