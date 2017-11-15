import java.util.Scanner;

public class primitiveObject {
	String nextInput;
	int userNum = -1;
	
	public primitiveObject(Scanner scannerChoice) {
		while(true) {
			try {
				System.out.println("Set the int value for type Primitive Object: ");
				nextInput = scannerChoice.nextLine();
				userNum = Integer.parseInt(nextInput);
				
				break;
				
			} catch (Exception e) {
				System.out.println("Invalid format for primtive object, please input a valid input");
			}
		}
	}
	
	public String toString() {
		return "\nClass: PrimitiveType\n" + "**Field Values**\n" + "userNum: " + userNum + "\n";
		
	}
	
	
	
}
