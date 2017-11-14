import org.jdom2.Element;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.IdentityHashMap;

import org.jdom2.Document;
import org.jdom2.Text;

public class Serializer {


	IdentityHashMap<Object, Integer> finSerialized = new IdentityHashMap<Object, Integer>();
	ArrayList objReferenceSerialized = new ArrayList();
	int uniqueID = 0;
	
	Element topRoot = new Element("serialized");
	public Document savedFile = new Document();
	
	Class objClass;
	Field[] objFields;
	
	public Document findSerializeObject(Object obj) {
		
		serializeObject(obj, uniqueID+1);
		
		savedFile.setRootElement(topRoot);
		
		return savedFile;
	}

	public void serializeObject(Object obj, int uID) {
		
		// Break out if object has already been serialized
		if (finSerialized.containsKey(obj)) {
			return;
		}
		
		Element eleObj = new Element("object");
		
		eleObj.setAttribute("class", obj.getClass().getName());
		eleObj.setAttribute("ID", String.valueOf(uID));
		finSerialized.put(obj,  uID);
		
		objClass = obj.getClass();
		objFields = objClass.getDeclaredFields();
		
		/*
		 * If field is an array, break into segments to be put into XML
		 */
		if(obj.getClass().isArray()) {
			Class<?> valType = (obj.getClass().getComponentType());
			
			eleObj.setAttribute("length", String.valueOf(Array.getLength(obj)));
			
			// Checks if valType is primitive	
			if(valType.isPrimitive()) {
				//Loop through primitive-array and add to corresponding element
				for(int j = 0; j < Array.getLength(obj); j++) {
					Element val = new Element("value");
					val.addContent(Array.get(obj,  j).toString());
					eleObj.addContent(val);
				}
				System.out.println("Finished serializng primitive array");
			} else {
				//valType is not primitive
				//Loop through non-primitive array and add to corresponding element
				for(int j = 0; j < Array.getLength(obj); j++) {
					Element ref = new Element("reference");
					ref.addContent(String.valueOf(uID));
					objReferenceSerialized.add(Array.get(obj, j));
					objReferenceSerialized.add(uID);
					eleObj.addContent(ref);
				}
				System.out.println("Finished serializing object reference Array");
			}
		}
		
		/*
		 * Arrays broken down
		 * Access fields of objects
		 */
		for (int i = 0; i < objFields.length; i++) {
			
			Field fieldVal = objFields[i];
			Object fieldObj = null;
			fieldVal.setAccessible(true);
			
			try {
				fieldObj = fieldVal.get(obj);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						

				
			//Check if field is primitive
			if (fieldVal.getType().isPrimitive()) {
				//Serialize primitive field
				Element primField = new Element("field");
				primField.setAttribute("name", fieldVal.getName());
				primField.setAttribute("declaringclass", fieldVal.getDeclaringClass().getName());
				
				Element val = new Element("value");	
				
				try {
					val.addContent(new Text(fieldVal.get(obj).toString()));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				primField.addContent(val);
				eleObj.addContent(primField);
				
			} else {
				//Field is not primitive
				
				Element objFieldVal = new Element("field");
				
				objFieldVal.setAttribute("name", fieldVal.getName());
				objFieldVal.setAttribute("declaringclass", fieldVal.getDeclaringClass().getName());
				
				Element ref = new Element("reference");
				ref.addContent(new Text(String.valueOf(uniqueID)));
				objReferenceSerialized.add(fieldObj);
				objReferenceSerialized.add(uniqueID+1);
				
				objFieldVal.addContent(ref);
				eleObj.addContent(objFieldVal);
			}
		}
		
		topRoot.addContent(eleObj);
			
			
		
		}
		
	}
	
