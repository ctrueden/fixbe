//
// StringWidget.java
//

package restless.fixbe.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import restless.fixbe.data.TextRule;

public class StringWidget extends RuleWidget {

	private TextRule rule;
	private JTextField textField;

	public StringWidget(TextRule rule) {
		super(rule);
		this.rule = rule;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		final JLabel label = new JLabel(rule.getLabel());
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label);
		add(Box.createHorizontalStrut(5));
		textField = new JTextField(8);
		add(textField);
	}

	@Override
	public void populate(byte[] data,
		int baseOffset, int defaultSpacing, int index)
	{
		final String value = rule.getValue(data, baseOffset, defaultSpacing, index);
		textField.setText(value.toString());
	}

}
