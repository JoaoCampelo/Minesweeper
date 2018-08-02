package minefield;

import edu.ufp.sd.minesweeper.client.MinesweeperClientHallGUI;
import edu.ufp.sd.minesweeper.client.MinesweeperClientRI;
import edu.ufp.sd.minesweeper.server.MinesweeperServerRI;
import timers.SystemTimeUpdater;
import minefield.jgui.MainFrame;
import minefield.jgui.MineSweeperMenuBar;
import models.GameModeManager;

/**
 * The main class. This class starts the application.
 *
 * @author Sorin ( soriniulus@yahoo.com ) At: Apr 8, 2007, 8:23:57 PM
 */
public class Driver {

    public static final String absPathToResourceIcons = "/resources/icons";

    public Driver(MinesweeperServerRI msServerRI, MinesweeperClientRI msClientRI, String gameName, MinesweeperClientHallGUI hall) {
        MainFrame.getInstance().setMsServerRI(msServerRI);
        MainFrame.getInstance().setMsClientRI(msClientRI);
        MainFrame.getInstance().setNomeJogo(gameName);
        MainFrame.getInstance().setTitle(gameName);
        MainFrame.getInstance().setHall(hall);
        MainFrame.getInstance().startNewGame();
        MainFrame.getInstance().setLocationRelativeTo(null);
        GameModeManager.getInstance().addGameModeListener(MainFrame.getInstance());
        SystemTimeUpdater.getInstance();
        MainFrame.getInstance().pack();
        MainFrame.getInstance().setVisible(true);
    }
}
