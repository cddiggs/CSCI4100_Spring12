import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class GUI_HTMLtest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launchs the HTML Creation Main Menu application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_HTMLtest frame = new GUI_HTMLtest();
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
	public GUI_HTMLtest() {
		setTitle("HTML Test Main Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHowWouldYou = new JLabel("HTML Test Options");
		lblHowWouldYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblHowWouldYou.setBounds(40, 15, 340, 15);
		contentPane.add(lblHowWouldYou);
		
		JButton btnSpecificQuestions = new JButton("Create Test using Specific Questions from Database");
		btnSpecificQuestions.setBounds(10, 40, 420, 25);
		btnSpecificQuestions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {try {
				GUI_SpecificQuestionsHTML frame = new GUI_SpecificQuestionsHTML();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
            }
        });
		contentPane.add(btnSpecificQuestions);
		
		JButton btnAllQuestionsFrom = new JButton("Create Test Using All Questions from Database");
		btnAllQuestionsFrom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {try {
				GUI_AllSolutionsHTML frame = new GUI_AllSolutionsHTML();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
            }
        });
		btnAllQuestionsFrom.setBounds(10, 80, 420, 25);
		contentPane.add(btnAllQuestionsFrom);
		
		JButton btnRandomquestions = new JButton("Create Test Using Random Questions");
		btnRandomquestions.setBounds(10, 120, 420, 25);
		btnRandomquestions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {try {
				GUI_RandomQuestionsHTML frame = new GUI_RandomQuestionsHTML();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
            }
        });
		contentPane.add(btnRandomquestions);
		
		JButton btnSolutionSpecific = new JButton("Create Solution Guide using Specific Questions");
		btnSolutionSpecific.setBounds(10, 160, 420, 25);
		btnSolutionSpecific.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {try {
				GUI_SpecificSolutionsHTML frame = new GUI_SpecificSolutionsHTML();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
            }
        });
		contentPane.add(btnSolutionSpecific);
		
		JButton btnSolutionAll = new JButton("Create Solution Guide using All Questions");
		btnSolutionAll.setBounds(10, 200, 420, 25);
		btnSolutionAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {try {
				GUI_AllSolutionsHTML frame = new GUI_AllSolutionsHTML();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
            }
        });
		contentPane.add(btnSolutionAll);
	}
}
