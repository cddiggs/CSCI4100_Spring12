import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

		File f = new File(databasePath);
		if(!f.exists() && !f.isDirectory()) {
			writer = new BufferedWriter(new FileWriter(databasePath,true));
			writer.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?><!-- XML Database - Math test question bank v0.2 29 March 2014 --><!--ENTITY infin \"&#8734;\"--><!--ENTITY radic \"&#8730;\"--><TestBank>");
			writer.newLine();
			writer.append("</TestBank>");
			writer.newLine();

			writer.close();

			addOrEditQuestionsFromDB add = new addOrEditQuestionsFromDB(databasePath);
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the Subject:");
			String subjectName=scan.nextLine();
			System.out.println("Enter the Section Number:");
			String sectionNumber=scan.nextLine();
			System.out.println("Enter the Topic:");
			String topicName=scan.nextLine();
			System.out.println("Enter the Difficulty (1-10):");
			String difficultyNumber=scan.nextLine();
			System.out.println("Enter the Instruction:");
			String instruction=scan.nextLine();
			System.out.println("Enter the Question Graph Path:");
			String graph=scan.nextLine();
			System.out.println("Enter the Answer:");
			String latex_a=scan.nextLine();
			System.out.println("Enter the Answer Graph Path:");
			String graph_a=scan.nextLine();
			System.out.println("Enter the Latex Question:");
			String lQuestion=scan.nextLine();
			System.out.println("Enter the Jeopardy Question:");
			String jQuestion=scan.nextLine();
			System.out.println("Enter the Jeopardy Answer:");
			String jAnswer=scan.nextLine();
			add.addNewElement(subjectName, sectionNumber, topicName, difficultyNumber, instruction, graph, latex_a, graph_a, lQuestion, jQuestion, jAnswer);

		}
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
