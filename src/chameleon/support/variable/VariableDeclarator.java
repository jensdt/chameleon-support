package chameleon.support.variable;

import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.type.TypeDescendant;
import chameleon.core.variable.Variable;

public interface VariableDeclarator<E extends VariableDeclarator, V extends Variable, P extends Element> extends TypeDescendant<E,P> {


	public V createVariable(SimpleNameSignature signature, Expression expression);

//	public Type type() throws MetamodelException;
}