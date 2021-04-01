/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS
 */
public class paiement {
    private int id;
    private String modepaiement;
    private int numcarte,cryptograme;
    private String dateexp;

    public paiement() {
    }

    public paiement(int id, String modepaiement, int numcarte, int cryptograme) {
        this.id = id;
        this.modepaiement = modepaiement;
        this.numcarte = numcarte;
        this.cryptograme = cryptograme;
    }

    public paiement(String modepaiement, int numcarte, int cryptograme) {
        this.modepaiement = modepaiement;
        this.numcarte = numcarte;
        this.cryptograme = cryptograme;
    }

    public paiement(int numcarte, int cryptograme) {
        this.numcarte = numcarte;
        this.cryptograme = cryptograme;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModepaiement() {
        return modepaiement;
    }

    public void setModepaiement(String modepaiement) {
        this.modepaiement = modepaiement;
    }

    public int getNumcarte() {
        return numcarte;
    }

    public void setNumcarte(int numcarte) {
        this.numcarte = numcarte;
    }

    public int getCryptograme() {
        return cryptograme;
    }

    public void setCryptograme(int cryptograme) {
        this.cryptograme = cryptograme;
    }

    public String getDateexp() {
        return dateexp;
    }

    public void setDateexp(String dateexp) {
        this.dateexp = dateexp;
    }

    public paiement(int id, String modepaiement, int numcarte, int cryptograme, String dateexp) {
        this.id = id;
        this.modepaiement = modepaiement;
        this.numcarte = numcarte;
        this.cryptograme = cryptograme;
        this.dateexp = dateexp;
    }

    public paiement(String modepaiement, int numcarte, int cryptograme, String dateexp) {
        this.modepaiement = modepaiement;
        this.numcarte = numcarte;
        this.cryptograme = cryptograme;
        this.dateexp = dateexp;
    }

    public paiement(String modepaiement, String dateexp) {
        this.modepaiement = modepaiement;
        this.dateexp = dateexp;
    }

    public paiement(String dateexp) {
        this.dateexp = dateexp;
    }

    public paiement(int numcarte, int cryptograme, String dateexp) {
        this.numcarte = numcarte;
        this.cryptograme = cryptograme;
        this.dateexp = dateexp;
    }

   
    

    @Override
    public String toString() {
        return "paiement{" + "id=" + id + ", modepaiement=" + modepaiement + ", numcarte=" + numcarte + ", cryptograme=" + cryptograme +"}\n" ;
    }
    
    
    
        
    
    
    
}
