package fr.ramiere.hibernate.search;

import static org.hibernate.search.Search.getFullTextSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BookTest {
	private static Logger log = LoggerFactory.getLogger(BookTest.class);
	protected int dbId = 0;

	protected EntityManagerFactory emf;

	protected EntityManager em;

	@Before
	@SuppressWarnings("deprecation")
	public void initHibernate() {
		org.hibernate.ejb.Ejb3Configuration config = new org.hibernate.ejb.Ejb3Configuration();
		config.configure("hibernate-search-example", new HashMap<Object, Object>());
		emf = config.buildEntityManagerFactory();
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	@After
	public void purgeSearch() {
		FullTextSession ftSession = getFullTextSession((Session) em.getDelegate());
		ftSession.purgeAll(Book.class);
		ftSession.flushToIndexes();
		ftSession.close();
		emf.close();
	}

	void index() {
		FullTextSession ftSession = org.hibernate.search.Search.getFullTextSession((Session) em.getDelegate());
		try {
			ftSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			log.error("Was interrupted during indexing", e);
		}
	}

	@SuppressWarnings("unchecked")
	List<Book> search(String searchQuery) throws ParseException {
		return searchQuery(searchQuery).getResultList();
	}

	private Query searchQuery(String searchQuery) throws ParseException {
		String[] bookFields = { "title", "subtitle", "authors.name", "publicationDate", "document" };

		// lucene part
		Map<String, Float> boostPerField = new HashMap<String, Float>(4);
		boostPerField.put(bookFields[0], (float) 4);
		boostPerField.put(bookFields[1], (float) 3);
		boostPerField.put(bookFields[2], (float) 4);
		boostPerField.put(bookFields[3], (float) .5);

		FullTextEntityManager ftEm = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		Analyzer customAnalyzer = ftEm.getSearchFactory().getAnalyzer("customanalyzer");
		QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_34, bookFields, customAnalyzer, boostPerField);

		org.apache.lucene.search.Query luceneQuery;
		luceneQuery = parser.parse(searchQuery);

		return ftEm.createFullTextQuery(luceneQuery, Book.class);
	}

	Author createAuthor(String name) {
		Author author = new Author();
		author.setId(dbId++);
		author.setName(name);
		em.persist(author);
		em.flush();
		return author;
	}
}
