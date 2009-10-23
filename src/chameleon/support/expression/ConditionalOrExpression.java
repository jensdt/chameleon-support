package chameleon.support.expression;

import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.LookupException;


/**
 * @author Marko van Dooren
 */
public class ConditionalOrExpression extends ConditionalBooleanExpression<ConditionalOrExpression> {

  /**
   * @param first
   * @param second
   */
  public ConditionalOrExpression(Expression first, Expression second) {
    super(first, second);
  }
  
  public ConditionalOrExpression clone() {
    Expression first = getFirst().clone();
    Expression second = getSecond().clone();
    return new ConditionalOrExpression(first, second);
  }

}
