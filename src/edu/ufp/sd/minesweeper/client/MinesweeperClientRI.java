package edu.ufp.sd.minesweeper.client;

import edu.ufp.sd.minesweeper.game.Jogadas;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MinesweeperClientRI extends Remote {

    /**
     * Outputs a client message in the console
     *
     * @param message
     * @throws RemoteException
     */
    public void sendMessage(String message) throws RemoteException;

    /**
     * Returns the client username
     *
     * @return
     * @throws RemoteException
     */
    public String getClientUsername() throws RemoteException;

    /**
     * Update for receiving states
     *
     * @throws RemoteException
     */
    public void update() throws RemoteException;
    
    public void vezJogador(Boolean value) throws RemoteException;
    
    public void restartJogo() throws RemoteException;
    
    public void notify(Jogadas jog) throws RemoteException;
}
