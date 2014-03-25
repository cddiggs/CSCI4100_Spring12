import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;


public class addOrRemoveDB {

	/**
	 * This method opens "./src/database.txt"
	 * and copies the available paths to the databases
	 * to an array of Strings called databaseList.
	 * Then database List is returned.
	 * @return databaseList
	 * @throws IOException
	 */
	public String[] displayDBList() throws IOException{ 
		String[] databaseList; 
		BufferedReader reader = new BufferedReader(new FileReader("./src/database.txt"));
		String line = null;
		int i=1;

		databaseList = new String[20];
		while ((line = reader.readLine()) != null) {
			databaseList[i]=line;
			i++;
		}
		reader.close();
		return databaseList;
	}
	/**
	 * This method adds a path to a database to the file "./src/database.txt".
	 * If the xml file does not exit, a template for a new file will be made.
	 *  The path to new database is required to be passed to this method
	 * @param databasePath
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws TransformerException
	 * @throws InterruptedException
	 */
	public void addDB(String databasePath) throws IOException, ParserConfigurationException, SAXException, TransformerException, InterruptedException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("./src/database.txt",true));
		
		writer.append(databasePath);
		writer.newLine();
		writer.close();

	}
	/**
	 * This method removes a path to a database from "./src/database.txt".
	 * The number corresponding to the path of the database
	 * that should be removed is passed to this method.
	 * @param lineToRemove
	 * @throws IOException
	 * @throws InterruptedException
	 */
	
	public void removeDB(String lineToRemove) throws IOException, InterruptedException{
		BufferedReader reader = new BufferedReader(new FileReader("./src/database.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("./src/tempDatabase.txt",true));

		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToRemove)) continue;
		    writer.write(currentLine);
			writer.newLine();					
			}	
		writer.close();
    	Process p;
        p = Runtime.getRuntime().exec("mv ./src/tempDatabase.txt ./src/database.txt");
        p.waitFor();
        reader.close();
	}
}
