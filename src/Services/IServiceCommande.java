/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.commande;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IServiceCommande {
    public void AddCommande(commande c);
    public List<commande>AfficherCommande() throws SQLException;
    public void DeleteCommande(commande c);
    public void ModifyCommande(commande c);
    public commande getCommandeById(int id);
    
    
    
}
