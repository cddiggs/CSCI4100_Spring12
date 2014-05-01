import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;


public class GUI_PDFtest extends JFrame {

	private JPanel contentPane;
	private JTextField txtpleaseEnterThe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_PDFtest frame = new GUI_PDFtest();
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
	// TODO Once the PDF portion of the program has been finished, the GUI for the PDF tests needs to be updated.
	public GUI_PDFtest() {
		setTitle("PDF Test Creation");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtpleaseEnterThe = new JTextField();
		txtpleaseEnterThe.setBounds(20, 31, 400, 20);
		txtpleaseEnterThe.setHorizontalAlignment(SwingConstants.CENTER);
		txtpleaseEnterThe.setText("(Please Enter the Name for the new test, alphanumeric only)");
		contentPane.add(txtpleaseEnterThe);
		txtpleaseEnterThe.setColumns(10);
		
		addOrRemoveDB ll = new addOrRemoveDB();
		JComboBox comboBox = new JComboBox();
		//comboBox.setMaximumRowCount(4);
		
		try {
			comboBox.setModel(new DefaultComboBoxModel(ll.displayDBList()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		comboBox.setBounds(20, 90, 400, 24);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {" ", "Random Selection", "Specific Selection"}));
		comboBox_1.setBounds(220, 130, 200, 24);
		contentPane.add(comboBox_1);
		
		JLabel lblQuestionSelectionMethod = new JLabel("Question Selection Method");
		lblQuestionSelectionMethod.setBounds(20, 135, 207, 15);
		contentPane.add(lblQuestionSelectionMethod);
		
		JLabel lblSelectADatabase = new JLabel("Select a Database from the current List");
		lblSelectADatabase.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectADatabase.setBounds(20, 60, 400, 25);
		contentPane.add(lblSelectADatabase);
	}
}
