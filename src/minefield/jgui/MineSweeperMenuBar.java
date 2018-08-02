package minefield.jgui;

import highscores.HighScoresDialog;
import highscores.HighScoresDialogMode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import models.GameMode;
import models.GameModeManager;

/**
 * This class represents the application menu bar.
 * <p>
 * @author Sorin ( soriniulus@yahoo.com )
 * At: Apr 12, 2007, 9:54:34 PM
 */
public class MineSweeperMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuItem exitMenuItem;

	private JCheckBoxMenuItem expertModeMenuItem;

	private JCheckBoxMenuItem mediumModeMenuItem;

	private JCheckBoxMenuItem juniorModeMenuItem;

	private JSeparator separator1;

	private JSeparator separator2;

	private JMenuItem newGameMenuItem;

	private JSeparator separator3;

	private JCheckBoxMenuItem questionMarksMenuItem;

	private JMenu fileMenu;

	private JMenu helpMenu;

	private JMenuItem instructionsMenuItem;

	private JMenuItem highScoresMenuItem;

	private static MineSweeperMenuBar INSTANCE = new MineSweeperMenuBar();

	/**
	 * Returns the single instance of this class.
	 * <p>
	 * @return			the single instance of this class.
	 */
	public static MineSweeperMenuBar getInstance() {
		return INSTANCE;
	}

	private MineSweeperMenuBar() {
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setMnemonic('X');
		expertModeMenuItem = new JCheckBoxMenuItem("Expert Mode");
		expertModeMenuItem.setMnemonic('E');
		expertModeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				KeyEvent.CTRL_MASK, false));
		mediumModeMenuItem = new JCheckBoxMenuItem("Medium Mode");
		mediumModeMenuItem.setMnemonic('M');
		mediumModeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				KeyEvent.CTRL_MASK, false));
		juniorModeMenuItem = new JCheckBoxMenuItem("Junior Mode");
		juniorModeMenuItem.setMnemonic('J');
		juniorModeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,
				KeyEvent.CTRL_MASK, false));
		separator1 = new JSeparator();
		separator2 = new JSeparator();
		newGameMenuItem = new JMenuItem("New Game");
		newGameMenuItem.setMnemonic('N');
		newGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_MASK, false));
		highScoresMenuItem = new JMenuItem("Best times...");
		highScoresMenuItem.setMnemonic('B');
		highScoresMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
				KeyEvent.CTRL_MASK, false));
		separator3 = new JSeparator();
		questionMarksMenuItem = new JCheckBoxMenuItem("Question marks");
		questionMarksMenuItem.setMnemonic('Q');
		questionMarksMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_Q, KeyEvent.CTRL_MASK, false));
		//
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		instructionsMenuItem = new JMenuItem("Instructions");
		instructionsMenuItem.setMnemonic('I');
		instructionsMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_I, KeyEvent.CTRL_MASK, false));
		setProperties();
	}

	private void setProperties() {
		fileMenu.add(newGameMenuItem);
		fileMenu.add(separator1);
		fileMenu.add(expertModeMenuItem);
		fileMenu.add(mediumModeMenuItem);
		fileMenu.add(juniorModeMenuItem);
		fileMenu.add(separator2);
		fileMenu.add(questionMarksMenuItem);
		fileMenu.add(highScoresMenuItem);
		fileMenu.add(separator3);
		fileMenu.add(exitMenuItem);
		//
		helpMenu.add(instructionsMenuItem);
		//
		add(fileMenu);
		add(helpMenu);
		//
		if (GameModeManager.getInstance().getGameMode() == GameMode.expertMode) {
			expertModeMenuItem.setSelected(true);
		} else if (GameModeManager.getInstance().getGameMode() == GameMode.mediumMode) {
			mediumModeMenuItem.setSelected(true);
		} else if (GameModeManager.getInstance().getGameMode() == GameMode.juniorMode) {
			juniorModeMenuItem.setSelected(true);
		}
		//
		expertModeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediumModeMenuItem.setSelected(false);
				juniorModeMenuItem.setSelected(false);
				if (expertModeMenuItem.isSelected()) {
					GameModeManager.getInstance().setGameMode(
							GameMode.expertMode);
				}
				expertModeMenuItem.setSelected(true);

			}
		});
		mediumModeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expertModeMenuItem.setSelected(false);
				juniorModeMenuItem.setSelected(false);
				if (mediumModeMenuItem.isSelected()) {
					GameModeManager.getInstance().setGameMode(
							GameMode.mediumMode);
				}
				mediumModeMenuItem.setSelected(true);
			}
		});
		juniorModeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expertModeMenuItem.setSelected(false);
				mediumModeMenuItem.setSelected(false);
				if (juniorModeMenuItem.isSelected()) {
					GameModeManager.getInstance().setGameMode(
							GameMode.juniorMode);
				}
				juniorModeMenuItem.setSelected(true);
			}
		});
		questionMarksMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameModeManager.getInstance().setQuestionMarksOn(
						questionMarksMenuItem.isSelected());
			}
		});
		highScoresMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HighScoresDialog(MainFrame.getInstance(), "High scores",
						true, HighScoresDialogMode.DISPLAY_MODE, 0);
			}
		});
		newGameMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().startNewGame();
			}
		});
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().shutDown();
			}
		});
		//
		final JPopupMenu popupMenu = new JPopupMenu();
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.append("\n");
		textArea
				.append("-----------JMinesweeper by Sorin Iulus ( soriniulus@yahoo.com )----------");
		textArea.append("\n\n");
		textArea.append("      INSTRUCTIONS:");
		textArea.append("\n\n");
		textArea.append("  1. Left mouse button to reveal a field");
		textArea.append("\n");
		textArea.append("  2. Right mouse button to mark a field as mine");
		textArea.append("\n");
		textArea
				.append("  3. Left click + right click on a field if you know where the bombs are");
		textArea.append("\n");
		textArea
				.append("     ( as a shortcut for revealing all its \"safe\" neighbours");
		textArea.append("\n\n");
		textArea
				.append("------------------------------------------------------------------------------------------------");
		textArea.append("\n\n");
		textArea.append("                                       GOOD LUCK !!!");
		textArea.append("\n");
		popupMenu.add(textArea);
		instructionsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(MainFrame.getInstance(), 10, 120);
			}
		});
	}
}
