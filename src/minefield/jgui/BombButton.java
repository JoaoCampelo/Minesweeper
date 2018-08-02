package minefield.jgui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JButton;

import edu.ufp.sd.minesweeper.game.Jogadas;
import minefield.jgui.ImageIconResourcer;
import minefield.jgui.MainFrame;
import minefield.listeners.BombButtonPressedListener;
import models.GameModeManager;

/**
 * This class represents a button in the minefield. When the button is pressed,
 * it will show up its state. If the button is an empty field, the buttons
 * closed to it will show their state automatically.
 * <p>
 *
 * @author Sorin ( soriniulus@yahoo.com ) At: Apr 8, 2007, 12:50:57 PM
 */
public class BombButton extends JButton implements BombButtonPressedListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int row;

    private int column;

    private int state;

    private Vector listenerList;

    private boolean buttonPressed;

    private int rightCliksNumber;

    /**
     * This constructor builds a bomb button.
     * <p>
     *
     * @param row the row index of the bomb button.
     * @param column the column index of the bomb button.
     * @param state the state of the button.
     */
    public BombButton(int row, int column, int state) {
        super();
        this.row = row;
        this.column = column;
        this.state = state;
        rightCliksNumber = 0;
        //
        setDefaultState();
        //
        addMouseListener(new BombButtonListener());
        //
        listenerList = new Vector();
        buttonPressed = false;
    }

    /**
     * Adds a listener at this button.
     * <p>
     *
     * @param listener a neighbour button that listens for this button's
     * discovering.
     */
    public void addBombButtonPressedListener(BombButtonPressedListener listener) {
        listenerList.add(listener);
    }

    /**
     * Remove a listener of this button.
     *
     * @param listener a neighbour button that listens for this button's
     * discovering.
     */
    public void removeBombButtonPressedListener(
            BombButtonPressedListener listener) {
        listenerList.remove(listener);
    }

    private void setDefaultState() {
        setIcon(null);
        setPreferredSize(new Dimension(20, 20));
    }

    /**
     * Returns the state of the button;
     * <p>
     *
     * @return the state of the button;
     */
    public int getState() {
        return state;
    }

    /**
     * Returns the row of this buttons.
     * <p>
     *
     * @return the row of this buttons.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of this button.
     * <p>
     *
     * @return the column of this button.
     */
    public int getColumn() {
        return column;
    }

    private void bombButtonPressed() {
        buttonPressed = true;
        switch (getState()) {
            case -1: {
                setIcon(ImageIconResourcer.getInstance().getIcon_1());
                askForNewGame();
                fireBombButtonPressed();
                return;
            }
            case 0: {
                setIcon(ImageIconResourcer.getInstance().getIcon0());
            }
            break;
            case 1: {
                setIcon(ImageIconResourcer.getInstance().getIcon1());
            }
            break;
            case 2: {
                setIcon(ImageIconResourcer.getInstance().getIcon2());
            }
            break;
            case 3: {
                setIcon(ImageIconResourcer.getInstance().getIcon3());
            }
            break;
            case 4: {
                setIcon(ImageIconResourcer.getInstance().getIcon4());
            }
            break;
            case 5: {
                setIcon(ImageIconResourcer.getInstance().getIcon5());
            }
            break;
            case 6: {
                setIcon(ImageIconResourcer.getInstance().getIcon6());
            }
            break;
            case 7: {
                setIcon(ImageIconResourcer.getInstance().getIcon7());
            }
            break;
            case 8: {
                setIcon(ImageIconResourcer.getInstance().getIcon8());
            }
            break;
        }
        MainFrame.getInstance().increaseNumberOfButtonsCorrectlyRevealed();
        fireBombButtonPressed();
    }

    /**
     * Reveal the state of the button.
     *
     */
    public void revealButton() {
        switch (getState()) {
            case -1: {
                setIcon(ImageIconResourcer.getInstance().getIconMark());
            }
            break;
            case 0: {
                setIcon(ImageIconResourcer.getInstance().getIcon0());
            }
            break;
            case 1: {
                setIcon(ImageIconResourcer.getInstance().getIcon1());
            }
            break;
            case 2: {
                setIcon(ImageIconResourcer.getInstance().getIcon2());
            }
            break;
            case 3: {
                setIcon(ImageIconResourcer.getInstance().getIcon3());
            }
            break;
            case 4: {
                setIcon(ImageIconResourcer.getInstance().getIcon4());
            }
            break;
            case 5: {
                setIcon(ImageIconResourcer.getInstance().getIcon5());
            }
            break;
            case 6: {
                setIcon(ImageIconResourcer.getInstance().getIcon6());
            }
            break;
            case 7: {
                setIcon(ImageIconResourcer.getInstance().getIcon7());
            }
            break;
            case 8: {
                setIcon(ImageIconResourcer.getInstance().getIcon8());
            }
            break;
        }
    }

    private void askForNewGame() {
        MainFrame.getInstance().gameLost(row, column);
    }

    /**
     * This class represents the listener for the bomb button.
     *
     * @author Sorin ( soriniulus@yahoo.com ) At: Apr 8, 2007, 1:05:14 PM
     */
    private class BombButtonListener implements MouseListener {

        private Jogadas jogada;

        public void mouseClicked(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {

            if (MainFrame.getInstance().isGameLost()) {
                return;
            }

            if (MainFrame.getInstance().isGameWon()) {
                return;
            }

            if (!MainFrame.getInstance().isGameStarted()) {
                MainFrame.getInstance().gameStarted(true);
            }

            if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK
                    + MouseEvent.BUTTON3_DOWN_MASK) {
                this.jogada = new Jogadas(
                        BombButton.this.row,
                        BombButton.this.column,
                        1,
                        MainFrame.getInstance().getMsClientRI(),
                        MainFrame.getInstance().getNomeJogo()
                );

                this.notifyServer();
            } else if (e.getButton() == MouseEvent.BUTTON1) {
                this.jogada = new Jogadas(
                        BombButton.this.row,
                        BombButton.this.column,
                        2,
                        MainFrame.getInstance().getMsClientRI(),
                        MainFrame.getInstance().getNomeJogo()
                );

                this.notifyServer();
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                this.jogada = new Jogadas(
                        BombButton.this.row,
                        BombButton.this.column,
                        3,
                        MainFrame.getInstance().getMsClientRI(),
                        MainFrame.getInstance().getNomeJogo()
                );

                this.notifyServer();
            }
        }

        private void notifyServer() {
            if (MainFrame.getInstance().getMinhaVez()) {
                try {
                    MainFrame.getInstance().getMsServerRI().fazerJogada(jogada);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        public void mouseReleased(MouseEvent e) {
            fireIncorrectNumberOfMarkedFieldPressed(false);
        }
    }

    public void handleMouseListener(Jogadas jogada) {
        if (!MainFrame.getInstance().isGameStarted()) {
            MainFrame.getInstance().gameStarted(true);
        }
        switch (jogada.getTipoMovimento()) {
            case 1: {
                if (getState() == getNumberOfMarkedField()) {
                    fireMarkedFieldPressed();
                } else {
                    fireIncorrectNumberOfMarkedFieldPressed(true);
                }
                break;
            }
            case 2: {
                if (BombButton.this.getIcon() != null) {
                    return;
                }
                bombButtonPressed();
                fireBombButtonPressed();
            }
            case 3: {
                if (!GameModeManager.getInstance().isQuestionsMarksOn()) {
                    rightCliksNumber = 0;
                    if (getIcon() == null) {
                        setIcon(ImageIconResourcer.getInstance().getIconMark());
                        GameModeManager.getInstance()
                                .setBombsNumber(
                                        GameModeManager.getInstance()
                                                .getBombsNumber() - 1);
                    } else if (!buttonPressed) {
                        if (getIcon() != ImageIconResourcer.getInstance()
                                .getIconQuestionMark()) {
                            GameModeManager.getInstance().setBombsNumber(
                                    GameModeManager.getInstance()
                                            .getBombsNumber() + 1);
                        }
                        setIcon(null);
                    }
                } else {
                    rightCliksNumber++;
                    if (rightCliksNumber == 3) {
                        rightCliksNumber = 0;
                    }
                    switch (rightCliksNumber) {
                        case 0: {
                            setIcon(null);
                        }
                        break;
                        case 1: {
                            if (getIcon() == ImageIconResourcer.getInstance()
                                    .getIconMark()) {
                                rightCliksNumber++;
                                setIcon(ImageIconResourcer.getInstance()
                                        .getIconQuestionMark());
                                GameModeManager.getInstance().setBombsNumber(
                                        GameModeManager.getInstance()
                                                .getBombsNumber() + 1);
                            } else if (getIcon() == null) {
                                setIcon(ImageIconResourcer.getInstance()
                                        .getIconMark());
                                GameModeManager.getInstance().setBombsNumber(
                                        GameModeManager.getInstance()
                                                .getBombsNumber() - 1);
                            }
                        }
                        break;
                        case 2: {
                            setIcon(ImageIconResourcer.getInstance()
                                    .getIconQuestionMark());
                            GameModeManager.getInstance()
                                    .setBombsNumber(
                                            GameModeManager.getInstance()
                                                    .getBombsNumber() + 1);
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * Inform this button's listeners that this button was pressed.
     */
    public void fireBombButtonPressed() {
        if (listenerList == null || listenerList.size() == 0) {
            return;
        }
        Object[] listeners = listenerList.toArray();
        if (getState() == 0) {
            for (int i = 0; i < listeners.length; i++) {
                if (((BombButton) listeners[i]).getState() != -1
                        && ((BombButton) listeners[i]).getIcon() == null) {
                    ((BombButton) listeners[i]).bombButtonPressed();
                }
            }
        }
    }

    /**
     * This method is called when a field for which the bombs are supposed to be
     * correctly set is 2-clicked.
     *
     */
    private void fireMarkedFieldPressed() {
        if (listenerList == null || listenerList.size() == 0) {
            return;
        }
        Object[] listeners = listenerList.toArray();
        for (int i = 0; i < listeners.length; i++) {
            if (((BombButton) listeners[i]).getIcon() == null) {
                ((BombButton) listeners[i]).bombButtonPressed();
            }
        }
    }

    private void fireIncorrectNumberOfMarkedFieldPressed(boolean pressed) {
        if (listenerList == null || listenerList.size() == 0) {
            return;
        }
        Object[] listeners = listenerList.toArray();
        for (int i = 0; i < listeners.length; i++) {
            if (pressed) {
                if (((BombButton) listeners[i]).getIcon() == null) {
                    ((BombButton) listeners[i]).setIcon(ImageIconResourcer
                            .getInstance().getTryToRevealIcon());
                }
            } else {
                if (((BombButton) listeners[i]).getIcon() == ImageIconResourcer
                        .getInstance().getTryToRevealIcon()) {
                    ((BombButton) listeners[i]).setIcon(null);
                }
            }
        }
    }

    /**
     * Returns the number of marked field for this button.
     * <p>
     *
     * @return the number of marked field for this button.
     */
    private int getNumberOfMarkedField() {
        if (listenerList == null || listenerList.size() == 0) {
            return 0;
        }
        Object[] listeners = listenerList.toArray();
        //
        int numberOfMarkedField = 0;
        //
        for (int i = 0; i < listeners.length; i++) {
            if (((BombButton) listeners[i]).getIcon() == ImageIconResourcer
                    .getInstance().getIconMark()) {
                numberOfMarkedField++;
            }
        }
        //
        return numberOfMarkedField;
    }
}
