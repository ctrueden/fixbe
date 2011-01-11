//
// StringRule.java
//

package restless.fixbe.data;

import restless.fixbe.view.RuleWidget;
import restless.fixbe.view.StringWidget;

public class TextRule extends SimpleRule {

	private Alphabet alphabet = Alphabet.ASCII;
	private int bytesPerChar = 1;

	public TextRule(Alphabet alpha) {
		alphabet = alpha;
	}

	public int getBytesPerChar() {
		return bytesPerChar > 0 ? bytesPerChar : 1;
	}

	public String getValue(byte[] data,
		int baseOffset, int defaultSpacing, int index)
	{
		StringBuilder sb = new StringBuilder();
		final int off = computeOffset(baseOffset, defaultSpacing, index);
		for (int i = 0; i < getByteCount(); i += getBytesPerChar()) {
			final int val = decodeWord(data, off + i, getBytesPerChar());
			char c = alphabet.getChar(val);
			if (c == '\0') break; // null terminator
			sb.append(c);
		}
		return sb.toString();
	}

	@Override
	public RuleWidget createWidget() {
		return new StringWidget(this);
	}

}
