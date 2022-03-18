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
public class Defi7EstPair extends Defi{

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
                if(!messageEntrant.equals("-- Début du défi : Est pair --") && !messageEntrant.equals("OK") && !messageEntrant.equals("NOK") && !messageEntrant.equals("Defi valide") && !messageEntrant.equals("FIN")){
                    nbBin= new NombreBinaire(messageEntrant);
                    boolean estPair = nbBin.estPair();
                    messageSortant=String.valueOf(estPair);
                    Network.sendMessage(messageSortant);
                }
                
            }
            
            Network.end();
            
        } catch (Exception ex) {
            Logger.getLogger(Defi1ConnexionAuServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
