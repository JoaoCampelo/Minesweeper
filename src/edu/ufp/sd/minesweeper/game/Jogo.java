/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.sd.minesweeper.game;

import edu.ufp.sd.minesweeper.client.MinesweeperClientRI;
import java.rmi.RemoteException;
import java.util.ArrayList;
import minefield.generator.MineFieldGenerator;

/**
 *
 * @author joaoc
 */
public class Jogo {
    private ArrayList<MinesweeperClientRI> msClientRI;
    private int minJogadores;
    private int maxJogadores;
    private int width;
    private int height;
    private int bombsNumber;
    private MineFieldGenerator mineFieldGenerator;
    private MinesweeperClientRI proxJogador;
    private String modo;
    private int numberOfButtonsRevealed;
    private String nomeJogo;

    public Jogo(int minJogadores, int maxJogadores, int width, int height, int bombsNumber, String modo, String nomeJogo) {
        this.minJogadores = minJogadores;
        this.maxJogadores = maxJogadores;
        this.width = width;
        this.height = height;
        this.bombsNumber = bombsNumber;
        this.modo = modo;
        this.nomeJogo = nomeJogo;
        
        this.msClientRI = new ArrayList<>();
        this.gerarTabuleiro();
    }

    public ArrayList<MinesweeperClientRI> getMsClientRI() {
        return msClientRI;
    }

    public void setMsClientRI(ArrayList<MinesweeperClientRI> msClientRI) {
        this.msClientRI = msClientRI;
    }

    public int getMinJogadores() {
        return minJogadores;
    }

    public void setMinJogadores(int minJogadores) {
        this.minJogadores = minJogadores;
    }

    public int getMaxJogadores() {
        return maxJogadores;
    }

    public void setMaxJogadores(int maxJogadores) {
        this.maxJogadores = maxJogadores;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBombsNumber() {
        return bombsNumber;
    }

    public void setBombsNumber(int bombsNumber) {
        this.bombsNumber = bombsNumber;
    }

    public MineFieldGenerator getMineFieldGenerator() {
        return mineFieldGenerator;
    }

    public void setMineFieldGenerator(MineFieldGenerator mineFieldGenerator) {
        this.mineFieldGenerator = mineFieldGenerator;
    }

    public MinesweeperClientRI getProxJogador() {
        return proxJogador;
    }

    public void setProxJogador(MinesweeperClientRI proxJogador) {
        this.proxJogador = proxJogador;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public int getNumberOfButtonsRevealed() {
        return numberOfButtonsRevealed;
    }

    public void setNumberOfButtonsRevealed(int numberOfButtonsRevealed) {
        this.numberOfButtonsRevealed = numberOfButtonsRevealed;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }
    
    public void gerarTabuleiro() {
        this.mineFieldGenerator = new MineFieldGenerator(this.width, this.height, this.bombsNumber);

        if (this.msClientRI.size() > 0) {
            this.proxJogador = this.msClientRI.get(0);
        }
    }
    
    public Boolean attach(MinesweeperClientRI msClientRI2) {
        if (this.msClientRI.contains(msClientRI2)) {
            return true;
        }
        
        if (this.msClientRI.size() > this.maxJogadores-1) {
            return false;
        }
        System.out.println(this.nomeJogo + this.msClientRI.size());
        this.msClientRI.add(msClientRI2);

        try {
            if (this.msClientRI.size() == 1) {
                System.out.println(msClientRI2);
                msClientRI2.vezJogador(true);
                this.proxJogador = msClientRI2;
            } else {
                msClientRI2.vezJogador(false);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return true;
    }
    
    public void jogada(Jogadas jog) throws RemoteException {
        if (jog.getMsClientRI().equals(this.proxJogador)) {
            for (MinesweeperClientRI msClientRI2 : this.msClientRI) {
                msClientRI2.notify(jog);
            }

            this.proxJogador.vezJogador(false);
            this.priximoJogador().vezJogador(true);
        }
    }

    private MinesweeperClientRI priximoJogador() {
        int index = this.msClientRI.indexOf(this.proxJogador);

        if (this.msClientRI.size() - 1 == index) {
            this.proxJogador = this.msClientRI.get(0);
        } else {
            this.proxJogador = this.msClientRI.get(index + 1);
        }

        return this.proxJogador;
    }

    public void removerDoJogo(MinesweeperClientRI msClientRI2) {
        if(msClientRI.contains(msClientRI2)){
            this.msClientRI.remove(msClientRI2);
            try {
                System.out.println(msClientRI2.getClientUsername() + " foi removido do jogo " + this.nomeJogo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void restart() throws RemoteException {
        this.gerarTabuleiro();

        for (MinesweeperClientRI msClientRI2 : this.getMsClientRI()) {
            msClientRI2.restartJogo();
            msClientRI2.vezJogador(false);
        }
        this.getMsClientRI().get(0).vezJogador(true);
    }
    
}
