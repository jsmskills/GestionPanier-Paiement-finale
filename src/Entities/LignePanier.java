/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author rania
 */
public class LignePanier {
     private int id;
    private Panier panier;
    private Formation formation;

    public LignePanier(Panier panier, Formation formation) {
        this.panier = panier;
        this.formation = formation;
    }

    public LignePanier(int id, Panier panier, Formation formation) {
        this.id = id;
        this.panier = panier;
        this.formation = formation;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
    
    
}
