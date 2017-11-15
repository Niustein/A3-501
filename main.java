import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class main{
	
	public static void main(String[] args) {
		
//		ObjectCreator oCreator = new ObjectCreator();
//		Vector<Object> objToSerialize = oCreator.userCreator();
		
//		Serializer serialize = new Serializer();
//		Document[] dList = new Document[objToSerialize.size()];
//		
//		for(int i = 0; i < objToSerialize.size(); i++) {
//			dList[i] = serialize.findSerializeObject(objToSerialize.get(i));
//		}
		
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		
		SAXBuilder builder = new SAXBuilder();
		
		try {
			Document readDoc = builder.build(new File("send0.xml"));
			
			System.out.println("Root: " + readDoc.getRootElement());
			
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		
		
		
//		Vector<Document> docToSend = new Vector<Document>();
		
//		for (int i = 0; i < dList.length; i++) {
//			docToSend.add(dList[i]); 
//		}

//		for (int i = 0; i < docToSend.size(); i++) {
	//		try {
//				xmlOutput.output(docToSend.get(i), new FileOutputStream(new File("send" + i + ".xml")));
//			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

//		System.out.println(objToSerialize);
	}
}