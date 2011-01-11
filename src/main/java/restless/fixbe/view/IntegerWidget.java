//
// IntegerWidget.java
//

package restless.fixbe.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

import restless.fixbe.data.IntegerRule;

public class IntegerWidget extends RuleWidget {

	private IntegerRule rule;
	private JSpinner spinner;

	public IntegerWidget(IntegerRule rule) {
		super(rule);
		this.rule = rule;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		final JLabel label = new JLabel(rule.getLabel());
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label);
		add(Box.createHorizontalStrut(5));
		spinner = new JSpinner();
		add(spinner);
	}

	@Override
	public void populate(byte[] data,
		int baseOffset, int defaultSpacing, int index)
	{
		final int value = rule.getValue(data, baseOffset, defaultSpacing, index);
		spinner.setValue(value);
	}

}
