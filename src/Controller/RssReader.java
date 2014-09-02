package Controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Model.Rss;

/**
 * 
 * @author Eknarin b5510546239
 * reader of rss
 *
 */
public class RssReader {

	private static Rss rss;
	private static URL urlInput;
	private static int i = 1;

	/**
	 * get rss
	 * @return rss
	 * @throws JAXBException
	 */
	public Rss getRss() throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance(Rss.class);
		Unmarshaller unmarshaller = ctx.createUnmarshaller();
		Object obj = unmarshaller.unmarshal(urlInput);
		rss = (Rss) obj;
		return rss;
	}
	
	/**
	 * set the url
	 * @param url for input
	 * @throws MalformedURLException
	 */
	public void setURL(String url) throws MalformedURLException {
		urlInput = new URL(url);
	}

}
