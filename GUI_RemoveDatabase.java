import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUI_RemoveDatabase extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_RemoveDatabase frame = new GUI_RemoveDatabase();
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
	public GUI_RemoveDatabase() {
		setTitle("Add Database to List");
		setBounds(100, 100, 430, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(4);
		addOrRemoveDB ll = new addOrRemoveDB();
		try {
			comboBox.setModel(new DefaultComboBoxModel(ll.displayDBList()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		comboBox.setBounds(20, 30, 400, 24);
		contentPane.add(comboBox);
		
		JLabel lblDatabasesCurrentlyIn = new JLabel("Please Select a Database to Remove from the List");
		lblDatabasesCurrentlyIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabasesCurrentlyIn.setBounds(20, 10, 400, 15);
		contentPane.add(lblDatabasesCurrentlyIn);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = (String)comboBox.getSelectedItem();
				addOrRemoveDB db = new addOrRemoveDB();
				try {
					db.removeDB(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					FileGenerated added = new FileGenerated(file+" has been removed from the list.");
					added.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					added.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRemove.setBounds(150, 92, 117, 25);
		contentPane.add(btnRemove);
	}

}
