package nng.utils;

// Import Section
import java.util.Map;

// Class
public class Utils {

	/**
	 * System Invironemnts
	 * @author Ashutosh Mishra
	 * @return Map<String, String>
	 */
	public static Map<String, String> getSystemInfo() { return System.getenv(); }
	
	public static void pauseFor(int timeInMillis) throws Exception {Thread.sleep(timeInMillis);}
}
