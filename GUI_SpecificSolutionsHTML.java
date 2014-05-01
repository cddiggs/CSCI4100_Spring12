import java.awt.EventQueue;
import java.awt.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUI_SpecificSolutionsHTML extends JFrame {

	private JPanel contentPane;
	private JTextField txtinsertTestName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_SpecificSolutionsHTML frame = new GUI_SpecificSolutionsHTML();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public GUI_SpecificSolutionsHTML() throws IOException {
		setTitle("HTML Solution Guide Creation");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtinsertTestName = new JTextField();
		txtinsertTestName.setBounds(20, 31, 400, 20);
		txtinsertTestName.setHorizontalAlignment(SwingConstants.CENTER);
		txtinsertTestName.setText("(Please Name the Solution Guide here, alphanumeric only)");
		contentPane.add(txtinsertTestName);
		txtinsertTestName.setColumns(10);
		
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(4);
		addOrRemoveDB ll = new addOrRemoveDB();
		try {
			comboBox.setModel(new DefaultComboBoxModel(ll.displayDBList()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		comboBox.setBounds(20, 90, 400, 24);
		contentPane.add(comboBox);
		
		JLabel lblQuestionSelectionMethod = new JLabel("Adding Specific Solutions");
		lblQuestionSelectionMethod.setBounds(120, 5, 207, 15);
		contentPane.add(lblQuestionSelectionMethod);
		
		JLabel lblSelectADatabase = new JLabel("Select a Database from the current List");
		lblSelectADatabase.setBounds(20, 63, 400, 25);
		contentPane.add(lblSelectADatabase);
		lblSelectADatabase.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel specificPanel = new JPanel();
		specificPanel.setBounds(20, 165, 400, 215);
		contentPane.add(specificPanel);
		specificPanel.setLayout(null);
		JButton btnGenerateTest = new JButton("Generate Test");
		btnGenerateTest.setBounds(120, 165, 140, 25);
		specificPanel.add(btnGenerateTest);
		
		JLabel lblOfQuestions = new JLabel("How Many Questions?");
		lblOfQuestions.setBounds(0, 0, 160, 15);
		specificPanel.add(lblOfQuestions);
		
		final JTextArea txtrnn = new JTextArea();
		txtrnn.setBounds(0, 55, 400, 100);
		specificPanel.add(txtrnn);
		txtrnn.setText("1 2 3 4");
		
		final JSpinner spinner = new JSpinner();
		spinner.setBounds(175, 0, 50, 20);
		specificPanel.add(spinner);
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		JLabel lblPleaseEnterThe = new JLabel("Please Enter the Question Numbers Here:");
		lblPleaseEnterThe.setBounds(0, 30, 400, 15);
		specificPanel.add(lblPleaseEnterThe);
		btnGenerateTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int Qnum = (Integer)spinner.getValue();
				Scanner readit = new Scanner(txtrnn.getText());
				String testname = txtinsertTestName.getText().replaceAll(" ", "_");
				List html_questions = new List();
				for(int cont=0; cont<Qnum;Qnum--){	
					//System.out.println("Enter the ID number:");
					int IDnumber = readit.nextInt();
					html_questions.add(Integer.toString(IDnumber));
				}
				htmlFile database2 = null;
				String file = (String)comboBox.getSelectedItem();
				try {
					database2 = new htmlFile(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				database2.WritehtmlSolutions(html_questions);
				database2.GeneratehtmlSolutions(testname);
				try {
					FileGenerated dialog = new FileGenerated();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				

			}
		});
		
		/** Will attempt to build the test with the preceeding options*/
		
		
	}
	
}
