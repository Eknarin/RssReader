package Controller;

import View.ReaderUI;

/**
 * 
 * @author Eknarin b5510546239
 *
 */
public class Main {
	
	public static void main(String[] args) {
		RssReader reader = new RssReader();
		ReaderUI readerUI = new ReaderUI();
		readerUI.run();		
		readerUI.setRssReader(reader);
	}
	
}
