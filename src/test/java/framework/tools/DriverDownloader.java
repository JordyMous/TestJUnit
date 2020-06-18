package framework.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;

/**
 * Enables user to let framework download missing drivers. 
 * When a new version of a webdriver is available, only the url needs to be adjusted. 
 *
 */
public class DriverDownloader {
	
	private String zipName = "driver.zip";

	private String fileUrl;
	private String filePath;
	private FileInputStream fis;
	private ZipInputStream zipIs;
	private ZipEntry zEntry;
	
	public DriverDownloader(String fileUrl, String filePath) throws MalformedURLException, IOException {
		this.fileUrl = fileUrl;
		this.filePath = filePath;
		this.download();
		this.unzipFile();
	}
	
	/**
	 * Method to download a Driver from the given fileUrl and places it at the filePath
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private void download() throws MalformedURLException, IOException {
		FileUtils.copyURLToFile(new URL(fileUrl), new File(filePath + "/" + zipName));
	}
	
	/**
	 * Method to unzip the zip file, dowloaded above
	 */
	private void unzipFile() {   		
        try {
            fis = new FileInputStream(filePath + "/" + zipName);
            zipIs = new ZipInputStream(new BufferedInputStream(fis));
            
            while((zEntry = zipIs.getNextEntry()) != null){
                try {
                    byte[] tmp = new byte[4*1024];
                    FileOutputStream fos = null;
                    String opFilePath = filePath + "/" + zEntry.getName();
                    fos = new FileOutputStream(opFilePath, false);
                    int size = 0;
                    
                    while((size = zipIs.read(tmp)) != -1) {
                        fos.write(tmp, 0 , size);
                    }
                    
                    fos.flush();
                    fos.close();
                    
                    /**
                     * In case when the tests are running on a Linux-system, the unzipped drivers need the correct permission first
                     */
                    if (System.getProperty("os.name").toLowerCase().contains("linux")) {
	                    try {
	                    	File file = new File(opFilePath);
	                		file.setWritable(true);
	                		file.setReadable(true);
	                		file.setExecutable(true);
	                    } catch (Exception e) {
	                    	e.printStackTrace();
	                    	System.out.println("Something went wrong giving access to the driver.");
	                    }
                    }
                    
                } catch(Exception e) {
                	e.printStackTrace();
                	System.out.println("Unzipping driver failed.");
                }
            }
            zipIs.close();
            
            //Delete the zip folder
            File zip = new File(filePath + "/" + zipName);
            zip.delete();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        	System.out.println("Can't find the zip-file.");
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
}
