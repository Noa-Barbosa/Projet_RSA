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
public class Defi14RandomBornes extends Defi{

    @Override
    public void executer() throws IOException {
        try {
            Network.connexion();
            
            NombreBinaire nbBin1=null;
            NombreBinaire nbBin2=null;
            NombreBinaire nbBin3=null;
            String messageEntrant="";
            String messageSortant="";
            while(!messageEntrant.equals("FIN"))
            {
                messageEntrant=Network.receiveMessage();
                if(!messageEntrant.contains("Début du défi") && !messageEntrant.contains("OK") && !messageEntrant.contains("NOK") && !messageEntrant.contains("Defi valide") && !messageEntrant.contains("Defi echoue !") && !messageEntrant.contains("FIN")){
                    nbBin1= new NombreBinaire(messageEntrant);
                    nbBin2= new NombreBinaire(Network.receiveMessage());
                    nbBin3 = NombreBinaire.random(nbBin1, nbBin2);
                    messageSortant=nbBin3.toString();
                    Network.sendMessage(messageSortant);
                }
                
            }
            
            Network.end();
            
        } catch (Exception ex) {
            Logger.getLogger(Defi1ConnexionAuServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
