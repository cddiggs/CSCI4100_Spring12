/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *this class depends on RetrieveXML class
 * generates latex test from database of questions
 * @author derek
 */
import java.io.*;
import java.util.Formatter;
import java.awt.List;
public class LatexFile {
	private Formatter latex_file_io;
	private File latex_file;
	private int qcount;
	private RetrieveXML XMLretriever;
	protected List LatexQuestions, LatexSolutions;
	/**
	 * Constructor to create latex file for test
	 * @param path the path of the filename for the test's latex file
	 * @param dpath the path to the database file
	 * @throws IOException 
	 */
	public LatexFile(String path, String dpath) throws IOException{
		XMLretriever = new RetrieveXML(dpath);
		latex_file = new File(path);
		if(!latex_file.exists()){
			latex_file.createNewFile();
		}
		latex_file_io = new Formatter(latex_file.getAbsolutePath());
		qcount=1;
	}
	/**
	 * Sets up the header for the test's latex file
	 * @param testname the name of the test
	 */
	public void WriteLatexHead(String testname){
		latex_file_io.format("\\documentclass[11pt,a4paper]{article}\n\\usepackage{amsmath,amsthm,graphicx}\n\n\\newcommand{\\fn}[1]{{\\tt #1}}\n\\newcommand{\\cn}[1]{{\\tt \\char\"5C #1}}\n\n\\title{");
		latex_file_io.format("$\\newline$");
		latex_file_io.format(testname);
		latex_file_io.format("}\n\n\\begin{document}\n\n\\maketitle\n\n");    
	}

	/**
	 * add instructions for problems on the test
	 * @param instructions instructions for problems
	 */
	public void WriteLatexInstructions(String instructions){
		latex_file_io.format("\n\\section{" + instructions + "}\n");
	}

	/**
	 * Writes a question to Latex using a given ID number for PDF test output
	 * Will check if tag name "graph" is not a character "_". If not, it will print out a graph image.
	 * @param IDnumber
	 */
	public void WriteLatexQuestionsID(int IDnumber){
		List questionsbyID = XMLretriever.returnByID(IDnumber);
		LatexQuestions = new List();

		for(int c=0;c<questionsbyID.getItemCount();c++){
			LatexQuestions.add(questionsbyID.getItem(c));


			latex_file_io.format(qcount + ") " + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_instructions"));
			latex_file_io.format("\n\n\\begin{center}$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q") + "$\\end{center}\n\n");
			if (XMLretriever.returnTestData(LatexQuestions.getItem(c), "graph").charAt(0)!='_')
				latex_file_io.format("\\begin{figure}[ht!] \\includegraphics[width=90mm]{" + XMLretriever.returnTestData(LatexQuestions.getItem(c),"graph")+"}\\end{figure}\n");
			latex_file_io.format("\\vfill");
			qcount++;

		}
	}
	/**
	 * Writes all questions of a database to Latex for PDF test output
	 * Will check if tag name "graph" is not a character "_". If not, it will print out a graph image.
	 * @param IDnumber
	 */
	public void WriteEntireLatexQuestions(){
		List allQuestions = XMLretriever.returnAllQuestions();
		LatexQuestions = new List();

		for(int c=0;c<allQuestions.getItemCount();c++){
			LatexQuestions.add(allQuestions.getItem(c));

		}

		for(int c=0;c<allQuestions.getItemCount();c++){
			latex_file_io.format(qcount + ") " + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_instructions"));
			latex_file_io.format("\n\n\\begin{center}$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q") + "$\\end{center}\n\n");
			if (XMLretriever.returnTestData(LatexQuestions.getItem(c), "graph").charAt(0)!='_')
				latex_file_io.format("\\begin{figure}[ht!] \\includegraphics[width=90mm]{" + XMLretriever.returnTestData(LatexQuestions.getItem(c),"graph")+"}\\end{figure}\n");
			latex_file_io.format("\\vfill");
			qcount++;

		}
	}

