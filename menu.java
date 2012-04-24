import java.io.IOException;
import java.util.Scanner;



public class menu {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void Main(String args[]) throws IOException {
		System.out.println("TEST GENERATION PROGRAM");
		System.out.println("Enter database name:");
		String file = scan.next();
		
		System.out.println("Select type of test to create:");
		System.out.println("A. LaTeX/PDF");
		System.out.println("B. Web");
		String test = scan.next();
		
		
		LatexFile database1 = new LatexFile(file);
		//HTMLFile database1 = new HTMLFile(file);
		boolean lbase = false; //true = Latex, false = HTML
		boolean hbase = false; //true = HTML, false = Latex
		if (test == "A" || test == "a") {
			lbase = true;
			hbase = false;
		}
		else if(test == "B" || test == "b") {
			hbase = true;
			lbase = false;
		}
		
		boolean continuance = true;
		do {
			System.out.println("Enter sections to print");
			Double section = scan.nextDouble();
			System.out.println("Enter question difficulties to print");
			int difficulty = scan.nextInt();
			System.out.println("Enter max number of questions to print");
			int number = scan.nextInt();
			if(lbase)
				database1.WriteLatexQuestions(section,difficulty,number);
			if (hbase){}
				//database2.WriteLatexQuestions(section,difficulty,number);
			System.out.println("Do you want to continue adding questions? Y = yes, N = no");
			char resp = scan.next().charAt(0);
			if (resp == 'Y' || resp == 'y')
				continuance = true;
			else {
				continuance = false;
				if(lbase)
					database1.WriteLatexFoot();
				if (hbase){}
					//database2.WriteHTMLFoot();
			}
		} while (continuance == true);
		System.out.println("Test file generated. Goodbye");		
	}
}