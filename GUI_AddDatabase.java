import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUI_AddDatabase extends JFrame {

	private JPanel contentPane;
	private JTextField txtHere;

	/**
	 * Launches the GUI for adding databases to the list.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_AddDatabase frame = new GUI_AddDatabase();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame for the Database adder.
	 */
	public GUI_AddDatabase() {
		setTitle("Add Database to List");
		setBounds(100, 100, 440, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(4);
		addOrRemoveDB ll = new addOrRemoveDB();
		try {
			comboBox.setModel(new DefaultComboBoxModel(ll.displayDBList()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		comboBox.setBounds(20, 30, 400, 24);
		contentPane.add(comboBox);
		
		JLabel lblDatabasesCurrentlyIn = new JLabel("Databases currently in the List");
		lblDatabasesCurrentlyIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabasesCurrentlyIn.setBounds(20, 10, 400, 15);
		contentPane.add(lblDatabasesCurrentlyIn);
		
		JLabel lblDatabaseToBe = new JLabel("Database to be Added to the List");
		lblDatabaseToBe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabaseToBe.setBounds(20, 80, 400, 15);
		contentPane.add(lblDatabaseToBe);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creates a file chooser to aid in selecting the file path
				JFileChooser chooser = new JFileChooser();			    
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			      try {
					txtHere.setText(chooser.getSelectedFile().getCanonicalPath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			    }
			}
		});
		btnOpen.setBounds(20, 110, 80, 25);
		contentPane.add(btnOpen);
		
		txtHere = new JTextField();
		txtHere.setHorizontalAlignment(SwingConstants.CENTER);
		txtHere.setText("(File path goes here)");
		txtHere.setToolTipText("Path of the File to be Added");
		txtHere.setBounds(120, 110, 300, 25);
		contentPane.add(txtHere);
		txtHere.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = txtHere.getText();
				addOrRemoveDB db = new addOrRemoveDB();
				try {
					db.addDB(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParserConfigurationException e1) {
					e1.printStackTrace();
				} catch (SAXException e1) {
					e1.printStackTrace();
				} catch (TransformerException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					FileGenerated added = new FileGenerated(file+" has been added to the list!");
					added.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					added.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(155, 170, 117, 25);
		contentPane.add(btnAdd);
	}
}
