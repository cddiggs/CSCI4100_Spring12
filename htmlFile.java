/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *this class uses the program latex2html, LatexFile class, and RetrieveXML class
 * generates a temporary latex file to convert latex math mode to png files
 * generates test directory that contains index.html this is the html test
 * @author derek
 */
import java.io.*;
import java.util.Formatter;
import java.awt.List;
public class htmlFile {
	private int qcount;
	private RetrieveXML XMLretriever;
	private LatexFile tex_file;
	private List html_questions;
	private List all_html_questions;
	private String test_name, dbpath;
	/**
	 * constructor
	 * @throws IOException 
	 */
	public htmlFile(String dpath) throws IOException{
		dbpath=dpath;
		XMLretriever = new RetrieveXML(dpath);
		html_questions = new List();
		all_html_questions = new List();
		tex_file = new LatexFile("temp.tex",dpath);
		tex_file.WriteLatexHead("temp");   
	}

	/**
	 * creates list of questions to add
	 * adds problems to temp.tex
	 * @param subject course subject title
	 * @param section lesson section
	 * @param difficulty difficulty of problems
	 * @param questionQuantity number of problems to add
	 */    
	public void WritehtmlQuestions(String subject, double section, int difficulty, int questionQuantity){
		List questionsbysubject = XMLretriever.returnByTopic(subject);
		List questionsbysection = XMLretriever.returnBySection(section);
		List questionsbydifficulty = XMLretriever.returnByDifficulty(difficulty);
		List temp_list = new List();
		List LatexQuestions = new List();


		for(int c=0;c<questionsbysubject.getItemCount();c++){
			for(int d=0;d<questionsbysection.getItemCount();d++){
				if(questionsbysubject.getItem(c).compareTo(questionsbysection.getItem(d))==0)
					temp_list.add(questionsbysection.getItem(d));
			}
		}

		for(int c=0;c<temp_list.getItemCount();c++){
			for(int d=0;d<questionsbydifficulty.getItemCount();d++){
				if(temp_list.getItem(c).compareTo(questionsbydifficulty.getItem(d))==0)
					LatexQuestions.add(questionsbydifficulty.getItem(c));
			}
		}
		tex_file.WriteLatexQuestions(subject, section, difficulty, questionQuantity);
		if(questionQuantity<=LatexQuestions.getItemCount()){
			for(int c=0;c<questionQuantity;c++){
				html_questions.add(LatexQuestions.getItem(c));
			}
		}
		else{
			System.out.println("Not enough questions in database. Adding " + LatexQuestions.getItemCount() + ".");
			for(int c=0;c<LatexQuestions.getItemCount();c++)
				html_questions.add(LatexQuestions.getItem(c));
		}
	}
	/**
	 * Given a list of question IDs, this method will write call WriteLatexQuestions method.    
	 * @param html_questions
	 */
	public void WritehtmlQuestions(List html_questions){
		this.all_html_questions=html_questions;
		tex_file.WriteLatexQuestions(html_questions);
	}
	/**
	 * Given a list of question IDs, this method will write call WriteLatexSolutions method.    
	 * @param html_questions
	 */
	public void WritehtmlSolutions(List html_questions){
		this.all_html_questions=html_questions;
		tex_file.WriteLatexSolutions(html_questions);


	}
	/**
	 * This method will write call WriteAllLatexSolutions method.    
	 */
	public void WritehtmlSolutions(){
		List allQuestions = XMLretriever.returnAllQuestions();
		this.all_html_questions=allQuestions;

		tex_file.WriteAllLatexSolutions();


	}

