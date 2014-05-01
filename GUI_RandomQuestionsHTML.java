import java.awt.EventQueue;
import java.awt.List;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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


public class GUI_RandomQuestionsHTML extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_RandomQuestionsHTML frame = new GUI_RandomQuestionsHTML();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_RandomQuestionsHTML() {
		setTitle("HTML Test Creation");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(20, 31, 400, 20);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("(Please Enter the Name for the new test, alphanumeric only)");
		contentPane.add(textField);
		textField.setColumns(10);
		
		
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
		
		JLabel lblQuestionSelectionMethod = new JLabel("Adding Random Questions");
		lblQuestionSelectionMethod.setBounds(130, 5, 207, 15);
		contentPane.add(lblQuestionSelectionMethod);
		
		JLabel lblSelectADatabase = new JLabel("Select a Database from the current List");
		lblSelectADatabase.setBounds(20, 63, 400, 25);
		contentPane.add(lblSelectADatabase);
		lblSelectADatabase.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblSelectTheHighest = new JLabel("Select the Highest Possible Question ID");
		lblSelectTheHighest.setBounds(20, 140, 300, 15);
		contentPane.add(lblSelectTheHighest);
		
		JLabel lblSelectTheLowest = new JLabel("Select the Lowest Possible Question ID");
		lblSelectTheLowest.setBounds(20, 180, 300, 15);
		contentPane.add(lblSelectTheLowest);
		
		JLabel lblSelectTheNumber = new JLabel("Select the Number of Questions");
		lblSelectTheNumber.setBounds(20, 220, 300, 15);
		contentPane.add(lblSelectTheNumber);
		
		final JSpinner highID = new JSpinner();
		highID.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		highID.setBounds(340, 138, 40, 20);
		contentPane.add(highID);
		
		final JSpinner lowID = new JSpinner();
		lowID.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		lowID.setBounds(340, 178, 40, 20);
		contentPane.add(lowID);
		
		final JSpinner questions = new JSpinner();
		questions.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		questions.setBounds(340, 218, 40, 20);
		contentPane.add(questions);
		
		/**Generate button passes information in the frame to the function to create the desired HTML test*/
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int Qnum = (Integer)questions.getValue();
				int min = (Integer)lowID.getValue();
				int max = (Integer)highID.getValue();
				String testname = textField.getText().replaceAll(" ", "_");
				String file = (String)comboBox.getSelectedItem();
				ArrayList<Integer> check = new ArrayList<Integer>(0);
				List check2 = new List();

				int cont=0;
				while(cont<Qnum){
					Random rand = new Random();
					int range = max - min +1;

					int randNum = rand .nextInt(range)+1;
					System.out.println(cont +", "+ Qnum + ", "+ randNum);

					//randNum is checked to see if it is in the list if it is add else
					//add output the randNum
					if (check.contains(randNum)) {
					}else{
						check.add(randNum);
						check2.add(Integer.toString(randNum));
						cont++;
					}
				}
				htmlFile database2 = null;
				try {
					database2 = new htmlFile(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				database2.WritehtmlQuestions(check2);
				database2.GeneratehtmlTest(testname);
				try {
					FileGenerated dialog = new FileGenerated();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnGenerate.setBounds(155, 280, 117, 25);
		contentPane.add(btnGenerate);
	}
}
