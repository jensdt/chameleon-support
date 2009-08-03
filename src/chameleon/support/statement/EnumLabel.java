package chameleon.support.statement;

import java.util.ArrayList;
import java.util.List;

import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.core.lookup.LookupException;
import chameleon.core.lookup.SelectorWithoutOrder;
import chameleon.core.reference.CrossReference;
import chameleon.core.variable.Variable;

public class EnumLabel extends SwitchLabel<EnumLabel> implements CrossReference<EnumLabel, SwitchCase, Variable>{

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

	public Variable getElement() throws LookupException {
		// class must move to Jnome because of enum dependency?
		Expression switchExpr = parent().parent().getExpression();
		return switchExpr.getType().targetContext().lookUp(selector());
	}

	private DeclarationSelector<Variable> selector() {
		return new SelectorWithoutOrder<Variable>(new SimpleNameSignature(name()),Variable.class);
	}
	
}