	/**
	 * This method will write call WriteAllLatexQuestions method.    
	 */
	public void WriteAllhtmlQuestions(){
		List allQuestions = XMLretriever.returnAllQuestions();

		tex_file.WriteAllLatexQuestions();

		for(int c=0;c<allQuestions.getItemCount();c++){
			all_html_questions.add(allQuestions.getItem(c));

		}



	}
	/**
	 * depends on latex2html. latex2html generates png files from math mode 
	 * sections in the temp.tex file. the directory created by latex2html
	 * is moved to a directory named after String testname. generates index.html
	 * and moves it to test directory
	 * @param testname name of html test to be generated
	 */
	public void GeneratehtmlTest(String testname){ 
		String test_dir = testname;
		test_dir = test_dir.replaceAll(" ", "_");
		try{
			tex_file.WriteLatexFoot();
			Process p;
			p = Runtime.getRuntime().exec("mkdir " + test_dir);
			p.waitFor();
			p=Runtime.getRuntime().exec("latex2html temp.tex");  
			p.waitFor();
			p=Runtime.getRuntime().exec("mv temp " + test_dir);
			p.waitFor();
			p=Runtime.getRuntime().exec("rm temp.tex");
			p.waitFor();
			File html_test = new File("index.html");
			if(!html_test.exists())
				html_test.createNewFile();

			Formatter html_test_io = new Formatter(html_test.getAbsolutePath());
			html_test_io.format("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n");
			html_test_io.format("<HTML>\n<HEAD>\n<TITLE>" + testname + "</TITLE>\n</HEAD>\n<BODY>\n\n");
			html_test_io.format("<center><b>" + testname + "</b></center><br><br>");
			for(int c=0;c<all_html_questions.getItemCount();c++){
				html_test_io.format((c+1) + ") " + XMLretriever.returnTestData(all_html_questions.getItem(c), "latex_instructions") + "\n<br>\n");
				html_test_io.format("<IMG SRC=\"temp/img" + (c+2) + ".png\">\n<br><br><br>\n");
				if (XMLretriever.returnTestData(all_html_questions.getItem(c), "graph").charAt(0)!='_')
					html_test_io.format("<IMG SRC=\"" + "../"+XMLretriever.returnTestData(all_html_questions.getItem(c), "graph") + "\">\n<br><br><br>\n");

			}
			html_test_io.format("\n\n</BODY>\n\n</HTML>");
			html_test_io.close();
			p=Runtime.getRuntime().exec("mv index.html " + test_dir);        
			p.waitFor();
		}
		catch(IOException e){
			System.out.println("IOexception");
			e.printStackTrace();
		}

		catch(InterruptedException d){
			System.out.println("InterruptedException");
		}
	}

	/**
	 * This method will write all questions and solutions to a html file.
	 * This method depends on latex2html unix command.
	 * The command, latex2html, generates png files from math mode 
	 * sections in the temp.tex file. The directory created by latex2html
	 * is moved to a directory named after String testname. 
	 * @param testname - name of html test to be generated
	 */
	public void GeneratehtmlSolutions(String testname){ 
		String test_dir = testname;
		test_dir = test_dir.replaceAll(" ", "_");
		try{
			tex_file.WriteLatexFoot();
			Process p;
			p = Runtime.getRuntime().exec("mkdir " + test_dir);
			p.waitFor();
			p=Runtime.getRuntime().exec("latex2html temp.tex");  
			p.waitFor();
			p=Runtime.getRuntime().exec("mv temp " + test_dir);
			p.waitFor();
			p=Runtime.getRuntime().exec("rm temp.tex");
			p.waitFor();
			File html_test = new File("index.html");
			if(!html_test.exists())
				html_test.createNewFile();

			Formatter html_test_io = new Formatter(html_test.getAbsolutePath());
			html_test_io.format("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n");
			html_test_io.format("<HTML>\n<HEAD>\n<TITLE>" + testname + "</TITLE>\n</HEAD>\n<BODY>\n\n");
			html_test_io.format("<center><b>" + testname + "</b></center><br><br>");
			int count = 2;
			for(int c=0;c<all_html_questions.getItemCount();c++){
				html_test_io.format((c+1) + ") " + XMLretriever.returnTestData(all_html_questions.getItem(c), "latex_instructions") + "\n<br>\n");
				html_test_io.format("<IMG SRC=\"temp/img" + (c+count) + ".png\">\n<br><br><br>\n");
				if (XMLretriever.returnTestData(all_html_questions.getItem(c), "graph").charAt(0)!='_')
					html_test_io.format("<IMG SRC=\"" + "../"+XMLretriever.returnTestData(all_html_questions.getItem(c), "graph") + "\">\n<br><br><br>\n");
				if (XMLretriever.returnTestData(all_html_questions.getItem(c), "latex_a").charAt(0)=='_')
					html_test_io.format("Solution: No Solution Available \n<br><br><br>\n");
				else 
					html_test_io.format("Solution: <IMG SRC=\"temp/img" + (c+count+1) + ".png\">\n<br><br><br>\n");
				if (XMLretriever.returnTestData(all_html_questions.getItem(c), "graph_a").charAt(0)!='_')
					html_test_io.format("Solution Graph: \n <br> \n <IMG SRC=\"" + "../"+XMLretriever.returnTestData(all_html_questions.getItem(c), "graph_a") + "\">\n<br><br><br>\n");
				count++;
			}
			html_test_io.format("\n\n</BODY>\n\n</HTML>");
			html_test_io.close();
			p=Runtime.getRuntime().exec("mv index.html " + test_dir);        
			p.waitFor();
		}
		catch(IOException e){
			System.out.println("IOexception");
			e.printStackTrace();
		}

		catch(InterruptedException d){
			System.out.println("InterruptedException");
		}
	}


