package chameleon.support.statement;

import java.util.ArrayList;
import java.util.List;

import chameleon.core.element.Element;

public class LabeledGotoStatement extends GotoStatement<LabeledGotoStatement>{

	public LabeledGotoStatement(String label) {
		setLabel(label);
	}
	
	private String _label;
	
	public String getLabel() {
		return _label;
	}
	
	/*@
	  @ public behavior
	  @
	  @ post getLabel() == label; 
	  @*/
	public void setLabel(String label) {
		_label = label;
	}

	@Override
	public LabeledGotoStatement clone() {
		return new LabeledGotoStatement(getLabel());
	}

	@Override
	public List<Element> getChildren() {
		return new ArrayList<Element>();
	}
}
