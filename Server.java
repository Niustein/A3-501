import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	static int count = 0;
	static int portNum = 0;
	public static void main(String[] args) {
		Scanner scannerChoice = new Scanner(System.in);
		
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
		
		try {
			ServerSocket ss = new ServerSocket(portNum);
			Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String k = dis.readUTF();
			System.out.println("File Transfered");
			FileOutputStream fos = new FileOutputStream("C:\\Users\\S-Niu\\Desktop\\HW\\Eclipse\\501-A3\\TF\\test" + count + ".xml");
			count++;
			byte[] b = k.getBytes();
			fos.write(b);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
