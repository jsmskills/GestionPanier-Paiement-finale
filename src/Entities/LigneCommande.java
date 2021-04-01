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
public class LigneCommande {
    private int id;
    private commande commande;
    private Formation formation;

    public commande getCommande() {
        return commande;
    }

    public void setCommande(commande commande) {
        this.commande = commande;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LigneCommande(commande commande, Formation formation) {
        this.commande = commande;
        this.formation = formation;
    }
    
    
}
