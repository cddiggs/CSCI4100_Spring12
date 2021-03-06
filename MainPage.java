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
 * <CENTER> <B> PDF/HTML Test  </B> </CENTER>

The user will be prompted with choices that include: 

<B>1. Add specific Question:</B> When this option is selected the user will input the amount of questions  and the question IDs desired for the test. 

<VAR>Pre-condition:</VAR> Image/graph has to be in the database as the tag name value
(EX: <graph>one.jpeg</graph>). And also the image needs to be in the correct directory
<VAR>Post-condition:</VAR> Will output desired questions via ID numbers.

<B>2. Add All Questions:</B> This will generate a Latex/PDF file with all questions in the database.

<B>3. Add Specific Solutions:</B> Similar to the Specific Questions the user will be prompted to give
the number of solution and then the corresponding ID numbers of the solutions to the desired
Questions.

<B>4. Add All Solutions:</B> This will generate a Latex/PDF file with all the Solutions in the database.

<B>5. Add Random Questions:</B> This will prompt the user to give a specific range of question ID
he would like the random numbers to come from, he then inputs the number of questions selected

<B>6. Move image to current directory:</B> This will prompt the user to give the <EM>complete</EM> path of an image and will move the image to the current directory. (For example, the complete path is "/home/user/Desktop/image.jpeg.")
 * <CENTER> <B> Jeopardy Board </B> </CENTER>


If the user would like to create a Jeopardy Board the user must input the number of categories/subjects, number of questions per category, the starting point value and how many points to the next point value (i.e. start each category with a 100 pt question then add 100 points to each of the following questions) and time for each question. The output would be a HTML file with the category names and the points displayed. When a point value is clicked, the question for that point value appears on screen or new window, and a timer starts. When either the game host ends the question or the time expires for the question, the answer is displayed.


<CENTER><B> Output  </B></CENTER>

- PDF Test: The output for a PDF Test will be located in current directory. 

- HTML Test:The output for a PDF Test will be located in a folder named after the test. This folder is located in the current directory. 

- Jeopardy Board: The output for a Jeopardy Board will be in file named "htmlTest.html" located in the current directory. 



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

- If there is a graph to the question or solution, then the user must include the extension of the image (i.e. ".jpeg", ".gif"). The name of the image can not have '_' as the first character of the name. Also, the image must be located in the current directory. (There will be a method to move an image from one location to current directory when making a PDF/HTML Test.)

- When creating a new HTML Test, you can not overwrite an existing HTML Test. Therefore, each HTML Test must have a unique name or that the test folder is moved or deleted from the current working directory.

- When choosing specific questions for a PDF/HTML Test, be sure that the question ID exists in the database.

 * \section UML Class Diagram
 * <IMG SRC=UMLDiagram.png>
 * 
 * UML Diagram generated with Object Aid Class Diagram Generator (Eclipse plug-in).
 */