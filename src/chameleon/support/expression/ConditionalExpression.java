package chameleon.support.expression;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chameleon.core.element.ChameleonProgrammerException;
import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.LookupException;
import chameleon.core.type.Type;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class ConditionalExpression extends TernaryExpression {

  /**
   * @param first
   * @param second
   */
  public ConditionalExpression(Expression condition, Expression first, Expression second) {
    super(first, second,condition);
  }

  public Expression<? extends Expression> getCondition() {
  	return getThird();
  }
  
  public void setCondition(Expression condition) {
  	setThird(condition);
  }

  protected Type actualType() throws LookupException {
  	//GENERALIZE PROMOTIONS
    Type firstType = getFirst().getType();
    Type secondType = getSecond().getType();
    if (firstType.equals(secondType)) {
      return firstType;
    }
    
    //TODO I think this code is redundant since NullType is assignable to anything.
    
//    else if (firstType instanceof NullType) {
//      return secondType;
//    }
//    else if (secondType instanceof NullType) {
//      return firstType;
//    }
    
    else if (firstType.assignableTo(secondType)) {
      return secondType;
    }
    else if (secondType.assignableTo(firstType)) {
      return firstType;
    }
//    else if ((firstType.getName().equals("short") && secondType.getName().equals("byte")) || (firstType.getName().equals("byte") && secondType.getName().equals("short"))) {
//      return getNamespace().getDefaultNamespace().findType("short");
//    }

    else {
      throw new ChameleonProgrammerException("Numerical promotion in conditional expression not yet complete");
    }
  }

  public boolean superOf(InvocationTarget target) throws LookupException {
    return (target instanceof ConditionalExpression) &&
           (getCondition().compatibleWith(((ConditionalExpression)target).getCondition())) &&
           (getFirst().compatibleWith(((ConditionalExpression)target).getFirst())) &&
           (getSecond().compatibleWith(((ConditionalExpression)target).getSecond()));
  }

  public ConditionalExpression clone() {
    return new ConditionalExpression(getCondition().clone(), getFirst().clone(), getSecond().clone());
  }

  public List children() {
    List result = Util.createNonNullList(getCondition());
    Util.addNonNull(getFirst(), result);
    Util.addNonNull(getSecond(), result);
    return result;
  }

  public Set getDirectExceptions() throws LookupException {
    return new HashSet();
  }

//  public AccessibilityDomain getAccessibilityDomain() throws LookupException {
//    return getCondition().getAccessibilityDomain().intersect(
//             getFirst().getAccessibilityDomain().intersect(
//                 getSecond().getAccessibilityDomain())
//           ); 
//  }

  
}
