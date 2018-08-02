/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.sd.minesweeper.game;

import edu.ufp.sd.minesweeper.client.MinesweeperClientRI;
import java.io.Serializable;

/**
 *
 * @author joaoc
 */
public class Jogadas implements Serializable {
    
    private int jogadaX;
    private int jogadaY;
    private int tipoMovimento;
    private MinesweeperClientRI msClientRI;
    private String nomeJogo;

    public Jogadas(int jogadaX, int jogadaY, int tipoMovimento, MinesweeperClientRI msClientRI, String nomeJogo) {
        this.jogadaX = jogadaX;
        this.jogadaY = jogadaY;
        this.tipoMovimento = tipoMovimento;
        this.msClientRI = msClientRI;
        this.nomeJogo = nomeJogo;
    }
    
    public int getJogadaX() {
        return jogadaX;
    }

    public void setJogadaX(int jogadaX) {
        this.jogadaX = jogadaX;
    }

    public int getJogadaY() {
        return jogadaY;
    }

    public void setJogadaY(int jogadaY) {
        this.jogadaY = jogadaY;
    }

    public int getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(int tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public MinesweeperClientRI getMsClientRI() {
        return msClientRI;
    }

    public void setMsClientRI(MinesweeperClientRI msClientRI) {
        this.msClientRI = msClientRI;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }
    
    
    
}
