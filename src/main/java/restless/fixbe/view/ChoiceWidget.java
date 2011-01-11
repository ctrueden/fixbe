//
// ChoiceWidget.java
//

package restless.fixbe.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import restless.fixbe.data.ChoiceRule;
import restless.fixbe.data.CodeEntry;

public class ChoiceWidget extends RuleWidget {

	private ChoiceRule rule;
	private JComboBox comboBox;

	public ChoiceWidget(ChoiceRule rule) {
		super(rule);
		this.rule = rule;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		final JLabel label = new JLabel(rule.getLabel());
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label);
		add(Box.createHorizontalStrut(5));
		comboBox = new JComboBox(rule.getCode().getEntries().toArray());
		add(comboBox);
	}

	@Override
	public void populate(byte[] data,
		int baseOffset, int defaultSpacing, int index)
	{
		final CodeEntry value = rule.getValue(data,
			baseOffset, defaultSpacing, index);
		comboBox.setSelectedItem(value);
	}

}
