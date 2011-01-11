//
// Rule.java
//

package restless.fixbe.data;

import restless.fixbe.view.RuleWidget;

/**
 * A format definition rule, identifying an editable area within a
 * particular binary file format such as a savegame or savestate.
 */
public abstract class Rule {

	private String label;
  private int offset;
  private int spacing;

  public String getLabel() { return label; }
  public int getOffset() { return offset; }
  public int getSpacing() { return spacing; }

	public abstract RuleWidget createWidget();

	@Override
	public String toString() { return label; }

	protected int computeOffset(int baseOffset, int defaultSpacing, int index) {
		final int width = spacing == 0 ? defaultSpacing : spacing;
		return baseOffset + offset + width * index;
	}

}
