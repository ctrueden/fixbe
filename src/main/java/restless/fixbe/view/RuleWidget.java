//
// RuleWidget.java
//

package restless.fixbe.view;

import javax.swing.JPanel;

import restless.fixbe.data.Rule;

/** A widget for visualizing a rule. */
public abstract class RuleWidget extends JPanel {

	private Rule rule;

	public RuleWidget(Rule rule) {
		this.rule = rule;
	}

	public Rule getRule() { return rule; }

	public abstract void populate(byte[] data,
		int baseOffset, int defaultSpacing, int index);

}
