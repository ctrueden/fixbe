//
// ToggleWidget.java
//

package restless.fixbe.view;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;

import restless.fixbe.data.ToggleRule;

public class ToggleWidget extends RuleWidget {

	private ToggleRule rule;
	private JCheckBox checkbox;

	public ToggleWidget(ToggleRule rule) {
		super(rule);
		this.rule = rule;
		setLayout(new BorderLayout());
		checkbox = new JCheckBox(rule.getLabel());
		add(checkbox);
	}

	@Override
	public void populate(byte[] data,
		int baseOffset, int defaultSpacing, int index)
	{
		final boolean value = rule.getValue(data,
			baseOffset, defaultSpacing, index);
		checkbox.setSelected(value);
	}

}
