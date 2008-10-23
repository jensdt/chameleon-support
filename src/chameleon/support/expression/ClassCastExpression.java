package chameleon.support.expression;

import java.util.Set;

import chameleon.core.MetamodelException;
import chameleon.core.accessibility.AccessibilityDomain;
import chameleon.core.expression.Expression;
import chameleon.core.expression.ExpressionContainer;
import chameleon.core.expression.InvocationTarget;
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


  public Type getType() throws MetamodelException {
    Type result = getTypeReference().getType();
    if(result == null) {
      getTypeReference().getType();
      throw new MetamodelException();
    }
    return result;
  }

  public boolean superOf(InvocationTarget target) throws MetamodelException {
    if(!(target instanceof ClassCastExpression)) {
      return false;
    }
    ClassCastExpression cce = (ClassCastExpression)target;
    return cce.getType().equals(getType()) && getExpression().compatibleWith(cce.getExpression());
  }
  
  public boolean subOf(InvocationTarget target) throws MetamodelException {
    return target.compatibleWith(getExpression());
  }

  public ClassCastExpression clone() {
    return new ClassCastExpression((TypeReference)getTypeReference().clone(), getExpression().clone());
  }

  public Set<Type> getDirectExceptions() throws MetamodelException {
    return Util.createNonNullSet(language().classCastException());
  }

//  public AccessibilityDomain getAccessibilityDomain() throws MetamodelException {
//    return getType().getTypeAccessibilityDomain().intersect(getExpression().getAccessibilityDomain());
//  }
}
