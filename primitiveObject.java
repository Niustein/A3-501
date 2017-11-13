import java.util.Scanner;

public class primitiveObject {
	String nextInput;
	int userNum;
	char userChar;
	
	public primitiveObject(Scanner scannerChoice) {
		System.out.println("Input value for Primitive Object:");
		while(true) {
			try {
				nextInput = scannerChoice.nextLine();
				
				System.out.println("Set the int value for type Primitive Object: ");
				userNum = Integer.parseInt(nextInput);
				
				nextInput = scannerChoice.nextLine();
				
				System.out.println("Set the char value for type Primitive Object: ");
				userChar = nextInput.charAt(0);
				
				break;
				
			} catch (Exception e) {
				System.out.println("Invalid format for primtive object, please input a valid input");
			}
		}
	}
	
	public String toString() {
		return "Class: PrimitiveType\n" + "Field Values\n" + "userNum: " + userNum + "\n" + "userChar" + userChar + "\n";
	}
	
	
	
}
