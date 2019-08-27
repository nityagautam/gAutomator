package nng.automator.ui.actions;

import java.awt.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;

import nng.automator.ui.Main;

public class Listener {
	
	// Action > System Information
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[][] performSystemInfo() {
        // HashMaps
        //HashMap sysProps = new HashMap(System.getProperties());
        HashMap sysEnv = new HashMap(System.getenv());
        
        // Creating and collecting data in array format
        String[][] envArrData = new String[sysEnv.size()][2];
        Iterator entriesIterator = sysEnv.entrySet().iterator();
        int i = 0;
        while(entriesIterator.hasNext()){
        	HashMap.Entry mapping = (HashMap.Entry) entriesIterator.next();
        	envArrData[i][0] = mapping.getKey().toString();
        	envArrData[i][1] = mapping.getValue().toString();
        	i++;
        }

		// Return as 2D array format as row containing key value
		return envArrData;
	}
	
	// Help > About MenuItem action
	public static void performAbout(Object obj) {
		javax.swing.JOptionPane.showMessageDialog((Component) obj, "Automator Prototype-A", "About", JOptionPane.INFORMATION_MESSAGE);
	}
	
	// Add logs
	public static void log(String logMessage) {
		// Add the log in to mainframe logger
		Main.logTxtArea.setText(Main.logTxtArea.getText() + "\n ["+(new Date())+"] " + logMessage);
	}
}
