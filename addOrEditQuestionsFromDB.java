import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class addOrEditQuestionsFromDB {
	private List all_questions;
	private RetrieveXML retrievesQuestions;
	private String path;
	/**
	 * This is the default constructor.
	 * This constructor needs to initialize the path to the database
	 * @param dpath - path to database
	 */
	addOrEditQuestionsFromDB(String dpath){
		path=dpath;
	}
	/**
	 * This method appends a new question to the database. 
	 * All information of the question (except question ID) is passed to the method
	 * in order for the new question to be created.
	 * (All parameters must contain a string other
	 * than white space, for example if there is no topic 
	 * name then "n/a" is acceptable but " " is not.)
	 * The new question ID is added to existing number of questions.
	 * (For example, if there are 10 questions in the database, 
	 * the new question ID is 13.)
	 * @param subjectName
	 * @param sectionNumber
	 * @param topicName
	 * @param difficultyNumber
	 * @param instruction
	 * @param lQuestion
	 * @param jQuestion
	 * @param jAnswer
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void addNewElement(String subjectName, String sectionNumber, String topicName, String difficultyNumber, String instruction, String Graph, String lAnswer, String gAnswer, String lQuestion, String jQuestion, String jAnswer) throws TransformerException, ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(new File(path));
		
		Node root = document.getDocumentElement();
		
		Node question = document.createElement("question");
		 root.appendChild(question);
		 Node id = document.createElement("id");
		 NodeList numberOfQs=document.getElementsByTagName("question");
		 	id.setTextContent(String.valueOf(numberOfQs.getLength()));
		 question.appendChild(id);
		 Node subject = document.createElement("subject");
		 subject.setTextContent(subjectName);
		 question.appendChild(subject);
		 Node section = document.createElement("section");
		 section.setTextContent(sectionNumber);
		 question.appendChild(section);
		 Node topic = document.createElement("topic");
		 topic.setTextContent(topicName);
		 question.appendChild(topic);
		 Node difficulty = document.createElement("difficulty");
		 difficulty.setTextContent(difficultyNumber);
		 question.appendChild(difficulty);
		 Node latex_instructions = document.createElement("latex_instructions");
		 latex_instructions.setTextContent(instruction);
		 question.appendChild(latex_instructions);
		 Node graph = document.createElement("graph");
		 graph.setTextContent(Graph);
		 question.appendChild(graph);
		 Node latex_a = document.createElement("latex_a");
		 latex_a.setTextContent(lAnswer);
		 question.appendChild(latex_a);
		 Node graph_a = document.createElement("graph_a");
		 graph_a.setTextContent(gAnswer);
		 question.appendChild(graph_a);
		 Node latex_q = document.createElement("latex_q");
		 latex_q.setTextContent(lQuestion);
		 question.appendChild(latex_q);
		 Node jeopardy_q = document.createElement("jeopardy_q");
		 jeopardy_q.setTextContent(jQuestion);
		 question.appendChild(jeopardy_q);
		 Node jeopardy_a = document.createElement("jeopardy_a");
		 jeopardy_a.setTextContent(jAnswer);
		 question.appendChild(jeopardy_a);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(path);
        transformer.transform(source, result);	
        

        
	}
	/**
	 * This method removes a element from a database. 
	 * In order for an element to be removed, 
	 * the question ID must be passed to the method.
	 * @param ID
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public void removeElement(String ID) throws ParserConfigurationException, SAXException, IOException, TransformerException{
	
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(new File(path));
				
		for(int j=1;j<=document.getElementsByTagName("id").getLength();j++){
			 if(Integer.parseInt(document.getElementsByTagName("id").item(j-1).getTextContent())==Integer.parseInt(ID)){
				 Node delete=document.getElementsByTagName("question").item(j-1);
				 delete.getParentNode().removeChild(delete);
			 }
		}
		 TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(document);

	        StreamResult result = new StreamResult(path);
	        transformer.transform(source, result);
	}
	/**
	 * This method sorts the questions.
	 * This method will create a temporary list;
	 * sort the list; then writes a whole new database 
	 * that is sorted by question ID. 
	 * The new database is then replaced by the old database
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 * @throws InterruptedException
	 */
	public void sortQuestions() throws ParserConfigurationException, SAXException, IOException, TransformerException, InterruptedException{
		List temp = new List();
		retrievesQuestions=new RetrieveXML(path);
		all_questions=retrievesQuestions.returnAllQuestions();
		for(int j=0; j<all_questions.getItemCount();j++)
			temp.add(all_questions.getItem(j), Integer.parseInt(all_questions.getItem(j))-1);
		

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(new File("tempDB.xml"));

		Node root = document.getDocumentElement();

		 for(int i=1; i<=temp.getItemCount(); i++){
			 for(int j=1;j<=all_questions.getItemCount();j++){
				 if(temp.getItem(i-1)==all_questions.getItem(j-1)){
					 
					 Node question = document.createElement("question");
					 root.appendChild(question);
					 Node id = document.createElement("id");
					 id.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "id"));
					 question.appendChild(id);
					 Node subject = document.createElement("subject");
					 subject.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "subject"));
					 question.appendChild(subject);
					 Node section = document.createElement("section");
					 section.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "section"));
					 question.appendChild(section);
					 Node topic = document.createElement("topic");
					 topic.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "topic"));
					 question.appendChild(topic);
					 Node difficulty = document.createElement("difficulty");
					 difficulty.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "difficulty"));
					 question.appendChild(difficulty);
					 Element latex_instructions = document.createElement("latex_instructions");
					 latex_instructions.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "latex_instructions"));
					 question.appendChild(latex_instructions);
					 
					 Node graph = document.createElement("graph");
					 graph.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "graph"));
					 question.appendChild(graph);
					 Node latex_a = document.createElement("latex_a");
					 latex_a.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "latex_a"));
					 question.appendChild(latex_a);
					 Node graph_a = document.createElement("graph_a");
					 graph_a.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "graph_a"));
					 question.appendChild(graph_a);
					 
					 Node latex_q = document.createElement("latex_q");
					 latex_q.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "latex_q"));
					 question.appendChild(latex_q);
					 Node jeopardy_q = document.createElement("jeopardy_q");
					 jeopardy_q.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "jeopardy_q"));
					 question.appendChild(jeopardy_q);
					 Node jeopardy_a = document.createElement("jeopardy_a");
					 jeopardy_a.setTextContent(retrievesQuestions.returnTestData(String.valueOf(i), "jeopardy_a"));
					 question.appendChild(jeopardy_a);


				 }
			 }
				 
			 
		 }
		 TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(document);

	        StreamResult result = new StreamResult("./tempDB2.xml");
	        transformer.transform(source, result);
	        Process p;
	 		p = Runtime.getRuntime().exec("mv ./tempDB2.xml ./src/database.xml");
	        p.waitFor();
		
	}
	/**
	 * This method edits a question in the database.
	 * The question ID is required to know what question to edit.
	 * The tag name of the question is required to edit that certain tag name. 
	 * The new content is needed to replace the old content.
	 * (If the Question ID is edited, the new ID cannot
	 * be bigger than the number of questions in the databse)
	 * @param id
	 * @param tagname
	 * @param newInfo
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public void editQuestion(String id, String tagname, String newInfo) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(new File(path));
		
		document.getElementsByTagName(tagname).item(Integer.parseInt(id)-1).setTextContent(newInfo);
		
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        
        
        StreamResult result = new StreamResult(path);
        transformer.transform(source, result);	
	}
	
	/**
	 * This method is executed after the removeElement method is executed. 
	 * This method will edit the question IDs of all the questions after 
	 * the removed question. For example, if question 4 is removed then the
	 * then the question ID of question 5 becomes 4.  
	 * @param id
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public void editQuestionID(String id) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(new File(path));
		

		id=Integer.toString(Integer.parseInt(id));
		for(int j=Integer.parseInt(id)+1; j<=document.getElementsByTagName("id").getLength()+1; j++){

		this.editQuestion(Integer.toString(j-1), "id", Integer.toString(j-1));
	}
	}

}
