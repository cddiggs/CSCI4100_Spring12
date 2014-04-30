import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.awt.List;
import java.util.Random;

/**
 * Class to create a HTML Jeopardy
 * 
 * @author Daniel Lowe
 */
public class htmlBoard {
	private String p_htmlFile;
	private String p_dbFile;
	private String p_test;

	/**
	 * HTML Jeopardy Constructor
	 * 
	 * @param htmlFile HTML for output
	 * @param dbFile XML file with data
	 * @param testName Name of the test
	 */
	public htmlBoard(String htmlFile, String dbFile, String testName) {
		p_htmlFile = htmlFile;
		p_dbFile = dbFile;
		p_test = testName;
	}

	/**
	 * Return the question HTML from the XML file
	 * 
	 * @param questionID Unique ID for the question
	 * 
	 * @return The HTML question from the XML file
	 */
	public String htmlQuestion(String questionID) {
		RetrieveXML xml = new RetrieveXML(p_dbFile);
		String question = xml.returnTestData(questionID, "jeopardy_q");
		if(question == null) return null;
		return question;
	}

	/**
	 * Return the answer HTML from the XML file
	 * 
	 * @param questionID Unique ID for the question
	 * 
	 * @return The HTML answer from the XML file
	 */
	public String htmlAnswer(String questionID) {
		RetrieveXML xml = new RetrieveXML(p_dbFile);
		String question = xml.returnTestData(questionID, "jeopardy_a");
		if(question == null) return null;
		return question;
	}

	/**
	 * Get the highest Difficulty level for the game
	 * 
	 * @return Highest Difficulty level
	 */
	public int getHighestDifficulty() {
		int highestDifficulty = 0;
		RetrieveXML xml = new RetrieveXML(p_dbFile);
		List allTopics = xml.returnAllQuestions();
		for(int i = 0; i < allTopics.getItemCount(); i++) {
			int temp = Integer.valueOf(xml.returnTestData(allTopics.getItem(i), "difficulty"));
			if(temp > highestDifficulty) highestDifficulty = temp;
		}
		return highestDifficulty;
	}

	/**
	 * Returns a random question ID for a topic and difficulty
	 * 
	 * @param topicSubject
	 * @param difficultyLevel
	 * @return 
	 */
	public String randomQuestion(String topicSubject, int difficultyLevel) {
		RetrieveXML xml = new RetrieveXML(p_dbFile);
		List byTopic = xml.returnByTopic(topicSubject);
		List byDifficulty = xml.returnByDifficulty(difficultyLevel);
		List topicDifficulty = new List();
		for(int topicCnt = 0; topicCnt < byTopic.getItemCount(); topicCnt++) {
			String topicID = byTopic.getItem(topicCnt);
			for(int difficultyCnt = 0; difficultyCnt < byDifficulty.getItemCount(); difficultyCnt++) {
				String difficultyID = byDifficulty.getItem(difficultyCnt);
				if(difficultyID.compareToIgnoreCase(topicID) == 0) topicDifficulty.add(difficultyID);
			}
		}
		Random rnd = new Random();
		int nextInt = topicDifficulty.getItemCount();
		int rndQuestionID = rnd.nextInt(nextInt);
		return topicDifficulty.getItem(rndQuestionID);
	}

	/**
	 * Creates a randomized list of all topics
	 * 
	 * @return Randomized list of all topics
	 */
	public List randomTopic() {
		RetrieveXML xml = new RetrieveXML(p_dbFile);
		List allTopics = xml.returnAllTopics();
		List randomTopic = new List();
		int totalTopics = allTopics.getItemCount();
		while(totalTopics > 0) {
			Random rnd = new Random();
			int randTopic = rnd.nextInt(totalTopics);
			randomTopic.add(allTopics.getItem(randTopic));
			allTopics.remove(randTopic);
			totalTopics = allTopics.getItemCount();
		}
		return randomTopic;
	}

	/**
	 * Creates a list of random questions per topic per difficulty level
	 * 
	 * @param topic The topic for the list
	 * 
	 * @return List of question IDs for a topic per difficulty level
	 */
	public List rndQuestionByTopic(String topic) {
		int highestDifficulty = getHighestDifficulty();
		List boardQuestionIDs = new List();
		for(int cnt = 0; cnt < highestDifficulty; cnt++)
			boardQuestionIDs.add(randomQuestion(topic, cnt + 1));
		return boardQuestionIDs;
	}

