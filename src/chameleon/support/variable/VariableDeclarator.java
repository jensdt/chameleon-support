package chameleon.support.variable;

import java.util.List;

import chameleon.core.declaration.DeclarationContainer;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.modifier.Modifier;
import chameleon.core.type.TypeReference;
import chameleon.core.variable.MemberVariable;
import chameleon.core.variable.Variable;

public interface VariableDeclarator<E extends VariableDeclarator, V extends Variable, P extends Element> extends Element<E,P> , DeclarationContainer<E, P> {

	public List<VariableDeclaration<V>> variableDeclarations();

	public V createVariable(SimpleNameSignature signature, Expression expression);
	
	public TypeReference typeReference();
	
	public List<Modifier> modifiers();

//	public Type type() throws LookupException;
}
