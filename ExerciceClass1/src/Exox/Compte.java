package Exox;

public class Compte {

	 private int numero;
	 private String titulaire;
	 private double soldeCompte = 0.0 ;
	 private String deviseCompte = "EUR" ;
	 int nbOperation = 0;
	 
	 public Compte()
	 {
		 numero = 0;
		 titulaire = "nom prenom";
	 }
	 public Compte(int numero, String titulaire)
	 {
		 this.numero = numero;
		 this.titulaire = titulaire;
	 }
	 
	 public String getNom()
	 {
		 String str[] = titulaire.split(" ");
		 return str[0];
	 }
	 public String getPrenom()
	 {
		 String str[] = titulaire.split(" ");
		 return str[1];
	 }
	 
	 public void depot(double montant){
		 if(montant > 10 || soldeCompte + montant <= 100)
		 {
			 soldeCompte = soldeCompte + montant ;
			 nbOperation++;
		 }
	 }
	 public String etat(){
		 String etatResultat = "Solde actuel = "+soldeCompte+" "+deviseCompte ;
		 etatResultat += "\noperation : " + nbOperation;
		 return etatResultat;
	 }
	 
	 public void retrait(double montant)
	 {
		 if(montant >= soldeCompte || montant > 5)
		 {
			 soldeCompte = soldeCompte - montant ;
			 nbOperation++;
		 }
		 else
			 System.out.println("retrait impossible");
		 
	 }
	 public void interets()
	 {
		 soldeCompte -= (soldeCompte * 0.01);
	 }
	 
	 private void convertireEnEuro()
	 {
		 if(deviseCompte.equals("USD"))
		 {
			 soldeCompte = soldeCompte * 0.740709;
		 }
		 else if(deviseCompte.equals("Yen"))
		 {
			 soldeCompte = soldeCompte * 0.00750731;
		 }
		 else if(deviseCompte.equals("Yuan"))
		 {
			 soldeCompte = soldeCompte * 0.12;
		 }
	 }
	 
	 public String devise(String nomNouvelleDevise)
	 {
		
		 convertireEnEuro();
		 if(nomNouvelleDevise.equals("USD"))
		 {
			 soldeCompte = soldeCompte * 1.35006;
		 }
		 else if(nomNouvelleDevise.equals("Yen"))
		 {
			 soldeCompte = soldeCompte * 133.203;
		 }
		 else if(nomNouvelleDevise.equals("Yuan"))
		 {
			 soldeCompte = soldeCompte * 8.24;
		 }
		 
		 deviseCompte = nomNouvelleDevise;
		 
		 return nomNouvelleDevise;
	 }
	//associée compte
	// Calcule le nouveau solde après conversion dans la nouvelle devise
	// Modifie la devise associée au compte
	// Retourne le nom de la nouvelle devise associée au compte
	/*1 USD = 0.740709 EUR 1 EUR = 1.35006 USD
	1 Yen = 0.00750731 EUR 1 EUR = 133.203 Yens
	1 Yuan = 0.12 EUR 1 EUR = 8.24 Yuans*/

	 
	  public String html(){
		    String page = "<html><body>"
		        + "<h1>BanqueEnLigne</h1>"
		        + "<hr />"
		        + "<p><b>Nom</b> : " + getNom() +" | <b>Prenom</b> : " + getPrenom() + "</p>"
		        + "<p><b>Numero : </b>"+ numero +"</p>"
		        + "Solde actuel = "+soldeCompte+" "+deviseCompte+" ("+nbOperation+")"
		        + "<hr />"
		        + "<h2> limite solde = 100 EUR </h2>"
		        + "<form method='GET' action='/compte'>"
		        + "<h3>Saisir un montant puis l'operation a realiser</h3>"
		        + "Montant : <input type='input' name='montant' /> " + deviseCompte
		        + "<input type='submit' name='operation' value='depot' />"
		        + "<input type='submit' name='operation' value='retrait' />"
		        + "<h3>ou bien convertir le compte dans la devise selectionnee</h3>"
		        + "<select name='devise'>"
		        + "<option>USD</option>"
		        + "<option>Yen</option>"
		        + "<option>Yuan</option>"
		        + "<option selected>EUR</option>"
		        + "</select>"
		        + "<input type='submit' name='operation' value='devise' />"
		        + "</form>"
		        + "</body></html>" ;
		    return page ;
		  }
	  
	  public String authentificationHtml()
	  {
		    String page = "<html><body>"
			        + "<h1>Authentification</h1>"
			        + "<hr />"
			        + "<form method='GET' action='/compte'>"
			        + "<label>numero</label>"
			        + "<input type='text' name='numero'/></br>"
			        + "<label>titulaire</label>"
			        + "<input type='text' name='titulaire'/></br>"
			        + "<input type='submit' name='valider' value='valider'/></br>"
			        + "</form>"
			        + "</body></html>" ;
		  
		  return page;
	  }

	
}
