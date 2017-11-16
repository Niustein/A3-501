import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class ObjectCreator {
	private Scanner uInput;
	private String uChoice = "";
	ArrayList<Object> objectList; 
	
	public ObjectCreator() {
		objectList = new ArrayList<Object>();
	}
	
	public ArrayList<Object> userCreator() {
		
		uInput = new Scanner(System.in);
		while(true) {
			System.out.println("Input 1 to create an object with only primitive values\n"
								+ "Input 2 to create an object with references to Primitive object 1\n"
								+ "Input 3 to create an Array object with only primitives\n"
								+ "Input 4 to create an Array object with object references\n"
								+ "Input 5 to create an object using the Java Collection Class to refer to other objects\n"
								+ "Input any other value to finish creating objects\n");
			
			uChoice = uInput.nextLine();
			int numTest;
			
			try {
				numTest = Integer.valueOf(uChoice);
			} catch(NumberFormatException e) {
				System.out.println("Finished creating objects");
				break;
			}
			
			if(numTest == 1) {
				System.out.println("Creating object with primitive values");
				createPrim(uInput);
			} else if (numTest == 2) {
				System.out.println("Creating object with references to primitive object 1");
				createRef(uInput);
			} else if (numTest == 3) {
				System.out.println("Creating Array object with only primitives");
				createArrayPrim(uInput);
			} else if (numTest == 4) {
				System.out.println("Creating array object with object references");
				createArrayObj(uInput);
			} else if (numTest == 5) {
				System.out.println("Creating object using Java Collection Class referencing other objects");
				createJavaCollectClassObj(uInput);
			} else {
				System.out.println("Finished creating objects");
				break;
			}
		}
		
		return objectList;
		
	}
	
	/*
	 * 1) Primitive type
	 * 2) Reference to other objects
	 * 3) Array of primitives
	 * 4) Array of object references
	 * 5) Instance of one of java's collection classes to refer to several other objects
	 */
	
	public void createPrim(Scanner scannerChoice) {
		Object primObj = new primitiveObject(scannerChoice);
		objectList.add(primObj);
	}
	
	public void createRef(Scanner scannerChoice) {
		Object refObj = new referenceObject(scannerChoice);
		objectList.add(refObj);
	}
	
	public void createArrayPrim(Scanner scannerChoice) {
		Object arrayPrimObj = new primitiveArrayObject(scannerChoice);
		objectList.add(arrayPrimObj);
	}
	
	public void createArrayObj(Scanner scannerChoice) {
		Object arrayObjectObj = new objectArrayObject(scannerChoice);
		objectList.add(arrayObjectObj);
	}
	
	public void createJavaCollectClassObj (Scanner scannerChoice) {
		Object javaCollectClassObj = new javaCollectClassObject(scannerChoice);
		objectList.add(javaCollectClassObj);
	}
	
	public int getSize() {
		return objectList.size(); 
	}
	
	public void showData(int val) {
		try {
			System.out.println(objectList.get(val).toString());
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Object> getObjectList(){
		return objectList;
	}
	
}
