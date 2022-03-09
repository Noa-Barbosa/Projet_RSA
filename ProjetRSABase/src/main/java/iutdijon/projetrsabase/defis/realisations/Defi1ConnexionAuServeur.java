package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 1er défi : Connexion au serveur
 * @author Matthieu
 */
public class Defi1ConnexionAuServeur extends Defi {

    @Override
    public void executer() throws IOException{
        try {
            Network.connexion();
            
            String messageEntrant="";
            String messageSortant="";
            int chiffreSortant=1;
            while(!messageEntrant.equals("FIN"))
            {
                messageEntrant=Network.receiveMessage();
                if(!messageEntrant.equals("-- Début du défi : Connexion au serveur --") && !messageEntrant.equals("OK") && !messageEntrant.equals("NOK") && !messageEntrant.equals("Defi valide") && !messageEntrant.equals("FIN")){
                    chiffreSortant=Integer.parseInt(messageEntrant)+1;
                    messageSortant=String.valueOf(chiffreSortant);
                    Network.sendMessage(messageSortant);
                }
                
            }
            
            Network.end();
            
        } catch (Exception ex) {
            Logger.getLogger(Defi1ConnexionAuServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
