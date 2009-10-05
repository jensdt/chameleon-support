package chameleon.support.expression;

import java.util.HashSet;
import java.util.Set;

import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.lookup.LookupException;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;

/**
 * @author Marko van Dooren
 */
public class InstanceofExpression extends ExprTypeRefContainingExpression<InstanceofExpression> {

  public InstanceofExpression(Expression expression, TypeReference type) {
	  setExpression(expression);
    setTypeReference(type);
  }

  protected Type actualType() throws LookupException {
    return language(ObjectOrientedLanguage.class).booleanType();
  }

  public InstanceofExpression clone() {
    return new InstanceofExpression(getExpression().clone(), (TypeReference)getTypeReference().clone());
  }

  public Set<Type> getDirectExceptions() throws LookupException {
    return new HashSet<Type>();
  }

}
