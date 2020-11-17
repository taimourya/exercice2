package Exox;

public class UnTypeEnregistrement {
	 String champChaine = "champ" ;
	 int champEntier = 0 ;
	 private double champPrivateDouble = 0.0;
	 
	 public UnTypeEnregistrement(String champChaine)
	 {
		 this.champChaine = champChaine;
	 }
	 public UnTypeEnregistrement(int champEntier)
	 {
		 this.champEntier = champEntier;
	 }
	 public UnTypeEnregistrement(String champChaine, int champEntier)
	 {
		 this.champChaine = champChaine;
		 this.champEntier = champEntier;
	 }
	 
	 public static void main(String[] arguments) {

	 UnTypeEnregistrement unEnregistrement = new UnTypeEnregistrement("TOTO", 2020);
	 System.out.println("unEnregistrement.champChaine = "+unEnregistrement.champChaine);
	 System.out.println("unEnregistrement.champEntier = "+unEnregistrement.champEntier);
	 
	 }
}

