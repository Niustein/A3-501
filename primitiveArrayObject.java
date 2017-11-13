import java.util.Scanner;

public class primitiveArrayObject {
	int userNum;
	int[] arrayPrim;
		
	public primitiveArrayObject(Scanner scannerChoice) {
		while(true) {
			try {
				System.out.println("Set the size of the array (from 1 to 10): ");
				int sizeUser = Integer.parseInt(scannerChoice.nextLine());
				
				int[] arrayPrim= new int[sizeUser];
				
				if (sizeUser > 0 && sizeUser < 11) {
					System.out.println("Set the values for the array");
					for(int i = 0; i < sizeUser; i++) {
						userNum = Integer.parseInt(scannerChoice.nextLine());
						arrayPrim[i] = userNum;
					}
				} else {
					System.out.println("Not an acceptable size");
					throw new Exception();
				}
				
				break;
				
			} catch (Exception e) {
				System.out.println("Invalid input, input will restart");
			}
		}
	}
	
	public String toString() {
		String arrayInputs = "";
		for (int i = 0; i < arrayPrim.length; i++) {
			if (i < arrayPrim.length-1) {
				arrayInputs += arrayPrim[i] + ", ";
			} else {
				arrayInputs += arrayPrim[i];
			}
		}
		
		return "Class: Primitive Array \n" + "Field Values \n" + "arrayPrim:{ " + arrayInputs + " }\n";
		
	}
}