	/**
	 * Using a given list of topics, create a board
	 * 
	 * @param topicList List of topics for the board
	 * @param increaseValue Increase of point value
	 * 
	 * @return String containing the HTML table
	 */
	public String chooseTable(List topicList, int increaseValue) {
		StringBuilder htmlTable = new StringBuilder("\t<table id=\"gameBoard\">\r\n");
		int categoryCount = topicList.getItemCount(),
				highestDifficulty = getHighestDifficulty();
		List[] htmlQuestions = new List[categoryCount];
		for(int cnt = 0; cnt < categoryCount; cnt++) {
			htmlQuestions[cnt] = rndQuestionByTopic(topicList.getItem(cnt));
		}
		htmlTable.append("\t\t<tr>\r\n");
		for(int cnt = 0; cnt < categoryCount; cnt++) {
			htmlTable.append("\t\t\t<th>");
			htmlTable.append(topicList.getItem(cnt));
			htmlTable.append("</th>\r\n");
		}
		htmlTable.append("\t\t</tr>\r\n");
		for(int q_cnt = 0; q_cnt < highestDifficulty; q_cnt++) {
			htmlTable.append("\t\t<tr>\r\n");
			for(int topicCnt = 0; topicCnt < categoryCount; topicCnt++) {
				String questionID = new String("" + topicCnt + q_cnt);
				htmlTable.append("\t\t\t<td id=\"td" + questionID + "\" ");
				htmlTable.append("onclick='prompt.show(\"" + questionID + "\")'>\r\n");
				htmlTable.append("\t\t\t\t<div class=\"hide\" ");
				htmlTable.append("id=\"q" + questionID + "\">");
				htmlTable.append(htmlQuestion(htmlQuestions[topicCnt].getItem(q_cnt)));
				htmlTable.append("</div>\r\n");
				htmlTable.append("\t\t\t\t<div class=\"hide\" ");
				htmlTable.append("id=\"a" + questionID + "\">");
				htmlTable.append(htmlAnswer(htmlQuestions[topicCnt].getItem(q_cnt)));
				htmlTable.append("</div>\r\n");
				htmlTable.append("\t\t\t\t<h3>");
				htmlTable.append((q_cnt + 1) * increaseValue);
				htmlTable.append("</h3>\r\n\t\t\t</td>\r\n");
			}
			htmlTable.append("\t\t</tr>\r\n");
		}
		htmlTable.append("\t</table>\r\n");
		return htmlTable.toString();
	}

	/**
	 * Create the top portion of an HTML file
	 * 
	 * @return Top portion of HTML
	 */
	public String htmlHeader() {
		StringBuilder header = new StringBuilder("<!DOCTYPE html>\r\n");
		header.append("<html>\r\n");
		header.append("\t<head>\r\n");
		header.append("\t\t<title>");
		header.append(p_test);
		header.append("</title>\r\n");
		header.append(htmlCSS());
		header.append(htmlScript());
		header.append("\t</head>\r\n\t<body>\r\n");
		return header.toString();
	}

	/**
	 * Create the CSS style section for the web page
	 * 
	 * @return String with CSS information
	 */
	public String htmlCSS() {
		StringBuilder css = new StringBuilder("\t\t<style>\r\n");
		css.append("\t\t\ttable,th,td, #prompt, span, #answer {\r\n");
		css.append("\t\t\t\tborder:3px solid black;\r\n");
		css.append("\t\t\t\tbackground-color:#2A3698;\r\n");
		css.append("\t\t\t\tcolor:#FFFF5F;\r\n");
		css.append("\t\t\t\ttext-align:center;\r\n");
		css.append("\t\t\t\tvertical-align:middle;\r\n");
		css.append("\t\t\t\tpadding:5px;\r\n");
		css.append("\t\t\t\twidth:19%;\r\n");
		css.append("\t\t\t\theight:60px;\r\n");
		css.append("\t\t\t\tfont-size:20px;\r\n");
		css.append("\t\t\t}\r\n");
		css.append("\t\t\ttable, #prompt {\r\n");
		css.append("\t\t\t\tmargin-left:auto;\r\n");
		css.append("\t\t\t\tmargin-right:auto;\r\n");
		css.append("\t\t\t}\r\n");
		css.append("\t\t\ttd:hover { border:3px solid #FFFF00; }\r\n");
		css.append("\t\t\ta { text-decoration:none; }\r\n");
		css.append("\t\t\ta:link,a:hover,a:active, a:visited { color:#FFFF00; }\r\n");
		css.append("\t\t\t#prompt { display:none; }\r\n");
		css.append("\t\t\t.hide { display:none; }\r\n");
		css.append("\t\t\t#prompt.hide();\r\n");
		css.append("\t\t</style>\r\n");
		return css.toString();
	}

