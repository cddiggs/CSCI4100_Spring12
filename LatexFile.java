/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
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
    /**
     * Constructor to create latex file for test
     * @param path the path of the filename for the test's latex file
     * @throws IOException 
     */
    public LatexFile(String path) throws IOException{
            XMLretriever = new RetrieveXML();
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
        latex_file_io.format("\\documentclass[11pt,a4paper]{article}\n\\usepackage{amsmath,amsthm}\n\n\\newcommand{\\fn}[1]{{\\tt #1}}\n\\newcommand{\\cn}[1]{{\\tt \\char\"5C #1}}\n\n\\title{");
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
     * Will add questions to the test from a section that are of certain difficulty
     * and will add no more than questionQuantity number of questions
     * @param section section of course material
     * @param difficulty difficulty of questions
     * @param questionQuantity number of questions
     */
    public void WriteLatexQuestions(double section, int difficulty, int questionQuantity){
        List questionsbysection = XMLretriever.returnBySection(section);
        List questionsbydifficulty = XMLretriever.returnByDifficulty(difficulty);
        List LatexQuestions = new List();
        String [] sectionquestions = questionsbysection.getItems();
        String [] difficultyquestions = questionsbydifficulty.getItems();
        for(int c=0;c<sectionquestions.length;c++){
            for(int d=0;d<difficultyquestions.length;d++){
                if(sectionquestions[c].matches(difficultyquestions[d]))
                    LatexQuestions.add(difficultyquestions[d]);
            }
        }
 //       String [] QuestionstoWrite = LatexQuestions.getItems();
        for(int c=0;c<LatexQuestions.getItemCount();c++){
            latex_file_io.format("\n\n" + qcount + ") " + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q"));
            qcount++;
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

    

