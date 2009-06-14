package chameleon.support.member.simplename.variable;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.expression.Expression;
import chameleon.core.type.Type;
import chameleon.core.type.TypeDescendant;
import chameleon.core.variable.Variable;

public interface VariableDeclarator<E extends VariableDeclarator, V extends Variable> extends TypeDescendant<E,Type> {


	public V createVariable(SimpleNameSignature signature, Expression expression);

	public Type type() throws MetamodelException;
}
