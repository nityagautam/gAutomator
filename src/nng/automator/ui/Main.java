package nng.automator.ui;

// Import Sction
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import nng.automator.ui.actions.Listener;
import nng.automator.ui.config.Configurations;

// Class
public class Main extends Settings {
	
	// Globals
	//=========
	// Main Frame and Work Panel
	public static JFrame mainFrame = null;
	public static JPanel workPanel = null;
	// TextArea for logging on mainframe
	public static JTextArea logTxtArea = null;
	
	/**
	 * Constructor
	 * =============
	 */
	public Main() {
		//Initialize a main Frame and set up the window.
		mainFrame = new JFrame("Automator ");
		mainFrame.setAlwaysOnTop(true);
		//mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(Configurations.WIDTH+50, Configurations.HEIGHT));
		//mainFrame.setLocationRelativeTo(null);
		
		// Initialize the working panel
		workPanel = new JPanel();
		// Adding font and color
		workPanel.setBackground(Color.GRAY);
		workPanel.setForeground(Color.CYAN);
		// Add to main frame to the east/center
		mainFrame.getContentPane().add(workPanel, BorderLayout.CENTER);
	}
	
	public void startFrame() {
		// Add the menubar
		addMenuBar();
		
		// Add the content to main frame
		workPanel();
		// Add the info panel to main frame
		infoPanel();
		
		//Display the window.
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		// Start the log
		Listener.log("Application Initiated");
	}
	
