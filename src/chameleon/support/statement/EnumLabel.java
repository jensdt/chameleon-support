package chameleon.support.statement;

import java.util.ArrayList;
import java.util.List;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationSelector;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.expression.SelectorWithoutOrder;
import chameleon.core.reference.CrossReference;
import chameleon.core.variable.Variable;

public class EnumLabel extends SwitchLabel<EnumLabel> implements CrossReference<EnumLabel, SwitchCase>{

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

	public Declaration getElement() throws MetamodelException {
		// class must move to Jnome because of enum dependency?
		Expression switchExpr = parent().parent().getExpression();
		return switchExpr.getType().targetContext().lookUp(selector());
	}

	private DeclarationSelector selector() {
		return new SelectorWithoutOrder<Variable>(new SimpleNameSignature(name()),Variable.class);
	}
	
}
