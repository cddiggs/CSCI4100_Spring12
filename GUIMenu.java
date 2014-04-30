

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;


public class GUIMenu extends JFrame {
	
	private JFrame frame=new JFrame("Test Generator");
	
	private JMenuBar menuBar = new JMenuBar();

	
	private JMenu File = new JMenu("File");

	private JMenu Edit = new JMenu("Edit");
	private JMenu Help = new JMenu("Help");

	private JMenu New= new JMenu("New");
	private JMenuItem Lpdf= new JMenuItem("Latex\\PDF Test");
	private JMenuItem HTML= new JMenuItem("HTML Test");
	private JMenuItem JB= new JMenuItem("Jepardy Board");
	private JMenuItem Exit = new JMenuItem("Exit");
	private JMenuItem About = new JMenuItem("About");

	private JPanel lpdfpanel = new JPanel();
	
	private JLabel lblPdfTest = new JLabel("This is a Test Generator and a Jeopardy Board Generator");
	private JLabel lblsuccess = new JLabel("Test generation successful!");
	private JLabel dpath = new JLabel("Enter the path of the database:");
	private JLabel testname = new JLabel("Enter the test name:");
	private JLabel subjectname = new JLabel("Enter the what subject you would like to use:");
	private JLabel sectionname = new JLabel("Enter the what section you would like to use:");
	private JLabel diffrange = new JLabel("Enter the the difficulty:");
	private JLabel numbofqs = new JLabel("Enter the number of questions you would like to add:");


	private JButton nextbutton = new JButton("Next");
	
	private JPanel contentPane = new JPanel(new GridLayout());
	private JTextField dtextfield = new JTextField("");
	private JTextField tntextfield = new JTextField("");
	private JTextField subtextfield = new JTextField("");
	private JTextField secttextfield = new JTextField("");
	private JTextField difftextfield = new JTextField("");
	private JTextField qtextfield = new JTextField("");


	private String testn;
	private String databn;
	private String subjectn;
	private double sectn;
	private int diffn;
	private int questn;
	
	
	public GUIMenu() {

		setupFrame();
	}
	private void setupFrame(){
		frame.setSize(700,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		frame.setResizable(false);
		
		menuBar.add(File);
		menuBar.add(Edit);
		menuBar.add(Help);
		
		

		New.add(Lpdf);
		New.add(HTML);
		New.add(JB);
		File.add(New);
		File.addSeparator();
		File.add(Exit);
			Exit.addActionListener(new exitaction());	
			Lpdf.addActionListener(new lpdfaction());
			HTML.addActionListener(new lpdfaction());
		Help.add(About);
		
		contentPane.setLayout(new GridLayout(13, 2));
		contentPane.add(lblPdfTest);
		frame.setContentPane(contentPane);
	
       }
	
	 class exitaction implements ActionListener{
		public void actionPerformed (ActionEvent e){
			System.exit(1);
			
		}
	}
	 class lpdfaction implements ActionListener{
		public void actionPerformed (ActionEvent e){
			removecomponents();
			addcomponents();

			nextbutton.addActionListener( new nextbutton1action());
			
			frame.setContentPane(contentPane);


			
		}
	}
	
	public class nextbutton1action implements ActionListener{
		public void actionPerformed (ActionEvent e){
		 testn=tntextfield.getText();
		 //System.out.println(testn);
		 databn=dtextfield.getText();
		subjectn=subtextfield.getText();
		sectn=Double.parseDouble(secttextfield.getText());
		diffn=Integer.parseInt(difftextfield.getText());
		questn=Integer.parseInt(qtextfield.getText());
		removecomponents();
		contentPane.add(lblsuccess);
		frame.setContentPane(contentPane);
		
		}
	}
	
	public void addcomponents(){
		contentPane.add(testname);
		contentPane.add(tntextfield);
		contentPane.add(dpath);
		contentPane.add(dtextfield);
		contentPane.add(subjectname);
		contentPane.add(subtextfield);
		contentPane.add(sectionname);
		contentPane.add(secttextfield);
		contentPane.add(diffrange);
		contentPane.add(difftextfield);
		contentPane.add(numbofqs);
		contentPane.add(qtextfield);
		contentPane.add(nextbutton);
		
	}
	public void removecomponents (){
		contentPane.remove(lblPdfTest);
		contentPane.remove(lblsuccess);
		contentPane.remove(testname);
		contentPane.remove(tntextfield);
		contentPane.remove(dpath);
		contentPane.remove(dtextfield);
		contentPane.remove(subjectname);
		contentPane.remove(subtextfield);
		contentPane.remove(sectionname);
		contentPane.remove(secttextfield);
		contentPane.remove(diffrange);
		contentPane.remove(difftextfield);
		contentPane.remove(numbofqs);
		contentPane.remove(qtextfield);
		contentPane.remove(nextbutton);
	}
}