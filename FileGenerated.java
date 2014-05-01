import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class FileGenerated extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FileGenerated dialog = new FileGenerated();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FileGenerated() {
		setBounds(100, 100, 200, 100);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblTestFileGenerated = new JLabel("File Generated");
			lblTestFileGenerated.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblTestFileGenerated, BorderLayout.CENTER);
		}
	}
	
	public FileGenerated(String given) {
		setBounds(100, 100, 600, 100);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblTestFileGenerated = new JLabel(given);
			lblTestFileGenerated.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblTestFileGenerated, BorderLayout.CENTER);
		}
	}

}
