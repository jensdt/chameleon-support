package chameleon.support.statement;

import java.util.ArrayList;
import java.util.List;

import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.Signature;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.core.lookup.DeclaratorSelector;
import chameleon.core.lookup.LookupException;
import chameleon.core.lookup.SelectorWithoutOrder;
import chameleon.core.reference.CrossReference;
import chameleon.core.reference.SpecificReference;
import chameleon.core.variable.Variable;

public class EnumLabel extends SwitchLabel<EnumLabel> implements CrossReference<EnumLabel, SwitchCase, Variable>{

	public EnumLabel(String name) {
		_name = name;
		_signature = new SimpleNameSignature(_name);
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
		_signature.setName(name);
	}
	
	private String _name;
	
	private SimpleNameSignature _signature;
	
	public Variable getElement() throws LookupException {
		return getElement(selector());
	}
	
	public <X extends Declaration> X getElement(DeclarationSelector<X> selector) throws LookupException {
		// class must move to Jnome because of enum dependency?
		Expression switchExpr = parent().parent().getExpression();
		return switchExpr.getType().targetContext().lookUp(selector);
	}

	public Declaration getDeclarator() throws LookupException {
		return getElement(new DeclaratorSelector(selector()));
	}

	public DeclarationSelector<Variable> selector() {
		return new SelectorWithoutOrder<Variable>(new SelectorWithoutOrder.SignatureSelector() {
			public Signature signature() {
				return _signature;
			}
		},Variable.class);
	}
	
}
