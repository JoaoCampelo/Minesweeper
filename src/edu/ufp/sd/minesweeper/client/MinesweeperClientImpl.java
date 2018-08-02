package edu.ufp.sd.minesweeper.client;

import edu.ufp.sd.minesweeper.game.Jogadas;
import edu.ufp.sd.minesweeper.server.State;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import edu.ufp.sd.minesweeper.server.MinesweeperServerRI;
import minefield.jgui.MainFrame;


/**
 *
 * @author joaoc
 */
public class MinesweeperClientImpl implements MinesweeperClientRI {

    private Object lastState;

    protected MinesweeperServerRI msServerRI;

    private MinesweeperClientUserGUI LoginUI = new MinesweeperClientUserGUI(this);
    private MinesweeperClientHallGUI HallUI = null;

    private String username;
    private String password;
    private boolean loggedin;
    private boolean playing = false;

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public Object getLastState() {
        return lastState;
    }

    public void setLastState(Object lastState) {
        this.lastState = lastState;
    }

    public MinesweeperServerRI getMsServerRI() {
        return msServerRI;
    }

    public void setMsServerRI(MinesweeperServerRI msServerRI) {
        this.msServerRI = msServerRI;
    }

    public MinesweeperClientUserGUI getLoginUI() {
        return LoginUI;
    }

    public void setLoginUI(MinesweeperClientUserGUI LoginUI) {
        this.LoginUI = LoginUI;
    }

    public MinesweeperClientHallGUI getHallUI() {
        return HallUI;
    }

    public void setHallUI(MinesweeperClientHallGUI HallUI) {
        this.HallUI = HallUI;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    /**
     *
     * @param msServerRI
     * @throws RemoteException
     */
    public MinesweeperClientImpl(MinesweeperServerRI msServerRI) throws RemoteException {
        exportObjectMethod();
        this.msServerRI = msServerRI;
    }

    private void exportObjectMethod() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            System.out.println("MinesweeperClientImpl: " + e.getMessage());
        }
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        System.out.println("MinesweeperClientImpl - sendMessage(): " + message);
    }

    @Override
    public String getClientUsername() throws RemoteException {
        return this.username;
    }

    @Override
    public void update() throws RemoteException {
        System.out.println("MinesweeperClientImpl - update(): ");
        this.lastState = this.msServerRI.getState();
        if (lastState instanceof State.NewRoom) {
            System.out.println("MinesweeperClientImpl - update(): State = NewRoom ");
            State.NewRoom nr = (State.NewRoom) lastState;
            HallUI.addNewRoom(nr);

        } else if (lastState instanceof State.GenericState) {
            State.GenericState state = (State.GenericState) lastState;
            String type = state.getType();
            System.out.println("MinesweeperClientImpl - update(): State = GenericState(" + type + ")");
            switch (type) {
                case "RoomsUpdate": {
                    HallUI.updateAllRooms();
                    break;
                }
            }
        } else if (lastState instanceof State.Disconnect) {
            System.out.println("MinesweeperClientImpl - update(): State = Disconnect ");
            disconnect();
        } 
    }

    /**
     *
     * @throws RemoteException
     */
    public void disconnect() throws RemoteException {
        System.exit(0);
    }

    /**
     *
     * @param username
     * @param password
     */
    public void triggeredLogin(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        System.out.println("triggeredLogin");
        try {
            int login = msServerRI.login(MinesweeperClientImpl.this, username, password);
            System.out.println("int login " + login);
            if (login != 0) {
                System.out.println("You are now logged in!");
                this.setLoggedin(true);
                LoginUI.setVisible(false);
                HallUI = new MinesweeperClientHallGUI(this, msServerRI);
            }

        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param username
     * @param password
     */
    public void triggeredRegister(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        System.out.println("triggeredRegister");
        try {
            int register = msServerRI.register(MinesweeperClientImpl.this, username, password);
            System.out.println("int register " + register);
            if (register != 0) {
                System.out.println("You are now registed, please login!");
            }

        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param username
     * @param password
     */
    public void triggeredLogout(String username, String password) {
        System.out.println("triggeredLogout");
        try {
            this.setLoggedin(false);
            msServerRI.logout(username);
        } catch (RemoteException e) {
        }
    }
    
    public void vezJogador(Boolean value) throws RemoteException {
        MainFrame.getInstance().setMinhaVez(value);
    }

    public void restartJogo() throws RemoteException {
        MainFrame.getInstance().restartGame();
    }
    
    @Override
    public void notify(Jogadas jog) throws RemoteException {
        MainFrame.getInstance().getMineFieldPanel().getBombButtons()[jog.getJogadaX()][jog.getJogadaY()].handleMouseListener(jog);
    }
}
