import java.util.Scanner;

public class primitiveArrayObject {
	int uInput;
	int[] arrayPrim;
		
	public primitiveArrayObject() {}
	
	public primitiveArrayObject(Scanner scannerChoice) {
		while(true) {
			try {
				System.out.println("Set the size of the array (from 1 to 5): ");
				int sizeUser = Integer.parseInt(scannerChoice.nextLine());
				
				arrayPrim= new int[sizeUser];
				
				if (sizeUser > 0 && sizeUser < 6) {
					System.out.println("Set the integer values for the array");
					for(int i = 0; i < sizeUser; i++) {
						int uInput = Integer.parseInt(scannerChoice.nextLine());		
						
						arrayPrim[i] = uInput;
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
		
		return "Class: Primitive Array \n" + "**Field Values** \n" + "arrayPrim:{ " + arrayInputs + " }\n";
		
	}
}
