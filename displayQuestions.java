import java.io.IOException;


public class displayQuestions {
	private String testname;
	private String file;
	/**
	 * This is the default constructor for displayQuestions
	 * The two parameters, name of the test, and path of the file are initialized.
	 * @param tn - test name
	 * @param f - file path
	 */
	displayQuestions(String tn, String f){
		testname=tn;
		file=f;
	}
	
	/**
	 * This method writes the questions to a html file then opens in the Firefox browser
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void display() throws IOException, InterruptedException {

	
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
       p = Runtime.getRuntime().exec("firefox " + "./" + testname + "/index.html");
       p.waitFor();

}
}
