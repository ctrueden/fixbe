//
// ToggleRule.java
//

package restless.fixbe.data;

import restless.fixbe.view.RuleWidget;
import restless.fixbe.view.ToggleWidget;

public class ToggleRule extends SimpleRule {

	private int bit;

  @Override
	public RuleWidget createWidget() {
    return new ToggleWidget(this);
  }

	public boolean getValue(byte[] data,
		int baseOffset, int defaultSpacing, int index)
	{
		final int off = computeOffset(baseOffset, defaultSpacing, index);
		final int value = decodeWord(data, off, 1);
		final int bitValue = (value >> bit) & 0x01;
		return bitValue != 0;
	}

}
