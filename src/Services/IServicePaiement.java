/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.paiement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IServicePaiement {
    public void AddPaiement(paiement p);
    public List<paiement>AfficherPaiement() throws SQLException;
    public void DeletePaiement(paiement p);
    public void ModifyPaiement(paiement p);
    public paiement getPaiementById(int id);
    
    
    
}
