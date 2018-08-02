package edu.ufp.sd.minesweeper.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import edu.ufp.sd.minesweeper.client.MinesweeperClientRI;
import edu.ufp.sd.minesweeper.game.Jogadas;
import java.util.ArrayList;
import minefield.generator.MineFieldGenerator;


public interface MinesweeperServerRI extends Remote {

    //Start: Observer Design Pattern

    /**
     *
     * @param client
     * @throws RemoteException
     */
    public void attach(MinesweeperClientRI client) throws RemoteException;

    /**
     *
     * @param client
     * @throws RemoteException
     */
    public void detach(MinesweeperClientRI client) throws RemoteException;

    public Object getState() throws RemoteException;

    /**
     *
     * @param s
     * @throws RemoteException
     */
    public void setState(Object s) throws RemoteException;
    //End: Observer Design Pattern
    
    
    /**
     * Method to verify login
     * 
     * @param client
     * @param username
     * @param password
     * @return
     * @throws RemoteException 
     */
    public int login(MinesweeperClientRI client, String username, String password) throws RemoteException;

    /**
     * Method that is used for logout
     * 
     * @param username
     * @return
     * @throws RemoteException 
     */
    public int logout(String username) throws RemoteException;

    /**
     * Method used for register
     * 
     * @param client
     * @param username
     * @param password
     * @return
     * @throws RemoteException 
     */
    public int register(MinesweeperClientRI client, String username, String password) throws RemoteException;

    /**
     * Returns the current online clients
     * @return
     * @throws RemoteException 
     */
    public int countConnectedClients() throws RemoteException;

    /**
     * Creates a room for play with other player
     * 
     * @param client
     * @param level
     * @return
     * @throws RemoteException 
     */
    public void createGameRoom(String nomeJogo, int minJogadores, int maxJogadores, int width, int height, int bombs, String modo) throws RemoteException;


    /**
     * Fetch the Rooms avaliable
     * @return
     * @throws RemoteException 
     */
    public ArrayList<String> fetchAvaliableRooms() throws RemoteException;

    // SERVER:
    
    
    public void attachGame(MinesweeperClientRI msClientRI, String gameName) throws RemoteException;
    
    public void fazerJogada(Jogadas jogada) throws RemoteException;
    public void restartGame(String gameName) throws RemoteException;
    public MineFieldGenerator tabuleiro(String nomeJogo) throws RemoteException;
    public String getModoJogo(String nomeJogo) throws RemoteException;

}
