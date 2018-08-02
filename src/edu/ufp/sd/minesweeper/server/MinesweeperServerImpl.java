package edu.ufp.sd.minesweeper.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.ufp.sd.minesweeper.client.MinesweeperClientRI;
import edu.ufp.sd.minesweeper.game.Jogadas;
import edu.ufp.sd.minesweeper.game.Jogo;
import java.util.HashMap;
import java.util.Map;
import minefield.generator.MineFieldGenerator;
import minefield.jgui.MainFrame;

/**
 *
 * @author joaoc
 */
public class MinesweeperServerImpl extends UnicastRemoteObject implements MinesweeperServerRI {

    private Object state;

    private MinesweeperServerGUI msServerGUI;
    protected ArrayList<MinesweeperClientRI> clients = new ArrayList<>();
    protected ArrayList<MainFrame> rooms = new ArrayList<>();
    
    private HashMap<String, Jogo> jogos = new HashMap<>();


    public static String PATH_USERS = "../../data/users/";

    // Uses RMI-default sockets-based transport
    // Runs forever (do not passivates) - Do not needs rmid (activation deamon)
    // Constructor must throw RemoteException due to export()
    public MinesweeperServerImpl() throws RemoteException {
        // Invokes UnicastRemoteObject constructor which exports remote object
        super();
        this.msServerGUI = new MinesweeperServerGUI(this);
    }

    @Override
    public synchronized int login(MinesweeperClientRI client, String username, String password) throws RemoteException {
        System.out.println("MinesweeperServerImpl - login(): " + username + " " + password);
        if (clientAlreadyLoggedin(username)) {
            System.out.println("MinesweeperServerImpl - login(): " + username + " is already logged in.");
            return 0;
        }

        String user_path = PATH_USERS + username + ".txt";
        System.out.println("MinesweeperServerImpl - var userpath: " + user_path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(user_path)));
            String file_password = br.readLine();
            if (file_password.compareTo(password) == 0) {
                System.out.println("MinesweeperServerImpl - login(): " + username + " está agora logado");
                clients.add(client);
                msServerGUI.addConnectedClient(username, clients.size());
                return 1;
            } else {
                System.out.println("MinesweeperServerImpl - login(): " + username + " a password está incorrecta");
                return 0;
            }

        } catch (FileNotFoundException ex) {
            client.sendMessage("Username inexistente! Registe-se.");
            return 0;
        } catch (IOException ex) {
            return 0;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MinesweeperServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public synchronized int logout(String username) throws RemoteException {
        System.out.println("MinesweeperServerImpl - logout(): " + username);
        for (MinesweeperClientRI user : this.clients) {
            if (user.getClientUsername().compareTo(username) == 0) {
                clients.remove(user);
                msServerGUI.removeConnectedClient(username, clients.size());
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int register(MinesweeperClientRI client, String username, String password) throws RemoteException {
        String user_path = PATH_USERS + username + ".txt";
        File f = new File(user_path);

        if (f.exists()) {
            client.sendMessage("Este username já existe, escolha outro!");
            return 0;
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(user_path)));
            bw.write(password);
        } catch (IOException e) {
            Logger.getLogger(MinesweeperServerImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                    client.sendMessage("Conta criada com sucesso!");
                }
            } catch (IOException e) {
                Logger.getLogger(MinesweeperServerImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return 1;
    }

    @Override
    public void attach(MinesweeperClientRI client) throws RemoteException {
        this.clients.add(client);
    }

    @Override
    public void detach(MinesweeperClientRI client) throws RemoteException {
        this.clients.remove(client);
    }

    @Override
    public Object getState() throws RemoteException {
        return this.state;
    }

    @Override
    public void setState(Object s) throws RemoteException {
        System.out.println("MinesweeperServerImpl - setState(): " + s.getClass().getName());
        this.state = s;
        if (!clients.isEmpty()) {
            notifyAllObservers();
        }
    }

    /**
     *
     * @throws RemoteException
     */
    public void notifyAllObservers() throws RemoteException {
        System.out.println("MinesweeperServerImpl - notifyAllObservers()");
        for (MinesweeperClientRI client : clients) {
            client.update();
        }
    }

    @Override
    public int countConnectedClients() throws RemoteException {
        return this.clients.size();
    }

    @Override
    public void createGameRoom(String nomeJogo, int minJogadores, int maxJogadores, int width, int height, int bombs, String modo) throws RemoteException {
        this.jogos.put(nomeJogo, new Jogo(minJogadores, maxJogadores,width,height,bombs, modo, nomeJogo));
        this.setState(new State().new NewRoom(nomeJogo));
        this.msServerGUI.addRoomToList(nomeJogo);
    }

    private boolean clientAlreadyLoggedin(String username) {
        System.out.println("clientAlreadyLoggedin: " + username);
        try {
            for (MinesweeperClientRI client : clients) {
                if (client.getClientUsername().compareTo(username) == 0) {
                    return true;
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MinesweeperServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<MainFrame> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<MainFrame> rooms) {
        this.rooms = rooms;
    }

    /**
     *
     * @param username
     * @return
     */
    public MinesweeperClientRI clientFromUsername(String username) {
        try {
            for (MinesweeperClientRI client : clients) {
                if (client.getClientUsername().compareTo(username) == 0) {
                    return client;
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MinesweeperServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     *
     */
    public void shutdown() {
        System.out.println("SHUTDOWN SERVER");
        System.exit(0);
    }

    

    @Override
    public ArrayList<String> fetchAvaliableRooms() throws RemoteException {

        ArrayList<String> itemsArr = new ArrayList();
        if (!jogos.isEmpty()) {
            for (Map.Entry<String, Jogo> entry : this.jogos.entrySet()) {
                System.out.println(entry.getValue().getNomeJogo());
                itemsArr.add(entry.getValue().getNomeJogo());
            }
        }

        return itemsArr;
    }
    
    @Override
    public void attachGame(MinesweeperClientRI msClientRI, String nomeJogo) throws RemoteException {
        this.jogos.get(nomeJogo).attach(msClientRI);
    }
    
    @Override
    public void fazerJogada(Jogadas jogada) throws RemoteException {
        if(this.jogos.get(jogada.getNomeJogo()).getMsClientRI().size()>=this.jogos.get(jogada.getNomeJogo()).getMinJogadores()){
            this.jogos.get(jogada.getNomeJogo()).jogada(jogada);
        }
    }
    
    public void restartGame(String gameName) throws RemoteException {
        this.jogos.get(gameName).restart();
    }
    
    public MineFieldGenerator tabuleiro(String nomeJogo) throws RemoteException {
        return this.jogos.get(nomeJogo).getMineFieldGenerator();
    }
    
    public String getModoJogo(String nomeJogo) throws RemoteException {
        return this.jogos.get(nomeJogo).getModo();
    }
}
