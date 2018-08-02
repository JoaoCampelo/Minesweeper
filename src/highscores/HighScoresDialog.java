package highscores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import minefield.jgui.MainFrame;

/**
 * This class represents the high scores dialog.
 * 
 * @author Sorin ( soriniulus@yahoo.com )
 * At: Apr 17, 2007, 10:37:14 PM
 */
public class HighScoresDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dialogMode;

	private JPanel contentPanel;

	private JLabel gameModeLabel;

	private JLabel timeLabel;

	private JLabel nameLabel;

	private JSeparator separator1;

	private JLabel expertModeLabel;

	private JLabel expertTimeLabel;

	private JTextField expertUserTextField;

	private JLabel mediumModeLabel;

	private JLabel mediumTimeLabel;

	private JTextField mediumUserTextField;

	private JLabel juniorModeLabel;

	private JLabel juniorTimeLabel;

	private JTextField juniorUserTextField;

	private JPanel buttonsPanel;

	private JButton okButton;

	private JButton resetButton;

	private Preferences pref = Preferences.userRoot();

	private int time;

	/**
	 * Constructor.
	 * <p>
	 * 
	 * @param 			owner			the parent of this dialog
	 * @param 			title			the dialog title
	 * @param 			modal			modal mode
	 * @param 			mode			the purpose of this dialog ( either update the time for a specific game
	 * 									mode, or just display the best times )
	 * @param 			time			the new time record
	 */
	public HighScoresDialog(JFrame owner, String title, boolean modal,
			String mode, int time) {
		super(owner, title, modal);
		//
		this.dialogMode = mode;
		this.time = time;
		//
		setResizable(false);
		//
		init();
		pack();
		setLocationRelativeTo(MainFrame.getInstance());
		setVisible(true);

	}

	private void init() {
		contentPanel = new JPanel();
		gameModeLabel = new JLabel();
		timeLabel = new JLabel();
		nameLabel = new JLabel();
		separator1 = new JSeparator();
		expertModeLabel = new JLabel();
		expertTimeLabel = new JLabel();
		expertUserTextField = new JTextField();
		mediumModeLabel = new JLabel();
		mediumTimeLabel = new JLabel();
		mediumUserTextField = new JTextField();
		juniorModeLabel = new JLabel();
		juniorTimeLabel = new JLabel();
		juniorUserTextField = new JTextField();
		buttonsPanel = new JPanel();
		okButton = new JButton();
		resetButton = new JButton();
		//

		Color backgroundColor = new Color(153, 204, 255);

		LineBorder lineBorder = new LineBorder(new Color(0, 0, 102));

		Font font = new Font("Tahoma", Font.BOLD, 11);

		contentPanel.setBackground(backgroundColor);

		contentPanel.setLayout(new GridBagLayout());
		((GridBagLayout) contentPanel.getLayout()).columnWidths = new int[] {
				15, 105, 105, 105, 10, 0 };
		((GridBagLayout) contentPanel.getLayout()).rowHeights = new int[] { 15,
				27, 7, 27, 27, 27, 15, 27, 10, 0 };
		((GridBagLayout) contentPanel.getLayout()).columnWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPanel.getLayout()).rowWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

		//---- gameModeLabel ----
		gameModeLabel.setText("Game mode");
		gameModeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(gameModeLabel, new GridBagConstraints(1, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		//---- timeLabel ----
		timeLabel.setText("Time");
		timeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(timeLabel, new GridBagConstraints(2, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		//---- nameLabel ----
		nameLabel.setText("Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(nameLabel, new GridBagConstraints(3, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		contentPanel.add(separator1, new GridBagConstraints(0, 2, 5, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));

		//---- expertModeLabel ----
		expertModeLabel.setText("Expert mode");
		expertModeLabel.setFont(font);
		contentPanel.add(expertModeLabel, new GridBagConstraints(1, 3, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		//---- expertTimeLabel ----
		expertTimeLabel.setText("text");
		expertTimeLabel.setFont(font);
		expertTimeLabel.setForeground(Color.red);
		contentPanel.add(expertTimeLabel, new GridBagConstraints(2, 3, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		//---- expertUserTextField ----
		expertUserTextField.setFont(font);
		expertUserTextField.setBorder(lineBorder);
		expertUserTextField.setBackground(backgroundColor);
		contentPanel.add(expertUserTextField, new GridBagConstraints(3, 3, 1,
				1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		//---- mediumModeLabel ----
		mediumModeLabel.setText("Medium mode");
		mediumModeLabel.setFont(font);
		contentPanel.add(mediumModeLabel, new GridBagConstraints(1, 4, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		//---- mediumTimeLabel ----
		mediumTimeLabel.setText("text");
		mediumTimeLabel.setFont(font);
		mediumTimeLabel.setForeground(Color.red);
		contentPanel.add(mediumTimeLabel, new GridBagConstraints(2, 4, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		//---- mediumUserTextField ----
		mediumUserTextField.setFont(font);
		mediumUserTextField.setBorder(lineBorder);
		mediumUserTextField.setBackground(backgroundColor);
		contentPanel.add(mediumUserTextField, new GridBagConstraints(3, 4, 1,
				1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		//---- juniorModeLabel ----
		juniorModeLabel.setText("Junior mode");
		juniorModeLabel.setFont(font);
		contentPanel.add(juniorModeLabel, new GridBagConstraints(1, 5, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		//---- juniorTimeLabel ----
		juniorTimeLabel.setText("text");
		juniorTimeLabel.setFont(font);
		juniorTimeLabel.setForeground(Color.red);
		contentPanel.add(juniorTimeLabel, new GridBagConstraints(2, 5, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		//---- juniorUserTextField ----
		juniorUserTextField.setFont(font);
		juniorUserTextField.setBorder(lineBorder);
		juniorUserTextField.setBackground(backgroundColor);
		contentPanel.add(juniorUserTextField, new GridBagConstraints(3, 5, 1,
				1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		//======== buttonsPanel ========
		{
			buttonsPanel.setBackground(backgroundColor);
			buttonsPanel.setLayout(new GridBagLayout());
			((GridBagLayout) buttonsPanel.getLayout()).columnWidths = new int[] {
					0, 0, 0, 0 };
			((GridBagLayout) buttonsPanel.getLayout()).rowHeights = new int[] {
					0, 0 };
			((GridBagLayout) buttonsPanel.getLayout()).columnWeights = new double[] {
					1.0, 0.0, 0.0, 1.0E-4 };
			((GridBagLayout) buttonsPanel.getLayout()).rowWeights = new double[] {
					0.0, 1.0E-4 };

			//---- okButton ----
			okButton.setText("Ok");
			okButton.setFont(font);
			buttonsPanel.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			//---- resetButton ----
			resetButton.setText("Reset scores");
			resetButton.setFont(font);
			buttonsPanel.add(resetButton, new GridBagConstraints(2, 0, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPanel.add(buttonsPanel, new GridBagConstraints(1, 7, 3, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		//

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		expertUserTextField.setDisabledTextColor(Color.BLACK);
		mediumUserTextField.setDisabledTextColor(Color.BLACK);
		juniorUserTextField.setDisabledTextColor(Color.BLACK);

		String expertUser = "";
		String mediumUser = "";
		String juniorUser = "";
		int expertTime = 999;
		int mediumTime = 999;
		int juniorTime = 999;

		expertUser = pref.node("/jminesweeper").get("expertuser", "Anonymus");
		mediumUser = pref.node("/jminesweeper").get("mediumuser", "Anonymus");
		juniorUser = pref.node("/jminesweeper").get("junioruser", "Anonymus");

		expertTime = pref.node("/jminesweeper").getInt("experttime", 999);
		mediumTime = pref.node("/jminesweeper").getInt("mediumtime", 999);
		juniorTime = pref.node("/jminesweeper").getInt("juniortime", 999);

		expertUserTextField.setText(expertUser);
		mediumUserTextField.setText(mediumUser);
		juniorUserTextField.setText(juniorUser);

		expertTimeLabel.setText(String.valueOf(expertTime));
		mediumTimeLabel.setText(String.valueOf(mediumTime));
		juniorTimeLabel.setText(String.valueOf(juniorTime));

		if (dialogMode
				.equalsIgnoreCase(HighScoresDialogMode.EXPERT_UPDATE_MODE)) {
			expertUserTextField.setEditable(true);
			expertUserTextField.setEnabled(true);
			expertUserTextField.setBorder(lineBorder);
			mediumUserTextField.setEditable(false);
			mediumUserTextField.setEnabled(false);
			juniorUserTextField.setEditable(false);
			juniorUserTextField.setEnabled(false);
			expertTimeLabel.setText(String.valueOf(time));
		}
		if (dialogMode
				.equalsIgnoreCase(HighScoresDialogMode.MEDIUM_UPDATE_MODE)) {
			expertUserTextField.setEditable(false);
			expertUserTextField.setEnabled(false);
			mediumUserTextField.setEditable(true);
			mediumUserTextField.setEnabled(true);
			mediumUserTextField.setBorder(lineBorder);
			juniorUserTextField.setEditable(false);
			juniorUserTextField.setEnabled(false);
			mediumTimeLabel.setText(String.valueOf(time));
		}
		if (dialogMode
				.equalsIgnoreCase(HighScoresDialogMode.JUNIOR_UPDATE_MODE)) {
			expertUserTextField.setEditable(false);
			expertUserTextField.setEnabled(false);
			mediumUserTextField.setEditable(false);
			mediumUserTextField.setEnabled(false);
			juniorUserTextField.setEditable(true);
			juniorUserTextField.setEnabled(true);
			juniorUserTextField.setBorder(lineBorder);
			juniorTimeLabel.setText(String.valueOf(time));
		}
		if (dialogMode.equalsIgnoreCase(HighScoresDialogMode.DISPLAY_MODE)) {
			expertUserTextField.setEditable(false);
			expertUserTextField.setEnabled(false);
			expertUserTextField.setBorder(null);
			mediumUserTextField.setEditable(false);
			mediumUserTextField.setEnabled(false);
			mediumUserTextField.setBorder(null);
			juniorUserTextField.setEditable(false);
			juniorUserTextField.setEnabled(false);
			juniorUserTextField.setBorder(null);
		}

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(HighScoresDialog.this,
						"Are you sure you want to reset the highscores?",
						"Reset highscores", JOptionPane.YES_NO_OPTION) == 0) {

					pref.node("/jminesweeper").put("expertuser", "Anonymus");
					pref.node("/jminesweeper").put("mediumuser", "Anonymus");
					pref.node("/jminesweeper").put("junioruser", "Anonymus");
					//
					pref.node("/jminesweeper").putInt("experttime", 999);
					pref.node("/jminesweeper").putInt("mediumtime", 999);
					pref.node("/jminesweeper").putInt("juniortime", 999);
				}
				HighScoresDialog.this.dispose();
			}
		});

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dialogMode
						.equalsIgnoreCase(HighScoresDialogMode.EXPERT_UPDATE_MODE)) {
					pref.node("/jminesweeper").put("expertuser",
							expertUserTextField.getText());
					pref.node("/jminesweeper").putInt("experttime", time);
				}
				if (dialogMode
						.equalsIgnoreCase(HighScoresDialogMode.MEDIUM_UPDATE_MODE)) {
					pref.node("/jminesweeper").put("mediumuser",
							mediumUserTextField.getText());
					pref.node("/jminesweeper").putInt("mediumtime", time);
				}
				if (dialogMode
						.equalsIgnoreCase(HighScoresDialogMode.JUNIOR_UPDATE_MODE)) {
					pref.node("/jminesweeper").put("junioruser",
							juniorUserTextField.getText());
					pref.node("/jminesweeper").putInt("juniortime", time);
				}
				HighScoresDialog.this.dispose();
			}
		});

	}
}
