import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class RetrieveXML {

	/**
	 * Default constructor
	 */
    private String dpath;
	public RetrieveXML(String path) {
		dpath = path;
	}
	
	/**
	 * Function to return question IDs for later output processing.
	 * @param section
	 * @return List of integer values of questions that are of the given difficulty
	 */
	public List returnBySection(double section){
		List questionsToReturn = new List();
		try {
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dBF.newDocumentBuilder();
			Document database = docBuilder.parse(new File(dpath));	
			NodeList listOfQuestions = database.getElementsByTagName("question");
			for(int i=0; i<listOfQuestions.getLength(); i++) {
				Node firstQuestion = listOfQuestions.item(i);
				Element firstQuestionE = (Element)firstQuestion;
				NodeList sectionlist = firstQuestionE.getElementsByTagName("section");
				Element firstSectionElement = (Element)sectionlist.item(0);
				NodeList textSectionList = firstSectionElement.getChildNodes();
				double sectionvalue = Double.valueOf(textSectionList.item(0).getNodeValue().trim());
				if (sectionvalue == section) {
					NodeList idlist = firstQuestionE.getElementsByTagName("id");
					Element firstIDElement = (Element)idlist.item(0);
					NodeList textIDList = firstIDElement.getChildNodes();
					String questionID = textIDList.item(0).getNodeValue().trim();
					questionsToReturn.add(questionID);
				}
			}
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return questionsToReturn;
	}
	
	/**
	 * Function to return question IDs for later output processing.
	 * @param sectionTopic
	 * @return List of integer values of questions that are of the given difficulty
	 */
	public List returnByTopic(String sectionTopic){
		List questionsToReturn = new List();
		try {
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dBF.newDocumentBuilder();
			Document database = docBuilder.parse(new File(dpath));
			
			NodeList listOfQuestions = database.getElementsByTagName("question");
			
			for(int i=0; i<listOfQuestions.getLength(); i++) {
				Node firstQuestion = listOfQuestions.item(i);
				Element firstQuestionE = (Element)firstQuestion;
				NodeList topiclist = firstQuestionE.getElementsByTagName("subject");
				Element firstTopicElement = (Element)topiclist.item(0);
				NodeList textTopicList = firstTopicElement.getChildNodes();
				String topicvalue = textTopicList.item(0).getNodeValue().trim();
				if (topicvalue.compareTo(sectionTopic)==0){
					NodeList idlist = firstQuestionE.getElementsByTagName("id");
					Element firstIDElement = (Element)idlist.item(0);
					NodeList textIDList = firstIDElement.getChildNodes();
					String questionID = textIDList.item(0).getNodeValue().trim();
					questionsToReturn.add(questionID);
				}
			}
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return questionsToReturn;
	}
	
	/**
	 * Function to return question IDs for later output processing.
	 * @param difficulty
	 * @return List of integer values of questions that are of the given difficulty
	 */
	public List returnByDifficulty(int difficulty){
		List questionsToReturn = new List();
		try {
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dBF.newDocumentBuilder();
			Document database = docBuilder.parse(new File(dpath));
			
			NodeList listOfQuestions = database.getElementsByTagName("question");
			
			for(int i=0; i<listOfQuestions.getLength(); i++) {
				Node firstQuestion = listOfQuestions.item(i);
				Element firstQuestionE = (Element)firstQuestion;
				NodeList sectionlist = firstQuestionE.getElementsByTagName("difficulty");
				Element firstDifficultyElement = (Element)sectionlist.item(0);
				NodeList textDifficultyList = firstDifficultyElement.getChildNodes();
				double difficultyValue = Double.valueOf(textDifficultyList.item(0).getNodeValue().trim());
				if (difficultyValue == difficulty) {
					NodeList idlist = firstQuestionE.getElementsByTagName("id");
					Element firstIDElement = (Element)idlist.item(0);
					NodeList textIDList = firstIDElement.getChildNodes();
					String questionID = textIDList.item(0).getNodeValue().trim();
					questionsToReturn.add(questionID);
				}
			}
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return questionsToReturn;
	}
	
	/**
	 * Function to return specified test data from a specific question. Any tag may be requested from the question element
	 * @param questionID
	 * @param tagname
	 * @return String with the requested test data
	 */
	public String returnTestData(String questionID, String tagname) {
		String returnvalue = "";
		try {
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dBF.newDocumentBuilder();
			Document database = docBuilder.parse(new File(dpath));
                        while(questionID.charAt(0)=='0')
                            questionID = questionID.substring(1);
			
			NodeList listOfQuestions = database.getElementsByTagName("question");
			for(int i=0; i<listOfQuestions.getLength(); i++) {
				Node firstQuestion = listOfQuestions.item(i);
				Element firstQuestionE = (Element)firstQuestion;
				NodeList sectionlist = firstQuestionE.getElementsByTagName("id");
				Element firstIDElement = (Element)sectionlist.item(0);
				NodeList textIDList = firstIDElement.getChildNodes();
				Integer IDvalue = Integer.valueOf(textIDList.item(0).getNodeValue().trim());
				if (IDvalue.toString().compareTo(questionID)==0) {
					NodeList returnList = firstQuestionE.getElementsByTagName(tagname);
					Element ReturnElement = (Element)returnList.item(0);
					NodeList textReturnList = ReturnElement.getChildNodes();
					returnvalue = textReturnList.item(0).getNodeValue().trim();
				}
			}
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}		
		return returnvalue;
	}
	
}
