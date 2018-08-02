package minefield.jgui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import minefield.generator.MineFieldGenerator;
import models.GameMode;
import models.GameModeManager;

/**
 * This class represents the mine field panel.
 *
 * @author Sorin ( soriniulus@yahoo.com ) At: Apr 8, 2007, 12:47:32 PM
 */
public class MineFieldPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private BombButton[][] bombButtons;

    private BombButton[][] gameLostBombButtons;

    private JPanel gameLostMineFieldPanel;

    private String gameMode;

    /**
     * This constructor creates a mine field panel, based on a new mine field
     * generation.
     *
     */
    public MineFieldPanel() {
        try {
            this.gameMode = MainFrame.getInstance().getMsServerRI().getModoJogo(MainFrame.getInstance().getNomeJogo());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (gameMode == null) {
            System.out.println("Game level invalid");
            return;
        }
        init();
    }

    public void init() {
        setLayout(new GridBagLayout());
        //
        gameLostMineFieldPanel = new JPanel();
        gameLostMineFieldPanel.setLayout(new GridBagLayout());
        //
        if (gameMode.equalsIgnoreCase(GameMode.expertMode)) {
            ((GridBagLayout) getLayout()).columnWidths = new int[]{25, 25,
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0};
            ((GridBagLayout) getLayout()).rowHeights = new int[]{25, 25, 25,
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
                25, 25, 0};
            ((GridBagLayout) getLayout()).columnWeights = new double[]{0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout) getLayout()).rowWeights = new double[]{0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            //
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).columnWidths = new int[]{
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
                0};
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).rowHeights = new int[]{
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
                25, 25, 25, 25, 25, 0};
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).columnWeights = new double[]{
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).rowWeights = new double[]{
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        } else if (gameMode.equalsIgnoreCase(GameMode.mediumMode)) {
            ((GridBagLayout) getLayout()).columnWidths = new int[]{25, 25,
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
                25, 25, 25, 0};
            ((GridBagLayout) getLayout()).rowHeights = new int[]{25, 25, 25,
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0};
            ((GridBagLayout) getLayout()).columnWeights = new double[]{0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout) getLayout()).rowWeights = new double[]{0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 1.0E-4};
            //
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).columnWidths = new int[]{
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
                25, 25, 25, 25, 25, 0};
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).rowHeights = new int[]{
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
                0};
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).columnWeights = new double[]{
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).rowWeights = new double[]{
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 1.0E-4};
        } else if (gameMode.equalsIgnoreCase(GameMode.juniorMode)) {
            ((GridBagLayout) getLayout()).columnWidths = new int[]{25, 25,
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0};
            ((GridBagLayout) getLayout()).rowHeights = new int[]{25, 25, 25,
                25, 25, 25, 25, 25, 25, 25, 0};
            ((GridBagLayout) getLayout()).columnWeights = new double[]{0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 1.0E-4};
            ((GridBagLayout) getLayout()).rowWeights = new double[]{0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            //
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).columnWidths = new int[]{
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
                0};
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).rowHeights = new int[]{
                25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0};
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).columnWeights = new double[]{
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout) gameLostMineFieldPanel.getLayout()).rowWeights = new double[]{
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        } else {
            return;
        }
        //
        GameModeManager.getInstance().reset();

        /*MineFieldGenerator mineFieldGenerator = new MineFieldGenerator(
				GameModeManager.getInstance().getMineFieldWidth(),
				GameModeManager.getInstance().getMineFieldHeight(),
				GameModeManager.getInstance().getBombsNumber());*/
        MineFieldGenerator mineFieldGenerator = null;
        try {
            mineFieldGenerator = MainFrame.getInstance().getMsServerRI().tabuleiro(MainFrame.getInstance().getNomeJogo());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        //
        if (mineFieldGenerator == null) {
            return;
        }
        //
        int[][] mineField = mineFieldGenerator.getGeneratedMinefield();
        bombButtons = new BombButton[mineField.length][mineField[0].length];
        //
        gameLostBombButtons = new BombButton[mineField.length][mineField[0].length];
        //
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                bombButtons[i][j] = new BombButton(i, j, mineField[i][j]);
                add(bombButtons[i][j], new GridBagConstraints(j, i, 1, 1, 0.0,
                        0.0, GridBagConstraints.CENTER,
                        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
            }
        }
        //
        for (int i = 0; i < bombButtons.length; i++) {
            for (int j = 0; j < bombButtons[i].length; j++) {
                registerListeners(i, j);
            }
        }

        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                gameLostBombButtons[i][j] = new BombButton(i, j,
                        mineField[i][j]);
                gameLostMineFieldPanel.add(gameLostBombButtons[i][j],
                        new GridBagConstraints(j, i, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER,
                                GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));
                gameLostBombButtons[i][j].revealButton();
            }
        }
    }

    /**
     * Returns the mine field panel in case of a game lost.
     * <p>
     * @return	the mine field panel in case of a game lost.
     */
    public JPanel getGameLostMineFieldPanel() {
        return gameLostMineFieldPanel;
    }

    /**
     * Registers the listeners for the button at the ( row, column ) position.
     * <p>
     * @param row	the row of the button.
     * @param column	the column of the button.
     */
    private void registerListeners(int row, int column) {
        if (row == 0) {
            if (column == 0) {
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column + 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column + 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column]);
            } else if (column == bombButtons[row].length - 1) {
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column]);
            } else {
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column + 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column + 1]);
            }
        } else if (row == bombButtons.length - 1) {
            if (column == 0) {
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column + 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column + 1]);
            } else if (column == bombButtons[row].length - 1) {
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column - 1]);
            } else {
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column + 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column + 1]);
            }
        } else {
            if (column == 0) {
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column + 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column + 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column + 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column]);
            } else if (column == bombButtons[row].length - 1) {
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column]);
            } else {
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row - 1][column + 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row][column + 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column - 1]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column]);
                bombButtons[row][column]
                        .addBombButtonPressedListener(bombButtons[row + 1][column + 1]);
            }
        }
    }

    /**
     * Returns the bomb buttons.
     * <p>
     *
     * @return	the bomb buttons.
     */
    public BombButton[][] getBombButtons() {
        return bombButtons;
    }

    /**
     * Returns the bomb buttons in case of a lost game.
     * <p>
     *
     * @return	the bomb buttons in case of a lost game.
     */
    public BombButton[][] getGameLostBombButtons() {
        return gameLostBombButtons;
    }

}
