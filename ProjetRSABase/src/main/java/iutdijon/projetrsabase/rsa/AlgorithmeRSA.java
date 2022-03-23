package iutdijon.projetrsabase.rsa;

import java.util.ArrayList;

/**
 * Description de la classe
 * @author Matthieu
 */
public class AlgorithmeRSA {

    
    //DEFI 17 - Chiffre un morceau (entrée : tailleMorceau, sortie : tailleCle)
    public static NombreBinaire chiffrerMorceau(NombreBinaire morceau, NombreBinaire N, NombreBinaire e){
      
        NombreBinaire res = new NombreBinaire();
        res=morceau.puissanceModulo(e, N);
        res.forcerTaille(ParametresRSA.getTailleCle());
        return res;
    }
   
    //DEFI 18 - Déchiffre un morceau (entrée : tailleCle, sortie : tailleMorceau)
    public static NombreBinaire dechiffrerMorceau(NombreBinaire morceau, NombreBinaire N, NombreBinaire d){
        NombreBinaire res = new NombreBinaire();
        res=morceau.puissanceModulo(d, N);
        res.forcerTaille(ParametresRSA.getTailleMorceau());
        return res;
    }

    //DEFI 21 - Chiffre le message avec les clés données
    public static NombreBinaire chiffrer(NombreBinaire messageAChiffrer, NombreBinaire N, NombreBinaire e) {
        
        ArrayList<NombreBinaire> arrayNb = new ArrayList<>();
        NombreBinaire morceauAChiffrer = new NombreBinaire();
        NombreBinaire messageChiffrer = new NombreBinaire();
        
        arrayNb = messageAChiffrer.scinder(ParametresRSA.getTailleMorceau());
        for(int i = 0; i < arrayNb.size(); i++)
        {
            morceauAChiffrer=arrayNb.get(i);
            morceauAChiffrer= AlgorithmeRSA.chiffrerMorceau(morceauAChiffrer, N, e);
            messageChiffrer=messageChiffrer.concatenation(morceauAChiffrer);
        }
        return messageChiffrer;
    }

    //DEFI 22 - Déchiffre le message avec les clés données
    public static NombreBinaire dechiffrer(NombreBinaire messageADechiffrer, NombreBinaire N, NombreBinaire d) {
       
        ArrayList<NombreBinaire> arrayNb = new ArrayList<>();
        NombreBinaire morceauADechiffrer = new NombreBinaire();
        NombreBinaire messageDechiffre = new NombreBinaire();
        
        arrayNb = messageADechiffrer.scinder(ParametresRSA.getTailleCle());
        for(int i = 0; i < arrayNb.size(); i++)
        {
            morceauADechiffrer=arrayNb.get(i);
            morceauADechiffrer= AlgorithmeRSA.dechiffrerMorceau(morceauADechiffrer, N, d);
            messageDechiffre=messageDechiffre.concatenation(morceauADechiffrer);
        }
        return messageDechiffre;
    }
}
