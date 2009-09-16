package chameleon.support.statement;

import chameleon.core.expression.Expression;

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
    return new ReturnStatement(childClone);
  }

}
