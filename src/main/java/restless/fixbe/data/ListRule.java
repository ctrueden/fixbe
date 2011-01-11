//
// ListRule.java
//

package restless.fixbe.data;

import java.util.List;

import restless.fixbe.view.ListWidget;
import restless.fixbe.view.RuleWidget;

/** A collection of rules, possibly repeated multiple times. */
public class ListRule extends Rule {

	private int repetitions;
	private int columns;
	private List<Rule> items;

	public ListRule(List<Rule> items) {
		this.items = items;
	}

	public int getRepetitions() { return repetitions > 0 ? repetitions : 1; }
	public int getColumns() { return columns > 0 ? columns : 1; }
	public List<Rule> getItems() { return items; }

	@Override
	public RuleWidget createWidget() {
		return new ListWidget(this);
	}

}
