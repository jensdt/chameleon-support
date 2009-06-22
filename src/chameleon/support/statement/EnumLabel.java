package chameleon.support.statement;

import java.util.ArrayList;
import java.util.List;

import chameleon.core.element.Element;

public class EnumLabel extends SwitchLabel<EnumLabel>{

	public EnumLabel(String name) {
		_name = name;
	}
	
	@Override
	public EnumLabel clone() {
		return new EnumLabel(name());
	}

	public List<? extends Element> children() {
		return new ArrayList<Element>();
	}
	
	public String name() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	private String _name;
	
}
