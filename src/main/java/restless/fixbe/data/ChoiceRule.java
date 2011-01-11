//
// ChoiceRule.java
//

package restless.fixbe.data;

import restless.fixbe.view.ChoiceWidget;
import restless.fixbe.view.RuleWidget;

public class ChoiceRule extends SimpleRule {

	private Code code;

	public Code getCode() { return code; }

  @Override
	public RuleWidget createWidget() {
    return new ChoiceWidget(this);
  }

	public CodeEntry getValue(byte[] data,
		int baseOffset, int defaultSpacing, int index)
	{
		final int off = computeOffset(baseOffset, defaultSpacing, index);
		final int value = decodeWord(data, off, getByteCount());
		for (CodeEntry entry : code.getEntries()) {
			if (entry.getValue() == value) return entry;
		}
		return null;
	}

}
