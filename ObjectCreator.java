import java.util.Scanner;
import java.util.Vector;

public class ObjectCreator {
	private Scanner uInput;
	private String uChoice = "";
	Vector<Object> objectList; 
	Object lastCreated;
	
	public ObjectCreator() {
		objectList = new Vector<Object>();
		lastCreated = null;
	}
	
	/*
	 * 1) Primitive type
	 * 2) Reference to other objects
	 * 3) Array of primitives
	 * 4) Array of object references
	 * 5) Instance of one of java's collection classes to refer to several other objects
	 */
	
	public void creatPrim(Scanner scannerChoice) {
		Object primObj = new primitiveObject(scannerChoice);
		objectList.add(primObj);
		lastCreated = primObj;
	}
	
	public void createRef(Scanner scannerChoice) {
		Object refObj = new referenceObject(scannerChoice);
		objectList.add(refObj);
		lastCreated = refObj;
	}
	
	public void createArrayPrim(Scanner scannerChoice) {
		Object arrayPrimObj = new primitiveArrayObject(scannerChoice);
		objectList.add(arrayPrimObj);
		lastCreated = arrayPrimObj;
	}
	
	public void createArrayObj(Scanner scannerChoice) {
		Object arrayObjectObj = new objectArrayObject(scannerChoice);
		objectList.add(arrayObjectObj);
		lastCreated = arrayObjectObj;
	}
	
	public void createJavaCollectClassObj (Scanner scannerChoice) {
		Object javaCollectClassObj = new javaCollectClassObject(scannerChoice);
		objectList.add(javaCollectClassObj);
		lastCreated = javaCollectClassObj;
	}
	
	
}
