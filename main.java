import java.util.Vector;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class main{
	
	public static void main(String[] args) {
		
		ObjectCreator oCreator = new ObjectCreator();
		Vector<Object> objToSerialize = oCreator.userCreator();
		
		Serializer serialize = new Serializer();
		Document[] dList = new Document[objToSerialize.size()];
		
		for(int i = 0; i < objToSerialize.size(); i++) {
			dList[i] = serialize.findSerializeObject(objToSerialize.get(i));
		}
		
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		
		System.out.println(objToSerialize);
	}
}