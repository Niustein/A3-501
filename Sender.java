import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

public class Sender {

	public static void main(String[] args) {
		int i;
		int portNum = 0;
		int testNum = 0;
		ObjectCreator objCreate = new ObjectCreator();
		Serializer ser = new Serializer();
		ArrayList<Object> objToSerialize = null;
		Vector<Document> docToSend = null;
		Scanner scannerChoice = new Scanner(System.in);
		XMLOutputter xmlOutput = new XMLOutputter();
		Document[] dList;
		
		String userChoice;

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
		
		// Runs until user quits
		while(true) {
			listCommands();
			System.out.println("Please enter a command");
			userChoice = scannerChoice.nextLine();
			
			if (userChoice.equals("quit")) {
				System.out.println("Quitting program");
				break;
				
			} else if (userChoice.equals("create")){
				//Creates object
				objToSerialize = objCreate.userCreator();
				
				//Serializes
				dList = new Document[objToSerialize.size()];
				
				// Create document array of serialized files 
				for (i = 0; i < objToSerialize.size(); i++) {
					dList[i] = ser.findSerializeObject(objToSerialize.get(i));
				}
							
				docToSend = new Vector<Document>();
				
				// Add serialized files to document vector
				for (i = 0; i < dList.length; i++) {
					docToSend.add(dList[i]); 
				}
				
				for (i = 0; i < docToSend.size(); i++) {
					try {
//						xmlOutput.output(docToSend.get(i), System.out);
						xmlOutput.output(docToSend.get(i), new FileOutputStream(new File("send" + i + ".xml")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}


				
			} else if (userChoice.equals("show")) {
				// Show data of an object to user
				try {
					testNum = objToSerialize.size() - 1;
				} catch(NullPointerException e) {
					System.out.println("No objects created at this time");
					continue;
				}
				
				System.out.println("Choose which object you would like to see (from 0 to " + testNum + ")\n");
				userChoice = scannerChoice.nextLine();
				
				try {
					objCreate.showData(Integer.parseInt(userChoice));
				} catch (IndexOutOfBoundsException | NumberFormatException e) {
					System.out.println("Invalid object chosen");
				}
				
				
				
			} else if (userChoice.equals("showSer")) {

				try {
					testNum = objToSerialize.size() - 1;
				} catch(NullPointerException e) {
					System.out.println("No objects created at this time");
					continue;
				}
				
				
				try {
					xmlOutput.output(docToSend.get(testNum), System.out);
				} catch (IndexOutOfBoundsException | NumberFormatException | IOException e) {
					System.out.println("Invalid object chosen");
				}
					
				
			} else if (userChoice.equals("send")) {
				//Serializes object and sends to receiver
				System.out.println("Sending all " + objCreate.getSize() +" serialized objects");
				
				//Create serialized XML files and send
				for (i = 0; i < docToSend.size(); i++) {
					try {
						Socket connection = new Socket("localhost", portNum);
				//		xmlOutput.output(docToSend.get(i), new FileOutputStream(new File("send" + i + ".xml")));
						BufferedReader br = new BufferedReader(new FileReader("send" + i + ".xml"));
						while(br.readLine() != null) {
							String k = br.readLine();
							DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
							dos.writeUTF(k);
						}

				//		OPStream.flush();
						connection.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}						
				
			} else if (userChoice.equals("commands")) {
			} else {
				System.out.println("Invalid command\n");
				listCommands();
			}

		}
				
	}
	
	
	private static void listCommands() {
		System.out.println("create\t\t Create an object\n"
						+ "send\t\t Send serialized version to a receiver (sending files ends the program)\n"
						+ "show\t\t shows the information of a created object\n"
						+ "showSer\t\t shows the serialized information of the last created object\n"
						+ "commands\t repeat these instructions\n"
						+ "quit\t\t Ends program");  
	}
}
