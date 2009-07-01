package chameleon.support.expression;

import java.util.Set;

import chameleon.core.expression.Expression;
import chameleon.core.expression.ExpressionContainer;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.LookupException;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;
import chameleon.util.Util;

/**
 * @author Tim Laeremans
 */
public class ClassCastExpression extends ExprTypeRefContainingExpression<ClassCastExpression>
       implements ExpressionContainer<ClassCastExpression,ExpressionContainer> {

  public ClassCastExpression(TypeReference type, Expression expression) {
	  setTypeReference(type);
    setExpression(expression);
  }


  public Type getType() throws LookupException {
    Type result = getTypeReference().getType();
    if(result == null) {
      getTypeReference().getType();
      throw new LookupException("Type reference of class cast expression returns null", getTypeReference());
    }
    return result;
  }

  public boolean superOf(InvocationTarget target) throws LookupException {
    if(!(target instanceof ClassCastExpression)) {
      return false;
    }
    ClassCastExpression cce = (ClassCastExpression)target;
    return cce.getType().equals(getType()) && getExpression().compatibleWith(cce.getExpression());
  }
  
  public boolean subOf(InvocationTarget target) throws LookupException {
    return target.compatibleWith(getExpression());
  }

  public ClassCastExpression clone() {
    return new ClassCastExpression((TypeReference)getTypeReference().clone(), getExpression().clone());
  }

  public Set<Type> getDirectExceptions() throws LookupException {
    return Util.createNonNullSet(language().classCastException());
  }

//  public AccessibilityDomain getAccessibilityDomain() throws LookupException {
//    return getType().getTypeAccessibilityDomain().intersect(getExpression().getAccessibilityDomain());
//  }
}