	/**
	 * This method will write all questions and all their data to a html file.
	 * This method depends on latex2html unix command.
	 * The command, latex2html, generates png files from math mode 
	 * sections in the temp.tex file. The directory created by latex2html
	 * is moved to a directory named after String testname. 
	 * @param testname - name of html test to be generated
	 */
	public void GenerateAllHtml(String testname){ 
		String test_dir = testname;
		test_dir = test_dir.replaceAll(" ", "_");
		try{
			tex_file.WriteLatexFoot();
			Process p;
			p = Runtime.getRuntime().exec("mkdir " + test_dir);
			p.waitFor();
			p=Runtime.getRuntime().exec("latex2html temp.tex");  
			p.waitFor();
			p=Runtime.getRuntime().exec("mv temp " + test_dir);
			p.waitFor();
			p=Runtime.getRuntime().exec("rm temp.tex");
			p.waitFor();
			File html_test = new File("index.html");
			if(!html_test.exists())
				html_test.createNewFile();
			System.out.println(html_test.getAbsolutePath());
			Formatter html_test_io = new Formatter(html_test.getAbsolutePath());
			html_test_io.format("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n");
			html_test_io.format("<HTML>\n<HEAD>\n<TITLE>" + dbpath + "</TITLE>\n</HEAD>\n<BODY>\n\n");
			html_test_io.format("<center><b>" + dbpath + "</b></center><br><br>");
			for(int c=0;c<all_html_questions.getItemCount();c++){
				html_test_io.format("ID: " + XMLretriever.returnTestData(all_html_questions.getItem(c), "id")+ "\n<br>\n");
				html_test_io.format("Subject: " + XMLretriever.returnTestData(all_html_questions.getItem(c), "subject")+ "\n<br>\n");
				html_test_io.format("Section: " + XMLretriever.returnTestData(all_html_questions.getItem(c), "section")+ "\n<br>\n");
				html_test_io.format("Topic: " + XMLretriever.returnTestData(all_html_questions.getItem(c), "topic")+ "\n<br>\n");
				html_test_io.format("Difficulty: " + XMLretriever.returnTestData(all_html_questions.getItem(c), "difficulty")+ "\n<br>\n");
				html_test_io.format("Question Instructions: " + XMLretriever.returnTestData(all_html_questions.getItem(c), "latex_instructions") + "\n<br>\n");
				html_test_io.format("Question Graph Path: " + XMLretriever.returnTestData(all_html_questions.getItem(c), "graph") + "\n<br>\n");
				html_test_io.format("Answer: " + XMLretriever.returnTestData(all_html_questions.getItem(c), "latex_a") + "\n<br>\n");
				html_test_io.format("Answer Graph Path: " + XMLretriever.returnTestData(all_html_questions.getItem(c), "graph_a") + "\n<br>\n");

				html_test_io.format("Question (latex output): <IMG SRC=\"temp/img" + (c+2) + ".png\">\n<br>\n");
				html_test_io.format("Jepardy Questions: " + XMLretriever.returnTestData(all_html_questions.getItem(c), "jeopardy_q") + "\n<br>\n");
				html_test_io.format("Jepardy Answers: " + XMLretriever.returnTestData(all_html_questions.getItem(c), "jeopardy_a") + "\n<br><br><br>\n");


			}
			html_test_io.format("\n\n</BODY>\n\n</HTML>");
			html_test_io.close();
			p=Runtime.getRuntime().exec("mv index.html " + test_dir);        
			p.waitFor();
		}
		catch(IOException e){
			System.out.println("IOexception");
			e.printStackTrace();
		}

		catch(InterruptedException d){
			System.out.println("InterruptedException");
		}
	}
}
