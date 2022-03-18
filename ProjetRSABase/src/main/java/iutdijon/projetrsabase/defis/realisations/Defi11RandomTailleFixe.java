/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author noaba
 */
public class Defi11RandomTailleFixe extends Defi {

    @Override
    public void executer() throws IOException {
        try {
            Network.connexion();
            
            NombreBinaire nbBin=null;
            String messageEntrant="";
            String messageSortant="";
            while(!messageEntrant.equals("FIN"))
            {
                messageEntrant=Network.receiveMessage();
                if(!messageEntrant.contains("Début du défi") && !messageEntrant.contains("OK") && !messageEntrant.contains("NOK") && !messageEntrant.contains("Defi valide") && !messageEntrant.contains("FIN")){
                    nbBin= NombreBinaire.randomAvecTailleMax(Integer.valueOf(messageEntrant));
                    messageSortant=nbBin.toString();
                    Network.sendMessage(messageSortant);
                }
                
            }
            
            Network.end();
            
        } catch (Exception ex) {
            Logger.getLogger(Defi1ConnexionAuServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
