//
// Choice.java
//

package restless.fixbe.data;

public class CodeEntry {

	private int value;
	private String label;

	public int getValue() { return value; }
	public String getLabel() { return label; }

	@Override
	public String toString() { return label; }

}
