import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.jdom2.Document;
import org.jdom2.Element;

public class Deserializer {

	int i;
	int length;
	int ref;
	Vector<Object> finDeserialized = new Vector<Object>();
	HashMap<Integer, Object> IDTrack = new HashMap<Integer, Object>();
	List<Element> objElement;
	
	public Object deserializer(Document docToD) {
		
		Element root = docToD.getRootElement();
		objElement = root.getChildren();
		
		
		return docToD;
	}
	
	public void makeBaseTemplate(List<Element> objElement) {
		for(i=0; i < objElement.size(); i++) {
			Object object = null;
			
			Element child = objElement.get(i);
			
			Class<?> objClass;
			try {
				objClass = Class.forName(child.getAttributeValue("class"));
				
				if(!objClass.isArray()) {
					Constructor<?> constructor = objClass.getDeclaredConstructor(new Class<?>[0]);
					constructor.setAccessible(true);
					object = constructor.newInstance(new Object[] {});
				} else {
					object = Array.newInstance(objClass.getComponentType(), Integer.valueOf(child.getAttributeValue("length")));
				}
				
					IDTrack.put(Integer.valueOf(child.getAttributeValue("id")), object);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void makeObject(Element objElement) {
		Object object = IDTrack.get(Integer.valueOf(objElement.getAttributeValue("id")));
		Class<?> objClass = object.getClass();
		List<Element> fields = objElement.getChildren();
		
		// Checks if object is an array
		if (objClass.isArray()) {
			length = Integer.valueOf(objElement.getAttributeValue("length"));
			makeArray(object, fields, length);
		} else {
			//Object is not an array
			finDeserialized.add(object);
			makeFields(object,fields);
		}
	}
	
	public void makeArray(Object object, List<Element> fields, int length) {
		Class<?> objClass = object.getClass();
		//Check if it's a primitive array or an object array
		
		if(!objClass.getComponentType().isPrimitive()) {
			//Object Array
			for(i = 0; i < length; i++) {
				ref = Integer.valueOf(fields.get(i).getText());
				Array.set(object,  i,  IDTrack.get(ref));
			}

		} else {
			//Primitive Array
			for(i = 0; i < length; i++) {
				String val = fields.get(i).getText();
				Array.set(object, i, getPrimType(objClass.getComponentType(), val));
			}
		}
		
	}
	
	public void makeFields(Object object, List<Element> fields) {

		for(i = 0; i < fields.size(); i++) {
			Element currentElementField = fields.get(i);
			String fieldName = currentElementField.getAttributeValue("name");
			
			try {
				Class<?> decClass = Class.forName(currentElementField.getAttributeValue("declaringclass"));
				Field currentField = decClass.getDeclaredField(fieldName);
				currentField.setAccessible(true);
				
				if(!currentField.getType().isPrimitive()) {
					//Object
					currentField.set(object,  IDTrack.get(Integer.valueOf(currentElementField.getChild("reference").getText())));
				} else {
					//primitive
					currentField.set(object,  getPrimType(currentField.getType(), currentElementField.getChild("value").getText()));
				}
				
			} catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}
	
	//Return primitive type
	public Object getPrimType(Class type, String val) {
		if (type == byte.class) {
			return Byte.parseByte(val);
		} else if (type == char.class) {
			if (val.length() > 0) {
				return val.charAt(0);
			} else {
				return " ";
			}
		} else if (type == int.class) {
			return Integer.parseInt(val);
		} else if (type == float.class) {
			return Float.parseFloat(val);
		} else if (type == long.class) {
			return Long.parseLong(val);
		} else if (type == short.class) {
			return Short.parseShort(val);
		} else if (type == boolean.class) {
			return Boolean.parseBoolean(val);
		} else if (type == double.class) {
			return Double.parseDouble(val);
		}
		
		return null;
		
	}
	
}
