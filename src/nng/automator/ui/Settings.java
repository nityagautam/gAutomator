package nng.automator.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Settings {

	// Create setting GUI
	protected static void settings(boolean isVisible) {
		if(isVisible) {
			// TabbedPane
			JTabbedPane tp = new JTabbedPane();  
			// Create other content to add to the panel
			JTextArea txtArea = new JTextArea("Something goes here ...");
			txtArea.setEditable(false);
			// Create Panels to attach in each tab
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			p1.add(txtArea);
					
		    //tp.setBounds(50,50,200,200);  
		    tp.add("main",p1);  
		    tp.add("visit",p2);
		    
		    // Finally add to passed panel and main frame
		    Main.workPanel.add(tp);
		    // Add to main frame to the east/center
			Main.mainFrame.getContentPane().add(Main.workPanel, BorderLayout.CENTER);
		    
		}
	}
}