	/**
	 * Create script section of the head area to run on the web page
	 * 
	 * @return Script section
	 */
	public String htmlScript() {
		StringBuilder script = new StringBuilder("\t\t<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js\">\r\n");
		script.append("\t\t</script>\r\n");
		script.append("\t\t<script type=\"text/javascript\">\r\n");
		script.append("\t\t\tvar prompt = {}\r\n");
		script.append("\t\t\tprompt.show = function(questionID) {\r\n");
		script.append("\t\t\t\t$(\"#question\").html($('#q' + questionID).html());\r\n");
		script.append("\t\t\t\t$(\"#answer\").html($('#a' + questionID).html());\r\n");
		script.append("\t\t\t\t$(\"#prompt\").fadeIn(1000);\r\n");
		script.append("\t\t\t\t$(\"#gameBoard\").hide();\r\n");
		script.append("\t\t\t}\r\n");
		script.append("\t\t\tprompt.hide = function() {\r\n");
		script.append("\t\t\t\t$(\"#gameBoard\").fadeIn(1000);\r\n");
		script.append("\t\t\t\t$(\"#answer\").hide();\r\n");
		script.append("\t\t\t\t$(\"#prompt\").hide();\r\n");
		script.append("\t\t\t}\r\n");
		script.append("\t\t\tprompt.correctAnswer = function() {\r\n");
		script.append("\t\t\t\t$(\"#answer\").fadeIn(1000);\r\n");
		script.append("\t\t\t}\r\n");
		script.append("\t\t</script>\r\n");
		return script.toString();
	}

	/**
	 * Create the last portion of the HTML file
	 * 
	 * @return Last portion of HTML
	 */
	public String htmlEnd() {
		StringBuilder end = new StringBuilder("\t\t<div id=\"prompt\" class=\"hide\">\r\n");
		end.append("\t\t\t<h2 id=\"question\"></h2>\r\n");
		end.append("\t\t\t<p><span><a href=\"javascript:prompt.correctAnswer();\"");
		end.append(">Answer</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		end.append("<span><a href=\"javascript:prompt.hide();\">");
		end.append("Continue</a></span></p>\r\n");
		end.append("\t\t\t<h2 id=\"answer\" class=\"hide\">blah</h2>\r\n");
		end.append("\t\t</div>\r\n");
		end.append("\t</body>\r\n");
		end.append("</html>");
		return end.toString();
	}

	/**
	 * Creates a HTML file with a randomized board
	 * 
	 * @param increaseValue Point value increase
	 * 
	 * @return HTML file with a randomized board
	 */
	public String randomBoard(int increaseValue) {
		String html = chooseBoard(randomTopic(), increaseValue);
		BufferedWriter htmlFile = null;
		try {
			htmlFile = new BufferedWriter(new FileWriter(p_htmlFile));
			htmlFile.write(html);
			htmlFile.newLine();
		}
		catch (FileNotFoundException fnf) { fnf.printStackTrace(); }
		catch (IOException ioe) { ioe.printStackTrace(); }
		finally {
			try {
				if(htmlFile != null) {
					htmlFile.flush();
					htmlFile.close();
				}
			}
			catch (IOException ioe) { ioe.printStackTrace(); }
		}
		return html;
	}

	/**
	 * Creates HTML for a page with a board with chosen topics
	 * 
	 * @param topicList Chosen topics
	 * @param increaseValue Point value increase
	 * 
	 * @return HTML page for board
	 */
	public String chooseBoard(List topicList, int increaseValue) {
		StringBuilder html = new StringBuilder(htmlHeader());
		html.append(chooseTable(topicList, increaseValue));
		html.append(htmlEnd());
		return html.toString();
	}
}
