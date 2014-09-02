package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
/**
 * 
 * @author  Eknarin b5510546239
 *item class
 */
public class Item {
	private String title;
	private String description;
	private String link;
	
	/**
	 * get item title
	 * @return item title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * set item title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * get item description
	 * @return item description
	 */
	public String getDescribtion() {
		return description;
	}
	
	/**
	 * set item description
	 * @param description
	 */
	public void setDescribtion(String description) {
		this.description = description;
	}
	
	/**
	 * get item link
	 * @return item link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * set item link
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * convert to String
	 * @return a item title in String type
	 */
	public String toString() {
		return title;
	}
	
}
