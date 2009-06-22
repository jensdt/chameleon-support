package chameleon.support.statement;

import java.util.List;

import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.statement.ExpressionContainingStatement;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class ReturnStatement extends ExpressionContainingStatement<ReturnStatement> {

	public ReturnStatement() {
		
	}
	
  /**
   * @param expression
   */
  public ReturnStatement(Expression expression) {
    super(expression);
  }

  public ReturnStatement clone() {
  	Expression childClone = getExpression();
  	if(childClone != null) {
  		childClone = childClone.clone();
  	}
    return new ReturnStatement(getExpression().clone());
  }

}
