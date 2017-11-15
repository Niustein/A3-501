import java.util.Scanner;

public class objectArrayObject {

	Object[] arrayObj = new Object[5];
		
	public objectArrayObject(Scanner scannerChoice) {
		while(true) {
				for (int i = 0; i < 3; i++) {
					System.out.println("Which object would you like to add into arrayObj?\n"
										+ "1: Primiative Object\n"
										+ "2: Reference Object\n"
										+ "3: Primitive Array Object\n"
										+ "4: Object Array Object\n" 
										+ "5: Collect Class Object");
					
					String userPick = scannerChoice.nextLine();
					
					if(userPick.equals("5")){
						arrayObj[i] = new javaCollectClassObject(scannerChoice);
					} else if (userPick.equals("4")) {
						arrayObj[i] = new objectArrayObject(scannerChoice);
					} else if (userPick.equals("3")) {
						arrayObj[i] = new primitiveArrayObject(scannerChoice);
					} else if (userPick.equals("2")) {
						arrayObj[i] = new referenceObject(scannerChoice);
					} else if (userPick.equals("1")) {
						arrayObj[i] = new primitiveObject(scannerChoice);
					} else {
						System.out.println("Invalid Input, input will restart");
						i=0;
					}
				}
				
				break;
			
		}
	}
	
	public String toString() {
		String arrayClassInfo = "";
		String arrayInfo = "";
		for (int i = 0; i < 3; i++) {
			if (i < 2) {
				arrayClassInfo += arrayObj[i].getClass().getSimpleName() + ", ";
			} else {
				arrayClassInfo += arrayObj[i].getClass().getSimpleName();
			}
			
			arrayInfo += "Field Element #: " + i  + "\n" + arrayObj[i].toString(); 
			
		}
		
		return "\nClass: objectArrayObject \n" + "Field Values \n" + "arrayObj:{ " + arrayClassInfo + " }\n" + arrayInfo;
		
	}
}
