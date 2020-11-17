package Exox;

import java.io.*;
import java.net.*;

class BanqueServer {

	   public static void main(String argv[]) throws Exception 
	   {
			ServerSocket serveurBanque = new ServerSocket(8080);
		   	Compte compte = authentification(serveurBanque);

		    String requeteClient ;
	
		    while(true) {
		        // attend une requ�te HTTP en provenance d'un navigateur
		        // puis r�cup�re les flux de lecture et d'�criture r�seau (cf Cours syst�me)
		        Socket connexionAvecNouveauClient = serveurBanque.accept();
		        BufferedReader inFromClient =  new BufferedReader(new InputStreamReader(connexionAvecNouveauClient.getInputStream()));
		        PrintWriter outToClient = new PrintWriter(connexionAvecNouveauClient.getOutputStream());
		        
		        // lit puis analyse la requ�te re�ue
		        requeteClient = inFromClient.readLine();
		        System.out.println("Requete: " + requeteClient);
		        Operation aFaire = analyseRequete(requeteClient);
		        System.out.println("A faire montant/operation/devise: "
		         + aFaire.montant + "/"
		         + aFaire.operation + "/"
		         + aFaire.devise);
		        
		        // R�alise l'op�ration demand�e sur le compte
		        if ( aFaire.operation.equals("depot") ) { compte.depot(aFaire.montant); }
		        if ( aFaire.operation.equals("retrait") ) { compte.retrait(aFaire.montant); }
		        if ( aFaire.operation.equals("devise") ) { compte.devise(aFaire.devise); }
		        
		        // Fabrique la r�ponse HTTP r�sultat qui contiendra la page HTML � afficher
		        outToClient.println("HTTP/1.0 200 OK");
		        outToClient.println("Content-Type: text/html; charset=utf-8");
		        outToClient.println(""); // Cette ligne VIDE est OBLIGATOIRE
		        outToClient.println(compte.html());
		        outToClient.close();
		    } // fin while infini
		  } // fin main
	   
   		 public static Compte authentification(ServerSocket serveurBanque) throws IOException 
   		 {
   			 Compte compte = new Compte();
 		     String requeteClient ;
   			 
 		    while(true) {
		        // attend une requ�te HTTP en provenance d'un navigateur
		        // puis r�cup�re les flux de lecture et d'�criture r�seau (cf Cours syst�me)
		        Socket connexionAvecNouveauClient = serveurBanque.accept();
		        BufferedReader inFromClient =  new BufferedReader(new InputStreamReader(connexionAvecNouveauClient.getInputStream()));
		        PrintWriter outToClient = new PrintWriter(connexionAvecNouveauClient.getOutputStream());
		        
		        // lit puis analyse la requ�te re�ue
		        requeteClient = inFromClient.readLine();
		        System.out.println("Requete: " + requeteClient);
		        Authentification aFaire = analyseAuthentification(requeteClient);
		        System.out.println("A faire montant/operation/devise: "
		         + aFaire.numero + "/"
		         + aFaire.titulaire);
		        
		        if(aFaire.numero != 0 && !aFaire.titulaire.equals(""))
		        {
		        	compte = new Compte(aFaire.numero, aFaire.titulaire);
		        	serveurBanque.close();

		        	return compte;
		        }
		        
		        // Fabrique la r�ponse HTTP r�sultat qui contiendra la page HTML � afficher
		        outToClient.println("HTTP/1.0 200 OK");
		        outToClient.println("Content-Type: text/html; charset=utf-8");
		        outToClient.println(""); // Cette ligne VIDE est OBLIGATOIRE
		        outToClient.println(compte.authentificationHtml());
		        outToClient.close();
		    } // fin while infini
   		 }
	   
   		 public static Authentification analyseAuthentification(String requete)
   		 {
   			Authentification authentifRes = new Authentification();
		    String finRequete = requete.replace("GET /compte?","");
		    String debutRequete = finRequete.replace(" HTTP/1.1","");
		    String[] tabParties = debutRequete.split("&");
		    
		    for (int i = 0; i < tabParties.length; i = i + 1){
		        String[] nomValeur = tabParties[i].split("=");
		        switch (nomValeur[0]) {
		           case "numero" :
		        	   authentifRes.numero = Integer.parseInt(nomValeur[1]);
		           break;
		           case "titulaire" :
		        	   authentifRes.titulaire = nomValeur[1] ;
		           break;
		        } // fin switch
		    } // fin for
		    
		    return authentifRes;
   		 }
   		 
		  // Comprendre cette fonction est une bonne r�vision avant le contr�le
		  public static Operation analyseRequete(String requete){
		    Operation operationResultat = new Operation();
		    String finRequete = requete.replace("GET /compte?","");
		    String debutRequete = finRequete.replace(" HTTP/1.1","");
		    String[] tabParties = debutRequete.split("&");
		      
		    for (int i = 0; i < tabParties.length; i = i + 1){
		        String[] nomValeur = tabParties[i].split("=");
		        switch (nomValeur[0]) {
		           case "operation" :
		             operationResultat.operation = nomValeur[1] ;
		           break;
		           case "devise" :
		             operationResultat.devise = nomValeur[1] ;
		           break;
		           case "montant" :
		             try {
		               if ( nomValeur.length == 2 ){
		                operationResultat.montant = Double.parseDouble(nomValeur[1]) ;
		               } 
		             } catch (NumberFormatException e) {System.out.println(e);}
		           break;
		        } // fin switch
		    } // fin for
		    return operationResultat ;
		  } // fin analyseRequete
	} // fin class BanqueServer0

	// Comprendre ce type enregistrement est une bonne r�vision avant le contr�le
	class Operation {
	    String operation = "" ;
	    double montant = 0.0 ;
	    String devise = "" ;
	} // fin class Operation
	
	class Authentification
	{
		int numero = 0;
		String titulaire = "";
	}