	protected static void addMenuBar() {
		// Create MenuBar and Menus
		JMenuBar menuBar = new JMenuBar();
		JMenu menuAction = new JMenu("Action");
		JMenu menuHelp = new JMenu("Help");
		JMenu menuDemo = new JMenu("Demo");
		
		// Create and Add Menu item to Menu
		JMenuItem demo = new JMenuItem("Start Demo Project");
		JMenuItem explorer = new JMenuItem("Explorer");
		JMenuItem settings = new JMenuItem("Settings");
		JMenuItem systemInfo = new JMenuItem("System Info");
		JMenuItem about = new JMenuItem("About");
		// Add menu items to the respected menus
		// Adding to Action Menu
		menuAction.add(explorer); menuAction.addSeparator();
		menuAction.add(settings); menuAction.addSeparator();
		// Adding to Help Menu
		menuHelp.add(about); menuHelp.addSeparator();
		menuHelp.add(systemInfo); menuHelp.addSeparator();
		// Adding to Demo menu
		menuDemo.add(demo); menuDemo.addSeparator();
		
		// Add menu to menu bar
		menuBar.add(menuAction); 
		menuBar.add(menuHelp);
		menuBar.add(menuDemo);
		
		// Menubar settings
		//menuBar.setBackground(Color.BLACK);
		//menuBar.setForeground(Color.WHITE);
		//menuAction.setForeground(Color.WHITE); menuHelp.setForeground(Color.WHITE);
		
		// Add MenuEvent to the menu item buttons
		about.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { Listener.performAbout(mainFrame); Listener.log("Clicked on About menu."); } });
		explorer.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { Main.explorerWindow(); Listener.log("Clicked on Explorer menu to choose file(s), SELECTED: "+ Configurations.SELECTED_FILE_PATH + ", Explorer Status: " + Configurations.EXPLORER_RETURN_STATUS); }});
		settings.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { Main.settingsWindow(); Listener.log("Clicked on Settings menu."); }});
		systemInfo.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { Main.systemInfoMsgBox(Listener.performSystemInfo(), "SYSTEM ENV"); Listener.log("Clicked on System info. menu."); } });
		demo.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { Listener.log("Initiating Demo Project ...."); } });
		
		// Add menubar to mainFrame
		mainFrame.getContentPane().add(menuBar, BorderLayout.NORTH);
	}
	
	protected static void workPanel() {
		// Add other Components to this setting window
			// Tabbed Pane
			JTabbedPane tabbedPaneForWorkPanel = new JTabbedPane();
			tabbedPaneForWorkPanel.setBounds(0, 0, Configurations.WIDTH-50, Configurations.HEIGHT);
			// TextArea for log and Notes
			JTextArea noteTxtArea = new JTextArea(":: Your Notes :: \n", Configurations.ROW, Configurations.COLUMN); noteTxtArea.setEditable(true);
			logTxtArea = new JTextArea(":: Logs ::\n", Configurations.ROW, Configurations.COLUMN); logTxtArea.setEditable(false);
			
			// Adding it to JScrollPane 
	        JScrollPane scrollPaneForLog = new JScrollPane(logTxtArea);
	        JScrollPane scrollPaneForNote = new JScrollPane(noteTxtArea);
	        
			// JPanels
			JPanel notePanel = new JPanel();
			JPanel logPanel = new JPanel();
			// Add text area to panel
			notePanel.add(scrollPaneForNote);
			logPanel.add(scrollPaneForLog);
			// Add comp to tab pane
			tabbedPaneForWorkPanel.add("Logs", logPanel);
			tabbedPaneForWorkPanel.add("Notes", notePanel);
			
		// Add to Frame
			workPanel.add(tabbedPaneForWorkPanel);
	}
	
	protected static void infoPanel() {
		//Create an information panel along with a label for date and time
		JPanel infoPanel = new JPanel();
		// Set layout for infoPanel
		infoPanel.setLayout(new GridLayout(1, 3));
		// Setting panel background color
		infoPanel.setBackground(Color.DARK_GRAY);
		
		// "  Not Ready", "  Ready", "  In Progress"
		//JLabel statusLabel = new JLabel(" Not Ready ", SwingConstants.LEFT); statusLabel.setForeground(Color.YELLOW);
		JLabel statusLabel = new JLabel(" Ready ", SwingConstants.LEFT); statusLabel.setForeground(Color.GREEN);
		//JLabel statusLabel = new JLabel(" In Progress ", SwingConstants.LEFT); statusLabel.setForeground(Color.RED);
		JLabel authorLabel = new JLabel(" NITYA NARAYAN GAUTAM ", SwingConstants.RIGHT);
		authorLabel.setForeground(Color.GRAY);
		
		
		infoPanel.add(statusLabel);
		infoPanel.add(new JLabel(""));
		infoPanel.add(authorLabel);
		
		// Add to main frame
		mainFrame.getContentPane().add(infoPanel, BorderLayout.SOUTH);
	}
	
	protected static void systemInfoMsgBox(String[][] sysData, String title) {
		// Column Names to be displayed in the JTable 
        String[] columnNames = { "ENV VARIABLES", "VALUES" }; 
          
        // Initializing the JTable // keyValue, columnNames
        JTable envTable = new JTable(sysData, columnNames); 
        envTable.setBounds(30, 40, 200, 300); 
        
        // Adding it to JScrollPane 
        JScrollPane scrollPaneForTable = new JScrollPane(envTable); 
        
		// display them in a message dialog
	    JOptionPane.showMessageDialog(mainFrame, scrollPaneForTable, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	// Create a separate GUI for setting
	protected static void settingsWindow() {
		// Frame for Settings
		JFrame settingsFrame = new JFrame("Automator > Settings");
		settingsFrame.setAlwaysOnTop(true);
		settingsFrame.setResizable(false);
		settingsFrame.setUndecorated(true); settingsFrame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		settingsFrame.setBounds(100, 100, Configurations.WIDTH, Configurations.HEIGHT);
		settingsFrame.setPreferredSize(new Dimension(Configurations.WIDTH, Configurations.HEIGHT));
		settingsFrame.setMaximumSize(new Dimension(Configurations.WIDTH, Configurations.HEIGHT));
		
		// Add other Components to this setting window
			// Tabbed Pane
			JTabbedPane tabbedPane = new JTabbedPane();
			// TextArea
			JTextArea txtArea = new JTextArea("Setting goes here ...", Configurations.ROW, Configurations.COLUMN);
			// JPanels
			JPanel settingPanelOne = new JPanel();
			// Add text area to panel
			settingPanelOne.add(txtArea);
			// Add comp to tab pane
			tabbedPane.add("Settings",settingPanelOne);
			// JButton
			JButton btnConfirm = new JButton("CONFIRM And CLOSE");
			btnConfirm.setBounds(0, 0, 50, 15);
		// Add to Frame
			settingsFrame.add(tabbedPane);
			settingsFrame.getContentPane().add(btnConfirm, BorderLayout.SOUTH);
			
		// Visualize
		settingsFrame.setLocationRelativeTo(Main.mainFrame);
		settingsFrame.setVisible(true);
	}
	
	// Create a separate GUI for setting
	protected static void explorerWindow() {
		// Frame for Settings
		JFrame explorerFrame = new JFrame("Automator > Explorer");
		explorerFrame.setAlwaysOnTop(true);
		explorerFrame.setResizable(false);
		explorerFrame.setUndecorated(true); explorerFrame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		explorerFrame.setBounds(100, 100, Configurations.WIDTH/2, Configurations.HEIGHT/2);
		explorerFrame.setPreferredSize(new Dimension(Configurations.WIDTH/2, Configurations.HEIGHT/2));
		explorerFrame.setMaximumSize(new Dimension(Configurations.WIDTH/2, Configurations.HEIGHT/2));
		
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(Main.mainFrame);
		
		JTextArea ta1 = new JTextArea(20, 60);
		ta1.setText(ta1.getText()+"\n "+returnVal+"\n "+fileChooser.getSelectedFile());
		
		// Set the selected file and return status to global config
		Configurations.EXPLORER_RETURN_STATUS = returnVal;
		Configurations.SELECTED_FILE_PATH = fileChooser.getSelectedFile().getAbsolutePath();
		
		// Visualize
		explorerFrame.add(ta1);
		explorerFrame.setLocationRelativeTo(Main.mainFrame);
		explorerFrame.setVisible(true);
	}
}
