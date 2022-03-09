/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client de connexion TCP
 * @author Matthieu
 */
public class Network {
    
    private static Network instance = null;
    private Socket socket;
    private BufferedReader fluxEntrant;
    private PrintWriter fluxSortant;
    private boolean connecte;
    
    /**
     * Constructeur de Network
     */
    private Network() {
        this.connecte = false;
    }
    
    /**
     * Getter du singleton
     * @return l'instance
     */
    private static Network get() {
        if (instance == null)
            instance = new Network();
        
        return instance;
    }
    
    /**
     * Création de la socket
     * @throws IOException 
     */
    private void creationSocket() throws IOException {
        this.socket = new Socket("127.0.0.1",1234);
    }
    
    /**
     * Création des flux
     * @throws IOException 
     */
    private void creationFlux() throws IOException {
        InputStreamReader reader = new InputStreamReader(this.socket.getInputStream());
        this.fluxEntrant = new BufferedReader(reader);
        this.fluxSortant = new PrintWriter(this.socket.getOutputStream(),true);
    }
    
    /**
     * Connexion
     * @throws IOException 
     */
    public static void connexion() throws Exception {
        get().creationSocket();
        get().creationFlux();
        
        /*if(recevoirLigne().equals("Saisir votre NOM"))
            envoyerLigne(Settings.getNomUtilisateur());
        
        if(recevoirLigne().equals("Connexion établie")){
            //System.out.println("test connexion");
            get().connecte = true;
        }*/
    }
    
    /**
     * Envoyer une ligne
     * @param message le message à envoyer 
     */
    public static void sendMessage(String message) {
        System.out.println(">" + message);
        get().fluxSortant.println(message);
    }
    
    /**
     * Recevoir un message
     * @return le message reçu
     * @throws IOException 
     */
    public static String receiveMessage() throws Exception {
        String message = get().fluxEntrant.readLine();
        System.out.println("<" + message);
        return message;
    }
    
    public static void end() throws IOException
    {
        get().closeSocket();
    }
    
    private void closeSocket() throws IOException {
        this.socket.close();
        System.out.println("socket deleted");
    }
    /**
     * Le client est-il connecté ?
     * @return est-ce que le client est connecté ou non.
     */
    public static boolean estConnecte() {
        return get().connecte;
    }
}

