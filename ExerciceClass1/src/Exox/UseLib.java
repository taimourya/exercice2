package Exox;

public class UseLib {

	public static void main(String[] args) {

		 System.out.print("depuis UneLibrairie.main() : ");
		 System.out.print("appel de : ");
		 UneLib.staticvoid();
		 System.out.println(" : OK");
		 System.out.print("depuis UneLibrairie.main() : ");
		 
		 System.out.print("appel de : ");
		 UneLib.publicstaticvoid();
		 System.out.println(" : OK");
		
		 System.out.print("depuis UneLibrairie.main() : ");
		 System.out.print("appel de : ");
		 //UneLib.privatestaticvoid();
		 System.out.print("impossible : ");
		 System.out.println(" : OK");

		 System.out.print("depuis UneLibrairie.main() : ");
		 System.out.println("staticstring == " + UneLib.staticstring);
		 System.out.print("depuis UneLibrairie.main() : ");
		 UneLib.staticint = 3;
		 System.out.println("staticint == " + UneLib.staticint);

	}

}
