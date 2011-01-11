//
// Code.java
//

package restless.fixbe.data;

import java.util.List;

/** A mapping from integer values to strings. */
public class Code {

	private String label;
	private List<CodeEntry> entries;

	public String getLabel() { return label; }
	public List<CodeEntry> getEntries() { return entries; }

}
