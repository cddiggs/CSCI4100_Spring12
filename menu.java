import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class menu {
    public static Scanner scan = new Scanner(System.in);
    public static String testname, file;
    public static String[] databaseList; 
    public static displayQuestions dQ;
    public static addOrRemoveDB addorremoveDB;




	public static void main(String args[]) throws InterruptedException, IOException, ParserConfigurationException, TransformerException, SAXException {
		System.out.println("Would you like to use:");
        System.out.println("a. GUI Menu");
        System.out.println("b. Command Line Menu:");
    	String option = scan.nextLine();
    	
    	if (option.charAt(0)=='a'||option.charAt(0)=='A'){
    		GUIMenu menu = new GUIMenu();
    	}
    	else if (option.charAt(0)=='b'||option.charAt(0)=='B'){
    		
            commandOptions();
    	}
    	else         System.out.println("Invalid Option");

	}
	
	public static void commandOptions() throws InterruptedException, IOException, ParserConfigurationException, TransformerException, SAXException {

        System.out.println("TEST GENERATION PROGRAM");
		System.out.println("Please select an option to choose from:");
		System.out.println("a. Make a PDF Test");
		System.out.println("b. Make a HTML Test");
		System.out.println("c. Make a Jeopardy Board");
		System.out.println("d. Add or remove a Database from the List");
		System.out.println("e. Add or edit questions in a Database");

		String option = scan.nextLine();
    	
    	if (option.charAt(0)=='a'||option.charAt(0)=='A'){
    		makeTest(true);//true to make pdf
    	}
    	else if (option.charAt(0)=='b'||option.charAt(0)=='B'){
    		makeTest(false);//false to make html
    	}
    	else if (option.charAt(0)=='c'||option.charAt(0)=='C'){
    		//makeJeopardBoard(): function to create jeopardy board here
    	}
    	else if (option.charAt(0)=='d'||option.charAt(0)=='D'){
    		addOrRemoveDatabase();
    	}
    	else if (option.charAt(0)=='e'||option.charAt(0)=='E'){
    		addOrEditQuestions();
    	}
    	else         System.out.println("Invalid Option");

	}
	
	public static void makeTest(boolean trueOrFalse) throws IOException, InterruptedException {
        System.out.println("Please enter the name of the Test (alphanumeric characters and spaces only):");
        testname = scan.nextLine();
        testname = testname.replaceAll(" ", "_");
		
		
		System.out.println("Please select a Database from the list:");
		System.out.println("Here is the current list of Databases:");
		addorremoveDB= new addOrRemoveDB();
		String[] databaseList = addorremoveDB.displayDBList();
		for (int i =1; i<=databaseList.length; i++){
			if (databaseList[i]==null)
				break;
			System.out.println(i+": "+ databaseList[i]);
		}
		int fileNumber;
		fileNumber=scan.nextInt();
		if (databaseList[fileNumber]== null){
			System.out.println("Invalid Option");
			System.exit(0);

		}
		else file=databaseList[fileNumber];
		
		System.out.println("Would you like to:");
		System.out.println("a. Randomly select Questions from the Database?");
		System.out.println("b. Specifically select Questions from the Database?");
		
		String option = scan.next();
		  	if (option.charAt(0)=='a'||option.charAt(0)=='A'){
	    		randomQuestions(trueOrFalse);
	    	}
		  	else if (option.charAt(0)=='b'||option.charAt(0)=='B'){
    		specificQuestions(trueOrFalse);

		  	}
	  	
		System.out.println("PDF File Generated!");
		
	}

	public static void randomQuestions(boolean trueOrFalse){
		if (trueOrFalse==true){
		//execute function to add random questions via question IDs to pdf
			}
		else {//execute function to add random specific questions via question IDs to html
			}
	}
		
	public static void specificQuestions(boolean trueOrFalse) throws InterruptedException, IOException{
		System.out.println("Would you like for the questions to be displayed in your default browser? (y/n)");
		String option = scan.next();
	  	if (option.charAt(0)=='y'||option.charAt(0)=='Y'){
    		dQ=new displayQuestions(testname, file);
    		dQ.display();
    	}
    	else {	}
		System.out.println("Enter the number of questions you would like to add:");
		int numberOfQuestions = scan.nextInt();
		int[] questionIDs;
		questionIDs = new int[numberOfQuestions];
		System.out.println("Enter the ID numbers of the " +numberOfQuestions+ " questions you would like to add:");
		for (int i=0;i<numberOfQuestions;i++){
			questionIDs[i]=scan.nextInt();
			}
		if (trueOrFalse==true){
		//execute function to add specific questions via question IDs to pdf
			}
		else {//execute function to add specific questions via question IDs to html
			}
		

	}

	public static void addOrRemoveDatabase() throws IOException, InterruptedException, ParserConfigurationException, SAXException, TransformerException{
			System.out.println("Here is the current list of Databases:");
			addorremoveDB= new addOrRemoveDB();
			String[] databaseList = addorremoveDB.displayDBList();
			for (int i =1; i<=databaseList.length; i++){
				if (databaseList[i]==null)
					break;
				System.out.println(i+": "+ databaseList[i]);
			}
			
			System.out.println("Would you like to:");
			System.out.println("a. Add a Database to the list?");
			System.out.println("b. Remove a Database from the list?");
			String option = scan.nextLine();
			if (option.charAt(0)=='a'||option.charAt(0)=='A'){
				System.out.println("Please type the path to the new Database you would like to add from the list:");
				String databasePath=scan.nextLine();
				addorremoveDB= new addOrRemoveDB();
				addorremoveDB.addDB(databasePath);
				System.out.println("You have successfully added a new Database to the list");

				}
		  	else if (option.charAt(0)=='b'||option.charAt(0)=='B'){
				System.out.println("Please select a Database you would like to remove from the list");

				int fileNumber=scan.nextInt();
				if (databaseList[fileNumber]== null){
					System.out.println("Invalid Option");
				}
				else{
					String lineToRemove = databaseList[fileNumber];
					addorremoveDB= new addOrRemoveDB();
					addorremoveDB.removeDB(lineToRemove);
					
				}

		  	}
			else 		System.out.println("Invalid Option");
	
			}
	
	public static void addOrEditQuestions() throws TransformerException, ParserConfigurationException, IOException, InterruptedException, SAXException {
		System.out.println("Please select a Database from the list:");
		System.out.println("Here is the current list of Databases:");
		addorremoveDB= new addOrRemoveDB();
		String[] databaseList = addorremoveDB.displayDBList();
		for (int i =1; i<=databaseList.length; i++){
			if (databaseList[i]==null)
				break;
			System.out.println(i+": "+ databaseList[i]);
		}
		
		int fileNumber;	
		String database1="";

		fileNumber=scan.nextInt();
		if (databaseList[fileNumber]== null){
			System.out.println("Invalid Option");
			System.exit(0);

		}
		else
			database1=databaseList[fileNumber];
		
		
		System.out.println("Would you like to:");
		System.out.println("a. Add a question to the Database?");
		System.out.println("b. Edit a quesion from the Database?");
		System.out.println("c. Remove a quesion from the Database?");

		String option = scan.next();
		
		if (option.charAt(0)=='a'||option.charAt(0)=='A'){
		  	addOrEditQuestionsFromDB add = new addOrEditQuestionsFromDB(databaseList[fileNumber]);
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
			System.out.println("Enter the Latex/HTML Question:");
			String lQuestion=scan.nextLine();
			System.out.println("Enter the Jeopardy Question:");
			String jQuestion=scan.nextLine();
			System.out.println("Enter the Jeopardy Answer:");
			String jAnswer=scan.nextLine();
		  	add.addNewElement(subjectName, sectionNumber, topicName, difficultyNumber, instruction, lQuestion, jQuestion, jAnswer);

		}
		else if (option.charAt(0)=='b'||option.charAt(0)=='B'){
			System.out.println("Would you like for the questions to be displayed in the Firefox browser? (y/n)");
			String option2 = scan.next();
		  	if (option2.charAt(0)=='y'||option2.charAt(0)=='Y'){
	    		dQ=new displayQuestions("display", databaseList[fileNumber]);
	    		dQ.display();
	    	}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse(new File(databaseList[fileNumber]));
		  	addOrEditQuestionsFromDB edit = new addOrEditQuestionsFromDB(databaseList[fileNumber]);
		  	String tagname;
		  	String id="";
		  	String newInfo="";
			System.out.println("Type the id number of the question you would like to edit, 1 - " + document.getElementsByTagName("id").getLength()+": ");
			id+=scan.nextInt();
			System.out.println("Choose the tagname of the question you would like to edit: " +
					"\n a. id" +
					"\n b. subject" +
					"\n c. section" +
					"\n d. topic" +
					"\n e. difficulty" +
					"\n f. latex_instruction" +
					"\n g. latex_q" +
					"\n h. jeopardy_q" +
					"\n i. jeopardy_a");
			tagname=scan.next();
			if (tagname.charAt(0)=='a'||tagname.charAt(0)=='A')
				tagname="id";
			else if (tagname.charAt(0)=='b'||tagname.charAt(0)=='B')
				tagname="subject";
			else if (tagname.charAt(0)=='c'||tagname.charAt(0)=='C')
				tagname="section";
			else if (tagname.charAt(0)=='d'||tagname.charAt(0)=='D')
				tagname="difficulty";
			else if (tagname.charAt(0)=='e'||tagname.charAt(0)=='E')
				tagname="topic";
			else if (tagname.charAt(0)=='f'||tagname.charAt(0)=='F')
				tagname="latex_instruction";
			else if (tagname.charAt(0)=='g'||tagname.charAt(0)=='G')
				tagname="latex_q";
			else if (tagname.charAt(0)=='h'||tagname.charAt(0)=='H')
				tagname="jeopardy_q";
			else {		
				System.out.println("Invalid Option ");
				System.exit(0);
			}

			
			System.out.println("Type the new content of the \"" +tagname+"\" tagname: ");
			newInfo+=scan.next();


		  	edit.editQuestion(id,tagname,newInfo);
		  	
			if(tagname=="id"&&Integer.parseInt(id)<Integer.parseInt(newInfo)){
			  	for(int j=Integer.parseInt(id)+1; j<=Integer.parseInt(newInfo); j++){
				edit.editQuestion(Integer.toString(j), "id", Integer.toString(j-1));
			}}
			else if (tagname=="id"&&Integer.parseInt(newInfo)<Integer.parseInt(id)){
			  	for(int j=Integer.parseInt(newInfo); j<Integer.parseInt(id); j++){
						System.out.println(document.getElementsByTagName(tagname).getLength()-j+", "+Integer.parseInt(document.getElementsByTagName(tagname).item(Integer.parseInt(id)-1).getTextContent()));
				edit.editQuestion(Integer.toString(j), "id", Integer.toString(j+1));
			}}
			edit.sortQuestions();
		}
		else if (option.charAt(0)=='c'||option.charAt(0)=='C'){
		  	String id;

			System.out.println("Type the id number of the question you would like to remove: ");
			id=scan.next();
			addOrEditQuestionsFromDB delete = new addOrEditQuestionsFromDB(databaseList[fileNumber]);
			delete.removeElement(id);
			delete.editQuestionID(id);


		}
		else 		System.out.println("Invalid Option");


}}


