/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Panier {
    
    private int id;
    private float prix;
    private Utilisateur user;
    private boolean commandee;
    private List< LignePanier> lignespanier;

    public List<LignePanier> getLignespanier() {
        return lignespanier;
    }

    public void setLignespanier(List<LignePanier> lignespanier) {
        this.lignespanier = lignespanier;
    }
    
    
    public Panier() {
        lignespanier=new ArrayList<>();
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public boolean isCommandee() {
        return commandee;
    }

    public void setCommandee(boolean commandee) {
        this.commandee = commandee;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Panier(float prix, Utilisateur user, boolean commandee) {
        this.prix = prix;
        this.user = user;
        this.commandee = commandee;
          lignespanier=new ArrayList<>();
    }

    

   
    
    
}
