import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class menu {
    public static Scanner scan = new Scanner(System.in);
    public static String testname, file;
    public static String[] databaseList; 


	public static void main(String args[]) throws InterruptedException, IOException {
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
	
	public static void commandOptions() throws InterruptedException, IOException {
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
		BufferedReader reader = new BufferedReader(new FileReader("./src/database.txt"));
		String line = null;
		int i=1;

		databaseList = new String[20];
		while ((line = reader.readLine()) != null) {
			databaseList[i]=line;
			System.out.println(i+ ": "+line);
			i++;
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
    		displayQuestions();
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
	public static void displayQuestions() throws IOException, InterruptedException {

        LatexFile database1 = null;
    	database1 = new LatexFile(testname + ".tex",file);
    	database1.WriteLatexHead(testname);
        database1.WriteAllLatexQuestions();
		database1.WriteLatexFoot();
		
		
        htmlFile database2 = null;
    	database2 = new htmlFile(file);
    	database2.WriteAllhtmlQuestions();
    	database2.GenerateAllHtml(testname);

    	Process p;
        p = Runtime.getRuntime().exec("firefox " + "./" + testname + "/index.html &");
        p.waitFor();
		
}
	
	public static void addOrRemoveDatabase() throws IOException, InterruptedException{
			System.out.println("Here is the current list of Databases:");
			BufferedReader reader = new BufferedReader(new FileReader("./src/database.txt"));
			String line = null;
			int i=1;
	
			databaseList = new String[20];
			while ((line = reader.readLine()) != null) {
				databaseList[i]=line;
				System.out.println(i+ ": "+line);
				i++;
			}
			
			System.out.println("Would you like to:");
			System.out.println("a. Add a Database to the list?");
			System.out.println("b. Remove a Database from the list?");
			String option = scan.nextLine();
			if (option.charAt(0)=='a'||option.charAt(0)=='A'){
				System.out.println("Please type the path to the new Database you would like to add from the list:");
				String databasePath=scan.nextLine();
				BufferedWriter writer = new BufferedWriter(new FileWriter("./src/database.txt",true));
			
				writer.append(databasePath);
				writer.newLine();
				writer.close();
				System.out.println("You have successfully added a new Database to the list:");
				}
		  	else if (option.charAt(0)=='b'||option.charAt(0)=='B'){
				System.out.println("Please select a Database you would like to remove from the list");

				int fileNumber=scan.nextInt();
				if (databaseList[fileNumber]== null){
					System.out.println("Invalid Option");
				}
				else{
					BufferedReader reader2 = new BufferedReader(new FileReader("./src/database.txt"));
					BufferedWriter writer = new BufferedWriter(new FileWriter("./src/tempDatabase.txt",true));

					String lineToRemove = databaseList[fileNumber];
					String currentLine;

					while((currentLine = reader2.readLine()) != null) {
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
				}
			
			
		  	}
			else 		System.out.println("Invalid Option");
	
			}
	
	public static void addOrEditQuestions() throws IOException, InterruptedException{
		System.out.println("Please select a Database from the list:");
		BufferedReader reader = new BufferedReader(new FileReader("./src/database.txt"));
		String line = null;
		int i=1;

		databaseList = new String[20];
		while ((line = reader.readLine()) != null) {
			databaseList[i]=line;
			System.out.println(i+ ": "+line);
			i++;
		}
		int fileNumber;
		fileNumber=scan.nextInt();
		if (databaseList[fileNumber]== null){
			System.out.println("Invalid Option");
			System.exit(0);

		}
		
		
		System.out.println("Would you like to:");
		System.out.println("a. Add a question to the Database?");
		System.out.println("b. Edit a quesion from the Database?");
		System.out.println("c. Remove a quesion from the Database?");

		String option = scan.next();
		if (option.charAt(0)=='a'||option.charAt(0)=='A'){
			//executes function to add blank tags in xml
			//executes function to edit the blank tags
		}
		else if (option.charAt(0)=='b'||option.charAt(0)=='B'){
			System.out.println("Would you like for the questions to be displayed in the Firefox browser? (y/n)");
			String option2 = scan.next();
		  	if (option2.charAt(0)=='y'||option2.charAt(0)=='Y'){
		  		testname="display";
		  		file=databaseList[fileNumber];
	    		displayQuestions();
	    	}
	    	else {	}
			//executes function to edit tags of an existing question, refer to question by ID
		}
		else if (option.charAt(0)=='c'||option.charAt(0)=='C'){
			//executes function to remove entire question from database
		}
		else 		System.out.println("Invalid Option");

	}
}

