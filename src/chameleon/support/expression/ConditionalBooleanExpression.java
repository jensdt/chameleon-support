package chameleon.support.expression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chameleon.core.context.LookupException;
import chameleon.core.element.Element;
import chameleon.core.expression.BinaryExpression;
import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.type.Type;


/**
 * @author Marko van Dooren
 */
public abstract class ConditionalBooleanExpression extends BinaryExpression {

  /**
   * @param first
   * @param second
   */
  public ConditionalBooleanExpression(Expression first, Expression second) {
    super(first, second);
  }
  
  public Type getType() throws LookupException {
    return language().booleanType(); 
  }
  
  public boolean superOf(InvocationTarget target) throws LookupException {
    if(!(target instanceof ConditionalBooleanExpression)) {
      return false;
    }
    ConditionalBooleanExpression other = (ConditionalBooleanExpression)target;
    return getFirst().compatibleWith(other.getFirst()) && (getSecond().compatibleWith(other.getSecond()));
  }
  
  public Set<Type> getDirectExceptions() throws LookupException {
    return new HashSet<Type>();
  }
  
  public List<Element> children() {
    List<Element> result = new ArrayList<Element>();
    result.add(getFirst());
    result.add(getSecond());
    return result;
  }

//  public AccessibilityDomain getAccessibilityDomain() throws LookupException {
//    return getFirst().getAccessibilityDomain().intersect(getSecond().getAccessibilityDomain());
//  }
  
}
