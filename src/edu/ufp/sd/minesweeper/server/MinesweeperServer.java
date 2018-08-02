package edu.ufp.sd.minesweeper.server;

import java.net.InetAddress;
import java.rmi.AccessException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinesweeperServer {

    public static String serviceName = "rmi://localhost:1099/MinesweeperService";

    /**
     *
     * @param hostIP
     */
    public MinesweeperServer(String hostIP) {
        System.out.println("CURRENT WORKING DIRECTORY:" + System.getProperty("user.dir"));
        try {
            // Create and install a security manager
            if (System.getSecurityManager() == null) {
                System.out.println("MinesweeperServer - Constructor(): set security manager");
                System.setSecurityManager(new SecurityManager());
            }
            // Get referencefor Registry
            InetAddress inetAddr = InetAddress.getLocalHost();
            String hostName = inetAddr.getHostName();
            String hostAddress = inetAddr.getHostAddress();

            MinesweeperServer.serviceName = (hostIP == null ? "rmi://" + hostAddress + ":1099/MinesweeperService" : "rmi://" + hostIP + ":1099/MinesweeperService");

            System.out.println("MinesweeperServer - Constructor(): Local host is " + hostName + " at IP address " + hostAddress);
            System.out.println("MinesweeperServer - Constructor(): get registry on " + hostAddress + " - default port 1099");
            Registry registry = LocateRegistry.getRegistry(inetAddr.getHostAddress(), 1099);
            if (registry != null) {
                String[] srvList = registry.list();
                System.out.println("MinesweeperServer - Constructor(): list of servervices svrList.length = " + srvList.length);

                for (int i = 0; i < srvList.length; i++) {
                    System.out.println("MinesweeperServer - Constructor(): service svrLis[" + i + "] = " + srvList[i]);
                }

                System.out.println("MinesweeperServer - Constructor(): try register service " + serviceName + "...");
                MinesweeperServerRI bdsRI = (MinesweeperServerRI) new MinesweeperServerImpl();

                try {
                    registry.rebind(MinesweeperServer.serviceName, bdsRI);
                    System.out.println("MinesweeperServer - Constructor(): service bound and running!");
                } catch (AccessException e) {
                    Logger.getLogger(MinesweeperServerImpl.class.getName()).log(Level.SEVERE, null, e);
                }

            } else {
                System.out.println("MinesweeperServer - Constructor(): create registry on port 1099");
                registry = LocateRegistry.createRegistry(1099);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(MinesweeperServerImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; args != null && i < args.length; i++) {
            System.out.println("MinesweeperServer - main(): args[" + i + "] = " + args[i]);
        }
        MinesweeperServer msServer = new MinesweeperServer((args != null && args.length > 0 ? args[0] : null));
    }
}
