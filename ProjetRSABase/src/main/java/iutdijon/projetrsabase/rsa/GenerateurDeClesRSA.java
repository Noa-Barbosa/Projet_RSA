package iutdijon.projetrsabase.rsa;

/**
 * Générateur de clé RSA
 * @author Matthieu
 */
public class GenerateurDeClesRSA {

    private static NombreBinaire P;
    private static NombreBinaire Q;
    private static NombreBinaire N;
    private static NombreBinaire phi;
    private static NombreBinaire e;

    public static NombreBinaire getP() {
        return P;
    }

    public static NombreBinaire getQ() {
        return Q;
    }

    public static NombreBinaire getN() {
        return N;
    }

    public static NombreBinaire getPhi() {
        return phi;
    }

    public static NombreBinaire getE() {
        return e;
    }
    
    
    public static void setP(NombreBinaire P) {
        GenerateurDeClesRSA.P = P;
    }

    public static void setQ(NombreBinaire Q) {
        GenerateurDeClesRSA.Q = Q;
    }

    public static void setPhi(NombreBinaire phi) {
        GenerateurDeClesRSA.phi = phi;
    }

    public static void setE(NombreBinaire e) {
        GenerateurDeClesRSA.e = e;
    }    
    
    //Défi 24 - Génère la clé publique (P,Q,N,phi et e)    
    public static void genererClePublique() {
        
    }
    
    //Défi 20 - Renvoie la clé privée d
    public static NombreBinaire genererClePrive(NombreBinaire P,NombreBinaire Q,NombreBinaire e) {
        NombreBinaire d = new NombreBinaire();
        setP(P);
        setQ(Q);
        setE(e);
        
        setPhi((getP().soustraction(new NombreBinaire(1))).multiplication((getQ().soustraction(new NombreBinaire(1)))));
        
        d=getE().inverseModulaire(getPhi());
        d.forcerTaille(ParametresRSA.getTailleCle());
        return d;
    }

    
    
}
