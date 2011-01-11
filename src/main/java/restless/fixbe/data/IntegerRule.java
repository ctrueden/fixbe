//
// IntegerRule.java
//

package restless.fixbe.data;

import restless.fixbe.view.IntegerWidget;
import restless.fixbe.view.RuleWidget;

public class IntegerRule extends SimpleRule {

  @Override
	public RuleWidget createWidget() {
    return new IntegerWidget(this);
  }

	public int getValue(byte[] data, int baseOffset,
		int defaultSpacing, int index)
	{
		final int off = computeOffset(baseOffset, defaultSpacing, index);
		return decodeWord(data, off, getByteCount());
	}

}
