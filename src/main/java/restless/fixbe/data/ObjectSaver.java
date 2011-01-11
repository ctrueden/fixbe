//
// ObjectSaver.java
//

package restless.fixbe.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

/** Object state serialization class. */
public class ObjectSaver<T> {

	/** Helper class for (Class, String) attribute mappings. */
	public static class Attribute {
		public Class<?> c;
		public String attr;
		public Attribute(Class<?> c, String attr) {
			this.c = c;
			this.attr = attr;
		}
	}

  // -- Fields --

  /** Object/XML serializer for saving and restoring object state. */
  private XStream xs;

  // -- Constructor --

  public ObjectSaver() {
    this(new HashMap<String, Class<?>>(), new ArrayList<Attribute>());
  }

  public ObjectSaver(Map<String, Class<?>> aliases, List<Attribute> attrs) {
    xs = new XStream();
    //xs.setMode(XStream.ID_REFERENCES);
    xs.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
    for (String key : aliases.keySet()) {
      xs.alias(key, aliases.get(key));
    }
    for (Attribute attr : attrs) {
    	xs.useAttributeFor(attr.c, attr.attr);
    }
  }

  // -- ObjectSaver methods --

  /** Saves object state to the given file. */
  public void saveObject(T object, String file) {
    String xml = xs.toXML(object);
    try {
      PrintWriter out = new PrintWriter(new FileWriter(file));
      out.println(xml);
      out.close();
    }
    catch (IOException exc) {
      exc.printStackTrace();
    }
  }

  /** Restores game state from the given file. */
  public T restoreObject(String file) {
    String xml = null;
    try {
      BufferedReader in = new BufferedReader(new FileReader(file));
      StringBuffer sb = new StringBuffer();
      while (true) {
        String line = in.readLine();
        if (line == null) break;
        sb.append(line);
      }
      in.close();
      xml = sb.toString();
    }
    catch (IOException exc) {
      exc.printStackTrace();
    }
    return decodeXML(xml);
  }

	@SuppressWarnings("unchecked")
	private T decodeXML(String xml) {
		return (T) xs.fromXML(xml);
	}

}
