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
    public void LatexFile(String path) throws IOException{
            latex_file = new File(path);
            if(!latex_file.exists()){
                latex_file.createNewFile();
            }
            latex_file_io = new Formatter(latex_file.getAbsolutePath());
            qcount=1;
    }

    public void WriteLatexHead(String testname){
        latex_file_io.format("\\documentclass[11pt,a4paper]{article}\n\\usepackage{amsmath,amsthm}\n\n\\newcommand{\\fn}[1]{{\\tt #1}}\n\\newcommand{\\cn}[1]{{\\tt \\char\"5C #1}}\n\n\\title{");
        latex_file_io.format(testname);
        latex_file_io.format("}\n\n\\begin{document}\n\n\\maketitle\n\n");    
    }
    
    
    public void WriteLatexInstructions(String instructions){
        latex_file_io.format("\n\\section{" + instructions + "}\n");
    }
    
    public void WriteLatexQuestions(double section, int difficulty, int questionQuantity){
        List questionsbysection = returnBySection(section);
        List questionsbydifficulty = returnByDifficult(difficulty);
        List LatexQuestions = new List();
        String [] sectionquestions = questionsbysection.getItems();
        String [] difficultyquestions = questionsbydifficulty.getItems();
        for(int c=0;c<sectionquestions.length;c++){
            for(int d=0;d<difficultyquestions.length;d++){
                if(sectionquestions[c]==difficultyquestions[d])
                    LatexQuestions.add(difficultyquestions[d]);
            }
        }
        String [] QuestionstoWrite = LatexQuestions.getItems();
        for(int c=0;c<QuestionstoWrite.length;c++){
            latex_file_io.format("\n\n" + qcount + QuestionstoWrite[c]);
            qcount++;
        } 
        
    }
    
    public void WriteLatexFoot(){
        latex_file_io.format("\n\\end{document}");
        latex_file_io.close();
    }
}

    

