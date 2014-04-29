/*! \mainpage MathTG 0.0.2
 *
 * \section intro_sec Introduction
 *
 * The main purpose of this program is to generate PDF of HTML tests or a Jeopardy Board in HTML format using a data bank. 
 *
 * \section install_sec Installation
 * 
 * In order to for this program to work you will need the following:
 * 
 * - UNIX platform 
 * 
 * - "latex2html" UNIX command installed 
 * 
 * - Firefox 
 *
 * \section Specifications
 * 
 * The user has the option to create a PDF test, HTML test, or Jeopardy Board. If the user chooses to create a PDF/HTML test, the user will have two methods of creating the test, either by random questions or by specific questions.
 * <CENTER> <B> PDF/HTML Test with Random Questions </B> </CENTER>


If the user would like to create a test with random the questions, the user must select a database to choose questions from, and input the following: test name, subject, sections, range of difficulty, and the number of questions. The out put can be either PDF or HTML test depending on the user's desire.
 
 * <CENTER> <B> PDF/HTML Test with Specific Questions </B> </CENTER>


If the user would like to create a test with specific questions, the user must select a database to choose questions from, and input the question IDs of all the questions that should appear on the test. The output can be either PDF or HTML test depending on the user's desire.

In addition, if the user wanted to create a test with specific questions, then the user will specify if a question includes an image (JPG format). Once done, the user will specify the path to the image. (In order for a graph/picture to be provided, a path has to be specified to retrieve the image.) Once done, it will generate the question, the image, plus the other questions. In the instance the test doesn't include a graph, the user will simply go through the specified inputs: test name, subject, sections, and the number of questions. Furthermore, there will be an answering key provided for the user if the user wants a answering key with the solutions. (This will be prompted at the end after the test has been generated.)
 
 * <CENTER> <B> Jeopardy Board </B> </CENTER>


If the user would like to create a Jeopardy Board the user must input the number of categories/subjects, number of questions per category, the starting point value and how many points to the next point value (i.e. start each category with a 100 pt question then add 100 points to each of the following questions) and time for each question. The output would be a HTML file with the category names and the points displayed. When a point value is clicked, the question for that point value appears on screen or new window, and a timer starts. When either the game host ends the question or the time expires for the question, the answer is displayed.


 * <CENTER> <B> GUI Menu </B> </CENTER>

Should the user wish to use a graphical interface, there will be a GUI available which will serve a purpose much like the command line menu. It will offer a button interface that will allow the user to select an option: PDF Test, HTML Test, or Jeopardy Board. From that point it will allow the user to enter in the specifications with which the option will be performed. After entering the specifications the requested task will be performed.

 * <CENTER> <B> Database Management </B> </CENTER>
 * 

 There will be a list of databases. The user can add or remove a database from this list. Any database in the list can be accessed so the user can add, edit, or remove questions. You can also view a database with all the questions and all the fields being generated in HTML format and displayed in your Firefox browser. After the database has been managed the user can access them to add to PDF/HTML test or Jeopardy Board.

The database will have the general layout as follows:

		<TestBank>
			<question>
				<id></id>
				<subject></subject>
				<section></section>
				<topic></topic>
				<difficulty></difficulty>
				<latex_instruction></latex_instructions>
				<graph></graph>
				<latex_a></latex_a>
				<graph_a></graph_a>
				<latex_q></latex_q>
				<joepardy_q></jeopardy_q>
				<jeopardy_a></jeopardy_a>
			</question>
		</TestBank>

<B>Important:</B> 

- The database cannot have any white spaces in between fields. For example, <topic> </topic> can not be accessed. However, <topic>_</topic> can be accessed and indicates the field is blank.

- When displaying the database, the program will launch the generated HTML file into Firefox in order to view the questions and all fields in the database. Thus, Firefox is required to display questions.
 	
- The questions in the database are numbered in ascending order as the question ID or <id> </id> field. You should not change a question ID of an existing question to a number greater than the number of questions in the database. 

 * \section UML Class Diagram
 * <IMG SRC=UMLDiagram.png>
 * 
 * UML Diagram generated with Object Aid Class Diagram Generator (Eclipse plug-in).
 */