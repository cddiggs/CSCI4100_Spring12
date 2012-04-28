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
        private String test_name;
/**
         * constructor
         * @throws IOException 
         */
    public htmlFile() throws IOException{
            XMLretriever = new RetrieveXML();
            html_questions = new List();
            tex_file = new LatexFile("temp.tex");
            tex_file.WriteLatexHead("temp");       
    }
    
/**
     * creates list of questions to add
     * adds problems to temp.tex
     * @param section lesson section
     * @param difficulty difficulty of problems
     * @param questionQuantity number of problems to add
     */    
    public void WritehtmlQuestions(double section, int difficulty, int questionQuantity){
        List questionsbysection = XMLretriever.returnBySection(section);
        List questionsbydifficulty = XMLretriever.returnByDifficulty(difficulty);
        List LatexQuestions = new List();
        for(int c=0;c<questionsbysection.getItemCount();c++){
            for(int d=0;d<questionsbydifficulty.getItemCount();d++){
                if(questionsbysection.getItem(c).compareTo(questionsbydifficulty.getItem(d))==0)
                    LatexQuestions.add(questionsbysection.getItem(c));
            }
        }
        tex_file.WriteLatexQuestions(section, difficulty, questionQuantity);
        if(questionQuantity>=LatexQuestions.getItemCount()){
            for(int c=0;c<questionQuantity;c++){
                html_questions.add(LatexQuestions.getItem(c));
            }
        }
        else{
                System.out.println("Not enough questions in database. Adding " + LatexQuestions.getItemCount());
            for(int c=0;c<LatexQuestions.getItemCount();c++)
                html_questions.add(LatexQuestions.getItem(c));
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
            for(int c=0;c<html_questions.getItemCount();c++){
                html_test_io.format((c+1) + ") " + XMLretriever.returnTestData(html_questions.getItem(c), "latex_instructions") + "\n<br>\n");
                html_test_io.format("<IMG SRC=\"temp/img" + (c+1) + ".png\">\n<br><br><br>\n");
            }
            html_test_io.format("\n\n</BODY>\n\n</HTML>");
            html_test_io.close();
            p=Runtime.getRuntime().exec("mv index.html " + test_dir);        
            p.waitFor();
        }
        catch(IOException e){
            System.out.println("IOexception");
        }
        
        catch(InterruptedException d){
            System.out.println("InterruptedException");
        }
    }
}