	/**
	 * Writes the question and solution to Latex using the given ID number for PDF test output.
	 * Will check if tag name "graph" is not a character "_". If not, it will print out a graph image.
	 * Will check if tag name "graph_a" is not a character "_". If not, it will print out a solution graph image.
	 * @param IDnumberSol
	 */
	public void WriteLatexSolution(int IDnumberSol){
		List questionsbyID = XMLretriever.returnByID(IDnumberSol);
		LatexQuestions = new List();

		for(int c=0;c<questionsbyID.getItemCount();c++){
			LatexQuestions.add(questionsbyID.getItem(c));

			latex_file_io.format(qcount + ") " + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_instructions"));
			latex_file_io.format("\n\n\\begin{center}$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q") + "$\\end{center}\n\n");
			if (XMLretriever.returnTestData(LatexQuestions.getItem(c), "graph").charAt(0)!='_')
				latex_file_io.format("\\begin{figure}[ht!] \\includegraphics[width=90mm]{" + XMLretriever.returnTestData(LatexQuestions.getItem(c),"graph")+"}\\end{figure}\n");
			latex_file_io.format("\\vfill");
			if (XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_a").charAt(0)=='_')
				latex_file_io.format("\n\n Solution " + qcount+".)" + "\n\n\\begin{center} No Solution Available\\end{center}\n\n");
			else 
				latex_file_io.format("\n\n Solution " + qcount+".)" + "\n\n\\begin{center}$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_a") + "$\\end{center}\n\n");
			if (XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_a").charAt(0)!='_')
				latex_file_io.format("\n\n Solution Graph \n\n\\begin{figure}[ht!] \\includegraphics[width=50mm]{" + XMLretriever.returnTestData(LatexQuestions.getItem(c),"latex_g")+"}\\end{figure}\n");

			latex_file_io.format("\\vfill");
			qcount++;
		}
	}


	/**
	 * Writes all questions and solutions for every question in a database
	 */
	public void WriteEntireLatexSolutions(){
		List allSolutions = XMLretriever.returnAllSolutions();
		LatexQuestions = new List();

		for(int c=0;c<allSolutions.getItemCount();c++){
			LatexQuestions.add(allSolutions.getItem(c));
		}
		for(int c=0;c<allSolutions.getItemCount();c++){

			latex_file_io.format(qcount + ") " + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_instructions"));
			latex_file_io.format("\n\n\\begin{center}$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q") + "$\\end{center}\n\n");
			if (XMLretriever.returnTestData(LatexQuestions.getItem(c), "graph").charAt(0)!='_')
				latex_file_io.format("\\begin{figure}[ht!] \\includegraphics[width=90mm]{" + XMLretriever.returnTestData(LatexQuestions.getItem(c),"graph")+"}\\end{figure}\n");
			latex_file_io.format("\\vfill");
			if (XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_a").charAt(0)=='_')
				latex_file_io.format("\n\n Solution " + qcount+".)" + "\n\n\\begin{center} No Solution Available\\end{center}\n\n");
			else 
				latex_file_io.format("\n\n Solution " + qcount+".)" + "\n\n\\begin{center}$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_a") + "$\\end{center}\n\n");
			if (XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_a").charAt(0)!='_')
				latex_file_io.format("\n\n Solution Graph: \n\n\\begin{figure}[h!] \\includegraphics[width=50mm]{" + XMLretriever.returnTestData(LatexQuestions.getItem(c),"graph_a")+"}\\end{figure}\n");


			latex_file_io.format("\\vfill");
			qcount++;
		}

	}
	/**
	 * From a given list of question IDs, this method will write only the "latex_q" tag of a question.
	 * This method is used for generating HTML output files. 
	 * The Unix command latex2html will convert these equations into images to be inserted into the HTML file. 
	 * @param html_questions
	 */
	public void WriteLatexSolutions(List html_questions){

		LatexQuestions = new List();

		for(int c=0;c<html_questions.getItemCount();c++){
			LatexQuestions.add(html_questions.getItem(c));

		}

		for(int c=0;c<html_questions.getItemCount();c++){
			latex_file_io.format("\n\n$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q") + "$");
			if (XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_a").charAt(0)!='_')
				latex_file_io.format("\n\n$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_a") + "$");
			else 
				latex_file_io.format("\n\n$\\sqrt{"+c+"}$");

		}
	}
	/**
	 * This method will write only the "latex_q" tag of all the questions in a database.
	 * This method is used for generating HTML output files and displaying questions from a database in Firefox. 
	 * The Unix command latex2html will convert these equations into images to be inserted into the HTML file. 
	 * @param html_questions
	 */
	public void WriteAllLatexQuestions(){
		List allQuestions = XMLretriever.returnAllQuestions();
		LatexQuestions = new List();

		for(int c=0;c<allQuestions.getItemCount();c++){
			LatexQuestions.add(allQuestions.getItem(c));

		}

		for(int c=0;c<allQuestions.getItemCount();c++){
			latex_file_io.format("\n\n$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q") + "$");
		}
	}


