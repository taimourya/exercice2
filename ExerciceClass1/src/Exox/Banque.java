package Exox;

import java.util.Scanner;

public class Banque {

	static Scanner clavier = new Scanner(System.in) ;
	public static int menu()
	{
		int choix;
		System.out.println("1 -> DEPOT");
		System.out.println("2 -> RETRET");
		System.out.println("3 -> INTEREST");
		System.out.println("4 -> CHANGER DEVISE");
		System.out.println("5 -> ETAT");
		System.out.println("0 -> QUITTER");
		System.out.println("VOTRE CHOIX ...");
		choix = clavier.nextInt();
		return choix;
	}
	public static String menu_devise()
	{
		int choix;
		System.out.println("1 -> EUR");
		System.out.println("2 -> USD");
		System.out.println("3 -> Yen");
		System.out.println("4 -> Yuan");
		choix = clavier.nextInt();
		switch(choix)
		{
		case 1:
			return "EUR";
		case 2:
			return "USD";
		case 3:
			return "Yen";
		case 4:
			return "Yuan";
		}
		return null;
	}
	
	
	public static void main(String[] arguments) {
		
		int choix;
		double montant;
		Compte compte = new Compte();
		do
		{
			choix = menu();
			switch(choix)
			{
			case 1:
				System.out.println("montant a deposer");
				montant = clavier.nextDouble();
				compte.depot(montant);
				break;
			case 2:
				System.out.println("montant a retirer");
				montant = clavier.nextDouble();
				compte.retrait(montant);
				break;
			case 3:	
				System.out.println("interest");
				compte.interets();
				break;
			case 4:
				System.out.println("new devise");
				compte.devise(menu_devise());
				break;
			case 5:
				System.out.println(compte.etat());
				break;
			case 0:
				break;
			default:
				System.out.println("VOTRE CHOIX NE FIGURE PAS DANS LA LISTE");
			}
			
		}while(choix != 0);
		
	 }
	
}
