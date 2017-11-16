import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Receiver {

	public static void main(String[] args) {
		int i;
		int portNum = 0;
		int testNum = 0;
		int countFiles = 0;
		ObjectCreator objCreate = new ObjectCreator();
		Serializer ser = new Serializer();
		XMLOutputter xmlOutput = new XMLOutputter();
		Scanner scannerChoice = new Scanner(System.in);
		ArrayList<Object> objFromSender = new ArrayList<Object>();
		
		String userChoice;
		
		xmlOutput.setFormat(Format.getPrettyFormat());
		objCreate.objectList = objFromSender;
		
		// Gets a port number from user
		while(true) {
			try {
				//Port number in range of ICANN
				System.out.println("Input port number of server between 1024 and 49151");
			
				portNum = Integer.parseInt(scannerChoice.nextLine());
				if(portNum < 1024 || portNum > 49151) {
					throw new NumberFormatException();
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid port number");
			}
		}
		
	//	serverR server = new serverR(objFromSender, portNum);
		while(true) {
			listCommands();
			System.out.println("Please enter a command");
			userChoice = scannerChoice.nextLine();
			
			if (userChoice.equals("quit")){
				System.out.println("Exiting receiver");
				break;
			} else {
				countFiles = new File("..\\TF").listFiles().length;
				
				if (countFiles < 1) {
					System.out.println("No objects have been sent so far");
					continue;
				}
				
				System.out.println("Which file would you like to inspect (from 0 to " + (countFiles-1) + ")");
				
			}
		}
		
	}
	
	private static void listCommands() {
		System.out.println("inspect\t\t inspects the field values of a received object\n"
						+ "quit\t\t Closes server and ends program");  
	}
}
