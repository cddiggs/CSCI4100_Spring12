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
public class htmlFile {
        private Formatter html_file_io;
        private File html_file;
        private int qcount;
        private RetrieveXML XMLretriever;
    public htmlFile(String path) throws IOException{
            XMLretriever = new RetrieveXML();
            html_file = new File(path);
            if(!html_file.exists()){
                html_file.createNewFile();
            }
            html_file_io = new Formatter(html_file.getAbsolutePath());
            qcount=1;            
    }
    
    public void WritehtmlHead(String testname){
        html_file_io.format("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\">\n");
        html_file_io.format("<html>\n<head>\n<title> \"" + testname + "\"</title>\n\n<body>");
        html_file_io.format(testname);
        html_file_io.format("}\n\n\\begin{document}\n\n\\maketitle\n\n");    
    }
    
    /**
     * closes the html file
     */
    public void WritehtmlFoot(){
        html_file_io.format("\n\n<\\body>\n\n<\\html>");
        html_file_io.close();
    }
    
    public void WritehtmlQuestions(double section, int difficulty, int questionQuantity){
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
        
            for(int c=0;c<LatexQuestions.getItemCount();c++){
            html_file_io.format("\n\n" + qcount + ") " + XMLretriever.returnTestData(LatexQuestions.getItem(c), "latex_q"));
            qcount++;
        } 
    }
    

}
