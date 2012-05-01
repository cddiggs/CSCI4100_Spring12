import java.io.IOException;
import java.util.Scanner;



public class menu {
	
	
	
	public static void main(String args[]) throws IOException {
	
            Scanner scan = new Scanner(System.in);
            
                System.out.println("TEST GENERATION PROGRAM");
		System.out.println("Enter database name:");
		String file = scan.nextLine();
                System.out.println(file);

		System.out.println("Select type of test to create:");
		System.out.println("A. LaTeX/PDF");
		System.out.println("B. Web");
		String test = scan.nextLine();
                System.out.println(test);
		
                System.out.println("Enter test name (alphanumeric characters and spaces only)");
                String testname = scan.nextLine();
                System.out.println(testname);
		
                LatexFile database1;
		htmlFile database2 = new htmlFile(file);
		boolean lbase = false; //true = Latex, false = HTML
		boolean hbase = false; //true = HTML, false = Latex
		if (test.charAt(0) == 'A' || test.charAt(0) == 'a') {
			lbase = true;
			hbase = false;
		}
		else if(test.charAt(0) == 'B' || test.charAt(0) == 'b') {
			hbase = true;
			lbase = false;
		}
		System.out.println("Enter subject covered");
                String subject = scan.nextLine();
                System.out.println(subject);
                if(lbase==true){
                    database1 = new LatexFile(testname + ".tex",file);
                    database1.WriteLatexHead(testname);
                }
		boolean continuance = true;
		while (continuance){
			System.out.println("Enter sections to print");
			Double section = scan.nextDouble();
                        System.out.println(section);
			System.out.println("Enter question difficulties to print");
			int difficulty = scan.nextInt();
                        System.out.println(difficulty);
			System.out.println("Enter max number of questions to print");
			int number = scan.nextInt();
                        System.out.println(number);
			if(lbase)
				database1.WriteLatexQuestions(subject,section,difficulty,number);
			if (hbase)
				database2.WritehtmlQuestions(subject,section,difficulty,number);
			System.out.println("Do you want to continue adding questions? Y = yes, N = no");

                        String resp = scan.next();
                        System.out.println(resp);
			if (resp.charAt(0) == ('Y' | 'y')){
				continuance = true;
                                System.out.println("yes");
                        }
			else {
				if(lbase==true)
					database1.WriteLatexFoot();
				if(hbase==true){
                                        System.out.println("no");
					database2.GeneratehtmlTest(testname);
                                }
                                continuance = false;
			}
		} 
		System.out.println("Test file generated. Goodbye");		
	}
}