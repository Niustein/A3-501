import java.util.ArrayList;
import java.util.Scanner;

public class javaCollectClassObject {

	ArrayList<Object> vectorObjects = new ArrayList<Object>();
	
	public javaCollectClassObject(Scanner scannerChoice) {
		while(true) {
				for (int i = 0; i < 3; i++) {
					System.out.println("Which object would you like to add into javaCollectClassObject?\n"
										+ "1: Primiative Object\n"
										+ "2: Reference Object\n"
										+ "3: Primitive Array Object\n"
										+ "4: Object Array Object\n" 
										+ "5: Collect Class Object");
					
					String userPick = scannerChoice.nextLine();
					
					if(userPick.equals("5")){
						vectorObjects.add(new javaCollectClassObject(scannerChoice));
					} else if (userPick.equals("4")) {
						vectorObjects.add(new objectArrayObject(scannerChoice));
					} else if (userPick.equals("3")) {
						vectorObjects.add(new primitiveArrayObject(scannerChoice));
					} else if (userPick.equals("2")) {
						vectorObjects.add(new referenceObject(scannerChoice));
					} else if (userPick.equals("1")) {
						vectorObjects.add(new primitiveObject(scannerChoice));
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
				arrayClassInfo += vectorObjects.get(i).getClass().getSimpleName() + ", ";
			} else {
				arrayClassInfo += vectorObjects.getClass().getSimpleName();
			}
			
			arrayInfo += "Field Element #: " + i  + "\n" + vectorObjects.get(i).toString(); 
			
		}
		
		return "\nClass: objectArrayObject \n" + "**Field Values** \n" + "arrayObj:{ " + arrayClassInfo + " }\n" + arrayInfo;
		
	}
}
