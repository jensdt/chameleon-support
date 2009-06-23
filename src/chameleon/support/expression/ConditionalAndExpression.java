package chameleon.support.expression;

import chameleon.core.MetamodelException;
import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;


/**
 * @author Marko van Dooren
 */
public class ConditionalAndExpression extends ConditionalBooleanExpression {

  /**
   * @param first
   * @param second
   */
  public ConditionalAndExpression(Expression first, Expression second) {
    super(first, second);
  }

  public boolean superOf(InvocationTarget target) throws MetamodelException {
    return (target instanceof ConditionalAndExpression) && super.superOf(target);
  }

  public ConditionalAndExpression clone() {
    Expression first = getFirst().clone();
    Expression second = getSecond().clone();
    return new ConditionalAndExpression(first, second);
  }


}
