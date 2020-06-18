package framework.tools;

/**
 * Will shut down the driver in case of an error during creation that would otherwise keep running in the background. 
 *
 */
public class DriverKiller {
	
	public DriverKiller(String driver) {
		System.out.println("Incorrect driver version.");
		
		try {
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				Runtime.getRuntime().exec("taskkill /IM " + driver + ".exe /F");
			} else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
				Runtime.getRuntime().exec("kill -9 /IM " + driver + " /F");
			} 
			System.out.println("Killing driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to kill drivers.");
		}
	}
}
