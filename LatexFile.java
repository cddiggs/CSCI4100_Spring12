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
    protected List LatexQuestions;
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
                  LatexQuestions.add(questionsbydifficulty.getItem(c));
            }
        }
        
       if(questionQuantity<=LatexQuestions.getItemCount()){
            for(int c=0;c<questionQuantity;c++){
               latex_file_io.format(qcount + ") " + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_instructions"));
               latex_file_io.format("\n\n$" + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q") + "$\n\n");
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
     * writes foot of latex test file and closes the formatter
     */
    public void WriteLatexFoot(){
        latex_file_io.format("\n\n\\end{document}");
        latex_file_io.close();
    }
}

    

