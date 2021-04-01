/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;


public class Formation {
    private int id; 
    private String titre;
    private String description;
    private Date datedebut;
    private Date datefin;
    private float prix;  
    private int nbreplace;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbreplace() {
        return nbreplace;
    }

    public void setNbreplace(int nbreplace) {
        this.nbreplace = nbreplace;
    }

    public Formation(int id, String titre, String description, Date datedebut, Date datefin, float prix, int nbreplace) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.prix = prix;
        this.nbreplace = nbreplace;
    }

    public Formation(String titre, String description, Date datedebut, Date datefin, float prix, int nbreplace) {
        this.titre = titre;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.prix = prix;
        this.nbreplace = nbreplace;
    }

    public Formation() {
    }
    
    




}
