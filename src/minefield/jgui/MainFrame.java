package minefield.jgui;

import edu.ufp.sd.minesweeper.client.MinesweeperClientHallGUI;
import edu.ufp.sd.minesweeper.client.MinesweeperClientImpl;
import edu.ufp.sd.minesweeper.client.MinesweeperClientRI;
import edu.ufp.sd.minesweeper.server.MinesweeperServerRI;
import highscores.HighScoresDialog;
import highscores.HighScoresDialogMode;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import minefield.listeners.GameModeListener;
import models.GameMode;
import models.GameModeManager;
import timers.ClockTimer;
import timers.SystemTimeUpdater;

/**
 * This class represents the main frame of the application.
 * <p>
 * The main frame contains the mine field area ( displayed in the center ), the toolbar ( displayed in the
 * <code>BorderLayout.NORTH</code> side ) and the status bar ( displayed in the <code>BorderLayout.SOUTH</code>
 * side). 
 * <p>
 * The main frame registers itself as a listener to the game mode changes. When the game mode is changed,
 * a new game is started.
 * <p>
 * The toolbar offers informations about the number of mines left undiscovered, the total amount of time
 * ellapsed since a new game has started, and a button that allows the user to start a new game.
 * <p>
 * The status bar shows the current system time.
 * <p>
 * The menu bar of the application allows the user to change the game mode, start a new game, exit the 
 * game, or providing informations about how the game is played ( from the <code>Help</code> menu).
 * 
 * @author Sorin ( soriniulus@yahoo.com )
 * At: Apr 8, 2007, 8:04:01 PM
 */
