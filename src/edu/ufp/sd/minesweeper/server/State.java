package edu.ufp.sd.minesweeper.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class State implements Serializable {

    public class Message implements Serializable {

        private String username;
        private int linha;
        private int coluna;
        private int matrizJogo[][];
        private ArrayList<String> pontosPlayer = new ArrayList<String>();

        public Message(String username, int linha, int coluna, int[][] matrizJogo) {
            this.username = username;
            this.linha = linha;
            this.coluna = coluna;
            this.matrizJogo = matrizJogo;
        }
        
        
        public String getUsername() {
            return username;
        }

        public int getLinha() {
            return linha;
        }

        public int getColuna() {
            return coluna;
        }

        public int[][] getMatrizJogo() {
            return matrizJogo;
        }

        public ArrayList<String> getPontosPlayer() {
            return pontosPlayer;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setLinha(int linha) {
            this.linha = linha;
        }

        public void setColuna(int coluna) {
            this.coluna = coluna;
        }

        public void setMatrizJogo(int[][] matrizJogo) {
            this.matrizJogo = matrizJogo;
        }

        public void setPontosPlayer(ArrayList<String> pontosPlayer) {
            this.pontosPlayer = pontosPlayer;
        }
        
        
    }
        

    public class ConnectedClients implements Serializable {

        private int clients;

        public ConnectedClients(int clients) {
            this.clients = clients;
        }

        public int getClients() {
            return clients;
        }

        public void setClients(int clients) {
            this.clients = clients;
        }

    }

    public class Disconnect implements Serializable {

        private int type;
        private String message;

        public Disconnect(int type, String message) {
            this.type = type;
            this.message = message;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public class NewRoom implements Serializable {

        private String roomName;

        public NewRoom( String roomName) {
            this.roomName = roomName;
        }

        public String getRoomName() {
            return roomName;
        }

        
    }
    
    public class GenericState implements Serializable {
        private String type;
        
        public GenericState(String type) {
            this.type = type;
        }
        
        public String getType() {
            return this.type;
        }
    }  
}
