package Model;

import java.util.ArrayList;

/**
 * 
 * @author Eknarin b5510546239
 *channel class
 */
public class Channel {
	
	private String title;
	private String link;
	private String description;
	private ArrayList<Item> item;
	
	/**
	 * get item in arraylist
	 * @return the arraylist item
	 */
	public ArrayList<Item> getItem() {
		return item;
	}

	/**
	 * set the item
	 * @param item
	 */
	public void setItem(ArrayList<Item> item) {
		this.item = item;
	}

	/**
	 * get channel title
	 * @return channel title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * set channel title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * get channel link
	 * @return channel link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * set channel link
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * get channel description
	 * @return channel description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * set channel description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
