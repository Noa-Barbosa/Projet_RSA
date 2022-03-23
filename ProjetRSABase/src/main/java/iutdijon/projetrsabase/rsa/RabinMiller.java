package iutdijon.projetrsabase.rsa;


/**
 * Description de la classe
 * @author Matthieu
 */
public class RabinMiller {
    
    //DEFI 16 - Méthode renvoyant si a est un témoin de Miller de n (preuve que n est composé)
    public static boolean temoin(NombreBinaire n, NombreBinaire a) {
        
        NombreBinaire x = new NombreBinaire();
        NombreBinaire[] SD=getSD(n);
        
        NombreBinaire s = SD[0];
        NombreBinaire d = SD[1];
        
        x=a.puissanceModulo(d, n);
        
        if(x.estEgal(new NombreBinaire(1)) || x.estEgal(n.soustraction(new NombreBinaire(1)))){
            return false;
        }
        
        for(int i=0; i<s.getTaille()-1;i++){
            x=x.puissanceModulo(new NombreBinaire(2), n);
            if(x.estEgal(n.soustraction(new NombreBinaire(1)))){
                return false;
            }
        }
        
        return true;
    }
    
    private static NombreBinaire[] getSD(NombreBinaire n)
    {
        NombreBinaire N = n.soustraction(new NombreBinaire(1));
        NombreBinaire s = new NombreBinaire(0);
        while(N.estPair())
        {
            N = N.decalageGauche();
            s = s.addition(new NombreBinaire(1));
        }
        
        NombreBinaire[] tab =  new NombreBinaire[2];
        tab[0] = s;
        tab[1] = N;
        return tab;
    }
    
    //DEFI 19 - Test de RabinMiller, test probabilistiquement que n est premier (proba erreur = 1/4^k)
    public static boolean testRabinMiller(NombreBinaire n) {
        return false;
    }
    
    //DEFI 23 - Renvoie le plus petit nombre premier supérieur à min
    public static NombreBinaire nombrePremier(NombreBinaire min) {
        return null;
    }
}
