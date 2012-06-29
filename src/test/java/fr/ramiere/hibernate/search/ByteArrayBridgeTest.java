package fr.ramiere.hibernate.search;

import static fr.ramiere.hibernate.search.BookLocation.HIBERNATE_SEARCH;
import static fr.ramiere.hibernate.search.BookLocation.PRIMEFACES_DOC;
import static org.apache.commons.io.FileUtils.readFileToByteArray;
import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Verifies that both the hibernate and the primefaces books can be converted by Tika
 */
public class ByteArrayBridgeTest {

	ByteArrayBridge bridge = new ByteArrayBridge();

	@Test
	public void hibernateSearch() throws Exception {
		assertThat(convert(HIBERNATE_SEARCH)).isNotEmpty();
	}

	@Test
	public void primeFaces() throws Exception {
		assertThat(convert(PRIMEFACES_DOC)).isNotEmpty();
	}

	private String convert(String filename) throws IOException {
		return bridge.objectToString(readFileToByteArray(new File(filename)));
	}
}