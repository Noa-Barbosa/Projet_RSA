package iutdijon.projetrsabase.rsa;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

/**
 * Description de la classe
 * @author Matthieu
 */
public class NombreBinaire {
    
    /**
     * Liste des bits du nombre binaire
     */
    private BitSet listeBits;
    
    /**
     * Taille forcée
     */
    private int tailleForcee = -1;
    
    // ------- METHODES DEJA IMPLEMENTEES --------------------------------------
    //Set un bit
    public void set(int i, boolean valeur) {
        this.listeBits.set(i,valeur);
    }
    
    //Get un bit
    public boolean get(int i) {
        return this.listeBits.get(i);
    }
    
    
    //Constructeurs standard
    public NombreBinaire() {
        this.listeBits = new BitSet();
    }
    
    //Constructeur clone
    public NombreBinaire(NombreBinaire nombre) {
        this.listeBits = new BitSet();
        for(int i=0;i<nombre.listeBits.length();i++) {
            this.listeBits.set(i,nombre.listeBits.get(i));
        } 
    }
    
    //Constructeur à partir d'un int
    public NombreBinaire(int valeur) {
        this.listeBits = new BitSet();
        int i = 0;
        while(valeur != 0) {
            this.listeBits.set(i,valeur%2==1);
            valeur /= 2;
            i++;
        }
    }
    
    
    //Constructeur à partir d'une chaine de caractère binaire
    public NombreBinaire(String s) {
        this();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(s.length()-i-1) == '1') {
                this.listeBits.set(i,true);
            }
        }
    }
    
    //Renvoie la taille (en nb de bits)
    public int getTaille() {
        int valeur = 0;
        if(this.tailleForcee == -1) valeur =  this.listeBits.length();
        else valeur = this.tailleForcee;
        return valeur;
    }
    
    //Force la taille (en nb de bits)
    public void forcerTaille(int valeur) {
        this.tailleForcee = valeur;
    }
    
    
    //Affichage
    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<this.getTaille();i++) {
            if(this.listeBits.get(i)) {
                res = "1"+res;
            }
            else {
                res = "0"+res;
            }
        }
        if("".equals(res)) {
            res = "0";
        }
        return res;
    }
    
    
     //Scinde un nombreBinaire en nombreBinaire de taille donnée
     public ArrayList<NombreBinaire> scinder(int tailleMorceau) {
        ArrayList<NombreBinaire> res = new ArrayList<>();
        for(int i=0;i<this.getTaille();i=i+tailleMorceau) {
            NombreBinaire bitset = new NombreBinaire();
            for(int j=0;j<tailleMorceau;j++) {
                bitset.set(j,this.listeBits.get(i+j));
            }
            NombreBinaire nb = new NombreBinaire(bitset);
            nb.tailleForcee = tailleMorceau;
            res.add(nb);
        }
        return res;
    }
     
    //Concaténation de deux nombre binaires de taille : tailleMorceau
     public NombreBinaire concatenation(NombreBinaire mot) {
         NombreBinaire sortie = new NombreBinaire(this);
         int taille = this.getTaille()+mot.getTaille();
         for(int i=0;i<taille;i++) {
             if(i<this.getTaille()) sortie.listeBits.set(i,this.listeBits.get(i));
             else sortie.listeBits.set(i,mot.listeBits.get(i-this.getTaille()));
         }
         sortie.forcerTaille(taille);
         return sortie;
     }
     
     //-------------------------------------------------------------------------
     
     //DEFI 2 - Renvoie le résultat de l'addition de this avec mot2
     public NombreBinaire addition(NombreBinaire mot2) {
         //Retenue
         int r=0;
         //Bit du premier nombre
         int b1=0;
         //Bit du deuxième nombre
         int b2=0;
         
         //Le nouveau nombre binaire former après l'addition
         NombreBinaire mot3 = new NombreBinaire();
         int taille=0;
         
         if(this.getTaille()>mot2.getTaille()){
             taille=this.getTaille();
         }
         else{
             taille=mot2.getTaille();
         }

         for(int i=0; i<taille; i++){
             if(i>=this.getTaille()){
                 b1=0;
             }
             
             
             else if(this.get(i)==true){
                 b1=1;
             }
             else{
                 b1=0;
             }
             
             if(i>=mot2.getTaille()){
                 b2=0;
             }
             else if(mot2.get(i)==true){
                 b2=1;
             }
             else{
                b2=0; 
             }
             
             
             int res = b1+b2+r;
             
             switch(res){
                 case 0 :
                     mot3.set(i, false);
                     r=0;
                     break;
                 case 1:
                     mot3.set(i, true);
                     r=0;
                     break;
                 case 2 :
                     mot3.set(i, false);
                     r=1;
                     break;
                 case 3:
                     mot3.set(i, true);
                     r=1;
                     break;
             }
             
         }
         
         if(r==1){
             
             mot3.set(taille, true);
         }

         return mot3;
     }
     
     //DEFI 3 - Caclule le décalage de n bits (multiplie par 2^n)
     public NombreBinaire decalage(int n) {
         
         String BinStr = this.toString();
         for(int i=0; i<n;i++){
             BinStr+="0";
         }
         
         return new NombreBinaire(BinStr);
     }
     
     //DEFI 4 - renvoie le resultat de l'addition de this avec mot3
     public NombreBinaire soustraction(NombreBinaire mot2) {
         //Retenue
         int r=0;
         //Bit du premier nombre
         int b1=0;
         //Bit du deuxième nombre
         int b2=0;
         
         //Le nouveau nombre binaire former après la soustraction
         NombreBinaire mot3 = new NombreBinaire();

         //mot2.forcerTaille(this.getTaille());
         //mot3.forcerTaille(this.getTaille());
         
         for(int i=0; i<this.getTaille(); i++){
             if(this.get(i)==true){
                 b1=1;
             }
             else{
                 b1=0;
             }
             
             if(mot2.get(i)==true){
                 b2=1;
             }
             else{
                b2=0; 
             }
             
             
             int res = b1-b2-r;
             
             switch(res){
                 case 0 :
                     mot3.set(i, false);
                     r=0;
                     break;
                 case 1:
                     mot3.set(i, true);
                     r=0;
                     break;
                 case -1 :
                     mot3.set(i, true);
                     r=1;
                     break;
                 case -2:
                     mot3.set(i, false);
                     r=1;
             }
             
         }
         
         
         return mot3;
     }
     
     //DEFI 5 - Renvoie si this est plus petit ou égal à mot2
     public boolean estInferieurA(NombreBinaire mot2) {
         if(this.getTaille()<mot2.getTaille()){
             return true;
         }
         else if(this.getTaille()>mot2.getTaille()){
             return false;
         }
         else{
            int N = this.getTaille();
            
            //On commence à la fin car le bit avec le poids le plus significatif se trouve à la fin du nombre binaire
            for (int i = N-1; i >= 0; i--) {
                if (this.get(i) ^ mot2.get(i)) return mot2.get(i);
            }
            return true;
         }
     }
     
     //DEFI 6 - Renvoie si this est égal à mot2 ou non
     public boolean estEgal(NombreBinaire mot2) {
         boolean estEgal=false;
         int i=0;
         boolean bitEgal=true;
         if(this.getTaille()==mot2.getTaille()){
             while(i!=this.getTaille() && bitEgal){
                 if(this.get(i)==mot2.get(i)){
                     bitEgal=true;
                 }
                 else{
                     bitEgal=false;
                 }
                 i++;
             }
             if(bitEgal){
                 estEgal=true;
             }
             else{
                 estEgal=false;
             }
         }
         else{
             estEgal=false;
         }
         
        return estEgal;
     }
     
     //DEFI 7 - Renvoie si un nombre est pair
     public boolean estPair() {
         return !this.get(0);
     }
     
     //DEFI 8 - Calcul la multiplication de this avec mot2
     public NombreBinaire multiplication(NombreBinaire mot2) {
         NombreBinaire res = new NombreBinaire(0);
         
         for(int i =0; i<mot2.getTaille(); i++){
             if(mot2.get(i)){
                 res=res.addition(this.decalage(i));
             }  
         }
        return res;
     }

     //DEFI 9 - Calcul le quotient dans la division euclidienne de this par mot2
     public NombreBinaire quotient(NombreBinaire mot2) {
        
         //Reste de la division euclidienne
         NombreBinaire r=new NombreBinaire(this);
         //Quotient de la division euclidienne
         NombreBinaire q= new NombreBinaire(0);
                  
         int n=0;
         if(mot2.get(n)==true && mot2.getTaille()==1){
             q= new NombreBinaire(this);
         }
         else{
            if(r.getTaille()>=mot2.getTaille()){
             while(mot2.estInferieurA(r)){
                 n=r.getTaille()-mot2.getTaille();
                 NombreBinaire mot2decale = mot2.decalage(n);
                 if(!mot2decale.estInferieurA(r)){
                     mot2decale=mot2.decalage(n-1);
                     n=n-1;
                 }
                 r=r.soustraction(mot2decale);
                 NombreBinaire puissance = new NombreBinaire(1);
                 puissance=puissance.decalage(n);
                 q=q.addition(puissance);
                }
            }
            else{
                q=new NombreBinaire(0);
            }
          
         }
         
         
         return q;
     }
     
     //DEFI 10 - Calcul this modulo mot2 via une division euclidienne
     public NombreBinaire modulo(NombreBinaire mot2) {
         
         //Reste de la division euclidienne
         NombreBinaire r=new NombreBinaire(this);
         //Quotient de la division euclidienne
         NombreBinaire q= new NombreBinaire(0);
                  
         int n=0;
         if(mot2.get(n)==true && mot2.getTaille()==1){
             r= new NombreBinaire(0);
         }
         else{
            if(r.getTaille()>=mot2.getTaille()){
             while(mot2.estInferieurA(r)){
                 n=r.getTaille()-mot2.getTaille();
                 NombreBinaire mot2decale = mot2.decalage(n);
                 if(!mot2decale.estInferieurA(r)){
                     mot2decale=mot2.decalage(n-1);
                     n=n-1;
                 }
                 r=r.soustraction(mot2decale);
                 NombreBinaire puissance = new NombreBinaire(1);
                 puissance=puissance.decalage(n);
                 q=q.addition(puissance);
                }
            }
            else{
                r=new NombreBinaire(this);
            }
          
         }
         
         
         return r;
     }  
     
    //DEFI 11 - Génère un nombre binaire aléatoire de "taille" bits au maximum.
    public static NombreBinaire randomAvecTailleMax(int taille) {
        Random r = new Random();
        NombreBinaire nbBin = null;
        if(taille==0){
            nbBin = new NombreBinaire(taille);
        }
        else{
            String binString=NombreBinaire.getRandom01String(r.nextInt(taille)+1);
            nbBin = new NombreBinaire(binString);
        }
          
        return nbBin;
    }
    
     //DEFI 12 - Calcul de this^exposant modulo m par exponentiation modulaire rapide
     public NombreBinaire puissanceModulo(NombreBinaire exposant, NombreBinaire m) {
         NombreBinaire res = new NombreBinaire();
         NombreBinaire a = new NombreBinaire(this);
         
         for(res=new NombreBinaire(1); !exposant.estInferieurA(new NombreBinaire(0));exposant=exposant.decalageGauche()){
             if(!exposant.estPair()){
                 res=res.multiplication(a);
                 res=res.modulo(m); 
             }
             a=a.multiplication(a);
             a=a.modulo(m);
         }
         return res;
     }
     
     //DEFI 13 - Calcul le PGCD de this et mot2
     public NombreBinaire PGCD(NombreBinaire mot2) {
         
         NombreBinaire a= new NombreBinaire(this);
         NombreBinaire b= new NombreBinaire(mot2);
         
         if(a.estInferieurA(b) && !a.estEgal(b)){
             NombreBinaire temp=new NombreBinaire(a);
             a=new NombreBinaire(b);
             b=new NombreBinaire(temp);     
         }
         while(!b.estEgal(new NombreBinaire(0))){
             NombreBinaire temp=new NombreBinaire(b);
             b=new NombreBinaire(a.modulo(b)); 
             a=new NombreBinaire(temp);      
         } 
         return a;
     }
     
    //DEFI 14 - renvoie un nombre aléatoire entre min (inclu) et max (non inclu)
    public static NombreBinaire random(NombreBinaire min,NombreBinaire max) {
        Random r = new Random();
        int taille=min.getTaille();
        NombreBinaire nbBin = NombreBinaire.randomAvecTailleMax(taille);

        while(!nbBin.estInferieurA(max) || (nbBin.estInferieurA(min))){
            
            if(taille==0){
                nbBin = new NombreBinaire(taille);
            }
            else{
                String binString=NombreBinaire.getRandom01String(taille);
                nbBin = new NombreBinaire(binString);
            }
        }
           
        return nbBin;
    }
    
     //DEFI 15 - Calcul de l'inverse modulo nombre
     //Basé sur l'algo d'euclide étendu (adapté).
     public NombreBinaire inverseModulaire(NombreBinaire nombre) {
         NombreBinaire ZERO = new NombreBinaire(0);
            
         NombreBinaire n0 = new NombreBinaire(nombre);
         NombreBinaire b0 = new NombreBinaire(this);
         NombreBinaire t0 = new NombreBinaire(0);
         NombreBinaire t = new NombreBinaire(1);
         
         NombreBinaire q = n0.quotient(b0);
         NombreBinaire r = n0.modulo(b0);
         while(!r.estEgal(ZERO)) {
             NombreBinaire produit = q.multiplication(t);
             NombreBinaire memoire;
             //Gére le fait qu'un nombreBinaire ne peut pas être négatif......
             if(t0.estInferieurA(produit)) {
                memoire = nombre.soustraction(produit.soustraction(t0).modulo(nombre));
             }
             else {
                memoire = t0.soustraction(produit).modulo(nombre);  
             }
             
             t0 = t;
             t = memoire;
             n0 = b0;
             b0 = r;
             q = n0.quotient(b0);
             r = n0.modulo(b0);
         }
         return t;
     }
     
     // function to generate a random string of length n
    public static String getRandom01String(int n)
    {
        Random r = new Random();
        // chose a Character random from this String
        String AlphaNumericString = "01";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
     
        while(sb.length()!=n){
            // generate a random number between
            // 0 to AlphaNumericString variable length-1
            int index = r.nextInt(AlphaNumericString.length());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }  
        if(sb.charAt(0)=='0'){
            sb.setCharAt(0, '1');
        }
        return sb.toString();
    }
    
    public NombreBinaire decalageGauche(){
        String binStr = this.toString().substring(0, this.toString().length()-1);
        
        NombreBinaire res = new NombreBinaire(binStr);

        return res;
    }
     
}