	/**
	 * This will write the "latex_q" tag and "latex_a" tag of all questions of a database.
	 * This method is used for generating HTML output files. 
	 * The Unix command latex2html will convert these equations into images to be inserted into the HTML file. 
	 */
	public void WriteAllLatexSolutions(){
		List allSolutions = XMLretriever.returnAllSolutions();
		LatexQuestions = new List();

		for(int c=0;c<allSolutions.getItemCount();c++){
			LatexQuestions.add(allSolutions.getItem(c));

		}

		for(int c=0;c<allSolutions.getItemCount();c++){
			latex_file_io.format("\n\n$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q") + "$");
			if (XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_a").charAt(0)!='_')
				latex_file_io.format("\n\n$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_a") + "$");
			else 
				latex_file_io.format("\n\n$\\sqrt{"+c+"}$");

		}
	}
	/**
	 * Will add questions to the test from a section that are of certain difficulty
	 * and will add no more than questionQuantity number of questions
	 * @param subject course subject title
	 * @param section section of course material
	 * @param difficulty difficulty of questions
	 * @param questionQuantity number of questions
	 */
	public void WriteLatexQuestions(String subject, double section, int difficulty, int questionQuantity){
		List questionsbysubject = XMLretriever.returnByTopic(subject);
		List questionsbysection = XMLretriever.returnBySection(section);
		List questionsbydifficulty = XMLretriever.returnByDifficulty(difficulty);
		List temp_list = new List();
		LatexQuestions = new List();

		for(int c=0;c<questionsbysubject.getItemCount();c++){
			for(int d=0;d<questionsbysection.getItemCount();d++){
				if(questionsbysubject.getItem(c).compareTo(questionsbysection.getItem(d))==0)
					temp_list.add(questionsbysection.getItem(d));
			}
		}

		for(int c=0;c<temp_list.getItemCount();c++){
			for(int d=0;d<questionsbydifficulty.getItemCount();d++){
				if(temp_list.getItem(c).compareTo(questionsbydifficulty.getItem(d))==0)
					LatexQuestions.add(questionsbydifficulty.getItem(d));
			}
		}

		if(questionQuantity<=LatexQuestions.getItemCount()){
			for(int c=0;c<questionQuantity;c++){
				latex_file_io.format(qcount + ") " + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_instructions"));
				latex_file_io.format("\n\n$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q") + "$\n\n");
				latex_file_io.format("\\vfill");
				qcount++;
			}
		}
		else{
			System.out.println("Not enough questions in database. Adding " + LatexQuestions.getItemCount());
			for(int c=0;c<LatexQuestions.getItemCount();c++){
				latex_file_io.format(qcount + ") " + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_instructions"));
				latex_file_io.format("\n\n$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q") + "$\n\n");
				qcount++;
			}
		}        
	}

	/**
	 * Given a list of question IDs, this will write only the "latex_q" tag of a question.
	 * This method is used for generating HTML output files. 
	 * The Unix command latex2html will convert these equations into images to be inserted into the HTML file. 
	 * @param html_questions
	 */
	public void WriteLatexQuestions(List html_questions){

		LatexQuestions = new List();

		for(int c=0;c<html_questions.getItemCount();c++){
			LatexQuestions.add(html_questions.getItem(c));

		}

		for(int c=0;c<html_questions.getItemCount();c++){
			latex_file_io.format("\n\n$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q") + "$");
		}
	}


	/**
	 * writes foot of latex test file and closes the formatter
	 */
	public void WriteLatexFoot(){
		latex_file_io.format("\n\n\\end{document}");
		latex_file_io.close();
	}
}



