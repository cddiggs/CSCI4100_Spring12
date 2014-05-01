import java.awt.EventQueue;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUI_AllQuestionsHTML extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_AllQuestionsHTML frame = new GUI_AllQuestionsHTML();
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
	public GUI_AllQuestionsHTML() {
		setTitle("HTML Test Creation");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
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
		
		JLabel lblQuestionSelectionMethod = new JLabel("Adding All Questions");
		lblQuestionSelectionMethod.setBounds(130, 5, 207, 15);
		contentPane.add(lblQuestionSelectionMethod);
		
		JLabel lblSelectADatabase = new JLabel("Select a Database from the current List");
		lblSelectADatabase.setBounds(20, 63, 400, 25);
		contentPane.add(lblSelectADatabase);
		lblSelectADatabase.setHorizontalAlignment(SwingConstants.CENTER);
		
		/**Generate button passes information in the frame to the function to create the desired HTML test*/
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String testname = textField.getText().replaceAll(" ", "_");
				String file = (String)comboBox.getSelectedItem();
				htmlFile database2 = null;
				try {
					database2 = new htmlFile(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				database2.WriteAllhtmlQuestions();
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
		btnGenerate.setBounds(155, 126, 117, 25);
		contentPane.add(btnGenerate);
	}

}
