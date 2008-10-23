package chameleon.support.expression;

import java.util.HashSet;
import java.util.Set;

import chameleon.core.MetamodelException;
import chameleon.core.accessibility.AccessibilityDomain;
import chameleon.core.expression.Expression;
import chameleon.core.expression.ExpressionContainer;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;

/**
 * @author Marko van Dooren
 */
public class InstanceofExpression extends ExprTypeRefContainingExpression<InstanceofExpression> implements ExpressionContainer<InstanceofExpression,ExpressionContainer> {

  public InstanceofExpression(Expression expression, TypeReference type) {
	  setExpression(expression);
    setTypeReference(type);
  }

  public Type getType() throws MetamodelException {
    return language().booleanType();
  }

  public boolean superOf(InvocationTarget target) throws MetamodelException {
    return (target instanceof InstanceofExpression) &&
           ((InstanceofExpression)target).getType().equals(getType()) &&
           getExpression().compatibleWith(((InstanceofExpression)target).getExpression());
  }

  public InstanceofExpression clone() {
    return new InstanceofExpression(getExpression().clone(), (TypeReference)getTypeReference().clone());
  }

  public Set<Type> getDirectExceptions() throws MetamodelException {
    return new HashSet<Type>();
  }

//  public AccessibilityDomain getAccessibilityDomain() throws MetamodelException {
//    return getTypeReference().getType().getTypeAccessibilityDomain().intersect(getExpression().getAccessibilityDomain());
//  }
}
