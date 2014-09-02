package Model;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement

/**
 * 
 * @author Eknarin b5510546239
 * rss class
 *
 */
public class Rss {
	private Channel channel;
	
	/**
	 * get the channel
	 * @return channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * set the channel
	 * @param channel
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
