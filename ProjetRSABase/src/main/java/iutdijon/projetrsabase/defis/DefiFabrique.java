package iutdijon.projetrsabase.defis;

import iutdijon.projetrsabase.defis.realisations.*;

/**
 * Fabrique des défis
 * @author Matthieu
 */
public class DefiFabrique {
    
    /**
     * Crée le défi
     * @param numeroDuDefis numéro du défi à créer
     * @return le défi
     */
    public static Defi creer(int numeroDuDefis) {
        Defi defi = null;
        
        switch(numeroDuDefis) {
            case 1 : defi = new Defi1ConnexionAuServeur(); break;
            case 5 : defi = new Defi5EstInferieur(); break;
            case 6 : defi = new Defi6EstEgal(); break;
            case 7 : defi = new Defi7EstPair(); break;
            case 11 : defi = new Defi11RandomTailleFixe(); break;
            case 14 : defi = new Defi14RandomBornes(); break;
            
            default : throw new UnsupportedOperationException("Défis non implémenté !");
        }
        
        return defi;
    }
    
}
