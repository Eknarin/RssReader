package View;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.bind.JAXBException;

import Controller.RssReader;
import Model.Item;

/**
 * 
 * @author  Eknarin b5510546239
 * GUI rss reader
 */
public class ReaderUI extends JFrame implements Runnable{

	private JLabel header;
	private JLabel itemHyperlink;
	private JLabel channelDescribtion;
	private JLabel channelTitle;
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JPanel mainPanel;
	private JPanel resultHeaderPanel;
	private JPanel itemPanel;
	private JPanel bottomRightPanel;
	private JScrollPane scrollPane;
	private JScrollPane describtionScrollPane;
	private JTextField urlField;
	private JTextArea itemDescribtion;
	private JButton okButton;
	private JList<Item> result;
	private RssReader reader;
	private String link;
	
	/**
	 * set the obj for the rss reader
	 * @param Rss reader obj.
	 */
	public void setRssReader( RssReader reader) {
		this.reader = reader;
	}
	
	/**
	 * constructor
	 */
	public ReaderUI() {
		super("RSS Reader");
		initializeComponent();
	}

	/**
	 * create all components
	 */
	public void initializeComponent() {
		createAll();
	}

	/**
	 * method for create components
	 */
	public void createAll() {
		createTop();
		createCenter();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(topPanel);
		mainPanel.add(centerPanel);
		this.getContentPane().add(mainPanel);		
	}

	/**
	 * create the top section of GUI
	 */
	public void createTop() {
		header = new JLabel("Please put your URL in textfield");	
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension( 700, 25));
		topPanel.add(header);
	}
	
	/**
	 * create the center section of GUI
	 */
	public void createCenter() {
		urlField = new JTextField();
		urlField.setPreferredSize(new Dimension( 600, 20));
		urlField.addKeyListener( new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					String url = urlField.getText();
					try {
						reader.setURL( url );
						createResultHeader();
						createBottom();
						mainPanel.add(bottomPanel);
						pack();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					} catch (JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		okButton = new JButton("confirm");
		okButton.setPreferredSize(new Dimension( 100, 20));
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		centerPanel.setPreferredSize(new Dimension(800, 25));
		centerPanel.add(urlField);
		centerPanel.add(okButton);

		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String url = urlField.getText();
				try {
					reader.setURL( url );
					createResultHeader();
					createBottom();
					mainPanel.add(bottomPanel);
					pack();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	/**
	 * create the part of bottom GUI
	 * it is the channel title and channel description 
	 * @throws JAXBException
	 */
	public void createResultHeader () throws JAXBException {
		channelTitle = new JLabel();
		channelTitle.setText( reader.getRss().getChannel().getTitle());
		channelTitle.setFont( new Font( null, Font.BOLD,30));
		
		channelDescribtion = new JLabel();
		channelDescribtion.setText( reader.getRss().getChannel().getDescription());
		channelDescribtion.setFont( new Font( null, Font.PLAIN, 14));
		
		resultHeaderPanel = new JPanel();
		resultHeaderPanel.setLayout(new BoxLayout(resultHeaderPanel, BoxLayout.Y_AXIS));
		resultHeaderPanel.add(channelTitle);
		resultHeaderPanel.add(channelDescribtion);
		mainPanel.add(resultHeaderPanel);
	}
	
	/**
	 * create bottom section of GUI
	 * divide into 3 parts :  item list, item description (in bottom right panel), item hyper-link (in bottom right panel)
	 */
	public void createBottom() {
		bottomPanel = new JPanel();
		bottomPanel.setLayout( new BoxLayout( bottomPanel, BoxLayout.X_AXIS));
		
		createItemPanel();
		createItemDescribtion();
		createBottomRightPanel();
		bottomPanel.add(itemPanel);
		bottomPanel.add(bottomRightPanel);
	}

	/**
	 * create the part of bottom GUI
	 * describe the item
	 */
	public void createItemDescribtion() {
		itemDescribtion = new JTextArea();
		itemDescribtion.setLineWrap(true);
		itemDescribtion.setWrapStyleWord(true);
	}
	
	/**
	 * part of bottom panel
	 * have 2 things : item description and item hyper-link
	 */
	public void createBottomRightPanel() {
		bottomRightPanel = new JPanel();
		bottomRightPanel.setLayout( new BoxLayout( bottomRightPanel, BoxLayout.Y_AXIS));
		describtionScrollPane = new JScrollPane( itemDescribtion);
		describtionScrollPane.setVisible( true );
		describtionScrollPane.setPreferredSize(new Dimension(400, 100));
		bottomRightPanel.add(describtionScrollPane);
		createItemHyperlink();
		bottomRightPanel.add(itemHyperlink);
	}
	
	/**
	 * create item hyper-link
	 */
	public void createItemHyperlink() {
		itemHyperlink = new JLabel("See more information...");
		itemHyperlink.addMouseListener( new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					try {
						desktop.browse(new URI(link));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}		
				
			}
		});	
	}
	
	/**
	 * show list of items
	 */
	public void createItemPanel() {
		Item[] x;
		try {
			x = new Item[reader.getRss().getChannel().getItem().size()];
			result = new JList<>(reader.getRss().getChannel().getItem().toArray(x));
			
			result.addListSelectionListener( new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					 Item item = (Item) result.getSelectedValue();
					 itemDescribtion.setText( item.getDescribtion());
					 link = item.getLink();
				}
			});
			
			scrollPane = new JScrollPane(result);
			scrollPane.setVisible( true );
			scrollPane.setPreferredSize(new Dimension(400, 500));
			
			itemPanel = new JPanel();
			itemPanel.add( scrollPane );
		} catch (JAXBException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void run() {
		this.pack();
		this.setVisible( true );
		
	}
}
