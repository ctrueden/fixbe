//
// FormatDefinition.java
//

package restless.fixbe.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A format definition ruleset, identifying editable areas within a particular
 * binary file format such as a savegame or savestate.
 */
public class FormatDefinition {

	private String name;
	private String type;
	private String extension;
	private int fileLength;
	private List<Alphabet> alphabets;
	private List<Code> codes;
	private List<Rule> rules;

	public String getName() { return name; }
	public String getType() { return type; }
	public String getExtension() { return extension; }
	public int getFileLength() { return fileLength; }
	public List<Alphabet> getAlphabets() { return alphabets; }
	public List<Code> getCodes() { return codes; }
	public List<Rule> getRules() { return rules; }

	// -- Serialization --

	public void save(String file) {
		createSaver().saveObject(this, file);
	}

	public static FormatDefinition load(String file) {
		return createSaver().restoreObject(file);
	}

	private static ObjectSaver<FormatDefinition> createSaver() {
		Map<String, Class<?>> aliases = new HashMap<String, Class<?>>();
		aliases.put("alphabet", Alphabet.class);
		aliases.put("code", Code.class);
		aliases.put("format", FormatDefinition.class);
		aliases.put("rule", Rule.class);
		aliases.put("list", ListRule.class);
		aliases.put("choice", ChoiceRule.class);
		aliases.put("entry", CodeEntry.class);
		aliases.put("integer", IntegerRule.class);
		aliases.put("text", TextRule.class);
		aliases.put("toggle", ToggleRule.class);
		List<ObjectSaver.Attribute> attrs = new ArrayList<ObjectSaver.Attribute>();
		attrs.add(new ObjectSaver.Attribute(FormatDefinition.class, "name"));
		attrs.add(new ObjectSaver.Attribute(FormatDefinition.class, "type"));
		attrs.add(new ObjectSaver.Attribute(FormatDefinition.class, "extension"));
		attrs.add(new ObjectSaver.Attribute(FormatDefinition.class, "fileLength"));
		attrs.add(new ObjectSaver.Attribute(Alphabet.class, "label"));
		attrs.add(new ObjectSaver.Attribute(Code.class, "label"));
		attrs.add(new ObjectSaver.Attribute(Rule.class, "label"));
		attrs.add(new ObjectSaver.Attribute(Rule.class, "offset"));
		attrs.add(new ObjectSaver.Attribute(Rule.class, "spacing"));
		attrs.add(new ObjectSaver.Attribute(SimpleRule.class, "bytes"));
		attrs.add(new ObjectSaver.Attribute(ToggleRule.class, "bit"));
		attrs.add(new ObjectSaver.Attribute(CodeEntry.class, "value"));
		attrs.add(new ObjectSaver.Attribute(CodeEntry.class, "label"));
		//attrs.add(new ObjectSaver.Attribute(StringRule.class, "bytesPerChar"));
		return new ObjectSaver<FormatDefinition>(aliases, attrs);
	}

}
