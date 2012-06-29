package fr.ramiere.hibernate.search;

import static com.google.common.base.Throwables.propagate;
import static org.apache.tika.io.IOUtils.closeQuietly;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.WriteOutContentHandler;
import org.hibernate.search.bridge.StringBridge;

/**
 * Convert a byte array to its string representation using <a href="http://tika.apache.org/">Tika</a>
 * 
 * @see AutoDetectParser
 * @see http://tika.apache.org/
 */
public class ByteArrayBridge implements StringBridge {
	@Override
	public String objectToString(Object object) {
		byte[] data = (byte[]) object;
		StringWriter writer = new StringWriter();
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(data);
			new AutoDetectParser().parse(is, //
					new WriteOutContentHandler(writer), //
					new Metadata(), //
					new ParseContext());
			return writer.toString();
		} catch (Exception e) {
			throw propagate(e);
		} finally {
			closeQuietly(is);
		}
	}
}
