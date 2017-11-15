import java.util.Scanner;

public class primitiveObject {
	String nextInput;
	int userNum = -1;
	char userChar;
	
	public primitiveObject(Scanner scannerChoice) {
		while(true) {
			try {
				System.out.println("Select 1 for Char, 2 for anything else");
				nextInput = scannerChoice.nextLine();
				
				if (Integer.valueOf(nextInput) == 1) {
					System.out.println("Set the char value for type Primitive Object: ");
					nextInput = scannerChoice.nextLine();
					userChar = nextInput.charAt(0);
				} else {
					System.out.println("Set the numeric value for type Primitive Object: ");
					nextInput = scannerChoice.nextLine();
					userNum = Integer.parseInt(nextInput);
				}
				
				break;
				
			} catch (Exception e) {
				System.out.println("Invalid format for primtive object, please input a valid input");
			}
		}
	}
	
	public String toString() {
		if(userNum == -1) {
			return "Class: PrimitiveType\n" + "**Field Values**\n" + "userNum:\n " + "userChar: " + userChar + "\n";
 		} else {
 			return "Class: PrimitiveType\n" + "**Field Values**\n" + "userNum: " + userNum + "\n" + "userChar: \n";
 		}
	}
	
	
	
}
