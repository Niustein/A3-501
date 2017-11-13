import java.util.Scanner;

public class referenceObject {
	String nextLine;
	int userNum;
	String userString;
	primitiveObject objReference;
	
	
	public referenceObject(Scanner scannerChoice) {
		while(true) {
			try {
				nextLine = scannerChoice.nextLine();
				
				System.out.println("Set the int value for type reference Object: ");
				userNum = Integer.parseInt(nextLine);
				
				nextLine = scannerChoice.nextLine();
				
				System.out.println("Set the string value for Object reference: ");
				userString = scannerChoice.nextLine();
				
				System.out.println("Set a primitive reference for Object Reference");
				objReference = new primitiveObject(scannerChoice);
				
				
				break;
			} catch (Exception e) {
				System.out.println("Invalid format for reference object, please input a valid input");
			}
		}
	}
	
	public String toString() {
		return "Class: reference Object\n" + "Field Values\n" + "userNum: " + userNum + "\n" + "userString" + userString + "\n" + "object Reference\n" + objReference.toString();
	}
	
	
}