public class MainFrame extends JFrame implements GameModeListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static MainFrame INSTANCE = new MainFrame();
    
    private MineFieldPanel mineFieldPanel;

    public MineFieldPanel getMineFieldPanel() {
        return mineFieldPanel;
    }

    public void setMineFieldPanel(MineFieldPanel mineFieldPanel) {
        this.mineFieldPanel = mineFieldPanel;
    }

    private boolean gameStarted;

    private int numberOfButtonsRevealed;

    private boolean gameLost;

    private boolean gameWon;

    private boolean mainFrameIconified;

    private ArrayList<MinesweeperClientRI> clients = new ArrayList<>(4);
    
    public void setClients(ArrayList<MinesweeperClientRI> clients) {
        this.clients = clients;
    }

    public ArrayList<MinesweeperClientRI> getClients() {
        return clients;
    }

    private String roomName;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    private int roomID;

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getRoomID() {
        return roomID;
    }

    private MinesweeperClientHallGUI hall;

    public MinesweeperClientHallGUI getHall() {
        return hall;
    }

    public void setHall(MinesweeperClientHallGUI hall) {
        this.hall = hall;
    }
    
    
    
    private MinesweeperServerRI msServerRI;

    public MinesweeperServerRI getMsServerRI() {
        return msServerRI;
    }

    public void setMsServerRI(MinesweeperServerRI msServerRI) {
        this.msServerRI = msServerRI;
    }
    
    
    
    private MinesweeperClientImpl msClient;

    public MinesweeperClientImpl getMsClient() {
        return msClient;
    }

    public void setMsClient(MinesweeperClientImpl msClient) {
        this.msClient = msClient;
    }
    
    private MinesweeperClientRI msClientRI;

    public MinesweeperClientRI getMsClientRI() {
        return msClientRI;
    }

    public void setMsClientRI(MinesweeperClientRI msClientRI) {
        this.msClientRI = msClientRI;
    }
    

    private boolean gerado = false;

    public boolean isGerado() {
        return gerado;
    }

    public void setGerado(boolean gerado) {
        this.gerado = gerado;
    }

    private Boolean minhaVez;

    public Boolean getMinhaVez() {
        return minhaVez;
    }

    public void setMinhaVez(Boolean minhaVez) {
        this.minhaVez = minhaVez;
    }
    
    private String nomeJogo;

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public MainFrame() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);
        setTitle("JMinesweeper by Sorin Iulus ( soriniulus@yahoo.com )");
        //
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                        shutDown();
                }

                public void windowIconified(WindowEvent e) {
                        mainFrameIconified = true;
                }

                public void windowDeiconified(WindowEvent e) {
                        mainFrameIconified = false;
                }
        });
    }

    /**
     * Returns the single instance of this clas.
     * <p>
     * @return			the single instance of this clas
     */
    public static MainFrame getInstance() {
            return INSTANCE;
    }

    /**
     * Start a new game.
     *
     */
    public void startNewGame() {
        try {
            this.msServerRI.restartGame(this.nomeJogo);
        } catch (RemoteException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void restartGame() {
        gameStarted(false);
        setGameLost(false);
        setGameWon(false);

        mineFieldPanel = new MineFieldPanel();
         
        getContentPane().removeAll();
        getContentPane().add(getMineFieldPanel(), BorderLayout.CENTER);
        getContentPane().add(MineSweeperToolbar.getInstance(), BorderLayout.NORTH);
        getContentPane().add(MineSweeperStatusBar.getInstance(), BorderLayout.SOUTH);
        MineSweeperToolbar.getInstance().initClock();
        pack();
    }

    /**
     * Increase the number of buttons correctly revealed. When all the buttons are correctly revealed, inform
     * the user that he won. 
     *
     */
    public void increaseNumberOfButtonsCorrectlyRevealed() {
            numberOfButtonsRevealed++;
            if (numberOfButtonsRevealed == GameModeManager.getInstance()
                            .getMineFieldHeight()
                            * GameModeManager.getInstance().getMineFieldWidth()
                            - GameModeManager.getInstance().getTotalNumberOfBombs()) {

                    int secondsHighScore = MineSweeperToolbar.getInstance()
                                    .getTimeEllapsed();

                    gameStarted(false);

                    for (int i = 0; i < getMineFieldPanel().getBombButtons().length; i++) {
                            for (int j = 0; j < getMineFieldPanel().getBombButtons()[i].length; j++) {
                                    if (getMineFieldPanel().getBombButtons()[i][j].getIcon() == null) {
                                            getMineFieldPanel().getBombButtons()[i][j]
                                                            .setIcon(ImageIconResourcer.getInstance()
                                                                            .getIconMark());
                                    }
                            }
                    }
                    //

                    setGameWon(true);

                    if (GameModeManager.getInstance().getGameMode() == GameMode.expertMode) {
                            Preferences pref = Preferences.userRoot();
                            int time = 999;
                            time = pref.node("/jminesweeper").getInt("experttime", 999);
                            if (time > secondsHighScore) {
                                    new HighScoresDialog(MainFrame.getInstance(),
                                                    "High scores", true,
                                                    HighScoresDialogMode.EXPERT_UPDATE_MODE,
                                                    secondsHighScore).setVisible(true);
                            }
                    }
                    if (GameModeManager.getInstance().getGameMode() == GameMode.expertMode) {
                            Preferences pref = Preferences.userRoot();
                            int time = 999;
                            time = pref.node("/jminesweeper").getInt("experttime", 999);
                            if (time > secondsHighScore) {
                                    new HighScoresDialog(MainFrame.getInstance(),
                                                    "High scores", true,
                                                    HighScoresDialogMode.EXPERT_UPDATE_MODE,
                                                    secondsHighScore);
                            }
                    }
                    if (GameModeManager.getInstance().getGameMode() == GameMode.mediumMode) {
                            Preferences pref = Preferences.userRoot();
                            int time = 999;
                            time = pref.node("/jminesweeper").getInt("mediumtime", 999);
                            if (time > secondsHighScore) {
                                    new HighScoresDialog(MainFrame.getInstance(),
                                                    "High scores", true,
                                                    HighScoresDialogMode.MEDIUM_UPDATE_MODE,
                                                    secondsHighScore);
                            }
                    }
                    if (GameModeManager.getInstance().getGameMode() == GameMode.juniorMode) {
                            Preferences pref = Preferences.userRoot();
                            int time = 999;
                            time = pref.node("/jminesweeper").getInt("juniortime", 999);
                            if (time > secondsHighScore) {
                                    new HighScoresDialog(MainFrame.getInstance(),
                                                    "High scores", true,
                                                    HighScoresDialogMode.JUNIOR_UPDATE_MODE,
                                                    secondsHighScore);
                            }
                    }
            }
    }

    /**
     * Ask the user if he really wants to shut down the application. If not, a new game beggins. Else
     * the application shuts down.
     *
     */
    public void shutDown() {
            int answer = JOptionPane.showConfirmDialog(MainFrame.this,
                            "Are you sure you want to exit?", "Exit?",
                            JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                    dispose();
                    gameStarted(false);
                    SystemTimeUpdater.getInstance().cancel();
            }
    }

    /**
     * Init the clock timer when the game starts, and cancel it when the game is lost.
     * <p>
     * @param 			b			whether the game is started or over.
     */
    public void gameStarted(boolean b) {
            gameStarted = b;
            if (b) {
                    ClockTimer.newInstance();
                    numberOfButtonsRevealed = 0;
            } else {
                    if (ClockTimer.getInstance() != null) {
                            ClockTimer.getInstance().cancel();
                    }
            }
    }

    /**
     * Returns whether the user started the game or not. The game is consider to be started when the first
     * button in the minefield is pressed.
     * <p>
     * @return			whether the user started the game or not.
     */
    public boolean isGameStarted() {
            return gameStarted;
    }

    /**
     * The player lost the game.
     *
     */
    public void gameLost(int row, int column) {
            gerado=false;
            gameStarted(false);
            setGameLost(true);
            //
            getContentPane().removeAll();
            getContentPane().add(getMineFieldPanel().getGameLostMineFieldPanel(),
                            BorderLayout.CENTER);

            for (int i = 0; i < getMineFieldPanel().getBombButtons().length; i++) {
                    for (int j = 0; j < getMineFieldPanel().getBombButtons()[i].length; j++) {
                            if (getMineFieldPanel().getBombButtons()[i][j].getIcon() == ImageIconResourcer
                                            .getInstance().getIconMark()
                                            && getMineFieldPanel().getBombButtons()[i][j].getState() != -1) {
                                    getMineFieldPanel().getGameLostBombButtons()[i][j]
                                                    .setIcon(ImageIconResourcer.getInstance()
                                                                    .getIconBombWrong());
                            }
                            if (getMineFieldPanel().getBombButtons()[i][j].getIcon() == null
                                            && getMineFieldPanel().getBombButtons()[i][j].getState() == -1) {
                                    getMineFieldPanel().getGameLostBombButtons()[i][j]
                                                    .setIcon(ImageIconResourcer.getInstance()
                                                                    .getIconBombUnfind());
                            }
                    }
            }

            getMineFieldPanel().getGameLostBombButtons()[row][column]
                            .setIcon(ImageIconResourcer.getInstance().getIcon_1());

            getContentPane().add(MineSweeperToolbar.getInstance(),
                            BorderLayout.NORTH);
            getContentPane().add(MineSweeperStatusBar.getInstance(),
                            BorderLayout.SOUTH);
            pack();
    }

    /**
     * Returns true if the game is lost. This helps when the user wants to continue clicking on buttons,
     * but the game is already lost.
     * <p>
     * @return			true if the game is lost.
     */
    public boolean isGameLost() {
            return gameLost;
    }

    /**
     * Set the status of the game: lost or not.
     * <p>
     * @param 			b			whether the game is lost or not.
     */
    private void setGameLost(boolean b) {
            gameLost = b;
    }

    private void setGameWon(boolean b) {
            gameWon = b;
    }

    /**
     * Returns true if the user won the game.
     * <p>
     * 
     * @return			true if the user won the game.
     */
    public boolean isGameWon() {
            return gameWon;
    }

    /**
     * Notify the main frame that the game mode has changed.
     */
    public void gameModeChanged() {
            startNewGame();
    }

    /**
     * Returns true if the main frame is iconified.
     * <p>
     * 
     * @return			true if the main frame is iconified.
     */
    public boolean isMainFrameIconified() {
            return mainFrameIconified;
    }

    public int getConnectedClients() {
        int sum = 0;
        for (MinesweeperClientRI client : this.clients) {
            System.out.println("teste");
            if (client != null) {
                sum++;
            }
        }
        System.out.println("Connected clients in Room # " + this.roomID + " - " + sum);
        return sum;
    }

    
}
